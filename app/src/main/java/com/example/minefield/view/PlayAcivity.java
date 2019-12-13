package com.example.minefield.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.minefield.R;
import com.example.minefield.presenter.FieldPresenter;
import com.example.minefield.database.AppDataBase;
import com.example.minefield.database.TopListRecord;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

public class PlayAcivity extends AppCompatActivity implements GameEndListener{
    private static final String TAG = MinefieldView.class.getName();
    private FieldPresenter field;
    private MinefieldView fieldView;
    private boolean doSaveOnExit = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(ThemeManager.getInstance(PreferenceManager.getDefaultSharedPreferences(this)).getTheme());
        setContentView(R.layout.activity_play);

        fieldView = findViewById(R.id.minefield);
        boolean isLoadGame = false;
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
             isLoadGame = extras.getBoolean("loadgame");
        }
        if(isLoadGame){
            try {
                FileInputStream openFileOutput = this.openFileInput( "lastGame.txt");
                field = new FieldPresenter(openFileOutput);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else{
            int xSize = extras.getInt("xSize");
            int ySize = extras.getInt("ySize");
            int mineNumber = extras.getInt("mineNumber");
            Log.d(TAG, String.format("Generated field with: %d %d, mine count: %d", xSize, ySize, mineNumber));
            field = new FieldPresenter(xSize, ySize, mineNumber);
        }
        fieldView.setField(field);
        fieldView.setGameEndListener(this);
    }

    @Override
    public void endGame() {
        doSaveOnExit = false;
        final TopListRecord newItem = new TopListRecord();
        newItem.Points = field.getPoints();
        newItem.time = new Date();
        new AsyncTask<Void, Void, TopListRecord>() {

            @Override
            protected TopListRecord doInBackground(Void... voids) {
                newItem.id = AppDataBase.getDatabase(getApplication()).topListDao().insert(newItem);
                return newItem;
            }

            @Override
            protected void onPostExecute(TopListRecord topListItem) {
            }
        }.execute();
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        if(doSaveOnExit){
            try {
                FileOutputStream openFileOutput = this.openFileOutput( "lastGame.txt", Context.MODE_PRIVATE);
                field.save(openFileOutput);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }
}
