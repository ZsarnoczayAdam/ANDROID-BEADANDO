package com.example.moviesandseries.ui.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.moviesandseries.R;
import com.example.moviesandseries.api.model.Movie;
import com.example.moviesandseries.api.model.Series;

public class SeriesDetails extends Fragment {
    private Button favoritesBtn;
    private ImageView poster;
    private TextView title, overview;

    private Series series;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_series_details, container, false);
        favoritesBtn = view.findViewById(R.id.addToFavoritesButton);
        poster = view.findViewById(R.id.detailsPoster);
        title = view.findViewById(R.id.detailsTitle);
        overview = view.findViewById(R.id.detailsOverview);

        if (getArguments() != null) {
            series = (Series) getArguments().getSerializable("series");
        }

        if (series != null) {
            title.setText(series.getName());
            overview.setText(series.getOverview());

            Glide.with(getContext())
                    .load("https://image.tmdb.org/t/p/w500" + series.getPosterPath())
                    .into(poster);
        }

        favoritesBtn.setOnClickListener(v -> {
            // Itt fogjuk elmenteni kedvencekbe
            Toast.makeText(getContext(), series.getName() + " Hozzáadva a kedvencekhez!", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}
