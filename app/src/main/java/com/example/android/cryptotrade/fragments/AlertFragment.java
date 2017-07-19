package com.example.android.cryptotrade.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.cryptotrade.AlertActivity;
import com.example.android.cryptotrade.R;
import com.example.android.cryptotrade.sql.DataHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlertFragment extends Fragment {

    DataHelper dbcenter;
    ListView LV_ALERT;
    public static AlertFragment alertFragment;
    String[] daftar;
    protected Cursor cursor;

    public AlertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_alert, container, false);

        LV_ALERT = (ListView)v.findViewById(R.id.lvAlert);
        dbcenter = new DataHelper(getContext());
        alertFragment = this;

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

        return v;
    }

    public void RefreshList() {
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
    }
}
