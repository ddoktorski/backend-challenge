package com.backend.challenge;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/list")
    public PlaceResult list(@RequestParam(value = "search") String search) throws IOException {
        Places placesData = Places.getData();
        placesData.filterByName(search);
        return new PlaceResult(placesData.toString(), search, 0);
    }

}
