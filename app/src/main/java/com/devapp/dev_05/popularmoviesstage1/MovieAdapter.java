package com.devapp.dev_05.popularmoviesstage1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    // Declare variables
    private Context mContext;
    private ArrayList<MovieItem> mMovieList;
    private OnItemClickListener mListener;

    // Base URL and image size for the movie poster image URL
    private String baseURL = "http://image.tmdb.org/t/p/";
    private String posterSize = "original";

    // Define an interface for the OnClick
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener (OnItemClickListener listener) {
        mListener = listener;
    }

    public MovieAdapter(Context context, ArrayList<MovieItem> movieList) {
        mContext = context;
        mMovieList = movieList;
    }



    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieItem currentItem = mMovieList.get(position);

        String imageUrl = currentItem.getmMovieImage();

        Picasso.get()
                .load(baseURL+posterSize+imageUrl)
                .error(R.drawable.ic_launcher_background)
                .resize(1000, 800)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.photo_not_available)
                .centerInside()
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;

        public MovieViewHolder(View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.image_view_movie_poster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
