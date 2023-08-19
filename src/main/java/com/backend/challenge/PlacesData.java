package com.backend.challenge;

import java.io.IOException;
import java.util.List;

import lombok.Getter;

@Getter
public class PlacesData {
    private List<Place> places;

    private PlacesData() throws IOException {
        this.places = DataProvider.getStructuredData();
    }

    public static PlacesData getData() throws IOException {
        return new PlacesData();
    }
}
