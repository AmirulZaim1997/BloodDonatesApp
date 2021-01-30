package com.example.bloodbank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class TableUserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    ArrayList<Record> mlist;
    Context context;

    public TableUserAdapter(Context context,ArrayList<Record> mlist){
        this.context = context;
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.table_list_item2,parent,false);
        return new RowViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (position == 0) {


            rowViewHolder.location.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.city.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.date.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.status.setBackgroundResource(R.drawable.table_header_cell_bg);

            rowViewHolder.location.setText("State");
            rowViewHolder.city.setText("City");
            rowViewHolder.date.setText("Date");
            rowViewHolder.status.setText("Status");
        }else {
            Record donor = mlist.get(position);

            rowViewHolder.location.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.city.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.date.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.status.setBackgroundResource(R.drawable.table_content_cell_bg);

            rowViewHolder.location.setText(donor.getState());
            rowViewHolder.city.setText(donor.getLocation());
            rowViewHolder.date.setText(donor.getDate());
            rowViewHolder.status.setText(donor.getStatus());
        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class RowViewHolder extends RecyclerView.ViewHolder{

        TextView location,city,date,status;
        public RowViewHolder(@NonNull View itemView) {
            super(itemView);

            location = itemView.findViewById(R.id.locationList);
            city = itemView.findViewById(R.id.city);
            date = itemView.findViewById(R.id.dateList);
            status = itemView.findViewById(R.id.statusList);


        }
    }

}
