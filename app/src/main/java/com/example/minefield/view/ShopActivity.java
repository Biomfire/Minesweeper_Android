package com.example.minefield.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreferenceCompat;

import com.example.minefield.R;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            Context context = getPreferenceManager().getContext();
            PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(context);
            if (this.getActivity() == null) {
                throw new NullPointerException();
            }
            final SharedPreferences sharedPreferences =
                    PreferenceManager.getDefaultSharedPreferences(this.getActivity());

            PreferenceCategory shopCategory = new PreferenceCategory(context);
            shopCategory.setKey("shop");
            shopCategory.setTitle(getResources().getString(R.string.shop_category_shop));
            screen.addPreference(shopCategory);

            final List<CharSequence> entries = new ArrayList<CharSequence>();
            final List<CharSequence> entyValues = new ArrayList<CharSequence>();
            entries.add("default");
            entyValues.add("Default");
            final ListPreference listPreference = new ListPreference(context);
            listPreference.setDefaultValue("Default");
            listPreference.setTitle(getResources().getString(R.string.shop_preference_choose_skin));
            listPreference.setSummary(listPreference.getEntry());
            listPreference.setKey("choosen_skin");

            for (ThemeManager.Theme theme : ThemeManager.Theme.values()) {
                final String key = theme.toString().toLowerCase() + "_settings_key";
                final String name = String.valueOf(theme.toString().charAt(0)).toUpperCase() + theme.toString().toLowerCase().substring(1);
                if (!sharedPreferences.getBoolean(key, false)) {
                    Preference feedbackPreference = new Preference(context);
                    feedbackPreference.setKey(key);
                    feedbackPreference.setTitle(name);
                    feedbackPreference.setSummary(getResources().getString(R.string.shop_preference_choose_skin));
                    feedbackPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                        @Override
                        public boolean onPreferenceClick(Preference preference) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean(key, true);
                            editor.apply();
                            preference.setVisible(false);
                            entries.add(key);
                            entyValues.add(name);
                            listPreference.setEntries(entyValues.toArray(new CharSequence[entries.size()]));
                            listPreference.setEntryValues(entries.toArray(new CharSequence[entyValues.size()]));
                            Toast toast = Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R.string.shop_preference_not_implemented), Toast.LENGTH_SHORT);
                            toast.show();

                            return true;

                        }
                    });
                    shopCategory.addPreference(feedbackPreference);
                } else {
                    entries.add(key);
                    entyValues.add(name);
                }
            }
            PreferenceCategory useCategory = new PreferenceCategory(context);
            useCategory.setKey("use");
            useCategory.setTitle(getResources().getString(R.string.shop_category_use));
            screen.addPreference(useCategory);
            listPreference.setEntries(entyValues.toArray(new CharSequence[entries.size()]));
            listPreference.setEntryValues(entries.toArray(new CharSequence[entyValues.size()]));
            useCategory.addPreference(listPreference);
            Preference resetPreference = new Preference(context);
            resetPreference.setKey("reset");
            resetPreference.setTitle("Reset Database");
            resetPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    sharedPreferences.edit().clear().apply();
                    return true;
                }
            });
            screen.addPreference(resetPreference);
            setPreferenceScreen(screen);

        }
    }
}