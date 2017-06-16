package com.leisurekr.leisuresportskorea.ticket;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.leisurekr.leisuresportskorea.R;
import com.leisurekr.leisuresportskorea.okhttp.OkHttpAPIHelperHandler;

import java.util.ArrayList;

public class TicketActivity extends AppCompatActivity {

    ViewPager viewPager;
    ArrayList<TicketObject> arrayList;
    Toolbar toolbar;
    TicketPagerAdapter adapter;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_activity);

        toolbar = (Toolbar) findViewById(R.id.ticket_toolbar);
        toolbar.setTitle("Mobile Ticket");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        arrayList = new ArrayList<>();

        viewPager = (ViewPager) findViewById(R.id.ticket_viewpager);
        fm = getSupportFragmentManager();
        adapter = new TicketPagerAdapter(fm,arrayList);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new AsyncTicketJSONList().execute();
    }

    public class AsyncTicketJSONList
            extends AsyncTask<String, Integer,  ArrayList<TicketObject>> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(TicketActivity.this, "", "Data Loading...", true);
        }

        @Override
        protected  ArrayList<TicketObject> doInBackground(String... params) {
            return OkHttpAPIHelperHandler.ticketJSONAllSelect();
        }

        @Override
        protected void onPostExecute( ArrayList<TicketObject> result) {
            dialog.dismiss();

            adapter.count=0;
            adapter.addAll(result);
        }
    }


    public class TicketPagerAdapter extends FragmentPagerAdapter {

        ArrayList<TicketObject> arrayList;

        public void addAll(ArrayList<TicketObject> arrayList){
            this.arrayList = arrayList;
            notifyDataSetChanged();
        }

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
        int count=0;
        @Override
        public int getItemPosition(Object object) {
            Log.e("ticket","getItemPosition"+count);
            ((TicketItemFragment)object).setTicket(arrayList.get(count));
            count++;
            return POSITION_NONE;
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
