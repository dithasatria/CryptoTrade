package com.example.android.cryptotrade.detailcrypto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.google.gson.Gson;

/**
 * Created by DITHA on 16/07/2017.
 */

public class FragmentTabDepths extends Fragment {

    RecyclerView RV_TAB_DEPTHS;
    DepthsAdapter adapter;
    Gson gson = new Gson();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_crypto_detail_depths, container, false);

        RV_TAB_DEPTHS = (RecyclerView) v.findViewById(R.id.rvDepths);
        //getActivity().setTitle("Depths");
        LoadData();
        return v;
    }


    public void LoadData(){
        final String URL = "https://vip.bitcoin.co.id/api/eth_btc/depth";
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ListDepths depths = gson.fromJson(response, ListDepths.class);

                        Log.i("depths", depths.getBuy().toString());

                        adapter = new DepthsAdapter(depths, getActivity());
                        RV_TAB_DEPTHS.setLayoutManager(new LinearLayoutManager(getActivity()));
                        RV_TAB_DEPTHS.setAdapter(adapter);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        // Adding request to request queue
        queue.add(stringRequest);
    }
}
