package com.example.bloodbank;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter {


    private Activity context;
    List<donation> donationList;



    public CustomAdapter(@NonNull Activity context,List<donation> donationList ) {
        super(context, R.layout.custom_layout,donationList);

        this.context = context;
        this.donationList = donationList;


    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_layout,null,true);
        TextView events = rowView.findViewById(R.id.tvevent);
        TextView dates = rowView.findViewById(R.id.tvDate);
        TextView times = rowView.findViewById(R.id.tvTime);
        TextView locations = rowView.findViewById(R.id.tvlocation);
        TextView states = rowView.findViewById(R.id.tvphone);

        donation donation = donationList.get(position);

        events.setText(donation.getEvent());
        dates.setText(donation.getDate());
        times.setText(donation.getTime());
        locations.setText(donation.getLocation());
        states.setText(donation.getState());


        return rowView;
    }
}
