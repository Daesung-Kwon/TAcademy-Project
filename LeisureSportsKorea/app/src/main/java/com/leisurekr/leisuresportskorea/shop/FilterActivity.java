package com.leisurekr.leisuresportskorea.shop;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.leisurekr.leisuresportskorea.R;

/**
 * Created by mobile on 2017. 5. 29..
 */

public class FilterActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private GridView gridView;

    static final String[] interestsValues = new String[] {
            "1", "0", "1", "0",
            "0", "1", "0", "0",
            "0", "0", "0", "1",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        toolbar = (Toolbar) findViewById(R.id.filter_toolbar);
        toolbar.setTitle("Interests");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setAdapter(new FilterImageAdapter(this, interestsValues));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    public class ImageAdapter extends BaseAdapter {
        private Context context;
        private final String[] interestValues;

        public ImageAdapter(Context context, String[] interestValues) {
            this.context = context;
            this.interestValues = interestValues;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View gridView;

            if (convertView == null) {
                gridView = new View(context);

                gridView = inflater.inflate(R.layout.interest_grid_item, null);

                TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
                textView.setText(interestValues[position]);

                ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);

                String interestTag = interestValues[position];

                switch (position) {
                    case 0:
                        if (interestTag == "0") {
                            imageView.setImageResource(R.drawable.bba);
                        }else {
                            imageView.setImageResource(R.drawable.bba);
                        }
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    case 11:
                        break;
                   default:
                       break;
                }

            } else {
                gridView = (View) convertView;
            }
            return gridView;
        }

        @Override
        public int getCount() {
            return interestValues.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
