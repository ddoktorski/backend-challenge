package com.backend.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.backend.challenge.data.container.Place;
import com.backend.challenge.wrapper.GeoApiWrapper;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class RequestTests {
    @Mock
    private GeoApiWrapper geoApiWrapper;

    @InjectMocks
    private Request request = new Request(buildListOfPlacesWithDummyData());

    @BeforeEach
    public void setup() {
        lenient().when(geoApiWrapper.getAddressFromCoordinates(anyDouble(), anyDouble()))
                .thenReturn("address");
    }

    @Test
    public void whenGetResults_givenDummyData_thenReturnOrderedPlaces() {
        List<PlaceResult> results = request.getResults("shop", 3, 3);

        assertEquals(2, results.size());
        assertEquals("Bicycle Shop", results.get(0).name());
        assertEquals("Grocery Shop", results.get(1).name());
    }

    @Test
    public void whenGetResults_givenNoMatchingSearch_thenReturnEmptyList() {
        List<PlaceResult> results = request.getResults("search", 0, 0);
        assertEquals(results, new ArrayList<>());
    }

    private static List<Place> buildListOfPlacesWithDummyData() {
        return Stream.of(
                Place.builder()
                        .lat(1)
                        .lon(1)
                        .name("Grocery Shop")
                        .build(),
                Place.builder()
                        .lat(1)
                        .lon(2)
                        .name("Supermarket")
                        .build(),
                Place.builder()
                        .lat(1)
                        .lon(3)
                        .name("Bicycle Shop")
                        .build())
                .collect(Collectors.toList());
    }

}
