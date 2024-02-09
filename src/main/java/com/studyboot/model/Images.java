package com.studyboot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Images {

    @JsonProperty("hits")
    List<Image> hits;

    public Images() {
    }

    public List<Image> getHits() {
        return hits;
    }

    public void setHits(List<Image> hits) {
        this.hits = hits;
    }

    @Override
    public String toString() {
        return "Images{" +
                "hits=" + hits +
                '}';
    }
}
