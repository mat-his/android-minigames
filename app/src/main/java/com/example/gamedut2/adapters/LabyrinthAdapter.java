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
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;

import com.example.gamedut2.R;

import java.util.ArrayList;

public class LabyrinthAdapter extends BaseAdapter {

    final String TAG = "BOX_ADAPTER";
    final Context context;
    final ArrayList<Boolean> isPlayer = new ArrayList<Boolean>();
    final ArrayList<Boolean> isWall = new ArrayList<Boolean>();

    public LabyrinthAdapter(Context context) {
        this.context = context;
    }

    public void setIsPlayer(ArrayList<Boolean> isPlayer) {
        this.isPlayer.clear();
        this.isPlayer.addAll(isPlayer);
        notifyDataSetChanged();
    }
    public Integer getPlayer() {
        return isPlayer.indexOf(true);
    }
    public void addValue(Boolean value) {
        isPlayer.add(value);
        notifyDataSetChanged();
    }

    public void setIsWall(ArrayList<Boolean> isWall) {
        this.isWall.clear();
        this.isWall.addAll(isWall);
        notifyDataSetChanged();
    }

    public Boolean getIsWall(int index) {
        return this.isWall.get(index);
    }


    @Override
    public int getCount() {
        return isPlayer.size();
    }

    @Override
    public Boolean getItem(int position) {
        return isPlayer.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ImageView box;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.labyrinth_case, null);

            Log.d(TAG, "getView: new box");
            box = view.findViewById(R.id.box);
            Display display = context.getDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = (size.x) / 8;
            box.setLayoutParams(new GridView.LayoutParams(width, width));
            box.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        else
        {
            box = (ImageView) view;
        }

        //conditional image displaying : mur, herbe ou android player
        if (isPlayer.get(position)) {
            box.setImageResource(R.drawable.ic_round_android_24);
        }
        else {
            if (position == 63) {
                box.setImageResource(R.drawable.ic_round_exit_to_app_24);
            } else {
                if (isWall.get(position)) {
                    box.setImageResource(R.drawable.mur);
                } else {
                    box.setImageResource(R.drawable.herbe);
                }
            }
        }
        return box;
    }
}
