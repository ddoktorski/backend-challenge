package com.backend.challenge;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/list")
    public PlaceResult list(@RequestParam(value = "search") String search) throws IOException {
        List<PlaceData> places = DataProvider.getStructuredData();
        return new PlaceResult(places.get(0).getName(), search, 0);
    }

}
