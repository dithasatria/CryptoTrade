package com.example.android.cryptotrade.utilities;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by DITHA on 17/07/2017.
 */

public class PrefManager {
    public PrefManager() {
    }

    public void saveString(Context context, String key, String value){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(key, value)
                .apply();
    }

    public String getString(Context context, String key){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(key, "5000");
    }


    public void deleteData(Context context, String key){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .clear()
                .apply();
    }
}