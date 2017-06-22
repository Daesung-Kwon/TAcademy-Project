package com.leisurekr.leisuresportskorea;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
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
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.leisurekr.leisuresportskorea.okhttp.OkHttpAPIHelperHandler;
import com.leisurekr.leisuresportskorea.shop.FilterActivity;
import com.leisurekr.leisuresportskorea.shop_detail.LKShopListObject;
import com.leisurekr.leisuresportskorea.shop_detail.ShopDetailActivity;
import com.leisurekr.leisuresportskorea.ticket.TicketActivity;

import java.util.ArrayList;
import java.util.List;

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

    SearchObject searchObject;

    SearchAdapter searchAdapter;
    TextView resultsCountTextView;
    FloatingActionButton filter;
    FloatingActionButton searchBtn;

    static TextView filterTag1;
    static TextView filterTag2;
    static TextView filterTag3;
    static TextView filterTag4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result_activity);

        Intent intent = getIntent();

        toolbardate = intent.getStringExtra("date");
        toolbarguest = intent.getStringExtra("guest");
        toolbarlocation = intent.getStringExtra("location");

        searchObject = new SearchObject();
        searchObject.setAdult(intent.getIntExtra("adult",1));
        searchObject.setChildren(intent.getIntExtra("children",0));
        searchObject.setDate(intent.getStringExtra("dateString"));
        searchObject.setLocation(toolbarlocation);

        toolbar = (Toolbar) findViewById(R.id.search_result_toolbar);
        toolbar.setTitle(toolbardate);
        toolbar.setSubtitle(toolbarguest + ", " + toolbarlocation);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

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
                if(date.getText().toString()==null||date.getText().toString().equals("Date")){
                    Toast.makeText(SearchResultActivity.this, "Please select Date of use"
                            , Toast.LENGTH_SHORT).show();
                }else if((adult == 0 && children == 0)||guest.getText().toString()
                        .equals("No. of Guests")){
                    Toast.makeText(SearchResultActivity.this, "Please select Number of Guests"
                            , Toast.LENGTH_SHORT).show();
                }else if(location.getText().toString()==null||location.getText().toString()
                        .equals("Location")){
                    Toast.makeText(SearchResultActivity.this, "Please select Location"
                            , Toast.LENGTH_SHORT).show();
                }else {
                    toolbar.setTitle(date.getText().toString());
                    toolbar.setSubtitle(guest.getText().toString() + ", " + location.getText().toString());
                    setSupportActionBar(toolbar);
                    searchObject = new SearchObject();
                    searchObject.setAdult(adult);
                    searchObject.setChildren(children);
                    searchObject.setDate(dateString);
                    searchObject.setLocation(location.getText().toString());

                    if (searchView.getVisibility()==View.VISIBLE) {
                        searchView.setVisibility(View.GONE);
                        //toolbar.setTitle(tabs[0]);
                        itemSearch.setVisible(true);
                        itemTicket.setVisible(true);
                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    }

                    new AsyncSearchInsert().execute(searchObject);
                }
            }
        });

        resultsCountTextView = (TextView) findViewById(R.id.result_count_text_search);
        filterTag1 = (TextView) findViewById(R.id.selected_filter_text1);
        filterTag2 = (TextView) findViewById(R.id.selected_filter_text2);
        filterTag3 = (TextView) findViewById(R.id.selected_filter_text3);
        filterTag4 = (TextView) findViewById(R.id.selected_filter_text4);

        RecyclerView rv = (RecyclerView) findViewById(R.id.search_result_recycler);

        rv.setLayoutManager(new LinearLayoutManager(LKApplication.getLKApplication()));
        searchAdapter = new SearchAdapter(new ArrayList<LKShopListObject>());
        rv.setAdapter(searchAdapter);

        filter = (FloatingActionButton)findViewById(R.id.search_result_filter) ;
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent filterIntent = new Intent(SearchResultActivity.this, FilterActivity.class);
                startActivity(filterIntent);
            }
        });

    }

    public class SearchAdapter
            extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

        private ArrayList<LKShopListObject> mResult;

        private Animation slideInAnimation;
        public SearchAdapter(ArrayList<LKShopListObject> resources) {
            mResult = resources;
            //slideInAnimation = AnimationUtils.loadAnimation(SearchResultActivity.this, android.R.anim.slide_in_left);
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            public View mView;
            public LinearLayout dim;

            public ImageView mShopMainImage;
            public TextView mFilterTag;
            public ImageView mShopCircleImage;
            public TextView mShopName;
            public TextView mShopLocation;
            public TextView mShopRating;
            public TextView mShopPrice;
            public ImageView mLikes;
            public ImageView mShare;


            public ViewHolder(View view) {
                super(view);
                mView = view;
                dim = (LinearLayout)view.findViewById(R.id.result_dim);

                mFilterTag = (TextView) view.findViewById(R.id.filtered_text_in_result);
                mShopMainImage = (ImageView) view.findViewById(R.id.result_main_image);
                mShopCircleImage = (ImageView) view.findViewById(R.id.result_circle_image);
                mShopName = (TextView) view.findViewById(R.id.result_name_text);
                mShopLocation = (TextView) view.findViewById(R.id.result_location_text);
                mShopRating = (TextView) view.findViewById(R.id.result_rating_text);
                mShopPrice = (TextView) view.findViewById(R.id.result_price_text);
                mLikes = (ImageView) view.findViewById(R.id.favorite_item_icon_in_result);
                mShare = (ImageView) view.findViewById(R.id.share_item_icon_in_result);
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
            int p = position;
            holder.dim.setAlpha(0.9f);

            final LKShopListObject shopInfo = mResult.get(p);
            holder.mFilterTag.setText("#" + shopInfo.activityName);
            holder.mShopName.setText(shopInfo.shopName);
            holder.mShopLocation.setText(shopInfo.shopAddress2 + " " + shopInfo.shopAddress1);
            holder.mShopPrice.setText("$" + shopInfo.price);
            holder.mShopRating.setText(String.valueOf(shopInfo.score));
            Glide.with(LKApplication.getLKApplication())
                    .load(shopInfo.shopImages)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .animate(android.R.anim.slide_in_left)
                    //.override(360, 280)
                    .into(holder.mShopMainImage);
            holder.mShopMainImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ShopDetailActivity.class);
                    intent.putExtra("shopId", 2); // shopInfo.shopId
                    startActivity(intent);
                }
            });

            Glide.with(LKApplication.getLKApplication())
                    .load(shopInfo.shopIcon)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .animate(android.R.anim.slide_in_left)
                    .override(40, 40)
                    .into(holder.mShopCircleImage);

            if (shopInfo.likes) {
                holder.mLikes.setImageResource(R.drawable.btn_heart_press);
                holder.mLikes.setSelected(true);
            }else{
                holder.mLikes.setImageResource(R.drawable.btn_heart_unpress);
                holder.mLikes.setSelected(false);
            }
            holder.mLikes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("heart in home","click");
                    final FavorObject favorObject = new FavorObject();
                    favorObject.setShopId(shopInfo.shopId);
                    favorObject.setUserId(1);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            final String result = OkHttpAPIHelperHandler.favorJSONInsert(favorObject);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.e("heart",result);
                                    if(result.equals("success")) {
                                        if(holder.mLikes.isSelected()) {
                                            holder.mLikes.setImageResource(R.drawable.btn_heart_unpress);
                                            holder.mLikes.setSelected(false);
                                        }else{
                                            holder.mLikes.setImageResource(R.drawable.btn_heart_press);
                                            holder.mLikes.setSelected(true);
                                        }
                                    }
                                    /*FragmentTransaction ft = getFragmentManager().beginTransaction();
                                    ft.detach(TabFragment2.tabFragment2)
                                            .attach(TabFragment2.tabFragment2)
                                            .commit();*/
                                            /*.commitAllowingStateLoss();*/

                                    for(int i=0;i<mResult.size();i++){
                                        if(mResult.get(i).shopName
                                                .equals(holder.mShopName.getText().toString())){
                                            if(mResult.get(i).likes==true){
                                                mResult.get(i).likes=false;
                                            }else{
                                                mResult.get(i).likes=true;
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }).start();

                }
            });
            holder.mShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendShare();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mResult.size();
        }

        public void addAll(ArrayList<LKShopListObject> objects) {
            this.mResult=objects;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("onStart()","onStart()");
        Log.i("onStart()",searchObject.getDate());
        Log.i("onStart()",searchObject.getLocation());
        if(flag == false) {
            new AsyncSearchInsert().execute(searchObject);
            flag=true;
        }
    }



    static ArrayList<String> tagList = null;

    public class AsyncSearchInsert extends AsyncTask<SearchObject , Integer, ArrayList<LKShopListObject>> {

        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("onPreExecute()","onPreExecute()");
            dialog = ProgressDialog.show(SearchResultActivity.this, "", "Loading...", true);
        }
        @Override
        protected ArrayList<LKShopListObject> doInBackground(SearchObject... searchObjects) {
            Log.i("doInBackground()","doInBackground()");
            return OkHttpAPIHelperHandler.searchJSONInsert(searchObjects);
        }
        @Override
        protected void onPostExecute(ArrayList<LKShopListObject> result) {
            Log.i("onPostExecute()","onPostExecute()");
            dialog.dismiss();
            //Log.i("onPostExecute()","onPostExecute()" + result.size());
            if (result != null && result.size() > 0) {
                searchAdapter.addAll(result);
                searchAdapter.notifyDataSetChanged();
                resultsCountTextView.setText(result.size() + " Results");
                tagList = new ArrayList<>(); tagList.clear();
                for (int i = 0; i < result.size(); i++) {
                    String tagName = result.get(i).activityName;
                    if (tagList.isEmpty()) {
                        tagList.add(tagName);
                    } else {
                        if (!tagList.contains(tagName)) {
                            tagList.add(tagName);
                        }
                    }
                }
                switch (tagList.size()) {
                    case 1:
                        setResultTagOne(tagList);
                        break;
                    case 2:
                        setResultTagTwo(tagList);
                        break;
                    case 3:
                        setResultTagThree(tagList);
                        break;
                    case 4:
                        setResultTagFour(tagList);
                        break;
                }
            }else {
                resultsCountTextView.setText("There is No Results");
            }
        }
    }
    LinearLayout datelayout;
    LinearLayout guestlayout;
    LinearLayout locationlayout;

    TextView date;
    TextView guest;
    TextView location;

    DatePicker datePicker;

    ImageView subAdult;
    TextView currentAdult;
    ImageView addAdult;

    ImageView subChildren;
    TextView currentChildren;
    ImageView addChildren;

    int adult = 1;
    int children = 0;
    String dateString=null;

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
                                dateString = year + "-" + month + "-" + day;
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
                    } else {
                        Log.i("검색test", "true = > false");
                        searchView.setVisibility(View.GONE);
                    }
                }
                return true;
            case R.id.action_ticket:
                Intent intent = new Intent(getApplicationContext(), TicketActivity.class);
                startActivity(intent);
                return true;
            case android.R.id.home:
                searchView.setVisibility(View.GONE);
                //toolbar.setTitle(tabs[0]);
                itemSearch.setVisible(true);
                itemTicket.setVisible(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public static void setResultTagOne(ArrayList<String> tagResult) {
        filterTag1.setText("#"+tagResult.get(0));
    }
    public static void setResultTagTwo(ArrayList<String> tagResult) {
        filterTag1.setText("#"+tagResult.get(0));
        filterTag2.setText("#"+tagResult.get(1));
    }
    public static void setResultTagThree(ArrayList<String> tagResult) {
        filterTag1.setText("#"+tagResult.get(0));
        filterTag2.setText("#"+tagResult.get(1));
        filterTag3.setText("#"+tagResult.get(2));
    }
    public static void setResultTagFour(ArrayList<String> tagResult) {
        filterTag1.setText("#"+tagResult.get(0));
        filterTag2.setText("#"+tagResult.get(1));
        filterTag3.setText("#"+tagResult.get(2));
        filterTag4.setText("#"+tagResult.get(3));
    }

    @Override
    public void onBackPressed() {
        if (searchView.getVisibility()==View.VISIBLE) {
            searchView.setVisibility(View.GONE);
            //toolbar.setTitle(tabs[0]);
            itemSearch.setVisible(true);
            itemTicket.setVisible(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        }else{
            super.onBackPressed();
        }
    }

    private void sendShare() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");

        List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(intent, 0);
        if (resInfo.isEmpty()) {
            return;
        }

        List<Intent> shareIntentList = new ArrayList<Intent>();

        for (ResolveInfo info : resInfo) {
            Intent shareIntent = (Intent) intent.clone();

            if (info.activityInfo.packageName.toLowerCase().equals("com.facebook.katana")) {
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "http:/leisurekr.com");
            } else if(info.activityInfo.packageName.toLowerCase().equals("com.kakao.talk")) {
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "http:/leisurekr.com");
            }else{
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "http:/leisurekr.com");
            }
            shareIntent.setPackage(info.activityInfo.packageName);
            shareIntentList.add(shareIntent);
        }

        Intent chooserIntent = Intent.createChooser(shareIntentList.remove(0), "select");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, shareIntentList.toArray(new Parcelable[]{}));
        startActivity(chooserIntent);
    }
}
