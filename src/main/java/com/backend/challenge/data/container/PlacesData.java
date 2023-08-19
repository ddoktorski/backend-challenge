package com.backend.challenge.data.container;

import java.io.IOException;
import java.util.List;

import com.backend.challenge.data.provider.DataProvider;

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
