package com.leisurekr.leisuresportskorea.shop_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.leisurekr.leisuresportskorea.R;

import java.util.ArrayList;

/**
 * Created by mobile on 2017. 5. 31..
 */

public class ShopProgramFragment extends Fragment {

    private ExpandableListView expListView1;
    private ExpandableListView expListView2;
    private ExpandableListView expListView3;

    TextView bookButton;
    static ShopDetailActivity owner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        owner = (ShopDetailActivity)getActivity();
        View view = inflater.inflate(R.layout.shop_program_fragment, container, false);

        ArrayList<ParentData> data = new ArrayList<>();

        expListView1 = (ExpandableListView) view.findViewById(R.id.best_program_elv);
        expListView2 = (ExpandableListView) view.findViewById(R.id.package_program_elv);
        expListView3 = (ExpandableListView) view.findViewById(R.id.individual_program_elv);

        /* 테스트 데이터 */
        ParentData pData1 = new ParentData("Best Program");
        pData1.child.add(new ChildData("Water Ski Beginner Lesson Package",
                "$"+"50", "One lesson on the deck and two rides on the river"));
        pData1.child.add(new ChildData("Water Ski Beginner Lesson Package",
                "$"+"50", "One lesson on the deck and two rides on the river"));
        data.add(pData1);

        ExpListViewAdapter listViewAdapter = new ExpListViewAdapter(getContext(), data);
        expListView1.setAdapter(listViewAdapter);
        expListView2.setAdapter(listViewAdapter);
        expListView3.setAdapter(listViewAdapter);

        expListView1.expandGroup(0);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // ListView Child 이벤트 리스너, 필요 시
        /*expListView1.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getContext(), "Group1 Child = " + childPosition, Toast.LENGTH_SHORT).show();

                return false;
            }
        });*/
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
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(CHILD_ROW, parent, false);
            }

            ImageView activityImage = (ImageView) convertView.findViewById(R.id.activity_image);
            TextView activityNameTextView = (TextView) convertView.findViewById(R.id.activity_name);
            TextView activityPriceTextView = (TextView) convertView.findViewById(R.id.activity_price);
            TextView activityDescTextView = (TextView) convertView.findViewById(R.id.activity_description);
            bookButton = (TextView) convertView.findViewById(R.id.shop_detail_book_btn);

            activityImage.setImageResource(R.drawable.girls_generation_tifany);
            activityNameTextView.setText(data.get(groupPosition).child.get(childPosition).getActivityName());
            activityPriceTextView.setText(data.get(groupPosition).child.get(childPosition).getActivityPrice());
            activityDescTextView.setText(data.get(groupPosition).child.get(childPosition).getActivityDesc());

            // Book Activity 화면 전환
            bookButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /**
                     * TODO : Intent 주석 해제, 클래스 명 aaa.class 변경
                     */
                    Intent bookIntent = new Intent(getActivity(), WriteReviewActivity.class);
                    startActivity(bookIntent);
                }
            });

            return convertView;
        }

        @Override
        public int getGroupCount() { return data.size(); }

        @Override
        public int getChildrenCount(int groupPosition) { return data.get(groupPosition).child.size(); }

        @Override
        public Object getGroup(int groupPosition) { return data.get(groupPosition); }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return data.get(groupPosition).child.get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) { return groupPosition; }

        @Override
        public long getChildId(int groupPosition, int childPosition) { return childPosition; }

        @Override
        public boolean hasStableIds() { return true; }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) { return true; }
    }
}