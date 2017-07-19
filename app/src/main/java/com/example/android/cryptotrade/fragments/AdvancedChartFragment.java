package com.example.android.cryptotrade.fragments;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.android.cryptotrade.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdvancedChartFragment extends Fragment {

    private ProgressDialog progressDialog;
    public AdvancedChartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_advanced_chart, container, false);

        getActivity().setTitle("Advanced Chart");
        progressDialog = new ProgressDialog(getActivity());
        WebView myWebView = (WebView)v.findViewById(R.id.wvBTC);

        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap bitmap) {
                progressDialog.setMessage("Loading Chart....");
                progressDialog.show();
                super.onPageStarted(view, url, bitmap);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressDialog .dismiss();
                super.onPageFinished(view, url);
            }
        });
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setDisplayZoomControls(true);
        myWebView.loadUrl("http://igpdsd.pe.hu/");

        return v;
    }

}
