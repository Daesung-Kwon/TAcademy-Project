package com.leisurekr.leisuresportskorea.ticket;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leisurekr.leisuresportskorea.R;
import com.leisurekr.leisuresportskorea.profile.ProgramObject;
import com.leisurekr.leisuresportskorea.profile.ShopObject;

public class TicketItemFragment extends Fragment {

    TicketObject ticket;
    ProgramObject programObject;
    ShopObject shopObject;

    ImageView backImage;
    RelativeLayout bottomImage;
    RelativeLayout topImage;
    LinearLayout dimLayout;

    TextView text1;
    TextView text2;
    TextView text3;
    ImageView activityIamge;

    TextView dateText;
    TextView date;
    TextView timeText;
    TextView time;
    TextView priceText;
    TextView price;
    TextView peopleText;
    TextView people;
    TextView locationText;
    TextView location1;
    TextView location2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.ticket_recycleritem,container,false);

        backImage = (ImageView) view.findViewById(R.id.ticket_backimage);
        bottomImage = (RelativeLayout) view.findViewById(R.id.ticket_bottonimage);
        topImage = (RelativeLayout) view.findViewById(R.id.ticket_topimage);
        dimLayout = (LinearLayout) view.findViewById(R.id.ticket_dim);
        text1 = (TextView) view.findViewById(R.id.ticket_text1);
        text2 = (TextView) view.findViewById(R.id.ticket_text2);
        text3 = (TextView) view.findViewById(R.id.ticket_text3);
        activityIamge = (ImageView) view.findViewById(R.id.ticket_activityimage);

        dateText = (TextView) view.findViewById(R.id.ticket_datetext);
        date = (TextView) view.findViewById(R.id.ticket_date);
        timeText = (TextView) view.findViewById(R.id.ticket_timetext);
        time = (TextView) view.findViewById(R.id.ticket_time);
        priceText = (TextView) view.findViewById(R.id.ticket_pricetext);
        price = (TextView) view.findViewById(R.id.ticket_price);
        peopleText = (TextView) view.findViewById(R.id.ticket_peopletext);
        people = (TextView) view.findViewById(R.id.ticket_people);
        locationText = (TextView) view.findViewById(R.id.ticket_locationtext);
        location1 = (TextView) view.findViewById(R.id.ticket_location1);
        location2 = (TextView) view.findViewById(R.id.ticket_location2);

        if(ticket.programObject!=null) {
            programObject = ticket.programObject;
            shopObject = programObject.getShopObject();
            text1.setText(shopObject.getName() + "'s");
            text2.setText(programObject.getActivityName());
            text3.setText(programObject.getName()
                    .substring(programObject.getActivityName().length()+1));
            Glide.with(TicketItemFragment.this).load(shopObject.getImage()).into(backImage);


            switch (programObject.getActivityName()){
                case "Water Ski":
                    activityIamge.setImageResource(R.drawable.ic_waterski);
                    break;
                case "Fun Boat":
                    activityIamge.setImageResource(R.drawable.ic_funboat);
                    break;
                case "Ski":
                    activityIamge.setImageResource(R.drawable.ic_waterski);
                    break;
            }

            date.setText(ticket.getDate());
            time.setText(ticket.getTime());
            price.setText("$"+Integer.toString(programObject.getPrice()));

            String s=" ";
            int adult = ticket.getAdult();
            int children = ticket.getChildren();
            if (adult != 0 && children != 0) {
                s = "Adult " + Integer.toString(adult
                ) + ",  Children " + Integer.toString(children);
            } else if (adult == 0 && children != 0) {
                s = "Children " + Integer.toString(children);
            } else if (children == 0 && adult != 0) {
                s = "Adult " + Integer.toString(adult);
            }
            people.setText(s);

            location1.setText(shopObject.getLocation3()+",");
            location2.setText(shopObject.getLocation2()+", "+shopObject.getLocation1());

        }else{
            Log.e("ticket","ticket.programObject==null");
        }

        //backImage.setImageResource(reservation.getBackImage());
        //backImage.setBackgroundResource(ticket.getBackImage());
        dimLayout.setBackgroundColor(Color.BLACK);
        dimLayout.setAlpha(0.3f);

//        text1.setText(ticket.getText1()+"'s");
//        text2.setText(ticket.getText2());
//        text3.setText(ticket.getText3());
//        switch (ticket.getMain()){
//            case "Water Ski":
//                activityIamge.setImageResource(R.drawable.ic_waterski);
//                break;
//            case "Fun Boat":
//                activityIamge.setImageResource(R.drawable.ic_funboat);
//                break;
//        }
//
//        date.setText(ticket.getDate());
//        time.setText(ticket.getTime());
//        price.setText("$"+Integer.toString(ticket.getPrice()));
//
//        String s=" ";
//        int adult = ticket.getAdult();
//        int children = ticket.getChildren();
//        if (adult != 0 && children != 0) {
//            s = "Adult " + Integer.toString(adult
//            ) + ",  Children " + Integer.toString(children);
//        } else if (adult == 0 && children != 0) {
//            s = "Children " + Integer.toString(children);
//        } else if (children == 0 && adult != 0) {
//            s = "Adult " + Integer.toString(adult);
//        }
//        people.setText(s);
//
//        location1.setText(ticket.getLocation3()+", "+ticket.getLocation1()+",");
//        location2.setText(ticket.getLocation2());

        return view;
    }

    public void setTicket(TicketObject ticket){
        this.ticket = ticket;
    }
}
