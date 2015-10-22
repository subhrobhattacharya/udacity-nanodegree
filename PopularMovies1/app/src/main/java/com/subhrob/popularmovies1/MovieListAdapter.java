package com.subhrob.popularmovies1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SUBHRO on 09-10-2015.
 */
public class MovieListAdapter extends BaseAdapter{

    private final List<MovieItem> mItems = new ArrayList<MovieItem>();
    private final Context mContext;

    public MovieListAdapter(Context context) {
        mContext = context;
    }

    public void add(MovieItem item) {
        mItems.add(item);
        notifyDataSetChanged();
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int pos) {
        return mItems.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final MovieItem toDoItem = (MovieItem)getItem(position);

        LinearLayout itemLayout = null;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemLayout = (LinearLayout)inflater.inflate(R.layout.movie_poster, parent, false);
        } else
            itemLayout = (LinearLayout)convertView;

        ImageView imageView = (ImageView)itemLayout.findViewById(R.id.poster_image);
        Picasso.with(mContext).load(toDoItem.getAbsoluteUri(toDoItem.getImgUri())).into(imageView);

        return itemLayout;
    }

}
