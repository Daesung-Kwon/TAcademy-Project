package com.leisurekr.leisuresportskorea.ticket;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.leisurekr.leisuresportskorea.R;

import java.util.ArrayList;

public class TicketActivity extends AppCompatActivity {

    ViewPager viewPager;
    ArrayList<TicketObject> arrayList;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_activity);

        toolbar = (Toolbar) findViewById(R.id.ticket_toolbar);
        toolbar.setTitle("Mobile Ticket");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        arrayList = new ArrayList<>();

        TicketObject object1 = new TicketObject();
        object1.setData(R.drawable.list_image,R.drawable.activityimagelight,"Water Ski","River City","Water Ski Beginner",
                "Lesson Package",50,"Approved","May 22, 2017","17:00",2,0,"Apgujeong-dong","Gangnam-gu, Seoul","379-1");

        TicketObject object2 = new TicketObject();
        object2.setData(R.drawable.list_image,R.drawable.activityimagelight,"Water Ski","River City","Water Ski Beginner",
                "Lesson Package",50,"Finished","May 22, 2017","15:00",2,0,"Apgujeong-dong","Gangnam-gu, Seoul","379-1");

        TicketObject object3 = new TicketObject();
        object3.setData(R.drawable.list_image,R.drawable.activityimagelight,"Water Ski","River City","Water Ski Beginner",
                "Lesson Package",50,"Canceled","May 22, 2017","11:00",2,0,"Apgujeong-dong","Gangnam-gu, Seoul","379-1");

        arrayList.add(object1);
        arrayList.add(object2);
        arrayList.add(object3);

        viewPager = (ViewPager) findViewById(R.id.ticket_viewpager);
        FragmentManager fm = getSupportFragmentManager();
        TicketPagerAdapter adapter = new TicketPagerAdapter(fm,arrayList);
        viewPager.setAdapter(adapter);
    }

    public class TicketPagerAdapter extends FragmentPagerAdapter {

        ArrayList<TicketObject> arrayList;

        public TicketPagerAdapter(FragmentManager fm, ArrayList<TicketObject> arrayList) {
            super(fm);
            this.arrayList = arrayList;
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Fragment getItem(int position) {
            TicketItemFragment ticketItemFragment = new TicketItemFragment();
            ticketItemFragment.setTicket(arrayList.get(position));
            return ticketItemFragment;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }
}
