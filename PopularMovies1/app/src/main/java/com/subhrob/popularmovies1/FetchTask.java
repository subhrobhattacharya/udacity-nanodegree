package com.subhrob.popularmovies1;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by SUBHRO on 08-10-2015.
 *
 */
public class FetchTask extends AsyncTask<String, Void, MovieItem[]> {
    private final String LOG_TAG = FetchTask.class.getSimpleName();

    private MovieItem[] getDataFromJson(String jsonStr, int numMovies)
            throws JSONException {

        // These are the names of the JSON objects that need to be extracted.
        final String TMDB_TITLE = "title";
        final String TMDB_ID = "id";
        final String TMDB_POSTER_PATH = "poster_path";
        final String TMDB_ORIG_TITLE = "original_title";
        final String TMDB_VOTE_AVG = "vote_average";
        final String TMDB_OVERVIEW = "overview";
        final String TMDB_RELEASE_DATE = "release_date";

        JSONObject jsonObject = new JSONObject(jsonStr);
        JSONArray jsonArray = jsonObject.getJSONArray("results");

        MovieItem[] movieItems = new MovieItem[numMovies];
        for(int i = 0; i < jsonArray.length(); i++) {

            JSONObject movie = jsonArray.getJSONObject(i);

            String title = movie.getString(TMDB_TITLE);
            String id = Integer.toString(movie.getInt(TMDB_ID));
            String imgUri = movie.getString(TMDB_POSTER_PATH);
            String original_title = movie.getString(TMDB_ORIG_TITLE);
            double vote_average = movie.getDouble(TMDB_VOTE_AVG);
            String overview = movie.getString(TMDB_OVERVIEW);
            String release_date = movie.getString(TMDB_RELEASE_DATE);

            MovieItem movieItem = new MovieItem(id, imgUri, title, original_title,vote_average,
                    overview,release_date);
            movieItems[i]= movieItem;
        }


        return movieItems;
    }
    @Override
    protected MovieItem[] doInBackground(String... params) {
        if (params.length != 1) {
            return null;
        }
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String jsonStr = null;
        int numMovies = 20;

        try {
            final String BASE_URL =
                    "http://api.themoviedb.org/3/discover/movie?";
            final String SORT_PARAM = "sort_by";
            final String API_PARAM = "api_key";
            final String API_VALUE = "";

            Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                    .appendQueryParameter(SORT_PARAM, params[0])
                    .appendQueryParameter(API_PARAM, API_VALUE)
                    .build();

            URL url = new URL(builtUri.toString());
            //Log.v(LOG_TAG, "Built URI " + builtUri.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            jsonStr = buffer.toString();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        try {
            return getDataFromJson(jsonStr, numMovies);
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }

        return null;
    }

}
