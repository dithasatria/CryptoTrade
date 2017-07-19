package com.example.android.cryptotrade.detailcrypto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.cryptotrade.R;
import com.example.android.cryptotrade.adapter.TradesAdapter;
import com.example.android.cryptotrade.pojo.Trades;
import com.example.android.cryptotrade.utilities.URLAddress;
import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

/**
 * Created by DITHA on 16/07/2017.
 */

public class FragmentTabTrades extends Fragment {

    RecyclerView RV_TAB_TRADES;
    TradesAdapter adapter;
    Gson gson = new Gson();
    SwipeRefreshLayout refreshLayout;
    ProgressView circularProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_crypto_detail_trades, container, false);

        RV_TAB_TRADES = (RecyclerView) v.findViewById(R.id.rvTrades);
        refreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swiperefresh);
        circularProgressBar = (ProgressView)v.findViewById(R.id.circular_proggress);
        Log.i("date", "LOL");
        LoadData();

        setHasOptionsMenu(true);

        RV_TAB_TRADES.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).build());
        refreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        LoadData();
                    }
                }
        );
        return v;
    }

    public void LoadData(){
        circularProgressBar.start();
        final String URL = "https://vip.bitcoin.co.id/api/"+ URLAddress.TRADES+"/trades";
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Trades[] trades = gson.fromJson(response, Trades[].class);

                        adapter = new TradesAdapter(trades, getActivity());
                        RV_TAB_TRADES.setLayoutManager(new LinearLayoutManager(getActivity()));
                        RV_TAB_TRADES.setAdapter(adapter);
                        circularProgressBar.stop();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        refreshLayout.setRefreshing(false);
                        circularProgressBar.stop();
                        error.printStackTrace();
                    }
                });

        refreshLayout.setRefreshing(false);
        // Adding request to request queue
        queue.add(stringRequest);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_crypto_detail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            LoadData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
