package com.backend.challenge.data.container;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.util.Precision;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Place {
    private String name;
    private String city;
    private String street;
    private String countryCode;
    private double lat;
    private double lon;

    @JsonIgnore
    private double distanceToLocation;

    @JsonIgnore
    @Setter
    private String address;

    public boolean containsPhrase(final String search) {
        return FilterHelper.checkIfContains(name, search) ||
                FilterHelper.checkIfContains(city, search) ||
                FilterHelper.checkIfContains(street, search) ||
                FilterHelper.checkIfContains(countryCode, search);
    }

    public void setDistanceToLocation(final double distanceToLocation) {
        this.distanceToLocation = Precision.round(distanceToLocation, 1);
    }

    static class FilterHelper {
        static String normaliseString(String text) {
            return StringUtils.stripAccents(text).toLowerCase();
        }

        static boolean checkIfContains(String string, String substring) {
            return string != null &&
                    normaliseString(string).contains(normaliseString(substring));
        }
    }
}
