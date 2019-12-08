package com.example.minefield.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.minefield.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onNewGameButtonClick(View v){
        Intent newGameIntent = new Intent(this, PlayAcivity.class);
        newGameIntent.putExtra("loadGame", false);
        startActivity(newGameIntent);
    }

    public void onLoadGameButtonClick(View v){
        Intent loadGameIntent = new Intent(this, PlayAcivity.class);
        loadGameIntent.putExtra("loadgame", true);
        startActivity(loadGameIntent);
    }
}
