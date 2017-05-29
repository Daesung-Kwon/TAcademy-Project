package com.leisurekr.leisuresportskorea.profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leisurekr.leisuresportskorea.R;

import java.util.ArrayList;

public class ProfileCartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<CartObject> arrayList;
    TextView totalPrice;
    Button checkOutBtn;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_cart_activity);

        toolbar = (Toolbar) findViewById(R.id.profile_cart_toolbar);
        toolbar.setTitle("Cart");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        totalPrice = (TextView) findViewById(R.id.profile_cart_totalprice);
        checkOutBtn = (Button) findViewById(R.id.profile_cart_checkout);

        totalPrice.setText("Order Subtotal $" + Integer.toString(0)
                + ", " + Integer.toString(0) + " item(s) in your cart");
        checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //예약하는 페이지로 이동
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.profile_cart_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(ProfileCartActivity.this, LinearLayout.VERTICAL, false));

        arrayList = new ArrayList<>();
        CartObject object = new CartObject();
        object.setData("River City", R.drawable.girls_generation_tifany
                , "River City's \nWater Ski Beginner \nLesson Package"
                , "Date   " + "May 22, 2017", "Time    " + "14:00"
                , "People    " + "Adoult 1", 50);
        CartObject object1 = new CartObject();
        object1.setData("River City", R.drawable.girls_generation_tifany
                , "River City's \nWater Ski Beginner \nLesson Package"
                , "Date   " + "May 22, 2017", "Time    " + "14:00"
                , "People    " + "Adoult 1", 50);

        arrayList.add(object);
        arrayList.add(object1);

        CartAdapter cartAdapter = new CartAdapter(arrayList);
        recyclerView.setAdapter(cartAdapter);

    }

    public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

        ArrayList<CartObject> arrayList;
        boolean isExpandable = false;
        boolean isEditable = false;
        int total = 0;
        int checkcount = 0;

        public CartAdapter(ArrayList<CartObject> arrayList) {
            this.arrayList = arrayList;
        }

        public void addAll(ArrayList<CartObject> arrayList) {
            this.arrayList = arrayList;
            notifyDataSetChanged();
        }

        public void add(CartObject cartObject) {
            arrayList.add(cartObject);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.profile_cart_recyclerviewitem, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final CartObject cartObject = arrayList.get(position);
            holder.shopNmae.setText(cartObject.getShopname());
            holder.activityImage.setImageResource(cartObject.getActivityImage());
            holder.activityName.setText(cartObject.getActivityName());
            holder.date.setText(cartObject.getDate());
            holder.time.setText(cartObject.getTime());
            holder.people.setText(cartObject.getPeople());
            holder.price.setText("$" + Integer.toString(cartObject.getPrice()));
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        //총 가격 상승
                        if (total >= 0)
                            checkcount++;
                        total = total + cartObject.getPrice();
                    } else {
                        //총 가격 하락
                        checkcount--;
                        total = total - cartObject.getPrice();
                        if (total <= 0) {
                            total = 0;
                            checkcount=0;
                        }
                    }
                    totalPrice.setText("Order Subtotal $" + Integer.toString(total)
                            + ", " + Integer.toString(checkcount) + " item(s) in your cart");
                }
            });
            holder.expander.setImageResource(R.drawable.ic_logout);
            holder.expander.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isExpandable) {
                        holder.expandedLayout.setVisibility(View.VISIBLE);
                        isExpandable = true;
                    } else {
                        holder.expandedLayout.setVisibility(View.GONE);
                        isExpandable = false;
                    }
                }
            });
            holder.editbtn.setBackgroundResource(R.drawable.ic_setting);
            holder.editbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isEditable) {
                        holder.edit.setVisibility(View.VISIBLE);
                        isEditable = true;
                    } else {
                        holder.edit.setVisibility(View.GONE);
                        isEditable = false;
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public CheckBox checkBox;
            public TextView shopNmae;
            public ImageView expander;
            public LinearLayout expandedLayout;
            public LinearLayout edit;
            public ImageView activityImage;
            public TextView activityName;
            public TextView date;
            public TextView time;
            public TextView people;
            public Button editbtn;
            public Button addAdultBtn;
            public TextView currentAdultText;
            public Button subAdultBtn;
            public Button addchildrenBtn;
            public TextView currentChildrenText;
            public Button subchildrenBtn;
            public TextView price;
            public Button cancleEditBtn;
            public Button okEditBtn;

            public ViewHolder(View itemView) {
                super(itemView);
                checkBox = (CheckBox) itemView.findViewById(R.id.profile_recycler_checkbox);
                shopNmae = (TextView) itemView.findViewById(R.id.profile_recycler_shopname);
                expander = (ImageView) itemView.findViewById(R.id.profile_recycler_expander);
                expandedLayout = (LinearLayout) itemView.findViewById(R.id.profile_recycler_expandedlayout);
                edit = (LinearLayout) itemView.findViewById(R.id.profile_recycler_editlayout);
                activityImage = (ImageView) itemView.findViewById(R.id.profile_recycler_activityimage);
                activityName = (TextView) itemView.findViewById(R.id.profile_recycler_activityname);
                date = (TextView) itemView.findViewById(R.id.profile_recycler_date);
                time = (TextView) itemView.findViewById(R.id.profile_recycler_time);
                people = (TextView) itemView.findViewById(R.id.profile_recycler_people);
                editbtn = (Button) itemView.findViewById(R.id.profile_recycler_editbtn);
                addAdultBtn = (Button) itemView.findViewById(R.id.profile_recycler_addadultbtn);
                currentAdultText = (TextView) itemView.findViewById(R.id.profile_recycler_curentadulttext);
                subAdultBtn = (Button) itemView.findViewById(R.id.profile_recycler_subadultbtn);
                addchildrenBtn = (Button) itemView.findViewById(R.id.profile_recycler_addchildrenbtn);
                currentChildrenText = (TextView) itemView.findViewById(R.id.profile_recycler_currentchildrenbtn);
                subchildrenBtn = (Button) itemView.findViewById(R.id.profile_recycler_subchildrenbtn);
                price = (TextView) itemView.findViewById(R.id.profile_recycler_price);
                cancleEditBtn = (Button) itemView.findViewById(R.id.profile_recycler_canceleditbtn);
                okEditBtn = (Button) itemView.findViewById(R.id.profile_recycler_okeditbtn);
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
