package com.example.bloodbank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;


public class TableViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    ArrayList<Record> mlist;
    ArrayList<Record>mlistAll;
    Context context;

    public TableViewAdapter(Context context,ArrayList<Record> mlist){
        this.context = context;
        this.mlist = mlist;
        mlistAll=new ArrayList<>(mlist);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.table_list_item,parent,false);
        return new RowViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (position == 0) {

            rowViewHolder.name.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.location.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.city.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.date.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.status.setBackgroundResource(R.drawable.table_header_cell_bg);

            rowViewHolder.name.setText("Donor Name");
            rowViewHolder.location.setText("Location");
            rowViewHolder.city.setText("City");
            rowViewHolder.date.setText("Date");
            rowViewHolder.status.setText("Status");
        }else {
            Record donor = mlist.get(position);

            rowViewHolder.name.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.location.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.city.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.date.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.status.setBackgroundResource(R.drawable.table_content_cell_bg);

            rowViewHolder.name.setText(donor.getName());
            rowViewHolder.location.setText(donor.getLocation());
            rowViewHolder.city.setText(donor.getState());
            rowViewHolder.date.setText(donor.getDate());
            rowViewHolder.status.setText(donor.getStatus());
        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    @Override
    public Filter getFilter() {
        return FilterUser;
    }

    private Filter FilterUser=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String searchText = charSequence.toString().toLowerCase();
            ArrayList<Record> tempList = new ArrayList<>();
            if (searchText.length() == 0 || searchText.isEmpty()) {
                tempList.addAll(mlistAll);
            }
            else{
                for (Record item:mlistAll){
                    if (item.getLocation().toLowerCase().contains(searchText)){
                        tempList.add(item);
                    }
                }
            }
            FilterResults filterResults=new FilterResults();
            filterResults.values=tempList;
                return filterResults;
            }

            @Override
            protected void publishResults (CharSequence constraint, FilterResults filterResults){
                    mlist.clear();
                    mlist.addAll((Collection<? extends Record>) filterResults.values);
                    notifyDataSetChanged();
            }

    };

    public static class RowViewHolder extends RecyclerView.ViewHolder{

            TextView name,location,city,date,status;
        public RowViewHolder(@NonNull View itemView) {
            super(itemView);

             name = itemView.findViewById(R.id.nameList);
             location = itemView.findViewById(R.id.locationList);
            city = itemView.findViewById(R.id.city);
             date = itemView.findViewById(R.id.dateList);
             status = itemView.findViewById(R.id.statusList);


        }
    }

}
