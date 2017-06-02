package com.leisurekr.leisuresportskorea.shop_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.leisurekr.leisuresportskorea.R;
import com.leisurekr.leisuresportskorea.home.CircleAnimIndicator;

/**
 * Created by mobile on 2017. 5. 31..
 */

public class ShopInfoFragment extends Fragment implements OnMapReadyCallback,
        GoogleMap.OnMarkerDragListener {

    static ShopDetailActivity owner;

    ViewFlipper viewFlipper;
    GridView interestGridView;
    GridView serviceGridView;
    GridView prepareGridView;

    ImageView shareImageBtn;
    ImageView myFavoriteBtn;

    Button previousImageBtn;
    Button nextImageBtn;
    TextView reviewMoreBtn;

    CircleAnimIndicator circleAnimIndicator;

    MapView mapView;
    GoogleMap gMap;

    String shopName;
    Float shopTotalRating;
    String shopLocation;
    LatLng shopLatLng;
    String aboutShop;
    Boolean FAVORITE_TAG;

    int currentPage = 1;

    static final String[] interestValues = new String[] {
            "1", "0", "0", "0"
    };
    static final String[] serviceValues = new String[] {
            "0", "1", "0",
            "0", "0", "0"
    };
    static final String[] prepareValues = new String[] {
            "0", "0", "1", "0",
    };

    private Animation slideInAnimation;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.include_shop_info_section1, container, false);
        circleAnimIndicator = (CircleAnimIndicator) view.findViewById(R.id.shop_info_flipper_indicator);

        mapView = (MapView) view.findViewById(R.id.map_on_shop_info);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        interestGridView = (GridView) view.findViewById(R.id.shop_info_grid_view1);
        interestGridView.setAdapter(new GridViewAdapter(getContext(), interestValues));
        serviceGridView = (GridView) view.findViewById(R.id.shop_info_grid_view2);
        serviceGridView.setAdapter(new GridViewAdapter(getContext(), serviceValues));
        prepareGridView = (GridView) view.findViewById(R.id.shop_info_grid_view3);
        prepareGridView.setAdapter(new GridViewAdapter(getContext(), prepareValues));

        ImageView shopCircleImage = (ImageView) view.findViewById(R.id.shop_detail_circle_image);
        shopCircleImage.setImageResource(R.drawable.girls_generation_tifany);
        ImageView reviewerCircleImage = (ImageView) view.findViewById(R.id.reviewer_circle_image);
        reviewerCircleImage.setImageResource(R.drawable.girls_generation_tifany);
        ImageView emailCircleButton = (ImageView) view.findViewById(R.id.email_btn);
        emailCircleButton.setImageResource(R.drawable.ic_cart);
        ImageView callCircleButton = (ImageView) view.findViewById(R.id.call_btn);
        callCircleButton.setImageResource(R.drawable.ic_custom);

        slideInAnimation = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_in_left);
        shopCircleImage.startAnimation(slideInAnimation);
        reviewerCircleImage.startAnimation(slideInAnimation);

        viewFlipper = (ViewFlipper) view.findViewById(R.id.shop_info_image_flipper);
        ImageView imageView1 = (ImageView) view.findViewById(R.id.shop_image_flipper1);
        imageView1.setImageResource(R.drawable.girls_generation_all);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.shop_image_flipper2);
        imageView2.setImageResource(R.drawable.girls_generation_tifany);
        ImageView imageView3 = (ImageView) view.findViewById(R.id.shop_image_flipper3);
        imageView3.setImageResource(R.drawable.girls_generation_all);
        ImageView imageView4 = (ImageView) view.findViewById(R.id.shop_image_flipper4);
        imageView4.setImageResource(R.drawable.girls_generation_tifany);

        previousImageBtn = (Button) view.findViewById(R.id.shop_info_prevbtn);
        nextImageBtn = (Button) view.findViewById(R.id.shop_info_nextbtn);
        reviewMoreBtn = (TextView) view.findViewById(R.id.review_more_btn);

        initIndicator();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        previousImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPage > 1 && currentPage < 5) {
                    viewFlipper.showPrevious();
                    currentPage--;
                    circleAnimIndicator.selectDot(currentPage-1);
                }
            }
        });

        nextImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPage > 0 && currentPage < 4) {
                    viewFlipper.showNext();
                    currentPage++;
                    circleAnimIndicator.selectDot(currentPage-1);
                }
            }
        });

        reviewMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * TODO:
                 */
                //Intent reviewIntent = new Intent(getActivity().getApplicationContext(), aaa.class);
                //getActivity().getApplicationContext().startActivity(reviewIntent);
                Intent reviewIntent = new Intent(getActivity(), ReviewActivity.class);
                startActivity(reviewIntent);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public class GridViewAdapter extends BaseAdapter {
        private Context context;
        private final String[] tagValues;

        public GridViewAdapter(Context context, String[] interestValues) {
            this.context = context;
            this.tagValues = interestValues;
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
                textView.setText(tagValues[position]);

                ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);
                String tag = tagValues[position];

                switch (position) {
                    case 0:
                        if (tag == "0") {
                            imageView.setImageResource(R.drawable.ic_about);
                        }else {
                            imageView.setImageResource(R.drawable.ic_alarm);
                        }
                        break;
                    case 1:
                        if (tag == "0") {
                            imageView.setImageResource(R.drawable.ic_alarm);
                        }else {
                            imageView.setImageResource(R.drawable.ic_about);
                        }
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        if (tag == "0") {
                            imageView.setImageResource(R.drawable.ic_alarm);
                        }else {
                            imageView.setImageResource(R.drawable.ic_about);
                        }
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
            return tagValues.length;
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
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        shopLatLng = new LatLng(37.56, 126.97);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(shopLatLng);
        markerOptions.title("서울");
        markerOptions.snippet("한국의 수도");
        gMap.addMarker(markerOptions);

        gMap.moveCamera(CameraUpdateFactory.newLatLng(shopLatLng));
        gMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }
    @Override
    public void onMarkerDrag(Marker marker) {

    }
    @Override
    public void onMarkerDragEnd(Marker marker) {

    }

    private void initIndicator() {

        //원사이의 간격
        circleAnimIndicator.setItemMargin(15);
        //애니메이션 속도
        circleAnimIndicator.setAnimDuration(300);
        //indecator 생성
        circleAnimIndicator.createDotPanel(4, R.mipmap.ic_launcher , R.mipmap.ic_launcher);
    }
}
