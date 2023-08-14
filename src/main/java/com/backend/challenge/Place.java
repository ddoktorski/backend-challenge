package com.backend.challenge;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Place {
    private long id;
    private String name;
    private double lat;
    private double lon;

    public boolean nameContainsPhrase(final String search) {
        return StringUtils.stripAccents(name)
                .toLowerCase()
                .contains(search.toLowerCase());
    }
}
