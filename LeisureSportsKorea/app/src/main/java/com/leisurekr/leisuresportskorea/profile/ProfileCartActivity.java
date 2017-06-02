package com.leisurekr.leisuresportskorea.profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import android.widget.Toast;

import com.leisurekr.leisuresportskorea.BookInformationActivity;
import com.leisurekr.leisuresportskorea.R;

import java.util.ArrayList;

public class ProfileCartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<CartObject> arrayList;
    ArrayList<CartObject> sendList;
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


        recyclerView = (RecyclerView) findViewById(R.id.profile_cart_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(ProfileCartActivity.this, LinearLayout.VERTICAL, false));

        arrayList = new ArrayList<>();

        CartObject object = new CartObject();
        object.setData(R.drawable.activityimagelight,"River City"
                , "Water Ski Beginner", "Lesson Package"
                , "May 22, 2017", "14:00"
                , 1, 50, 0, 1, 50,0);

        CartObject object1 = new CartObject();
        object1.setData( R.drawable.activityimagelight
                , "River City","Water Ski Beginner ","Lesson Package"
                , "May 22, 2017", "14:00"
                , 1, 60, 1, 1, 61,0);

        CartObject object2 = new CartObject();
        object2.setData( R.drawable.activityimagelight
                , "River City","Water Ski Beginner ","Lesson Package"
                , "May 22, 2017","14:00"
                , 2, 60, 1, 1, 61,0);

        CartObject object3 = new CartObject();
        object3.setData( R.drawable.activityimagelight
                , "River City","Water Ski Beginner ","Lesson Package"
                , "May 22, 2017", "14:00"
                , 3, 60, 1, 1, 61,0);

        CartObject object4 = new CartObject();
        object4.setData( R.drawable.activityimagelight
                , "River City","Water Ski Beginner ","Lesson Package"
                , "May 22, 2017", "14:00"
                , 4, 60, 1, 1, 61,0);

        CartObject object5 = new CartObject();
        object5.setData( R.drawable.activityimagelight
                , "River City","Water Ski Beginner ","Lesson Package"
                , "May 22, 2017", "14:00"
                , 5, 60, 1, 1, 61,0);

        arrayList.add(object);
        arrayList.add(object1);
        arrayList.add(object2);
        arrayList.add(object3);
        arrayList.add(object4);
        arrayList.add(object5);

        final CartAdapter cartAdapter = new CartAdapter(arrayList);
        recyclerView.setAdapter(cartAdapter);

        checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //예약하는 페이지로 이동
                Intent intent = new Intent(ProfileCartActivity.this, BookInformationActivity.class);
                if(arrayList == null){
                    Toast.makeText(ProfileCartActivity.this,"1",Toast.LENGTH_SHORT).show();
                }
                intent.putExtra("check out",cartAdapter.checkedArray);
                startActivity(intent);
            }
        });

    }

    public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {


        ArrayList<CartObject> arrayList;
        ArrayList<CartObject> checkedArray;
        boolean isExpandable = false;
        boolean isEditable = false;
        int total = 0;
        int checkcount = 0;
        int adult = 0;
        int children = 0;
        int adultPrice = 0;
        int childrenPrice = 0;
        String people;


        public CartAdapter(ArrayList<CartObject> arrayList) {
            this.arrayList = arrayList;
            checkedArray = new ArrayList<>();
        }

        public void addAll(ArrayList<CartObject> arrayList) {
            this.arrayList = arrayList;
            notifyDataSetChanged();
        }

        public void add(CartObject cartObject) {
            arrayList.add(cartObject);
            notifyDataSetChanged();
        }

        public void remove(int position) {
            arrayList.remove(position);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.profile_cart_recycleritem, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final int p = position;
            final CartObject cartObject = arrayList.get(position);
            holder.shopNmae.setText(cartObject.getShopname());
            holder.activityImage.setBackgroundResource(cartObject.getActivityImage());
            //holder.activityImage.setImageResource(cartObject.getActivityImage());
            holder.text1.setText(cartObject.getShopName()+"'s");
            holder.text2.setText(cartObject.getText1());
            holder.text3.setText(cartObject.getText2());
            holder.date.setText(cartObject.getDate());
            holder.time.setText(cartObject.getTime());
            holder.price.setText("$" + Integer.toString(cartObject.getPrice()));
            adult = cartObject.getAdult();
            children = cartObject.getChildren();
            adultPrice = cartObject.getAdultPrice();
            childrenPrice = cartObject.getChildrenPrice();
            holder.currentAdultText.setText("Adult " + Integer.toString(adult));
            holder.currentChildrenText.setText("Children " + Integer.toString(children));
            holder.price.setText("$ " + ((adult * adultPrice) + (children * childrenPrice)));

            if (adult != 0 && children != 0) {
                people = "   Adult " + Integer.toString(adult
                ) + ",  Children " + Integer.toString(children);
            } else if (adult == 0 && children != 0) {
                people = "   Children " + Integer.toString(children);
            } else if (children == 0 && adult != 0) {
                people = "   Adult " + Integer.toString(adult);
            }
            holder.people.setText(people);

            //체크 박스를 클릭하면 해당 액티비티의 가격을 총가격에 더하거나 뺀다다
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        //총 가격 상승
                        if (total >= 0)
                            checkcount++;
                        checkedArray.add(cartObject);
                        total = total + cartObject.getPrice();
                    } else {
                        //총 가격 하락
                        checkcount--;
                        checkedArray.remove(cartObject);
                        total = total - cartObject.getPrice();
                        if (total <= 0) {
                            total = 0;
                            checkcount = 0;
                        }
                    }
                    totalPrice.setText("Order Subtotal $" + Integer.toString(total)
                            + ", " + Integer.toString(checkcount) + " item(s) in your cart");
                }
            });

            holder.delete.setImageResource(R.drawable.ic_logout);//휴지통으로 수정해야함

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

            holder.addAdultBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adult = cartObject.getAdult();
                    children = cartObject.getChildren();
                    if (adult >= 0) {
                        adult++;
                        holder.currentAdultText.setText("Adult " + Integer.toString(adult));
                        if (adult != 0 && children != 0) {
                            people = "   Adult " + Integer.toString(adult
                            ) + ",  Children " + Integer.toString(children);
                        } else if (adult == 0 && children != 0) {
                            people = "   Children " + Integer.toString(children);
                        } else if (children == 0 && adult != 0) {
                            people = "   Adult " + Integer.toString(adult);
                        }
                        holder.people.setText(people);
                    }
                    cartObject.setAdult(adult);
                    holder.price.setText("$ " + ((adult * adultPrice) + (children * childrenPrice)));
                }
            });

            holder.subAdultBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adult = cartObject.getAdult();
                    children = cartObject.getChildren();
                    if (adult > 0) {
                        adult--;
                        holder.currentAdultText.setText("Adult " + Integer.toString(adult));
                        if (adult != 0 && children != 0) {
                            people = "   Adult " + Integer.toString(adult
                            ) + ",  Children " + Integer.toString(children);
                        } else if (adult == 0 && children != 0) {
                            people = "   Children " + Integer.toString(children);
                        } else if (children == 0 && adult != 0) {
                            people = "   Adult " + Integer.toString(adult);
                        }
                        holder.people.setText(people);
                    } else {
                        adult = 0;
                    }
                    cartObject.setAdult(adult);
                    holder.price.setText("$ " + ((adult * adultPrice) + (children * childrenPrice)));
                }
            });
            holder.addChildrenBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adult = cartObject.getAdult();
                    children = cartObject.getChildren();
                    if (children >= 0) {
                        children++;
                        holder.currentChildrenText.setText("Children " + Integer.toString(children));
                        if (adult != 0 && children != 0) {
                            people = "   Adult " + Integer.toString(adult
                            ) + ",  Children " + Integer.toString(children);
                        } else if (adult == 0 && children != 0) {
                            people = "   Children " + Integer.toString(children);
                        } else if (children == 0 && adult != 0) {
                            people = "   Adult " + Integer.toString(adult);
                        }
                        holder.people.setText(people);
                    }
                    cartObject.setChildren(children);
                    holder.price.setText("$ " + ((adult * adultPrice) + (children * childrenPrice)));
                }
            });

            holder.subChildrenBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adult = cartObject.getAdult();
                    children = cartObject.getChildren();
                    if (children > 0) {
                        children--;
                        holder.currentChildrenText.setText("Children " + Integer.toString(children));
                        if (adult != 0 && children != 0) {
                            people = "   Adult " + Integer.toString(adult
                            ) + ",  Children " + Integer.toString(children);
                        } else if (adult == 0 && children != 0) {
                            people = "   Children " + Integer.toString(children);
                        } else if (children == 0 && adult != 0) {
                            people = "   Adult " + Integer.toString(adult);
                        }
                        holder.people.setText(people);
                    } else {
                        children = 0;
                    }
                    cartObject.setChildren(children);
                    holder.price.setText("$ " + ((adult * adultPrice) + (children * childrenPrice)));
                }
            });

            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(ProfileCartActivity.this).setTitle("Do you want delete?")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    remove(p);
                                }
                            }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
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
            public ImageView delete;
            public LinearLayout expandedLayout;
            public LinearLayout edit;
            public LinearLayout activityImage;
            //public ImageView activityImage;
            public TextView text1;
            public TextView text2;
            public TextView text3;
            public TextView date;
            public TextView time;
            public TextView people;
            public ImageView editbtn;
            public Button addAdultBtn;
            public TextView currentAdultText;
            public Button subAdultBtn;
            public Button addChildrenBtn;
            public TextView currentChildrenText;
            public Button subChildrenBtn;
            public TextView price;
            public Button cancleEditBtn;
            public Button okEditBtn;

            public ViewHolder(View itemView) {
                super(itemView);
                checkBox = (CheckBox) itemView.findViewById(R.id.cart_recycler_checkbox);
                shopNmae = (TextView) itemView.findViewById(R.id.cart_recycler_shopname);
                delete = (ImageView) itemView.findViewById(R.id.cart_recycler_delete);
                expandedLayout = (LinearLayout) itemView.findViewById(R.id.cart_recycler_expandedlayout);
                edit = (LinearLayout) itemView.findViewById(R.id.cart_recycler_editlayout);
                activityImage = (LinearLayout) itemView.findViewById(R.id.cart_recycler_activityimage);
                //activityImage = (ImageView) itemView.findViewById(R.id.cart_recycler_activityimage);
                text1 = (TextView) itemView.findViewById(R.id.cart_recycler_text1);
                text2=(TextView) itemView.findViewById(R.id.cart_recycler_text2);
                text3 = (TextView) itemView.findViewById(R.id.cart_recycler_text3);
                date = (TextView) itemView.findViewById(R.id.cart_recycler_date);
                time = (TextView) itemView.findViewById(R.id.cart_recycler_time);
                people = (TextView) itemView.findViewById(R.id.cart_recycler_people);
                editbtn = (ImageView) itemView.findViewById(R.id.cart_recycler_editbtn);
                addAdultBtn = (Button) itemView.findViewById(R.id.cart_recycler_addadultbtn);
                currentAdultText = (TextView) itemView.findViewById(R.id.cart_recycler_curentadulttext);
                subAdultBtn = (Button) itemView.findViewById(R.id.cart_recycler_subadultbtn);
                addChildrenBtn = (Button) itemView.findViewById(R.id.cart_recycler_addchildrenbtn);
                currentChildrenText = (TextView) itemView.findViewById(R.id.cart_recycler_currentchildrenbtn);
                subChildrenBtn = (Button) itemView.findViewById(R.id.cart_recycler_subchildrenbtn);
                price = (TextView) itemView.findViewById(R.id.cart_recycler_price);
                cancleEditBtn = (Button) itemView.findViewById(R.id.cart_recycler_canceleditbtn);
                okEditBtn = (Button) itemView.findViewById(R.id.cart_recycler_okeditbtn);
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
