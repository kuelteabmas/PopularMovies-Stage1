package com.devapp.dev_05.popularmoviesstage1;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.zip.Inflater;

import static com.devapp.dev_05.popularmoviesstage1.MainActivity.EXTRA_DATE;
import static com.devapp.dev_05.popularmoviesstage1.MainActivity.EXTRA_GENRE;
import static com.devapp.dev_05.popularmoviesstage1.MainActivity.EXTRA_RATING;
import static com.devapp.dev_05.popularmoviesstage1.MainActivity.EXTRA_SYNOPSIS;
import static com.devapp.dev_05.popularmoviesstage1.MainActivity.EXTRA_TITLE;
import static com.devapp.dev_05.popularmoviesstage1.MainActivity.EXTRA_URL;

public class MovieDetailActivity extends AppCompatActivity {

    // Base URL and image size for the movie poster image URL
    private String baseURL = "http://image.tmdb.org/t/p/";
    private String posterSize = "original";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        // Define variables
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String movieTitle = intent.getStringExtra(EXTRA_TITLE);
        String rating = intent.getStringExtra(EXTRA_RATING);
        String synopsis = intent.getStringExtra(EXTRA_SYNOPSIS);
        String releaseDate = intent.getStringExtra(EXTRA_DATE);

        // Define the different views
        ImageView imageView = findViewById(R.id.image_view_movie_poster_detail);
        TextView textViewMovieTitle = findViewById(R.id.text_view_movie_title);
        TextView textViewReleaseDate = findViewById(R.id.text_view_release_date);
        TextView textViewRating = findViewById(R.id.text_view_rating);
        TextView textViewSynopsis = findViewById(R.id.text_view_synopsis);


        // Fetch the appropriate movie image, set and display the image
        Picasso.get()
                .load(baseURL + posterSize + imageUrl)
                .error(R.drawable.ic_launcher_background)
                .resize(1000, 800)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.photo_not_available)
                .centerInside()
                .into(imageView);

        // Fetch the corresponding movie details
        textViewMovieTitle.setText(movieTitle);
        textViewReleaseDate.setText(releaseDate);
        textViewRating.setText(rating);
        textViewSynopsis.setText(synopsis);
    }
}
