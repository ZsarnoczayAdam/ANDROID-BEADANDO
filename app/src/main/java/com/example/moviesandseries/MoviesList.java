package com.example.moviesandseries;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesandseries.api.ApiService;
import com.example.moviesandseries.api.RetrofitClient;
import com.example.moviesandseries.api.model.Movie;
import com.example.moviesandseries.api.model.TmdbResponse;
import com.example.moviesandseries.ui.adapter.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesList extends Fragment {

    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private List<Movie> movieList = new ArrayList<>();

    private static final String API_KEY = "8c4327ae6ba5f3350c39d815d0de0f5e";

    public MoviesList() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movies_list, container, false);

        recyclerView = view.findViewById(R.id.moviesRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new MovieAdapter(getContext(), movieList);
        recyclerView.setAdapter(adapter);

        loadMovies();

        return view;
    }

    private void loadMovies() {
        ApiService api = RetrofitClient.getInstance().create(ApiService.class);

        api.getPopularMovies(API_KEY, "en-US", 1)
                .enqueue(new Callback<TmdbResponse>() {
                    @Override
                    public void onResponse(Call<TmdbResponse> call, Response<TmdbResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            movieList.clear();
                            movieList.addAll(response.body().getResults());
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<TmdbResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
