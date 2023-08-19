package com.backend.challenge.wrapper;

import java.io.IOException;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GeoApiWrapper {
    private final static String API_KEY = "API_KEY";
    private final static String EMPTY_STRING = "";
    private GeoApiContext context;

    public GeoApiWrapper() {
        this.context = new GeoApiContext.Builder()
                .apiKey(System.getenv(API_KEY))
                .build();
    }

    public String getAddressFromCoordinates(double lat, double lon) {
        try {
            GeocodingResult[] results = GeocodingApi.reverseGeocode(context,
                    new LatLng(lat, lon)).await();
            return results[0].formattedAddress;
        } catch (ApiException | InterruptedException | IOException e) {
            log.error("Failed to do reverse geocoding.", e);
        }
        return EMPTY_STRING;
    }

    public void closeClient() {
        context.shutdown();
    }
}
