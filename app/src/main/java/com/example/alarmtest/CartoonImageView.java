package com.example.alarmtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by mingchunhu on 2014/12/30.
 */
public class CartoonImageView extends ImageView {

    public CartoonImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }

//        int width = MeasureSpec.getSize(widthMeasureSpec);
//        int height = drawable.getMinimumHeight();//.getIntrinsicHeight();
//        Log.d(MainActivity.TAG, "onMeasure width:" + width + ", height:" + height);
//        setMeasuredDimension(width, 11460);

        int height = MeasureSpec.getSize(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        Log.d(MainActivity.TAG, "onMeasure before adjust width:" + width + ", height:" + height +
                ", intrinsicHeight:" + drawable.getIntrinsicHeight() + ", intrinsicWidth:" + drawable.getIntrinsicWidth());

        if(width >= height)
            height = (int) Math.ceil(width * (float) drawable.getIntrinsicHeight() / drawable.getIntrinsicWidth());
        else
            width = (int) Math.ceil(height * (float) drawable.getIntrinsicWidth() / drawable.getIntrinsicHeight());
        Bitmap bmp = ((BitmapDrawable) drawable).getBitmap();
        Log.d(MainActivity.TAG, "onMeasure after width:" + width + ", height:" + height + ", bmp width" + bmp.getWidth() + ", bmp Height:" + bmp.getHeight() );
        this.setMeasuredDimension(width, height);

    }
}
