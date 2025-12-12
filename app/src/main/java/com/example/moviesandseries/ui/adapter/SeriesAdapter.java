package com.example.moviesandseries.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesandseries.R;
import com.example.moviesandseries.api.model.Movie;
import com.example.moviesandseries.api.model.Series;

import java.util.List;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder> {

    private Context context;
    private List<Series> seriesList;
    public interface OnSeriesClickListener {
        void onSeriesClick(Series series);
    }

    private OnSeriesClickListener listener;

    public void setOnSeriesClickListener(OnSeriesClickListener listener) {
        this.listener = listener;
    }
//INNEn folytatni
    public SeriesAdapter(Context context, List<Series> seriesList) {
        this.context = context;
        this.seriesList = seriesList;
    }

    @NonNull
    @Override
    public SeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_series, parent, false);
        return new SeriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesViewHolder holder, int position) {
        Series series = seriesList.get(position);

        holder.title.setText(series.getName());

        // TMDB kép URL
        String imageUrl = "https://image.tmdb.org/t/p/w500" + series.getPosterPath();
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onSeriesClick(series);
            }
        });

        Glide.with(context)
                .load(imageUrl)
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return seriesList.size();
    }

    public static class SeriesViewHolder extends RecyclerView.ViewHolder {

        ImageView poster;
        TextView title;

        public SeriesViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.seriesPoster);
            title = itemView.findViewById(R.id.seriesTitle);
        }
    }
}
