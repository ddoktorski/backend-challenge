package com.backend.challenge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.ToString;

@ToString
public class Places {
    private List<Place> places;

    private Places() throws IOException {
        // This makes the list of places mutable.
        this.places = new ArrayList<>(DataProvider.getStructuredData());
    }

    public static Places getData() throws IOException {
        return new Places();
    }

    public void filterByName(final String search) {
        places.removeIf(place -> !place.nameContainsPhrase(search));
    }
}
