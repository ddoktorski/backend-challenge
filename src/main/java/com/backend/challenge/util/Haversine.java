package com.backend.challenge.util;

public class Haversine {
    private static final int EARTH_RADIUS = 6371;

    public static double calculateDistance(double startLat, double startLon,
            double endLat, double endLon) {

        double dLat = Math.toRadians(endLat - startLat);
        double dLon = Math.toRadians(endLon - startLon);

        startLat = Math.toRadians(startLat);
        endLat = Math.toRadians(endLat);

        double x = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLon);
        double angle = 2 * Math.atan2(Math.sqrt(x), Math.sqrt(1 - x));

        return EARTH_RADIUS * angle;
    }

    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
}
