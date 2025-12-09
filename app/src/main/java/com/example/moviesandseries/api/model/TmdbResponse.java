package com.example.moviesandseries.api.model;

import java.util.List;

public class TmdbResponse {

    private int page;
    private List<Movie> results;

    public int getPage() {
        return page;
    }

    public List<Movie> getResults() {
        return results;
    }
}
