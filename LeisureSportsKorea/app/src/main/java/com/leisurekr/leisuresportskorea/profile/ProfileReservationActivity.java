package com.leisurekr.leisuresportskorea.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leisurekr.leisuresportskorea.R;

import java.util.ArrayList;

public class ProfileReservationActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ReservationObject> arrayList;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_reservation_activity);

        toolbar = (Toolbar) findViewById(R.id.profile_reservation_toolbar);
        toolbar.setTitle("Reservation List");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ReservationObject object1 = new ReservationObject();
        object1.setData(R.drawable.list_image,R.drawable.activityimagelight,"Water Ski","River City","Water Ski Beginner",
                "Lesson Package",50,"Approved","May 22, 2017","17:00",2,0,"Apgujeong-dong","Gangnam-gu, Seoul","379-1");

        ReservationObject object2 = new ReservationObject();
        object2.setData(R.drawable.list_image,R.drawable.activityimagelight,"Water Ski","River City","Water Ski Beginner",
                "Lesson Package",50,"Finished","May 22, 2017","15:00",2,0,"Apgujeong-dong","Gangnam-gu, Seoul","379-1");

        ReservationObject object3 = new ReservationObject();
        object3.setData(R.drawable.list_image,R.drawable.activityimagelight,"Water Ski","River City","Water Ski Beginner",
                "Lesson Package",50,"Canceled","May 22, 2017","11:00",2,0,"Apgujeong-dong","Gangnam-gu, Seoul","379-1");

        arrayList = new ArrayList<>();
        arrayList.add(object1);
        arrayList.add(object2);
        arrayList.add(object3);

        recyclerView = (RecyclerView) findViewById(R.id.profile_reservation_recycler);
        ReservationAdapter adapter = new ReservationAdapter(arrayList);

        recyclerView.setLayoutManager(new LinearLayoutManager
                (ProfileReservationActivity.this,LinearLayout.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }


    public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {

        ArrayList<ReservationObject> arrayList;
        int adult;
        int children;

        public ReservationAdapter(){
            arrayList = new ArrayList<>();
        }
        public ReservationAdapter(ArrayList<ReservationObject> arrayList){
            this.arrayList = arrayList;
        }

        public void addAll(ArrayList<ReservationObject> arrayList){
            this.arrayList = arrayList;
            notifyDataSetChanged();
        }

        public void add(ReservationObject object){
            arrayList.add(object);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.profile_reservation_recycleritem,parent,false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final ReservationObject object = arrayList.get(position);
            adult = object.getAdult();
            children = object.getChildren();
            String people=null;

            holder.rootLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(ProfileReservationActivity.this
                            ,ReservationDetailActivity.class);
                    intent.putExtra("reservation",object);
                    startActivity(intent);
                }
            });
            holder.leftBackImage.setBackgroundResource(object.getLeftBackImage());
            switch (object.getProgress()){
                case "Approved":
                    holder.rightBackImage.setBackgroundResource(R.drawable.approved);
                    break;
                case "Finished":
                    holder.rightBackImage.setBackgroundResource(R.drawable.finished1);
                    break;
                case "Canceled":
                    holder.rightBackImage.setBackgroundResource(R.drawable.canceled);
                    break;
            }
            holder.textView1.setText(object.getText1()+"'s");
            holder.textView2.setText(object.getText2());
            holder.textView3.setText(object.getText3());
            holder.price.setText("$"+Integer.toString(object.getPrice()));

            holder.progress.setText(object.getProgress());
            holder.date.setText(object.getDate());
            if (adult != 0 && children != 0) {
                people = "Adult " + Integer.toString(adult
                ) + ",  Children " + Integer.toString(children);
            } else if (adult == 0 && children != 0) {
                people = "Children " + Integer.toString(children);
            } else if (children == 0 && adult != 0) {
                people = "Adult " + Integer.toString(adult);
            }
            holder.people.setText(people);
            holder.location1.setText(object.getLocation1());
            holder.location2.setText(object.getLocation2());

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            LinearLayout rootLayout;
            LinearLayout rightBackImage;
            RelativeLayout leftBackImage;

            TextView textView1;
            TextView textView2;
            TextView textView3;
            TextView price;


            TextView progress;
            TextView date;
            TextView people;
            TextView location1;
            TextView location2;

            public ViewHolder(View itemView) {
                super(itemView);
                rootLayout = (LinearLayout)itemView.findViewById(R.id.reservation_recycler_rootlayout);
                rightBackImage = (LinearLayout) itemView.findViewById(R.id.reservation_recycler_rightbackimage);
                leftBackImage = (RelativeLayout) itemView.findViewById(R.id.reservation_recycler_leftbackimage);

                textView1 = (TextView) itemView.findViewById(R.id.reservation_recycler_text1);
                textView2 = (TextView) itemView.findViewById(R.id.reservation_recycler_text2);
                textView3 = (TextView) itemView.findViewById(R.id.reservation_recycler_text3);
                price = (TextView) itemView.findViewById(R.id.reservation_recycler_price);

                progress = (TextView) itemView.findViewById(R.id.reservation_recycler_progress);
                date = (TextView) itemView.findViewById(R.id.reservation_recycler_date);
                people = (TextView) itemView.findViewById(R.id.reservation_recycler_people);
                location1 = (TextView) itemView.findViewById(R.id.reservation_recycler_location1);
                location2 = (TextView) itemView.findViewById(R.id.reservation_recycler_location2);

            }
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