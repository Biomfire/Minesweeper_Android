package com.example.minefield.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.minefield.R;
import com.example.minefield.database.AppDataBase;
import com.example.minefield.database.TopListRecord;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class RecordsActivity extends AppCompatActivity {
    private TopListRecordAdapter adapter;
    private AppDataBase database;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        database= AppDataBase.getDatabase(getApplication());
        recyclerView = (RecyclerView) findViewById(R.id.rvTopList);
        adapter = new TopListRecordAdapter();
        loadItemsInBackground();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void loadItemsInBackground() {
        new AsyncTask<Void, Void, List<TopListRecord>>() {

            @Override
            protected List<TopListRecord> doInBackground(Void... voids) {
                return database.topListDao().getAll();
            }

            @Override
            protected void onPostExecute(List<TopListRecord> topListItems) {
                adapter.update(topListItems);
            }
        }.execute();
    }
}
