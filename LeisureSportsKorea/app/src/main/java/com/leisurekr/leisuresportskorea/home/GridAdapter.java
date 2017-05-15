package com.leisurekr.leisuresportskorea.home;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.leisurekr.leisuresportskorea.R;

/**
 * Created by user on 2017-05-15.
 */

public class GridAdapter extends BaseAdapter {


    private int[] images = {R.drawable.bba, R.mipmap.ic_launcher,
            R.drawable.bba, R.mipmap.ic_launcher, R.drawable.bba,
            R.mipmap.ic_launcher, R.drawable.bba, R.mipmap.ic_launcher};

    private int[] images1 = {R.mipmap.ic_launcher, R.drawable.bba,
            R.mipmap.ic_launcher, R.drawable.bba, R.mipmap.ic_launcher,
            R.drawable.bba,R.mipmap.ic_launcher, R.drawable.bba};

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    ImageView imageView;
    int position1;
    boolean flag = false;
    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        this.position1=position;

        if(convertView == null){

            imageView = new ImageView(parent.getContext());
            imageView.setImageResource(images[position]);
            imageView.setLayoutParams(new GridView.LayoutParams(100,100));
            /*imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    flag=!flag;
                    Toast.makeText(parent.getContext(),Boolean.toString(flag),Toast.LENGTH_SHORT).show();
                    if(flag) {
                        imageView.setImageResource(images1[position1]);
                    }
                    else {
                        imageView.setImageResource(images[position1]);
                    }
                    return false;
                }
            });*/

        }else{
            imageView = (ImageView)convertView;
        }
        return imageView;
    }
}
