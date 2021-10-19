package com.example.gamedut2.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.gamedut2.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {
    final String TAG = "ITEM_ADAPTER";
    final Context context;

    // Keep all images and titles in arrays
    public Integer[] table = {
            R.drawable.ic_round_dashboard_24, R.drawable.ic_round_android_24,
    };
    public String[] titles = {
            "Puzzle", "Labyrinth",
    };

    public ItemAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return table.length;
    }

    @Override
    public Integer getItem(int position) {
        return table[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder holder;

        if (view == null) {

            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item, parent, false);

            /*// intialization
            holder.image = (ImageView) view.findViewById(R.id.image);
            holder.title = (TextView) view.findViewById(R.id.title);

            // set custom values
            holder.image.setLayoutParams(new MaterialCardView.LayoutParams(MaterialCardView.LayoutParams.MATCH_PARENT, 400));
            holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            holder.image.setPadding(18, 18, 18, 18);*/

        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }

       /* holder = (ViewHolder) view.getTag();

        holder.image.setImageResource(table[position]);
        holder.title.setText(titles[position]);*/

        ImageView img = (ImageView) view.findViewById(R.id.image);
        TextView title = (TextView) view.findViewById(R.id.title);

        img.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 400));
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        img.setPadding(18, 18, 18, 18);

        //BIND
        img.setImageResource(table[position]);
        title.setText(titles[position]);

        return view;
    }

    //View Holder class used for reusing the same inflated view. It will decrease the inflation overhead @getView
    private static class ViewHolder {
        ImageView image;
        TextView title;
    }
}

