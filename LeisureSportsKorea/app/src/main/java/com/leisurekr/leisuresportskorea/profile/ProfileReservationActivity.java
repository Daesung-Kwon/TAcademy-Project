package com.leisurekr.leisuresportskorea.profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.leisurekr.leisuresportskorea.R;
import com.leisurekr.leisuresportskorea.okhttp.OkHttpAPIHelperHandler;

import java.util.ArrayList;

public class ProfileReservationActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ReservationObject> arrayList;
    Toolbar toolbar;
    ReservationAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_reservation_activity);

        toolbar = (Toolbar) findViewById(R.id.profile_reservation_toolbar);
        toolbar.setTitle("Reservation List");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ReservationObject object1 = new ReservationObject();
        object1.setData(R.drawable.pic_waterski1, " ", " ", " ",
                " ", 0, " ", " ", " ", 0, 0, " ", " ", " ");

        arrayList = new ArrayList<>();
        arrayList.add(object1);

        recyclerView = (RecyclerView) findViewById(R.id.profile_reservation_recycler);
        adapter = new ReservationAdapter(arrayList);

        recyclerView.setLayoutManager(new LinearLayoutManager
                (ProfileReservationActivity.this, LinearLayout.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }


    public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {

        ArrayList<ReservationObject> arrayList;
        int adult;
        int children;
        ProgramObject programObject;
        ShopObject shopObject;

        public ReservationAdapter() {
            arrayList = new ArrayList<>();
        }

        public ReservationAdapter(ArrayList<ReservationObject> arrayList) {
            this.arrayList = arrayList;
        }

        public void addAll(ArrayList<ReservationObject> arrayList) {
            this.arrayList = arrayList;
            notifyDataSetChanged();
        }

        public void add(ReservationObject object) {
            arrayList.add(object);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.profile_reservation_recycleritem, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final ReservationObject object = arrayList.get(position);
            adult = object.getAdult();
            children = object.getChildren();
            String people = null;
            if(object.programObject!=null) {
                programObject = object.programObject;
                shopObject = programObject.shopObject;

                holder.textView1.setText(shopObject.name + "'s");
                holder.textView2.setText(programObject.activityName);
                holder.textView3.setText(programObject.name);
                Glide.with(ProfileReservationActivity.this).load(programObject.image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(new ViewTarget<LinearLayout, GlideDrawable>(holder.leftBackImage) {
                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                               holder.leftBackImage.setBackgroundDrawable(resource);

                            }
                        });

                if (position == 0)
                    holder.rootLayout.setPadding(0, 72, 0, 0);
                else
                    holder.rootLayout.setPadding(0, 0, 0, 0);
                holder.rootLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(ProfileReservationActivity.this
                                , ReservationDetailActivity.class);
                        intent.putExtra("reservation", object);
                        startActivity(intent);
                    }
                });
                holder.dim.setAlpha(0.3f);
                switch (object.getProgress()) {
                    case "Approved":
                        holder.rightBackImage.setBackgroundResource(R.drawable.pic_box_re_li_ap);
                        break;
                    case "Finished":
                        holder.rightBackImage.setBackgroundResource(R.drawable.pic_box_re_li_fi);
                        break;
                    case "Canceled":
                        holder.rightBackImage.setBackgroundResource(R.drawable.pic_box_re_li_ca);
                        break;
                }

                //holder.price.setText("$" + Integer.toString(object.getPrice()));
                holder.price.setText("$" + programObject.price);

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
                holder.location1.setText(shopObject.getLocation2()+",");
                holder.location2.setText(shopObject.getLocation1());

                //Glide.with(ProfileReservationActivity.this).load(shopObject.image).into(holder.leftBackImage);
                //holder.leftBackImage.setImageResource(object.getLeftBackImage());
            }else{
                holder.textView1.setText(object.getText1() + "'s");
                holder.textView2.setText(object.getText2());
                holder.textView3.setText(object.getText3());
                holder.leftBackImage.setBackgroundResource(R.drawable.pic_ti_2);
            }


        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            LinearLayout rootLayout;
            LinearLayout rightBackImage;
            LinearLayout leftBackImage;
            //ImageView leftBackImage;
            LinearLayout dim;
            LinearLayout border;

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
                rootLayout = (LinearLayout) itemView.findViewById(R.id.reservation_recycler_rootlayout);
                rightBackImage = (LinearLayout) itemView.findViewById(R.id.reservation_recycler_rightbackimage);
                leftBackImage = (LinearLayout) itemView.findViewById(R.id.reservation_recycler_leftbackimage);
                dim = (LinearLayout) itemView.findViewById(R.id.reservation_recycler_dim);
                border = (LinearLayout) itemView.findViewById(R.id.reservation_recycler_border);

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

    ArrayList<ReservationObject> reservationObject;

    @Override
    protected void onStart() {
        super.onStart();

        new AsyncReservationJSONList().execute();
    }

    public class AsyncReservationJSONList
            extends AsyncTask<String, Integer,  ArrayList<ReservationObject>> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(ProfileReservationActivity.this, "", "Data Loading...", true);
        }

        @Override
        protected  ArrayList<ReservationObject> doInBackground(String... params) {
            return OkHttpAPIHelperHandler.reservationJSONAllSelect();
        }

        @Override
        protected void onPostExecute( ArrayList<ReservationObject> result) {
            dialog.dismiss();
            reservationObject = result;
            adapter.addAll(reservationObject);
        }
    }
}
