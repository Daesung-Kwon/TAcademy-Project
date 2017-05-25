package com.leisurekr.leisuresportskorea.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.leisurekr.leisuresportskorea.R;

/**
 * Created by user on 2017-05-11.
 */

public class ImageAdapter extends PagerAdapter {

    Context context;
    Bitmap galImage;
    BitmapFactory.Options options;

    public ImageAdapter(Context context){
        this.context = context;
        options = new BitmapFactory.Options();
    }
    int images[] ={
                    R.drawable.aa, R.drawable.bb,R.drawable.aa,R.drawable.bb
            };

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);

        imageView.setImageResource(images[position]);
        //galImage = BitmapFactory.decodeResource(context.getResources(), images[position]);

        //imageView.setImageBitmap(galImage);
        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}
