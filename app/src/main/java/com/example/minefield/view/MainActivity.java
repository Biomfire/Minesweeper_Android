package com.example.minefield.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.minefield.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!isMyServiceRunning(AnnoyingService.class)) {
            startService(new Intent(this, AnnoyingService.class));
        }
    }

    public void onNewGameButtonClick(View v) {
        Intent newGameIntent = new Intent(this, PlayAcivity.class);
        newGameIntent.putExtra("loadGame", false);
        startActivity(newGameIntent);
    }

    public void onLoadGameButtonClick(View v) {
        Intent loadGameIntent = new Intent(this, PlayAcivity.class);
        loadGameIntent.putExtra("loadgame", true);
        startActivity(loadGameIntent);
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i("isMyServiceRunning?", true + "");
                return true;
            }
        }
        Log.i("isMyServiceRunning?", false + "");
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
