package com.subhrob.popularmovies1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

abstract public class MovieListFragment extends Fragment {

    MovieListAdapter mAdapter;

    public MovieListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_grid, container, false);
        mAdapter = new MovieListAdapter(getActivity().getApplicationContext());
        final GridView mMovieView = (GridView)rootView.findViewById(R.id.gridview_most_popular);


        mMovieView.setAdapter(mAdapter);
        mMovieView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final MovieItem movieItem = (MovieItem) mAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                MovieItem.packageIntent(intent, movieItem);
                startActivity(intent);
            }
        });
        return rootView;
    }

    protected class Fetch extends FetchTask {
        @Override
        protected void onPostExecute(MovieItem[] movieItems) {
            if (movieItems != null){
                mAdapter.clear();
                for (MovieItem movieItem : movieItems) {
                    mAdapter.add(movieItem);
                }
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
