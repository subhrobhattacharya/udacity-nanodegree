package com.subhrob.popularmovies1;

/**
 * A placeholder fragment containing a simple view.
 */
public class MostPopularFragment extends MovieListFragment{

    @Override
    public void onStart() {
        super.onStart();
        Fetch task = new Fetch();
        task.execute("popularity.desc");
    }
}

