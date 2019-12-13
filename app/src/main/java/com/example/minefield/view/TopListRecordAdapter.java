package com.example.minefield.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minefield.R;
import com.example.minefield.database.TopListRecord;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TopListRecordAdapter extends RecyclerView.Adapter<TopListRecordAdapter.ViewHolder> {
    private final List<TopListRecord> toplist;

    public TopListRecordAdapter() {
        this.toplist = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View topListView = LayoutInflater.from(parent.getContext()).inflate(R.layout.records_row_layout, parent, false);
        return new ViewHolder(topListView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TopListRecord topListRecord = toplist.get(position);
        TextView time = holder.time;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd\tHH:mm");
        String timeString = dateFormat.format(topListRecord.time);
        time.setText(timeString);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        TextView points = holder.points;
        points.setText(decimalFormat.format(topListRecord.Points));
    }

    @Override
    public int getItemCount() {
        return toplist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView time;
        public TextView points;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            super(itemView);
            time= (TextView) itemView.findViewById(R.id.textViewTime);
            points= (TextView) itemView.findViewById(R.id.textViewPoints);
        }
    }
    public void addItem(TopListRecord item) {
        toplist.add(item);
        notifyItemInserted(toplist.size() - 1);
    }

    public void update(List<TopListRecord> shoppingItems) {
        toplist.clear();
        toplist.addAll(shoppingItems);
        notifyDataSetChanged();
    }
}
