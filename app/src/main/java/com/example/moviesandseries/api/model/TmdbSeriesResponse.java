package com.example.moviesandseries.api.model;

import java.util.List;

public class TmdbSeriesResponse {

    private int page;
    private List<Series> results;

    public int getPage() {
        return page;
    }

    public List<Series> getResults() {
        return results;
    }
}
