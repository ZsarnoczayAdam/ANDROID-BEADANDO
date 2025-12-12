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
import com.example.moviesandseries.api.model.Series;
import com.example.moviesandseries.api.model.TmdbSeriesResponse;
import com.example.moviesandseries.ui.adapter.SeriesAdapter;
import com.example.moviesandseries.ui.adapter.SeriesDetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesList extends Fragment {

    private RecyclerView recyclerView;
    private SeriesAdapter adapter;
    private List<Series> seriesList = new ArrayList<>();

    private static final String API_KEY = "f3dabfacdedcdb785feaceb112452f26";

    public SeriesList() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_series_list, container, false);

        recyclerView = view.findViewById(R.id.seriesRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new SeriesAdapter(getContext(), seriesList);
        adapter.setOnSeriesClickListener(series -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("series", series);

            SeriesDetails fragment = new SeriesDetails();
            fragment.setArguments(bundle);

            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        });

        recyclerView.setAdapter(adapter);

        loadSeries();
        return view;
    }

    private void loadSeries() {
        ApiService api = RetrofitClient.getInstance().create(ApiService.class);

        api.getSeries(API_KEY, "hu-HU", 1)
                .enqueue(new Callback<TmdbSeriesResponse>() {
                    @Override
                    public void onResponse(Call<TmdbSeriesResponse> call, Response<TmdbSeriesResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            seriesList.clear();
                            seriesList.addAll(response.body().getResults());
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<TmdbSeriesResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
