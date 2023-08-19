package com.backend.challenge;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.challenge.data.container.PlacesData;

@RestController
public class Controller {

    @GetMapping("/list")
    public List<PlaceResult> list(@RequestParam(value = "search") String search,
            @RequestParam(value = "lat", defaultValue = "0", required = false) double lat,
            @RequestParam(value = "lon", defaultValue = "0", required = false) double lon)
            throws IOException {
        PlacesData placesData = PlacesData.getData();
        Request request = Request.create(placesData);
        return request.getResults(search, lat, lon);
    }

}
