package com.example.minefield.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.example.minefield.R;
import com.example.minefield.controller.FieldController;
import com.example.minefield.model.Field;

public class PlayAcivity extends AppCompatActivity implements GameEndListener{
    private FieldController field;
    private MinefieldView fieldView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        fieldView = findViewById(R.id.minefield);
        boolean isLoadGame = false;
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
             isLoadGame = extras.getBoolean("loadGame");
        }
        if(isLoadGame){
            field = new FieldController(true);
        }
        else{
            field = new FieldController(false);
        }
        fieldView.setField(field);
        fieldView.setGameEndListener(this);
    }

    @Override
    public void endGame() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
