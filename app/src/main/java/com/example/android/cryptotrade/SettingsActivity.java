package com.example.android.cryptotrade;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.cryptotrade.utilities.PrefManager;

/**
 * Created by DITHA on 17/07/2017.
 */

public class SettingsActivity extends AppCompatActivity {

    Button BTN_REFRESH_RATE;
    Context context;

    PrefManager manager = new PrefManager();
    String refreshTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        BTN_REFRESH_RATE = (Button) findViewById(R.id.btnRefreshDuration);

        Long second = Long.parseLong(manager.getString(this, "refreshRate"));
        BTN_REFRESH_RATE.setText(second/1000 + " Second");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;

        BTN_REFRESH_RATE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Choose Refresh Rate");

                String[] refreshRate = {"5 Second",
                        "10 Second",
                        "15 Second",
                        "30 Second",
                        "1 Minutes",
                        "3 Minutes",
                        "5 Minutes",
                        "10 Minutes"};

                builder.setItems(refreshRate, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                refreshTime = "5000";
                                BTN_REFRESH_RATE.setText("5 Second");
                                break;
                            case 1:
                                refreshTime = "10000";
                                BTN_REFRESH_RATE.setText("10 Second");
                                manager.saveString(getApplicationContext(), "refreshRate", refreshTime);
                                break;
                            case 2:
                                refreshTime = "15000";
                                BTN_REFRESH_RATE.setText("15 Second");
                                break;
                            case 3:
                                refreshTime = "30000";
                                BTN_REFRESH_RATE.setText("30 Second");
                                break;
                            case 4:
                                refreshTime = "60000";
                                BTN_REFRESH_RATE.setText("1 Minutes");
                                break;
                            case 5:
                                refreshTime = "90000";
                                BTN_REFRESH_RATE.setText("3 Minutes");
                                break;
                            case 6:
                                refreshTime = "300000";
                                BTN_REFRESH_RATE.setText("5 Minutes");
                                break;
                            case 7:
                                refreshTime = "600000";
                                BTN_REFRESH_RATE.setText("10 Minutes");
                                break;
                        }
                    }
                });

                manager.saveString(getApplicationContext(), "refreshRate", refreshTime);
// create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
