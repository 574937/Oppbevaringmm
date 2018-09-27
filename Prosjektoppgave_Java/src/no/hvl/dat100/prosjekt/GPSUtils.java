package no.hvl.dat100.prosjekt;

import static java.lang.Math.*;

public class GPSUtils {

	public GPSUtils() {
	
	}
	
	// konverter sekunder til string på formen hh:mm:ss 3d)
	public static String printTime(int secs) {
		
		String timestr = "";
		String TIMESEP = ":";
//
		int timer = (secs/3600);
		int resttimer = (secs % 3600);
		int minutter = resttimer/60;
		int sluttsekunder = resttimer % 60; 
	
		timestr = String.format("%02d:%02d:%02d",timer,minutter,sluttsekunder);
		
		return timestr;
	}
	
	// beregn maximum av en tabell av doubles med minnst et element
	public static double findMax(double[] da) {

		double max = da[0];

		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}

		return max;
	}

	// beregn minimum av en tabell av doubles med minnst et element
	public static double findMin(double[] da) {

		// fjern = "0.0" når metoden implementeres for ikke få forkert minimum 3a
		double min = da[0]; 

		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}

		return min;
	}

	
	private static int R = 6371000; // jordens radius
	
	// Beregn avstand mellom to gps punkter ved bruk av Haversine formlen
	public static double distance(double latitude1, double longitude1, double latitude2, double longitude2) {

		double a,c,d;
		double latitude_1 = toRadians(latitude1);
		double latitude_2 = toRadians(latitude2);		
		double differanselat = toRadians(latitude2-latitude1);

		
		double longitude_1 = toRadians(longitude1);
		double longitude_2 = toRadians(longitude2);
		double differanselong = toRadians(longitude2-longitude1);
		
		a = (Math.pow(Math.sin(differanselat/2), 2)) + (Math.cos(latitude_1) * Math.cos(latitude_2) * (Math.pow(Math.sin(differanselong/2), 2)));
		
		
		c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt((1-a)));
		
		d = R * c;
		
		return d;
	}
	
	// beregn gjennomsnits hastighet i km/t mellom to gps punkter
	public static double speed(int secs, double latitude1, double longitude1, double latitude2, double longitude2) {

		double speed = 0.0;
		int R = 6371000;
	
		double d = distance(latitude1, longitude1, latitude2, longitude2);
		speed = (d/secs)*3.6;
		

		return speed;
	}
	
	private static int TEXTWIDTH = 10;
	
	// konverter double til string med 2 decimaler og streng lengde 10
	// eks. 1.346 konverteres til "      1.35" og enhet til slutt
	// Hint: se på String.format metoden 3c
	
	public static String printDouble(double d) {
		
		String str = "";
		
		String d_2 = String.format("%.2f", d);
		
		str = String.format("%10s", d_2).replace(",", ".");
	
		
		return str;
	}
}
