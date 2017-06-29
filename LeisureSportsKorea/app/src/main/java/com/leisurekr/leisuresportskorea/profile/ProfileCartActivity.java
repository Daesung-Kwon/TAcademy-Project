package com.leisurekr.leisuresportskorea.profile;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.leisurekr.leisuresportskorea.BookInformationActivity;
import com.leisurekr.leisuresportskorea.R;
import com.leisurekr.leisuresportskorea.common.NetworkDefineConstant;
import com.leisurekr.leisuresportskorea.okhttp.OkHttpAPIHelperHandler;

import java.util.ArrayList;

public class ProfileCartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<CartObject> arrayList;
    TextView totalPrice;
    Button checkOutBtn;
    CartAdapter cartAdapter;

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
        object.setData(R.drawable.pic_waterski1, "River City"
                , "Water Ski Beginner", "Lesson Package"
                , "May 22, 2017", "14:00"
                , 1, 50, 0, 1, 50, 0);


        arrayList.add(object);

        cartAdapter = new CartAdapter(arrayList);
        recyclerView.setAdapter(cartAdapter);

        checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //예약하는 페이지로 이동
                Intent intent = new Intent(ProfileCartActivity.this, BookInformationActivity.class);
                if (arrayList == null) {
                    Toast.makeText(ProfileCartActivity.this, "1", Toast.LENGTH_SHORT).show();
                }
                intent.putExtra("check out", cartAdapter.checkedArray);
                intent.putExtra("type",2);
                startActivity(intent);
                finish();
            }
        });

    }

    public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

        ArrayList<CartObject> arrayList;
        ArrayList<CartObject> checkedArray;
        boolean isEditable = false;
        int total = 0;
        int adult = 0;
        int children = 0;
        int adultPrice = 0;
        int childrenPrice = 0;
        String people;

        public CartAdapter(ArrayList<CartObject> arrayList) {
            this.arrayList = arrayList;
            checkedArray = new ArrayList<>();
            CartObject cartObject;
            ProgramObject programObject;
            if (arrayList != null&&arrayList.size()>0) {
                if (arrayList.get(0).programObject != null) {
                    for (int j = 0; j < arrayList.size(); j++) {
                        cartObject = arrayList.get(j);
                        programObject = cartObject.programObject;
                        total += programObject.getPrice();
                        checkedArray.add(arrayList.get(j));
                    }
                }
            }
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
            CartObject cartObject = arrayList.get(position);
            ProgramObject programObject = cartObject.programObject;
            for (int i = 0; i < checkedArray.size(); i++) {
                if (checkedArray.get(i).equals(cartObject)) {
                    checkedArray.remove(i);
                    Log.e("remove", Integer.toString(i));
                    break;
                }
            }
            total -= programObject.getPrice();
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
            if (cartObject.programObject != null) {

                final ProgramObject programObject = cartObject.programObject;
                ShopObject shopObject = programObject.shopObject;

                holder.shopNmae.setText(cartObject.getShopname());

                Glide.with(ProfileCartActivity.this).load(programObject.image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(new ViewTarget<LinearLayout, GlideDrawable>(holder.activityImage) {
                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                holder.activityImage.setBackgroundDrawable(resource);
                            }
                        });


                holder.text1.setText(shopObject.name + "'s");
                holder.text2.setText(programObject.activityName);
                holder.text3.setText(programObject.getName().substring(programObject.getActivityName().length() + 1));

                holder.date.setText(cartObject.getDate());
                holder.time.setText(cartObject.getTime());

                adult = cartObject.getAdult();
                children = cartObject.getChildren();
                adultPrice = cartObject.getAdultPrice();
                childrenPrice = cartObject.getChildrenPrice();

                holder.currentAdultText.setText("Adult " + Integer.toString(adult));
                holder.currentChildrenText.setText("Children " + Integer.toString(children));

                holder.price.setText("$ " + ((adult * adultPrice) + (children * childrenPrice)));

                Log.e("checkBox", "position: " + Integer.toString(position));
                //Log.e("checkBox","check "+checkedArray.get(position).getAdult());
                for (int j = 0; j < checkedArray.size(); j++) {
                    if (checkedArray.get(j).equals(cartObject)) {
                        holder.checkBox.setChecked(true);
                        Log.e("checkBox", "true: " + Integer.toString(j));
                        break;
                    }
                }

                totalPrice.setText("Order Subtotal $" + Integer.toString(total)
                        + ", " + Integer.toString(checkedArray.size()) + " item(s) in your cart");

                if (adult != 0 && children != 0) {
                    people = "   Adult " + Integer.toString(adult
                    ) + "\n   Children " + Integer.toString(children);
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
                            Log.e("checkBox", "up");
                            if (total >= 0) {
                                checkedArray.add(cartObject);
                                total = total + programObject.getPrice();
                            }
                        } else {
                            //총 가격 하락
                            Log.e("checkBox", "down");
                            checkedArray.remove(cartObject);
                            total = total - programObject.getPrice();
                            if (total <= 0) {
                                total = 0;
                            }
                        }
                        totalPrice.setText("Order Subtotal $" + Integer.toString(total)
                                + ", " + Integer.toString(checkedArray.size()) + " item(s) in your cart");
                    }
                });


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

                holder.addAdult.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adult = cartObject.getAdult();
                        children = cartObject.getChildren();
                        total -= ((adult * adultPrice) + (children * childrenPrice));
                        if (adult >= 0) {
                            adult++;
                            holder.currentAdultText.setText("Adult " + Integer.toString(adult));
                            if (adult != 0 && children != 0) {
                                people = "   Adult " + Integer.toString(adult
                                ) + "\n   Children " + Integer.toString(children);
                            } else if (adult == 0 && children != 0) {
                                people = "   Children " + Integer.toString(children);
                            } else if (children == 0 && adult != 0) {
                                people = "   Adult " + Integer.toString(adult);
                            } else
                                people = " ";
                            holder.people.setText(people);
                        }
                        cartObject.setAdult(adult);
                        holder.price.setText("$ " + ((adult * adultPrice) + (children * childrenPrice)));
                        total += ((adult * adultPrice) + (children * childrenPrice));
                        totalPrice.setText("Order Subtotal $" + Integer.toString(total)
                                + ", " + Integer.toString(checkedArray.size()) + " item(s) in your cart");
                    }
                });

                holder.subAdult.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adult = cartObject.getAdult();
                        children = cartObject.getChildren();
                        total -= ((adult * adultPrice) + (children * childrenPrice));
                        if (adult > 0) {
                            adult--;
                            holder.currentAdultText.setText("Adult " + Integer.toString(adult));
                            if (adult != 0 && children != 0) {
                                people = "   Adult " + Integer.toString(adult
                                ) + "\n   Children" + Integer.toString(children);
                            } else if (adult == 0 && children != 0) {
                                people = "   Children " + Integer.toString(children);
                            } else if (children == 0 && adult != 0) {
                                people = "   Adult " + Integer.toString(adult);
                            } else
                                people = " ";
                            holder.people.setText(people);
                        } else {
                            adult = 0;
                        }
                        cartObject.setAdult(adult);
                        holder.price.setText("$ " + ((adult * adultPrice) + (children * childrenPrice)));
                        total += ((adult * adultPrice) + (children * childrenPrice));
                        totalPrice.setText("Order Subtotal $" + Integer.toString(total)
                                + ", " + Integer.toString(checkedArray.size()) + " item(s) in your cart");
                    }
                });
                holder.addChildren.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adult = cartObject.getAdult();
                        children = cartObject.getChildren();
                        total -= ((adult * adultPrice) + (children * childrenPrice));
                        if (children >= 0) {
                            children++;
                            holder.currentChildrenText.setText("Children " + Integer.toString(children));
                            if (adult != 0 && children != 0) {
                                people = "   Adult " + Integer.toString(adult
                                ) + "\n   Children" + Integer.toString(children);
                            } else if (adult == 0 && children != 0) {
                                people = "   Children " + Integer.toString(children);
                            } else if (children == 0 && adult != 0) {
                                people = "   Adult " + Integer.toString(adult);
                            } else
                                people = " ";
                            holder.people.setText(people);
                        }
                        cartObject.setChildren(children);
                        holder.price.setText("$ " + ((adult * adultPrice) + (children * childrenPrice)));
                        total += ((adult * adultPrice) + (children * childrenPrice));
                        totalPrice.setText("Order Subtotal $" + Integer.toString(total)
                                + ", " + Integer.toString(checkedArray.size()) + " item(s) in your cart");
                    }
                });

                holder.subChildren.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adult = cartObject.getAdult();
                        children = cartObject.getChildren();
                        total -= ((adult * adultPrice) + (children * childrenPrice));
                        if (children > 0) {
                            children--;
                            holder.currentChildrenText.setText("Children " + Integer.toString(children));
                            if (adult != 0 && children != 0) {
                                people = "   Adult " + Integer.toString(adult
                                ) + "\n   Children" + Integer.toString(children);
                            } else if (adult == 0 && children != 0) {
                                people = "   Children " + Integer.toString(children);
                            } else if (children == 0 && adult != 0) {
                                people = "   Adult " + Integer.toString(adult);
                            } else
                                people = " ";
                            holder.people.setText(people);
                        } else {
                            children = 0;
                        }
                        cartObject.setChildren(children);
                        holder.price.setText("$ " + ((adult * adultPrice) + (children * childrenPrice)));
                        total += ((adult * adultPrice) + (children * childrenPrice));
                        totalPrice.setText("Order Subtotal $" + Integer.toString(total)
                                + ", " + Integer.toString(checkedArray.size()) + " item(s) in your cart");
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
                                        new AsyncCartDelete().execute(cartObject);
                                    }
                                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            if (arrayList != null)
                return arrayList.size();
            else
                return 0;
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
            public ImageView addAdult;
            public TextView currentAdultText;
            public ImageView subAdult;
            public ImageView addChildren;
            public TextView currentChildrenText;
            public ImageView subChildren;
            public TextView price;

            public ViewHolder(View itemView) {
                super(itemView);
                checkBox = (CheckBox) itemView.findViewById(R.id.cart_recycler_checkbox);
                shopNmae = (TextView) itemView.findViewById(R.id.cart_recycler_shopname);
                delete = (ImageView) itemView.findViewById(R.id.cart_recycler_delete);
                expandedLayout = (LinearLayout) itemView.findViewById(R.id.cart_recycler_expandedlayout);
                edit = (LinearLayout) itemView.findViewById(R.id.cart_recycler_editlayout);
                activityImage = (LinearLayout) itemView.findViewById(R.id.cart_recycler_activityimage);
                text1 = (TextView) itemView.findViewById(R.id.cart_recycler_text1);
                text2 = (TextView) itemView.findViewById(R.id.cart_recycler_text2);
                text3 = (TextView) itemView.findViewById(R.id.cart_recycler_text3);
                date = (TextView) itemView.findViewById(R.id.cart_recycler_date);
                time = (TextView) itemView.findViewById(R.id.cart_recycler_time);
                people = (TextView) itemView.findViewById(R.id.cart_recycler_people);
                editbtn = (ImageView) itemView.findViewById(R.id.cart_recycler_editbtn);
                addAdult = (ImageView) itemView.findViewById(R.id.cart_recycler_addadult);
                currentAdultText = (TextView) itemView.findViewById(R.id.cart_recycler_curentadulttext);
                subAdult = (ImageView) itemView.findViewById(R.id.cart_recycler_subadult);
                addChildren = (ImageView) itemView.findViewById(R.id.cart_recycler_addchildren);
                currentChildrenText = (TextView) itemView.findViewById(R.id.cart_recycler_currentchildren);
                subChildren = (ImageView) itemView.findViewById(R.id.cart_recycler_subchildren);
                price = (TextView) itemView.findViewById(R.id.cart_recycler_price);
            }
        }
    }

    ArrayList<CartObject> cartArrayList;

    @Override
    protected void onStart() {
        super.onStart();
        new AsyncCartJSONList().execute();
    }

    public class AsyncCartJSONList
            extends AsyncTask<String, Integer, ArrayList<CartObject>> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(ProfileCartActivity.this, "", "Data Loading...", true);
        }

        @Override
        protected ArrayList<CartObject> doInBackground(String... params) {
            return OkHttpAPIHelperHandler.cartJSONAllSelect();
        }

        @Override
        protected void onPostExecute(ArrayList<CartObject> result) {
            dialog.dismiss();
            cartArrayList = result;
            cartAdapter = new CartAdapter(cartArrayList);
            recyclerView.setAdapter(cartAdapter);
        }
    }

    public class AsyncCartDelete extends AsyncTask<CartObject, Integer, String> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(ProfileCartActivity.this,
                    "서버입력중", "잠시만 기다려 주세요 ...", true);
        }

        @Override
        protected String doInBackground(CartObject... cartObjects) {
            return OkHttpAPIHelperHandler.cartJSONDelete(cartObjects);
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
            if (result != null) {
                if (result.equalsIgnoreCase("OK")) {
                    showDialog(NetworkDefineConstant.LK_INSERT_DIALOG_OK, null);
                } else {
                    showDialog(NetworkDefineConstant.LK_INSERT_DIALOG_FAIL, null);
                }
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("message", "삭제 중 문제 발생[디버깅]!");
                showDialog(NetworkDefineConstant.LK_INSERT_DIALOG_FAIL, bundle);
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
