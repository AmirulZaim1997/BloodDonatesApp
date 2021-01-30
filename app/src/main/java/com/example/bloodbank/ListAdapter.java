package com.example.bloodbank;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


public class ListAdapter extends ArrayAdapter {


    private Activity context;
    List<Bloodbank> bloodbankList ;



        public ListAdapter(Activity context, List<Bloodbank> bloodbankList){
            super(context,R.layout.custom_blood,bloodbankList);
            this.context = context;
            this.bloodbankList = bloodbankList;
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.custom_blood,null,true);
            TextView tvplace = rowView.findViewById(R.id.tvplace);
            TextView tvstate = rowView.findViewById(R.id.tvstate);
            TextView tvphone = rowView.findViewById(R.id.tvphone);

            Bloodbank bloodbank = bloodbankList.get(position);

            tvplace.setText(bloodbank.getBankname());
            tvstate.setText(bloodbank.getState());
            tvphone.setText(bloodbank.getPhone());

            return rowView;

        }
    }


