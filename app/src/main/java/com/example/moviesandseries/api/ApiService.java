package com.example.moviesandseries.api;

import com.example.moviesandseries.api.model.TmdbResponse;
import com.example.moviesandseries.api.model.TmdbSeriesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movie/popular")
    Call<TmdbResponse> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );
    @GET("tv/popular")
    Call<TmdbSeriesResponse> getSeries(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );
}
