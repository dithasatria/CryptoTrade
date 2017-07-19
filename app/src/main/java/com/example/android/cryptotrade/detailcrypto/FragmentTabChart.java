package com.example.android.cryptotrade.detailcrypto;

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
import com.example.android.cryptotrade.utilities.URLAddress;

/**
 * Created by DITHA on 16/07/2017.
 */

public class FragmentTabChart extends Fragment{

    ProgressDialog progressDialog;
    WebView myWebView;
    String URL_CHART;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_crypto_detail_chart, container, false);

        //getActivity().setTitle("Chart");

        progressDialog = new ProgressDialog(getActivity());
        myWebView = (WebView)v.findViewById(R.id.wvBTC);

        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap bitmap) {
                progressDialog.setMessage("Loading....");
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
        setMyWebView();
        myWebView.loadUrl(URL_CHART);

        return v;
    }

    private void setMyWebView(){
        if(URLAddress.TOOLBAR_NAME.equals("BTS/BTC")){
            URL_CHART = URLAddress.URL_CHART_BTS;
        } else if(URLAddress.TOOLBAR_NAME.equals("DASH/BTC")){
            URL_CHART = URLAddress.URL_CHART_DASH;
        } else if(URLAddress.TOOLBAR_NAME.equals("DOGE/BTC")){
            URL_CHART = URLAddress.URL_CHART_DOGE;
        } else if(URLAddress.TOOLBAR_NAME.equals("ETH/BTC")){
            URL_CHART = URLAddress.URL_CHART_ETH;
        } else if(URLAddress.TOOLBAR_NAME.equals("LTC/BTC")){
            URL_CHART = URLAddress.URL_CHART_LTC;
        } else if(URLAddress.TOOLBAR_NAME.equals("NXT/BTC")){
            URL_CHART = URLAddress.URL_CHART_NXT;
        } else if(URLAddress.TOOLBAR_NAME.equals("STR/BTC")){
            URL_CHART = URLAddress.URL_CHART_STR;
        } else if(URLAddress.TOOLBAR_NAME.equals("NEM/BTC")){
            URL_CHART = URLAddress.URL_CHART_NEM;
        } else if(URLAddress.TOOLBAR_NAME.equals("XRP/BTC")){
            URL_CHART = URLAddress.URL_CHART_XRP;
        }
    }
}
