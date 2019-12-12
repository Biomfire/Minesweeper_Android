package com.example.minefield.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.SeekBar;

import com.example.minefield.R;

public class MainActivity extends AppCompatActivity {
    PopupWindow newGameSettings;
    View popupView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(isMyServiceRunning(AnnoyingService.class)) {
            stopService(new Intent(this, AnnoyingService.class));
        }
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        popupView = inflater.inflate(R.layout.new_game_settings_popup_view,  null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        newGameSettings = new PopupWindow(popupView, width, height, true);
    }

    public void onNewGameButtonClick(View v) {
        newGameSettings.showAtLocation(v, Gravity.CENTER, 0,0);
    }

    public void onLoadGameButtonClick(View v) {
        Intent loadGameIntent = new Intent(this, PlayAcivity.class);
        loadGameIntent.putExtra("loadgame", true);
        startActivity(loadGameIntent);
    }
    public void onRecordButtonClick(View v){
        startActivity(new Intent(this, RecordsActivity.class));


    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        assert manager != null;
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i("isMyServiceRunning?", true + "");
                return true;
            }
        }
        Log.i("isMyServiceRunning?", false + "");
        return false;
    }
    public void onNewGamePopUpClick(View v){
        SeekBar xSizeSeekBar = popupView.findViewById(R.id.xSizeSeekBar);
        SeekBar ySizeSeekBar = popupView.findViewById(R.id.ySizeSeekBar);
        SeekBar mineNumberSeekBar = popupView.findViewById(R.id.mineNumberSeekBar);
        Intent newGameIntent = new Intent(this, PlayAcivity.class);
        newGameIntent.putExtra("loadgame", false);
        newGameIntent.putExtra("xSize", xSizeSeekBar.getProgress());
        newGameIntent.putExtra("ySize", ySizeSeekBar.getProgress());
        newGameIntent.putExtra("mineNumber", mineNumberSeekBar.getProgress());
        newGameSettings.dismiss();
        startActivity(newGameIntent);
    }

    @Override
    protected void onDestroy() {
        if(!isMyServiceRunning(AnnoyingService.class)) {
            startService(new Intent(this, AnnoyingService.class));
        }
        super.onDestroy();
    }
}
