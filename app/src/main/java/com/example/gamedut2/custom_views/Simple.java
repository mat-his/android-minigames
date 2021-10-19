package com.example.gamedut2.custom_views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.gamedut2.R;

public class Simple extends RelativeLayout {

    private Context mContext;
    private String TAG = "QUAD";

    public interface CommandEventListener {
        public void onHit();

    }

    //cet evenement permet de communiquer entre la customview et le fragment
    private CommandEventListener mEventListener;

    public void setEventListener(CommandEventListener mEventListener) {
        this.mEventListener = mEventListener;
    }

    protected void hit() {

        if (mEventListener != null) {
            mEventListener.onHit();
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    public Simple(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        init();


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.simple, this, true);

        ImageView button = findViewById(R.id.button);

        button.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Log.d(TAG, "onTouch: Top");
                    hit();
                }

                return true;
            }
        });
    }

    private void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    public Simple(Context context) {
        this(context, null);
    }
}
