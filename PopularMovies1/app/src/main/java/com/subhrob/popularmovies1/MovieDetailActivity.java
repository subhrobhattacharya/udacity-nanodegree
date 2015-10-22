package com.subhrob.popularmovies1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class MovieDetailActivity extends FragmentActivity {

    TextView mTitle;
    ImageView mPoster;
    TextView mRating;
    TextView mReleaseDate;
    TextView mOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        mTitle = (TextView)findViewById(R.id.movie_detail_title);
        mPoster = (ImageView)findViewById(R.id.movie_detail_image);
        mRating = (TextView)findViewById(R.id.movie_detail_rating);
        mReleaseDate = (TextView)findViewById(R.id.movie_detail_relDate);
        mOverview = (TextView)findViewById(R.id.moview_detail_overview);

        populateUI();

    }


    void populateUI(){
        Intent intent = getIntent();

        mTitle.setText(intent.getStringExtra(MovieItem.ORIG_TITLE));
        Picasso.with(this).load(intent.getStringExtra(MovieItem.THUMBNAIL)).into(mPoster);
        mRating.setText(intent.getStringExtra(MovieItem.RATING));
        mReleaseDate.setText(intent.getStringExtra(MovieItem.RELEASE_DATE));

        String overView  = intent.getStringExtra(MovieItem.OVERVIEW);
        if (overView != null && !overView.equals("null"))
            mOverview.setText(overView);
        else
            mOverview.setText("");
    }
}
