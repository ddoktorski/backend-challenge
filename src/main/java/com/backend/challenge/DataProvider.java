package com.backend.challenge;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataProvider {
    private static final String DATA_ENDPOINT = "https://payback-coding-challenge.s3.eu-central-1.amazonaws.com/germany.json";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static List<PlaceData> getStructuredData() throws IOException {
        final JsonNode rawData = getRawData();
        return parseData(rawData);
    }

    private static JsonNode getRawData() throws IOException {
        try {
            return MAPPER.readTree(new URL(DATA_ENDPOINT));
        } catch (IOException e) {
            log.error("Failed to retrieve data.", e.getMessage());
            throw e;
        }
    }

    private static List<PlaceData> parseData(final JsonNode rawData) throws IOException {
        try {
            return Arrays.asList(MAPPER.readValue(rawData.get("places").traverse(),
                    PlaceData[].class));
        } catch (IOException e) {
            log.error("Failed to parse the data.", e.getMessage());
            throw e;
        }
    }

}
