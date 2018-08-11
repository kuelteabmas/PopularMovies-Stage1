package com.devapp.dev_05.popularmoviesstage1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.devapp.dev_05.popularmoviesstage1.R.layout.activity_main;

public class MainActivity extends AppCompatActivity implements MovieAdapter.OnItemClickListener{

    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_TITLE = "movieTitle";
    public static final String EXTRA_DATE = "releaseDate";
    public static final String EXTRA_SYNOPSIS = "synopsis";
    public static final String EXTRA_RATING = "rating";
    public static final String EXTRA_GENRE = "movieGenre";

    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private MovieAdapter mMovieAdapter;
    private ArrayList<MovieItem> mMovieList;
    private RequestQueue mRequestQueue;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
//
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        mRecyclerView = findViewById(R.id.recycler_view);
        mGridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        //mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMovieList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();


    }

    // Method to parse JSON from API URL
    private void parseJSON() {
        String my_api_key = getString(R.string.my_api_key);
        String url = "https://api.themoviedb.org/3/discover/movie?page=1&include_video=false&include_adult=false&sort_by=popularity.desc&language=en-US&api_key=" + my_api_key;

        // Fetching the main JSON Object from API
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    // Fetch first JSON Array, "results"
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Define the JSON Array
                            JSONArray jsonArray = response.getJSONArray("results");

                            // Go through the Array to find the movie details needed
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject results = jsonArray.getJSONObject(i);

                                // Fetch the Movie Poster Path
                                String moviePosterPath = results.getString("poster_path");
                                String movieTitle = results.getString("title");
                                String releaseDate = results.getString("release_date");
                                String synopsis = results.getString("overview");
                                String rating = results.getString("vote_average");

                                mMovieList.add(new MovieItem(moviePosterPath, movieTitle, releaseDate, synopsis, rating));
                            }

                            // This will take care of parsing the JSON data into our Array List
                            // and then set the adapter into our RecyclerView
                            mMovieAdapter = new MovieAdapter(MainActivity.this, mMovieList);
                            mRecyclerView.setAdapter(mMovieAdapter);
                            mMovieAdapter.setOnItemClickListener(MainActivity.this);


                            // If there are any errors, catch
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    // If there are any errors, catch
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        // Add our request to our RequestQueue
        mRequestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent movieDetailIntent = new Intent(this, MovieDetailActivity.class);
        MovieItem clickedItem = mMovieList.get(position);

        movieDetailIntent.putExtra(EXTRA_URL, clickedItem.getmMovieImage());
        movieDetailIntent.putExtra(EXTRA_TITLE, clickedItem.getMovieTitle());
        movieDetailIntent.putExtra(EXTRA_DATE, clickedItem.getReleaseDate());
        movieDetailIntent.putExtra(EXTRA_SYNOPSIS, clickedItem.getSynopsis());
        movieDetailIntent.putExtra(EXTRA_RATING, clickedItem.getRating());

        startActivity(movieDetailIntent);
    }
}
