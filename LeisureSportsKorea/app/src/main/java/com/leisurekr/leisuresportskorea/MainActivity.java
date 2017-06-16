package com.leisurekr.leisuresportskorea;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.leisurekr.leisuresportskorea.interfaces.ShopListSetListener;
import com.leisurekr.leisuresportskorea.shop.FilterActivity;
import com.leisurekr.leisuresportskorea.shop.MapActivity;
import com.leisurekr.leisuresportskorea.shop_detail.LKShopListObject;
import com.leisurekr.leisuresportskorea.ticket.TicketActivity;

import java.util.ArrayList;

import static com.leisurekr.leisuresportskorea.R.id.action_search;


public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, ShopListSetListener {

    ArrayList<LKShopListObject> objectsFromShopList;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Toolbar toolbar;

    private View searchView;
    boolean flag = false;
    Menu topMenu;

    MenuItem itemSearch;
    MenuItem itemTicket;

    String[] tabs = {"Leisure Korea", "Shop", "My Page"};

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
                        itemSearch = topMenu.findItem(R.id.action_search);
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

    static final int MAP_REQUEST = 1;
    static final int FILTER_REQUEST = 2;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1, fab2;
    private TextView textViewForFab1, textViewForFab2;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    Intent mapIntent;
    Intent filterIntent;

    LinearLayout datelayout;
    LinearLayout guestlayout;
    LinearLayout locationlayout;

    TextView date;
    TextView guest;
    TextView location;

    FloatingActionButton searchBtn;

    ImageView tabHome;
    ImageView tabShop;
    ImageView tabMypage;

    int callPopupActivityCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.search_view);
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
                    Toast.makeText(MainActivity.this, "Please select Date of use"
                            , Toast.LENGTH_SHORT).show();
                }else if((adult == 0 && children == 0)||guest.getText().toString()
                        .equals("No. of Guests")){
                    Toast.makeText(MainActivity.this, "Please select Number of Guests"
                            , Toast.LENGTH_SHORT).show();
                }else if(location.getText().toString()==null||location.getText().toString()
                        .equals("Location")){
                    Toast.makeText(MainActivity.this, "Please select Location"
                            , Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(MainActivity.this, SearchResultActivity.class);
                    intent.putExtra("date", date.getText().toString());
                    intent.putExtra("dateString", dateString);
                    intent.putExtra("guest", guest.getText().toString());
                    intent.putExtra("adult", adult);
                    intent.putExtra("children", children);
                    intent.putExtra("location", location.getText().toString());
                    startActivity(intent);
                    if (searchView.getVisibility()==View.VISIBLE) {
                        searchView.setVisibility(View.GONE);
                        //toolbar.setTitle(tabs[0]);
                        itemSearch.setVisible(true);
                        itemTicket.setVisible(true);
                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    }
                }
            }
        });
        //searchButton = (FloatingActionButton) findViewById(R.id.search_actionbtn);

        // Floating Action Button
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        textViewForFab1 = (TextView) findViewById(R.id.text_for_fab1);
        textViewForFab2 = (TextView) findViewById(R.id.text_for_fab2);

        // FAB Animation Setting
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);

        // Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Tab Layout
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);

        View home = getLayoutInflater().inflate(R.layout.tap_homeimage_view, null);
        tabHome = (ImageView) home.findViewById(R.id.tab_homeimage);
        View shop = getLayoutInflater().inflate(R.layout.tap_shopimage_view, null);
        tabShop = (ImageView) shop.findViewById(R.id.tab_shopimage);
        View maPage = getLayoutInflater().inflate(R.layout.tap_mypageimage_view, null);
        tabMypage = (ImageView) maPage.findViewById(R.id.tab_mypageimage);

        tabLayout.addTab(tabLayout.newTab().setCustomView(home), true);
        tabLayout.addTab(tabLayout.newTab().setCustomView(shop));
        tabLayout.addTab(tabLayout.newTab().setCustomView(maPage));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // View Pager
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Adapter For View Pager
        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // FAB Listener
        fab.hide();
        textViewForFab1.setVisibility(View.INVISIBLE);
        textViewForFab2.setVisibility(View.INVISIBLE);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapIntent = new Intent(MainActivity.this, MapActivity.class);
                // Fragment2로 부터 Shop List 객체 가져와서 Map Activity로 값 전달.
                mapIntent.putParcelableArrayListExtra("shopInfoList", objectsFromShopList);
                startActivityForResult(mapIntent, MAP_REQUEST);
                animateFAB();

            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : HHere, insert New Activity for Filter
                filterIntent = new Intent(MainActivity.this, FilterActivity.class);
                startActivity(filterIntent);
                animateFAB();

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();
                switch (id) {
                    case R.id.fab: {
                        animateFAB();
                        break;
                    }
                }
            }
        });

        // Set Tab Selected Listener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabPosition = tab.getPosition();
                toolbar.setTitle(tabs[tabPosition]);
                switch (tabPosition) {
                    case 0: {
                        fab.hide();
                        if (tabHome != null)
                            tabHome.setImageResource(R.drawable.ic_home_press);
                        if (isFabOpen == true) {
                            animateFAB();
                        }
                        break;
                    }
                    case 1: {
                        fab.show();
                        if (tabShop != null)
                            tabShop.setImageResource(R.drawable.ic_shop_press);
                        break;
                    }
                    case 2: {
                        fab.hide();
                        if (tabMypage != null)
                            tabMypage.setImageResource(R.drawable.ic_mypage_press);
                        if (isFabOpen == true) {
                            animateFAB();
                        }
                        break;
                    }
                    default: {
                        break;
                    }
                }
                viewPager.setCurrentItem(tabPosition);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int tabPosition = tab.getPosition();

                switch (tabPosition) {
                    case 0:
                        if (tabHome != null)
                            tabHome.setImageResource(R.drawable.ic_home_unpress);
                        break;
                    case 1:
                        if (tabShop != null)
                            tabShop.setImageResource(R.drawable.ic_shop_unpress);
                        break;
                    case 2:
                        if (tabMypage != null)
                            tabMypage.setImageResource(R.drawable.ic_mypage_unpress);
                        break;
                }

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            Transition exitTrans = new Explode(); // Fade(), Slide()

            Transition reenterTrans = new Explode(); // Fade(), Slide()


        }

        if (callPopupActivityCount == 0) {
            Intent intent = new Intent(getApplicationContext(), PreInterestsActivity.class);
            startActivity(intent);
            callPopupActivityCount++;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPermission();
    }

    private final int MY_PERMISSION_REQUEST_LOCATION = 100;

    private void checkPermission() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    // Explain to the user why we need to write the permission.
                    Toast.makeText(this, "Shop Info. for Location", Toast.LENGTH_SHORT).show();
                }
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSION_REQUEST_LOCATION);
            } else {
                // always granted
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 사용자가 퍼미션을 수락했을 경우...
                    // No Action
                } else {
                    // 사용자가 퍼미션을 거절했을 경우...
                    // SnackBar 추가하여 App 사용에 제한을 알려줌.
                    /*RelativeLayout layoutRoot = (RelativeLayout) findViewById(R.id.layout_root);
                    Snackbar sb = Snackbar.make(layoutRoot,
                            "App 사용에 제한이 있을 수 있습니다.", Snackbar.LENGTH_LONG);
                    sb.setAction("Re-Check", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            checkPermission();
                        }
                    });
                    sb.show();*/
                }
                break;
        }
    }

    public void animateFAB() {
        if (isFabOpen) {
            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            textViewForFab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            textViewForFab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
        } else {
            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            textViewForFab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            textViewForFab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
        }
    }

    LinearLayout title;
    LinearLayout saveLayout;
    Button saveBtn;
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
                view = View.inflate(MainActivity.this, R.layout.dialog_calender, null);

                datePicker = (DatePicker) view.findViewById(R.id.calender_pickerdate);
                datePicker.setDrawingCacheBackgroundColor(Color.RED);

                new AlertDialog.Builder(MainActivity.this)
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
                view = View.inflate(MainActivity.this, R.layout.dialog_guests, null);


                subAdult = (ImageView) view.findViewById(R.id.dialog_subadult);
                currentAdult = (TextView) view.findViewById(R.id.dialog_currentadult);
                addAdult = (ImageView) view.findViewById(R.id.dialog_addadult);
                subChildren = (ImageView) view.findViewById(R.id.dialog_subchildren);
                currentChildren = (TextView) view.findViewById(R.id.dialog_currentchildren);
                addChildren = (ImageView) view.findViewById(R.id.dialog_addchildren);

                currentAdult.setText("Adults " + adult);
                currentChildren.setText("Children " + children);

                SearchClickLisenter searchClickLisenter = new SearchClickLisenter();
                subAdult.setOnClickListener(searchClickLisenter);
                addAdult.setOnClickListener(searchClickLisenter);
                subChildren.setOnClickListener(searchClickLisenter);
                addChildren.setOnClickListener(searchClickLisenter);

                new AlertDialog.Builder(MainActivity.this)
                        .setView(view)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (adult != 0 && children != 0)
                                    guest.setText(currentAdult.getText().toString()
                                            + "   " + currentChildren.getText().toString());
                                else if (adult != 0)
                                    guest.setText(currentAdult.getText().toString());
                                else if (children != 0)
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
                view = View.inflate(MainActivity.this, R.layout.dialog_location, null);

                radioGroup = (RadioGroup) view.findViewById(R.id.dialog_group);
                if (!location.getText().toString().equals("Location")) {
                    RadioButton rb = (RadioButton) view.findViewById(selectedId);
                    rb.setChecked(true);
                } else {
                    RadioButton rb = (RadioButton) view.findViewById(R.id.dialog_yonsangu);
                    rb.setChecked(true);
                }
                new AlertDialog.Builder(MainActivity.this)
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

    @Override
    public void shopListSetData(ArrayList<LKShopListObject> data) {
        objectsFromShopList = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            LKShopListObject obj = new LKShopListObject();

            obj.activityName = data.get(i).activityName;
            obj.price = data.get(i).price;
            obj.shopId = data.get(i).shopId;
            obj.shopIcon = data.get(i).shopIcon;
            obj.shopName = data.get(i).shopName;
            obj.shopAddress1 = data.get(i).shopAddress1;
            obj.shopAddress2 = data.get(i).shopAddress2;
            obj.shopAddress3 = data.get(i).shopAddress3;
            obj.latitude = data.get(i).latitude;
            obj.longitude = data.get(i).longitude;
            obj.shopImages = data.get(i).shopImages;
            obj.likes = data.get(i).likes;
            obj.score = data.get(i).score;

            objectsFromShopList.add(obj);
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

    private final long FINSH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (searchView.getVisibility()==View.VISIBLE) {
            searchView.setVisibility(View.GONE);
            //toolbar.setTitle(tabs[0]);
            itemSearch.setVisible(true);
            itemTicket.setVisible(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        }else{
            if (0 <= intervalTime && FINSH_INTERVAL_TIME >= intervalTime) {
                super.onBackPressed();
            } else {
                backPressedTime = tempTime;
                Toast.makeText(getApplicationContext(), "'뒤로'버튼을한번더누르시면종료됩니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

