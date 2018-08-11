package com.devapp.dev_05.popularmoviesstage1;

public class MovieItem {
    // Declare variables
    private String mMovieImage; // Movie Image URL
    private String mMovieTitle; // Movie Title
    private String mReleaseDate; // Move Release Date
    private String mSynopsis; // Movie Synopsis
    private String mRating; // Movie Rating

    public MovieItem(String mMovieImage, String mMovieTitle, String mReleaseDate, String mSynopsis, String mRating) {
        this.mMovieImage = mMovieImage;
        this.mMovieTitle = mMovieTitle;
        this.mReleaseDate = mReleaseDate;
        this.mSynopsis = mSynopsis;
        this.mRating = mRating;
    }


    // Getters for movie image and title
    public String getmMovieImage() { return mMovieImage; }

    public String getMovieTitle() { return mMovieTitle; }

    public String getReleaseDate() { return mReleaseDate; }

    public String getSynopsis() { return mSynopsis; }

    public String getRating() { return "Rating: " + mRating + "/10"; }

}
