package com.example.minefield.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.minefield.R;
import com.example.minefield.model.TopListRecord;

import java.util.ArrayList;

public class RecordsActivity extends AppCompatActivity {
    ArrayList<TopListRecord> toplist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvTopList);

        // Initialize contacts
        toplist = TopListRecord.createTopList(20);
        // Create adapter passing in the sample user data
        TopListRecordAdapter adapter = new TopListRecordAdapter(toplist);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }
}
