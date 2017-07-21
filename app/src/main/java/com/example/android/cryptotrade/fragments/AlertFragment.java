package com.example.android.cryptotrade.fragments;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.cryptotrade.AlertActivity;
import com.example.android.cryptotrade.MainActivity;
import com.example.android.cryptotrade.R;
import com.example.android.cryptotrade.sql.DataHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlertFragment extends Fragment {

    DataHelper dbcenter;
    ListView LV_ALERT;
    public static AlertFragment alertFragment;
    Context context;
    public static final String  KEY_ID      = "_id";

    public AlertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_alert, container, false);

        LV_ALERT = (ListView)v.findViewById(R.id.lvAlert);
        LV_ALERT.setEmptyView(v.findViewById(R.id.tvAlertNoData));
        dbcenter = new DataHelper(getContext());
        dbcenter.open();
        alertFragment = this;
        context = getActivity();

        getActivity().setTitle("Alert");

        FloatingActionButton fab = (FloatingActionButton)v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AlertActivity.class);
                startActivity(intent);
            }
        });

        RefreshList();
        notif("ETH/BTC", "0.10899000", 1);

        return v;
    }


    public void RefreshList() {

        final Cursor cur = dbcenter.getAllData();

        String[] from  = new String[]{
                DataHelper.COL_CRYPTO_NAME, DataHelper.COL_PRICE, DataHelper.COL_STATUS
        };
        int [] to = new int[]{
                R.id.tvAlertCryptoName, R.id.tvAlertPrice, R.id.tvAlertStatus
        };

        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(), R.layout.row_alert, cur, from, to);
        LV_ALERT.setAdapter(adapter);

        LV_ALERT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, final long l) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                String[] action = {"Delete"};

                builder.setItems(action, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                builder.setMessage("Delete data?");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dbcenter.DeleteAlert(safeLongToInt(l));
                                        Fragment fragment = new AlertFragment();
                                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();
                                    }
                                });
                                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                });
                                builder.show();
                                break;
                        }

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        /*
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM alert", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }
        LV_ALERT.setAdapter(new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, daftar));
        LV_ALERT.setSelected(true);

        ((ArrayAdapter) LV_ALERT.getAdapter()).notifyDataSetInvalidated();
        */
    }

    public static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException
                    (l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }

    public void notif(String title, String message, int idNotif){

        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(getActivity())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(),
                        R.mipmap.ic_launcher))
                .setContentTitle(title)
                .setContentText(message)
                //.setDefaults(DEFAULT_VIBRATE | DEFAULT_SOUND)
                .setAutoCancel(true);

        Intent intent = new Intent(getActivity(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(idNotif /* ID of notification */, notificationBuilder.build());
    }
}
