package com.backend.challenge;

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
    private double lat;
    private double lon;

    @JsonIgnore
    private double distanceToLocation;

    @JsonIgnore
    @Setter
    private String address;

    public boolean nameContainsPhrase(final String search) {
        return StringUtils.stripAccents(name)
                .toLowerCase()
                .contains(search.toLowerCase());
    }

    public void setDistanceToLocation(final double distanceToLocation) {
        this.distanceToLocation = Precision.round(distanceToLocation, 1);
    }
}
