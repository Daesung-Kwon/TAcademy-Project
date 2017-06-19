package com.leisurekr.leisuresportskorea;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.leisurekr.leisuresportskorea.profile.CartObject;
import com.leisurekr.leisuresportskorea.profile.ProgramObject;
import com.leisurekr.leisuresportskorea.profile.ShopObject;

import java.util.ArrayList;

public class BookInformationActivity extends AppCompatActivity {

    TextInputEditText nameEdit;
    TextInputEditText phoneEdit;
    TextInputEditText emailEdit;


    RecyclerView recyclerView;
    Button paypal;
    ArrayList<CartObject> arrayList;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_information_activity);

        toolbar = (Toolbar) findViewById(R.id.book_information_toolbar);
        toolbar.setTitle("Book Information");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        arrayList = (ArrayList<CartObject>) intent.getSerializableExtra("check out");
        if (arrayList == null) {
            Toast.makeText(BookInformationActivity.this, "2", Toast.LENGTH_SHORT).show();
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
        paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckoutObject [] checkoutObjects = new CheckoutObject[arrayList.size()];
                CheckoutObject checkoutObject;
                for(int i=0; i< arrayList.size();i++) {
                    checkoutObject = new CheckoutObject();
                    checkoutObject.setName(nameEdit.getText().toString());
                    checkoutObject.setPhoneNum(phoneEdit.getText().toString());
                    checkoutObject.setEmail(emailEdit.getText().toString());
                    checkoutObject.setDate(arrayList.get(i).getDate());
                    checkoutObject.setTime(arrayList.get(i).getTime());
                    checkoutObject.setAdult(arrayList.get(i).getAdult());
                    checkoutObject.setChild(arrayList.get(i).getChildren());
                    checkoutObject.setStatus(2);
                    checkoutObject.setPicked(arrayList.get(i).isPicked());
                    checkoutObject.setProgramId(arrayList.get(i).getProgramObject().getId());

                    checkoutObjects[i] = checkoutObject;
                }

                if(nameEdit.getText().toString().equals("")|| nameEdit.getText()==null){
                    Toast.makeText(BookInformationActivity.this,
                            "Please Input your name",Toast.LENGTH_SHORT).show();
                }else if(phoneEdit.getText().toString().equals("")||phoneEdit.getText()==null){
                    Toast.makeText(BookInformationActivity.this,
                            "Please Input your phoneNumber",Toast.LENGTH_SHORT).show();
                }else if(emailEdit.getText().toString().equals("") || emailEdit.getText()==null
                        ||emailEdit.isInEditMode()){
                    Toast.makeText(BookInformationActivity.this,
                            "Please Input your phoneNumber",Toast.LENGTH_SHORT).show();
                }else {
                    new AsyncCheckoutInsert(BookInformationActivity.this, 2).execute(checkoutObjects);
                }
            }
        });
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
                    .inflate(R.layout.book_information_recycleritem, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final CartObject object = arrayList.get(position);
            ProgramObject programObject = object.getProgramObject();
            ShopObject shopObject = programObject.getShopObject();
            if (object != null) {
                Glide.with(BookInformationActivity.this).load(shopObject.getImage())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(new ViewTarget<LinearLayout, GlideDrawable>(holder.activityImage) {
                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                holder.activityImage.setBackgroundDrawable(resource);
                            }
                        });
                holder.text1.setText(shopObject.getName() + "'s");
                holder.text2.setText(programObject.getActivityName());
                holder.text3.setText(programObject.getName().substring(programObject.getActivityName().length()));
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
                holder.isPickuped.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        object.setPicked(isChecked);
                    }
                });
            } else {
                Log.e("BookInformation", "cartObject = null");
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
            CheckBox isPickuped;

            public ViewHolder(View itemView) {
                super(itemView);

                activityImage = (LinearLayout) itemView.findViewById(R.id.information_recycler_activityimage);
                text1 = (TextView) itemView.findViewById(R.id.information_recycler_text1);
                text2 = (TextView) itemView.findViewById(R.id.information_recycler_text2);
                text3 = (TextView) itemView.findViewById(R.id.information_recycler_text3);

                date = (TextView) itemView.findViewById(R.id.information_recycler_date);
                time = (TextView) itemView.findViewById(R.id.information_recycler_time);
                people = (TextView) itemView.findViewById(R.id.information_recycler_people);
                isPickuped = (CheckBox) itemView.findViewById(R.id.information_recycler_pickup);
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
