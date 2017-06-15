package com.leisurekr.leisuresportskorea.shop_detail;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.leisurekr.leisuresportskorea.BookActivity;
import com.leisurekr.leisuresportskorea.LKApplication;
import com.leisurekr.leisuresportskorea.R;
import com.leisurekr.leisuresportskorea.okhttp.OkHttpAPIHelperHandler;

import java.util.ArrayList;

/**
 * Created by mobile on 2017. 5. 31..
 */

public class ShopProgramFragment extends Fragment {

    private static ExpListViewAdapter listViewAdapter;
    private NonScrollExpandableListView nonScrollExpListView1;
    private NonScrollExpandableListView nonScrollExpListView2;
    private NonScrollExpandableListView nonScrollExpListView3;

    TextView bookButton;
    static ShopDetailActivity owner;

    static int mShopId = -1;
    int groupCount = 0;
    int childCount[];
    int number = 0;

    public ShopProgramFragment() {
    }

    public static ShopProgramFragment newInstance(int shopId) {
        ShopProgramFragment shopProgramFragment = new ShopProgramFragment();
        mShopId = shopId;
        return shopProgramFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        owner = (ShopDetailActivity) getActivity();
        View view = inflater.inflate(R.layout.shop_program_fragment, container, false);

        nonScrollExpListView1 = (NonScrollExpandableListView) view.findViewById(R.id.best_program_elv);
        nonScrollExpListView2 = (NonScrollExpandableListView) view.findViewById(R.id.package_program_elv);
        nonScrollExpListView3 = (NonScrollExpandableListView) view.findViewById(R.id.individual_program_elv);

        /* 테스트 데이터 */
        ArrayList<ParentData> data = new ArrayList<>();
        listViewAdapter = new ExpListViewAdapter(getContext(), data);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new AsyncShopProgramJSONList().execute();
    }

    public class ExpListViewAdapter extends BaseExpandableListAdapter {

        private static final int PARENT_ROW = R.layout.listview_parent_row;
        private static final int CHILD_ROW = R.layout.listview_child_row;

        private Context mContext;
        private ArrayList<ParentData> data;
        private LayoutInflater inflater = null;

        public ExpListViewAdapter(Context context, ArrayList<ParentData> data) {
            this.data = data;
            this.mContext = context;
            this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(PARENT_ROW, parent, false);
            }

            TextView programNameTextView = (TextView) convertView.findViewById(R.id.program_name);
            programNameTextView.setText(data.get(groupPosition).getProgramName());

            return convertView;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, final ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(CHILD_ROW, parent, false);
            }

            ImageView activityImage = (ImageView) convertView.findViewById(R.id.activity_image);
            TextView activityNameTextView = (TextView) convertView.findViewById(R.id.activity_name);
            TextView activityPriceTextView = (TextView) convertView.findViewById(R.id.activity_price);
            TextView activityDescTextView = (TextView) convertView.findViewById(R.id.activity_description);
            bookButton = (TextView) convertView.findViewById(R.id.shop_detail_book_btn);

            activityNameTextView.setText(data.get(groupPosition).child.get(childPosition).getProgramName());
            activityPriceTextView.setText("$" + String.valueOf(data.get(groupPosition).child.get(childPosition).getActivityPrice()));
            activityDescTextView.setText(data.get(groupPosition).child.get(childPosition).getActivityDesc());

            Glide.with(LKApplication.getLKApplication())
                    .load(data.get(groupPosition).child.get(childPosition).activityImage)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .animate(android.R.anim.slide_in_left)
                    //.override(40, 40)
                    .into(activityImage);

            // Book Activity 화면 전환
            bookButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent bookIntent = new Intent(getActivity(), BookActivity.class);
                    bookIntent.putExtra("programId", data.get(groupPosition).child.get(childPosition).programId);
                    bookIntent.putExtra("programName", data.get(groupPosition).child.get(childPosition).programName);
                    bookIntent.putExtra("adultPrice", data.get(groupPosition).child.get(childPosition).adultPrice);
                    bookIntent.putExtra("childPrice", data.get(groupPosition).child.get(childPosition).childPrice);
                    bookIntent.putExtra("programImage", data.get(groupPosition).child.get(childPosition).activityImage);
                    bookIntent.putExtra("activityName", data.get(groupPosition).child.get(childPosition).getActivityName());
                    bookIntent.putExtra("shopName", data.get(groupPosition).child.get(childPosition).getShopName());
                    bookIntent.putExtra("shopImage", data.get(groupPosition).child.get(childPosition).getShopImage());
                    bookIntent.putExtra("shopId", data.get(groupPosition).child.get(childPosition).getShopId());
                    bookIntent.putExtra("groupId", ((NonScrollExpandableListView) parent).getId());
                    if (groupPosition != 0) {
                        for (int i = 0; i < groupPosition; i++)
                            number += childCount[i];
                    }
                    Log.e("1", " " + groupPosition);
                    Log.e("1", " " + groupCount);
                    number += (childPosition + 1);
                    bookIntent.putExtra("number", number);
                    startActivity(bookIntent);
                    number = 0;
                }
            });

            return convertView;
        }

        @Override
        public int getGroupCount() {
            return data.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return data.get(groupPosition).child.size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return data.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return data.get(groupPosition).child.get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        public void setupAdapter(ArrayList<ParentData> item) {
            ArrayList<ParentData> parentDatas;
            Log.e("item size()", " " + item.size());

            for (int i = 0; i < item.size(); i++) {
                parentDatas = new ArrayList<>();
                parentDatas.add(item.get(i));
                listViewAdapter = new ExpListViewAdapter(getContext(), parentDatas);

                switch (item.get(i).category) {
                    case "Best Programs":
                        nonScrollExpListView1.setAdapter(listViewAdapter);
                        if (i == 0)
                            nonScrollExpListView1.expandGroup(0);
                        break;
                    case "Package Programs":
                        nonScrollExpListView2.setAdapter(listViewAdapter);
                        if (i == 0)
                            nonScrollExpListView2.expandGroup(0);
                        break;
                    case "Individual Programs":
                        nonScrollExpListView3.setAdapter(listViewAdapter);
                        if (i == 0)
                            nonScrollExpListView3.expandGroup(0);
                        break;
                }
            }
        }
    }

    public class AsyncShopProgramJSONList
            extends AsyncTask<String, Integer, ArrayList<ParentData>> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(owner, "", "Loading...", true);
        }

        @Override
        protected ArrayList<ParentData> doInBackground(String... params) {
            return OkHttpAPIHelperHandler.shopProgramJSONALLSelect(mShopId);
        }

        @Override
        protected void onPostExecute(ArrayList<ParentData> result) {
            dialog.dismiss();
            // TODO
            if (result != null && result.size() > 0) {
                listViewAdapter.setupAdapter(result);
                groupCount = listViewAdapter.getGroupCount();
                childCount = new int[groupCount];
                for (int i = 0; i < groupCount; i++) {
                    childCount[i] = listViewAdapter.getChildrenCount(i);
                }
            }
        }
    }
}
