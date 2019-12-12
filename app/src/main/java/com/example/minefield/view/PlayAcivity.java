package com.example.minefield.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.example.minefield.R;
import com.example.minefield.controller.FieldController;
import com.example.minefield.model.Field;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PlayAcivity extends AppCompatActivity implements GameEndListener{
    private static final String TAG = MinefieldView.class.getName();
    private FieldController field;
    private MinefieldView fieldView;
    private boolean doSaveOnExit = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                field = new FieldController(openFileOutput);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else{
            int xSize = extras.getInt("xSize");
            int ySize = extras.getInt("ySize");
            int mineNumber = extras.getInt("mineNumber");
            Log.d(TAG, String.format("Generated field with: %d %d, mine count: %d", xSize, ySize, mineNumber));
            field = new FieldController(xSize, ySize, mineNumber);
        }
        fieldView.setField(field);
        fieldView.setGameEndListener(this);
    }

    @Override
    public void endGame() {
        doSaveOnExit = false;
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
