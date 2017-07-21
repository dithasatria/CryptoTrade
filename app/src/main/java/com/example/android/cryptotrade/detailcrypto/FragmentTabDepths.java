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
import com.example.android.cryptotrade.adapter.DepthsAdapter;
import com.example.android.cryptotrade.pojo.ListDepths;
import com.example.android.cryptotrade.utilities.URLAddress;
import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

/**
 * Created by DITHA on 16/07/2017.
 */

public class FragmentTabDepths extends Fragment {

    RecyclerView RV_TAB_DEPTHS;
    DepthsAdapter adapter;
    Gson gson = new Gson();
    SwipeRefreshLayout refreshLayout;
    ProgressView circularProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_crypto_detail_depths, container, false);

        RV_TAB_DEPTHS = (RecyclerView) v.findViewById(R.id.rvDepths);
        RV_TAB_DEPTHS.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).build());

        refreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swiperefresh_depths);
        circularProgressBar = (ProgressView)v.findViewById(R.id.circular_proggress_depths);

        setHasOptionsMenu(true);

        refreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        LoadData();
                    }
                }
        );
        LoadData();
        return v;
    }


    public void LoadData(){
        circularProgressBar.start();
        try {
            final String URL = "https://vip.bitcoin.co.id/api/" + URLAddress.TRADES + "/depth";
            RequestQueue queue = Volley.newRequestQueue(getActivity());

            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                ListDepths depths = gson.fromJson(response, ListDepths.class);

                                Log.i("depths", depths.getBuy().toString());

                                adapter = new DepthsAdapter(depths, getActivity());
                                RV_TAB_DEPTHS.setLayoutManager(new LinearLayoutManager(getActivity()));
                                RV_TAB_DEPTHS.setAdapter(adapter);

                                circularProgressBar.stop();
                            } catch (Exception e) {
                                refreshLayout.setRefreshing(false);
                                circularProgressBar.stop();
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });

            refreshLayout.setRefreshing(false);
            // Adding request to request queue
            queue.add(stringRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
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
