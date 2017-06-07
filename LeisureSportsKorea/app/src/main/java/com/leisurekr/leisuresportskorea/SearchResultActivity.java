package com.leisurekr.leisuresportskorea;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.leisurekr.leisuresportskorea.shop.TestArrayList;
import com.leisurekr.leisuresportskorea.shop_detail.ShopDetailActivity;
import com.leisurekr.leisuresportskorea.ticket.TicketActivity;

import java.util.ArrayList;

import static com.leisurekr.leisuresportskorea.R.id.action_search;

public class SearchResultActivity extends AppCompatActivity implements View.OnClickListener{

    Toolbar toolbar;
    String toolbardate;
    String toolbarguest;
    String toolbarlocation;
    private View searchView;
    boolean flag = false;
    Menu topMenu;

    MenuItem itemSearch;
    MenuItem itemTicket;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result_activity);

        Intent intent = getIntent();

        toolbardate = intent.getStringExtra("date");
        toolbarguest = intent.getStringExtra("guest");
        toolbarlocation = intent.getStringExtra("location");

        toolbar = (Toolbar) findViewById(R.id.search_result_toolbar);
        toolbar.setTitle(toolbardate);
        toolbar.setSubtitle(toolbarguest + ", " + toolbarlocation);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchView = findViewById(R.id.search_result_search);
        datelayout = (LinearLayout) searchView.findViewById(R.id.search_datelayout);
        guestlayout = (LinearLayout) searchView.findViewById(R.id.search_guestlayout);
        locationlayout = (LinearLayout) searchView.findViewById(R.id.search_locationlayout);

        date = (TextView) searchView.findViewById(R.id.search_date);
        guest = (TextView) searchView.findViewById(R.id.search_guest);
        location = (TextView) searchView.findViewById(R.id.search_location);

        datelayout.setOnClickListener(this);
        guestlayout.setOnClickListener(this);
        locationlayout.setOnClickListener(this);

        searchBtn = (FloatingActionButton) searchView.findViewById(R.id.search_searchbtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchResultActivity.this,SearchResultActivity.class);
                intent.putExtra("date",date.getText().toString());
                intent.putExtra("guest",guest.getText().toString());
                intent.putExtra("location",location.getText().toString());
                startActivity(intent);
                finish();
            }
        });



        RecyclerView rv = (RecyclerView) findViewById(R.id.search_result_recycler);

        rv.setLayoutManager(new LinearLayoutManager(LKApplication.getLKApplication()));
        rv.setAdapter(new SearchAdapter(TestArrayList.getArrayList())); // Test...

    }



    LinearLayout datelayout;
    LinearLayout guestlayout;
    LinearLayout locationlayout;

    TextView date;
    TextView guest;
    TextView location;

    FloatingActionButton searchBtn;

    DatePicker datePicker;

    ImageView subAdult;
    TextView currentAdult;
    ImageView addAdult;

    ImageView subChildren;
    TextView currentChildren;
    ImageView addChildren;

    int adult = 1;
    int children = 0;


    RadioGroup radioGroup;
    int selectedId;

    @Override
    public void onClick(View v) {

        final View view;
        switch (v.getId()) {
            case R.id.search_datelayout:
                view = View.inflate(SearchResultActivity.this, R.layout.dialog_calender, null);

                datePicker = (DatePicker) view.findViewById(R.id.calender_pickerdate);
                datePicker.setDrawingCacheBackgroundColor(Color.RED);

                new AlertDialog.Builder(SearchResultActivity.this)
                        .setView(view)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int year = datePicker.getYear();
                                int month = datePicker.getMonth() + 1;
                                int day = datePicker.getDayOfMonth();
                                date.setText(Integer.toString(year)
                                        + "년 " + Integer.toString(month) + "월 " + Integer.toString(day) + "일");

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();


                break;
            case R.id.search_guestlayout:
                view = View.inflate(SearchResultActivity.this, R.layout.dialog_guests, null);


                subAdult = (ImageView) view.findViewById(R.id.dialog_subadult);
                currentAdult = (TextView) view.findViewById(R.id.dialog_currentadult);
                addAdult = (ImageView) view.findViewById(R.id.dialog_addadult);
                subChildren = (ImageView) view.findViewById(R.id.dialog_subchildren);
                currentChildren = (TextView) view.findViewById(R.id.dialog_currentchildren);
                addChildren = (ImageView) view.findViewById(R.id.dialog_addchildren);

                currentAdult.setText("Adults "+adult);
                currentChildren.setText("Children "+children);

                SearchClickLisenter searchClickLisenter = new SearchClickLisenter();
                subAdult.setOnClickListener(searchClickLisenter);
                addAdult.setOnClickListener(searchClickLisenter);
                subChildren.setOnClickListener(searchClickLisenter);
                addChildren.setOnClickListener(searchClickLisenter);

                new AlertDialog.Builder(SearchResultActivity.this)
                        .setView(view)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(adult!=0&&children!=0)
                                    guest.setText(currentAdult.getText().toString()
                                            + "   " + currentChildren.getText().toString());
                                else if(adult!=0)
                                    guest.setText(currentAdult.getText().toString());
                                else if(children!=0)
                                    guest.setText(currentChildren.getText().toString());
                                else
                                    guest.setText("No. of Guests");

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
                break;
            case R.id.search_locationlayout:
                view = View.inflate(SearchResultActivity.this, R.layout.dialog_location, null);

                radioGroup = (RadioGroup) view.findViewById(R.id.dialog_group);
                if(!location.getText().toString().equals("Location")) {
                    RadioButton rb = (RadioButton) view.findViewById(selectedId);
                    rb.setChecked(true);
                }else{
                    RadioButton rb = (RadioButton) view.findViewById(R.id.dialog_yonsangu);
                    rb.setChecked(true);
                }
                new AlertDialog.Builder(SearchResultActivity.this)
                        .setView(view).setTitle("Location")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectedId = radioGroup.getCheckedRadioButtonId();
                                RadioButton rb = (RadioButton) view.findViewById(selectedId);

                                location.setText(rb.getText().toString());

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
                break;
        }

    }

    class SearchClickLisenter implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.dialog_subadult:
                    if (adult > 0) {
                        adult--;
                        currentAdult.setText("Adults " + Integer.toString(adult));
                    }
                    break;
                case R.id.dialog_addadult:
                    if (adult >= 0) {
                        adult++;
                        currentAdult.setText("Adults " + Integer.toString(adult));
                    }
                    break;
                case R.id.dialog_subchildren:
                    if (children > 0) {
                        children--;
                        currentChildren.setText("Children " + Integer.toString(children));
                    }
                    break;
                case R.id.dialog_addchildren:
                    if (children >= 0) {
                        children++;
                        currentChildren.setText("Children " + Integer.toString(children));
                    }
                    break;
            }

        }
    }

    class SearchAdapter
            extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

        private ArrayList<Integer> shopImages;

        private Animation slideInAnimation;

        public SearchAdapter(ArrayList<Integer> resources) {
            shopImages = resources;
            slideInAnimation = AnimationUtils.loadAnimation(SearchResultActivity.this
                    , android.R.anim.slide_in_left);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final LinearLayout mShopMainImage;
            public final LinearLayout dim;
            //public final ImageView mShopMainImage;
            public final ImageView mShopCircleImage;
            public final TextView mShopName;
            public final TextView mShopActivity;
            public final TextView mShopLocation;
            public final TextView mShopRating;
            public final TextView mShopPrice;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mShopMainImage = (LinearLayout) view.findViewById(R.id.result_recycler_mainimage);
                dim = (LinearLayout) view.findViewById(R.id.result_recycler_dim);
                mShopCircleImage = (ImageView) view.findViewById(R.id.result_recycler_circleimage);
                mShopName = (TextView) view.findViewById(R.id.result_recycler_name);
                mShopActivity = (TextView) view.findViewById(R.id.result_recycler_text);
                mShopLocation = (TextView) view.findViewById(R.id.result_recycler_location);
                mShopRating = (TextView) view.findViewById(R.id.result_recycler_rating);
                mShopPrice = (TextView) view.findViewById(R.id.result_recycler_price);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.search_result_recycleritem, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final SearchAdapter.ViewHolder holder, int position) {
            Integer shopCircleImageInfo = shopImages.get(position); // circle image;
            holder.mShopName.setText("Costa leisure sport");
            holder.mShopLocation.setText("Han River");
            holder.mShopRating.setText("4.8");
            holder.mShopPrice.setText("$54");

            holder.mShopMainImage.setBackgroundResource(R.drawable.pic_shop);
            holder.dim.setAlpha(0.3f);

            holder.mShopMainImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ShopDetailActivity.class);
                    startActivity(intent);
                }
            });

            holder.mShopCircleImage.setImageResource(R.drawable.pic_shop1);
            holder.mShopCircleImage.startAnimation(slideInAnimation);
        }

        @Override
        public int getItemCount() {
            return shopImages.size();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        topMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Log.i("검색test", "클릭됨");
        switch (item.getItemId()) {
            case action_search:
                if (searchView.getVisibility() == View.GONE) {
                    Log.i("검색test1111", "false = > true");
                    searchView.setVisibility(View.VISIBLE);
                    searchView.setClickable(true);
                    toolbar.setTitle("Search");
                    if (topMenu != null) {
                        itemSearch = topMenu.findItem(action_search);
                        itemTicket = topMenu.findItem(R.id.action_ticket);
                        itemSearch.setVisible(false);
                        itemTicket.setVisible(false);
                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                        flag = true;
                    } else {
                        Log.i("검색test", "true = > false");
                        searchView.setVisibility(View.GONE);
                        flag = false;
                    }
                }
                return true;
            case R.id.action_ticket:
                Intent intent = new Intent(getApplicationContext(), TicketActivity.class);
                startActivity(intent);
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
