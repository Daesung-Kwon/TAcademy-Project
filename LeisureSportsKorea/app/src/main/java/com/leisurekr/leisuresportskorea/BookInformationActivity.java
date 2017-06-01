package com.leisurekr.leisuresportskorea;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.leisurekr.leisuresportskorea.profile.CartObject;

import java.util.ArrayList;

public class BookInformationActivity extends AppCompatActivity {

    TextInputEditText nameEdit;
    TextInputEditText phoneEdit;
    TextInputEditText emailEdit;

    RecyclerView recyclerView;

    Button paypal;

    ArrayList<CartObject> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_information_activity);

        Intent intent = getIntent();
        arrayList = (ArrayList<CartObject>) intent.getSerializableExtra("check out");
        if(arrayList == null){
            Toast.makeText(BookInformationActivity.this,"2",Toast.LENGTH_SHORT).show();
        }

        nameEdit = (TextInputEditText) findViewById(R.id.book_information_name);
        phoneEdit = (TextInputEditText) findViewById(R.id.book_information_phonenumber);
        emailEdit = (TextInputEditText) findViewById(R.id.book_information_email);

        recyclerView = (RecyclerView) findViewById(R.id.book_information_recyclerview);

        InformationAdapter adapter = new InformationAdapter(arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager
                (BookInformationActivity.this, LinearLayout.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        paypal = (Button) findViewById(R.id.book_information_paypal);
    }

    class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.ViewHolder> {

        ArrayList<CartObject> arrayList;
        int adult;
        int children;

        public InformationAdapter(ArrayList<CartObject> arrayList) {
            this.arrayList = arrayList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.book_information_recycleritem,parent,false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            CartObject object = arrayList.get(position);
            if(object != null) {
                holder.activityImage.setBackgroundResource(object.getActivityImage());
                holder.text1.setText(object.getShopName() + "'s");
                holder.text2.setText(object.getText1());
                holder.text3.setText(object.getText2());
                holder.date.setText((object.getDate()));
                holder.time.setText(object.getTime());
                adult = object.getAdult();
                children = object.getChildren();
                String people = "";
                if (adult != 0 && children != 0) {
                    people = "Adult " + Integer.toString(adult
                    ) + ",  Children " + Integer.toString(children);
                } else if (adult == 0 && children != 0) {
                    people = "Children " + Integer.toString(children);
                } else if (children == 0 && adult != 0) {
                    people = "Adult " + Integer.toString(adult);
                }
                holder.people.setText(people);
            }else{
                Log.e("1","1111111111111111");
            }
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            LinearLayout activityImage;
            TextView text1;
            TextView text2;
            TextView text3;

            TextView date;
            TextView time;
            TextView people;

            public ViewHolder(View itemView) {
                super(itemView);

                activityImage = (LinearLayout) itemView.findViewById(R.id.information_recycler_activityimage);
                text1 = (TextView) itemView.findViewById(R.id.information_recycler_text1);
                text2 = (TextView) itemView.findViewById(R.id.information_recycler_text2);
                text3 = (TextView) itemView.findViewById(R.id.information_recycler_text3);

                date = (TextView) itemView.findViewById(R.id.information_recycler_date);
                time = (TextView) itemView.findViewById(R.id.information_recycler_time);
                people = (TextView) itemView.findViewById(R.id.information_recycler_people);
            }
        }

    }
}
