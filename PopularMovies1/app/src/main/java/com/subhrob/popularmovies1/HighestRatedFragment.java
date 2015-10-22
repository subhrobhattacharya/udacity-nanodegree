package com.subhrob.popularmovies1;

public class HighestRatedFragment extends MovieListFragment{

    @Override
    public void onStart() {
        super.onStart();
        Fetch task = new Fetch();
        task.execute("vote_average.desc");
    }
}
