package com.example.minefield.view;

import android.content.SharedPreferences;
import android.hardware.camera2.params.BlackLevelPattern;

import androidx.preference.PreferenceManager;

import com.example.minefield.R;

public class ThemeManager {
    private static volatile ThemeManager INSTANCE;
    private Theme currentTheme = Theme.BLUE;
    private SharedPreferences sharedPreferences;
    private ThemeManager() {
    }

    public enum Theme {
        BLACK,
        WHITE,
        BLUE;

        public static Theme getEnumFromKey(Object newValue) {
            if(newValue.equals("Default"))
                return WHITE;
            else if(newValue.equals("white_settings_key"))
                return WHITE;
            else if(newValue.equals("black_settings_key"))
                return BLACK;
            else if(newValue.equals("blue_settings_key"))
                return BLUE;
            throw new IllegalStateException("Unexpected value: " + newValue);
        }
    }

    public static ThemeManager getInstance(SharedPreferences sharedPreferences) {
        if (INSTANCE == null) {
            synchronized (ThemeManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ThemeManager();
                    INSTANCE.sharedPreferences = sharedPreferences;
                    String theme = INSTANCE.sharedPreferences.getString("choosen_skin", "default");

                    INSTANCE.currentTheme = Theme.getEnumFromKey(theme);
                }
            }
        }
        return INSTANCE;
    }
    public void setTheme(Theme newTheme){
        synchronized (ThemeManager.class) {
            currentTheme = newTheme;
        }
    }
    public int getTheme(){
        currentTheme = Theme.getEnumFromKey(sharedPreferences.getString("choosen_skin", "default"));
        switch (currentTheme) {
            case BLACK:
                return R.style.BlackTheme;
            case WHITE:
                return R.style.WhiteTheme;
            case BLUE:
                return R.style.BlueTheme;
        }
        throw new IllegalStateException("Unexpected value: " + currentTheme);
    }
}
