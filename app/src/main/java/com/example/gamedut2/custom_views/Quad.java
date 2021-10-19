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

public class Quad extends RelativeLayout {

    private final Context mContext;
    private final String TAG = "QUAD";
    //cet evenement permet de communiquer entre la customview et le fragment
    private CommandEventListener mEventListener;

    @SuppressLint("ClickableViewAccessibility")
    public Quad(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        init();

        //setOrientation(LinearLayout.VERTICAL);
        //setGravity(Gravity.CENTER);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.quad, this, true);

        // ConstraintLayout quad = findViewById(R.id.quad);

        //ImageButton rond = getChildAt(0);
        RelativeLayout.LayoutParams rondParams = new RelativeLayout.LayoutParams(400, 400);
        rondParams.leftMargin = 0;
        rondParams.topMargin = 0;
        rondParams.bottomMargin = 0;
        rondParams.rightMargin = 0;
        //quad.setLayoutParams(rondParams);
        //ivCenter.getLocationOnScreen(center);

        context = getContext();

        ImageView orange = findViewById(R.id.orange);
        ImageView blue = findViewById(R.id.blue);
        ImageView red = findViewById(R.id.red);
        ImageView green = findViewById(R.id.green);

        orange.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Log.d(TAG, "onTouch: Top");
                    top();
                }

                return true;
            }
        });
        blue.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Log.d(TAG, "onTouch: Left");
                    left();
                }

                return true;
            }
        });
        red.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Log.d(TAG, "onTouch: Right");
                    right();
                }

                return true;
            }
        });
        green.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Log.d(TAG, "onTouch: Bottom");
                    bottom();
                }

                return true;
            }
        });
    }

    public Quad(Context context) {
        this(context, null);
    }

    public void setEventListener(CommandEventListener mEventListener) {
        this.mEventListener = mEventListener;
    }

    protected void bottom() {

        if (mEventListener != null) {
            mEventListener.onBottom();
        }
    }

    protected void top() {

        if (mEventListener != null) {
            mEventListener.onTop();
        }
    }

    protected void right() {

        if (mEventListener != null) {
            mEventListener.onRight();
        }
    }

    protected void left() {

        if (mEventListener != null) {
            mEventListener.onLeft();
        }
    }

    private void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    public interface CommandEventListener {
        void onBottom();

        void onTop();

        void onRight();

        void onLeft();
    }
}
