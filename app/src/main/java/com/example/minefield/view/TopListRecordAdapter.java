package com.example.minefield.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minefield.R;
import com.example.minefield.model.TopListRecord;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TopListRecordAdapter extends RecyclerView.Adapter<TopListRecordAdapter.ViewHolder> {
    ArrayList<TopListRecord> toplist;

    public TopListRecordAdapter(ArrayList<TopListRecord> toplist) {
        this.toplist = toplist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.records_row_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TopListRecord topListRecord = toplist.get(position);
        TextView time = holder.time;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm:ss");
        String timeString = dateFormat.format(topListRecord.getTimeOfAchievment());
        time.setText(timeString);
        TextView points = holder.points;
        points.setText(Integer.toString(topListRecord.getPoints()));
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
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            time= (TextView) itemView.findViewById(R.id.textViewTime);
            points= (TextView) itemView.findViewById(R.id.textViewPoints);
        }
    }
}
