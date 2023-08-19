package com.backend.challenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.backend.challenge.util.Haversine;
import com.backend.challenge.wrapper.GeoApiWrapper;

public class Request {
    private List<Place> places;
    private GeoApiWrapper geoApiWrapper;

    private Request(final List<Place> places) {
        // This makes the list of places mutable.
        this.places = new ArrayList<>(places);

        this.geoApiWrapper = new GeoApiWrapper();
    }

    public static Request create(final PlacesData placesData) {
        return new Request(placesData.getPlaces());
    }

    public List<PlaceResult> getResults(final String search, final double lat, final double lon) {
        filterOutUnmatchedPlaces(search);
        calculateDistancesToLocation(lat, lon);
        sortPlacesByDistanceToLocation();
        mapAddressesToPlaces();
        return convertPlacesToResults();
    }

    private void filterOutUnmatchedPlaces(final String search) {
        places.removeIf(place -> !place.nameContainsPhrase(search));
    }

    private void calculateDistancesToLocation(final double lat, final double lon) {
        places.forEach(place -> {
            double distance = Haversine.calculateDistance(lat, lon, place.getLat(), place.getLon());
            place.setDistanceToLocation(distance);
        });
    }

    private void sortPlacesByDistanceToLocation() {
        Collections.sort(places, Comparator.comparing(Place::getDistanceToLocation));
    }

    private void mapAddressesToPlaces() {
        places.forEach(place -> {
            place.setAddress(geoApiWrapper.getAddressFromCoordinates(place.getLat(), place.getLon()));
        });
    }

    private List<PlaceResult> convertPlacesToResults() {
        List<PlaceResult> results = new ArrayList<>();
        places.forEach(place -> {
            results.add(new PlaceResult(place.getName(),
                    place.getAddress(),
                    place.getDistanceToLocation()));
        });
        return results;
    }

    @Override
    protected void finalize() throws Throwable {
        geoApiWrapper.closeClient();
    }
}
