package com.subhrob.popularmovies1;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by SUBHRO on 09-10-2015.
 */
public class MovieItem {

    private final String LOG_TAG = MovieItem.class.getSimpleName();
    private final String FORECAST_BASE_URL = "http://image.tmdb.org/t/p/w185/";

    public final static String ORIG_TITLE = "original_title";
    public final static String THUMBNAIL = "thumbnail";
    public final static String OVERVIEW = "overview";
    public final static String RATING = "rating";
    public final static String RELEASE_DATE = "release_date";

    private String id;
    private String imgUri;
    private String title;
    private String original_title;
    private double vote_average;
    private String overview;
    private String release_date;

    MovieItem(String id, String imgUri, String title, String original_title,
                double vote_average, String overview, String release_date ){
        this.id = id;
        this.imgUri = imgUri;
        this.title = title;
        this.original_title = original_title;
        this.vote_average = vote_average;
        this.overview = overview;
        this.release_date = release_date;
    }

    public String getId() {return id;}
    public void setId(String id) {this.id =id;}

    public String getImgUri() {return imgUri;}
    public void setImgUri(String imgUri) {this.imgUri = imgUri;}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getAbsoluteUri(String relativePath){
        String absolute_url = "";
        try {
            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon().appendEncodedPath(imgUri).build();
            URL url = new URL(builtUri.toString());
            absolute_url = url.toString();
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "MalformedURLException");
        }
        return absolute_url;
    }

    public String getOriginal_title() {return original_title;}
    public void setOriginal_title(String original_title) {this.original_title = original_title;}

    public double getVote_average() {return vote_average;}
    public void setVote_average(double vote_average) {this.vote_average = vote_average;}

    public String getOverview() {return overview;}
    public void setOverview(String overview) {this.overview = overview;}

    public String getRelease_date() {return release_date;}
    public void setRelease_date(String release_date) {this.release_date = release_date;}


    public static void packageIntent(Intent intent, MovieItem movieItem) {
        intent.putExtra(MovieItem.ORIG_TITLE, movieItem.getOriginal_title());
        intent.putExtra(MovieItem.THUMBNAIL, movieItem.getAbsoluteUri(movieItem.getImgUri()));
        intent.putExtra(MovieItem.RATING, Double.toString(movieItem.getVote_average()));
        intent.putExtra(MovieItem.OVERVIEW, movieItem.getOverview());
        intent.putExtra(MovieItem.RELEASE_DATE, movieItem.getRelease_date());
    }

}

