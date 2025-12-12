package com.example.moviesandseries.api.model;

import java.io.Serializable;

public class Series implements Serializable {

    private String name;
    private String overview;
    private String poster_path;

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return poster_path;
    }
}
