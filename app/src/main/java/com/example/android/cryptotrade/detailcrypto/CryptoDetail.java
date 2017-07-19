package com.example.android.cryptotrade.detailcrypto;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.android.cryptotrade.R;
import com.example.android.cryptotrade.utilities.SaveData;
import com.example.android.cryptotrade.utilities.URLAddress;

public class CryptoDetail extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private String subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(1);

        getSupportActionBar().setTitle(URLAddress.TOOLBAR_NAME);
        SetSubtitle();
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

    }

    private void SetSubtitle(){
        if(URLAddress.TOOLBAR_NAME.equals("BTS/BTC")){
            subtitle = SaveData.PriceBTS + " BTC";
        } else if(URLAddress.TOOLBAR_NAME.equals("DASH/BTC")){
            subtitle = SaveData.PriceDASH + " BTC";
        } else if(URLAddress.TOOLBAR_NAME.equals("DOGE/BTC")){
            subtitle = SaveData.PriceDOGE + " BTC";
        }else if(URLAddress.TOOLBAR_NAME.equals("ETH/BTC")){
            subtitle = SaveData.PriceETH +" BTC";
        } else if(URLAddress.TOOLBAR_NAME.equals("LTC/BTC")){
            subtitle = SaveData.PriceLTC +" BTC";
        } else if(URLAddress.TOOLBAR_NAME.equals("NXT/BTC")){
            subtitle = SaveData.PriceNXT +" BTC";
        } else if(URLAddress.TOOLBAR_NAME.equals("STR/BTC")){
            subtitle = SaveData.PriceSTR +" BTC";
        } else if(URLAddress.TOOLBAR_NAME.equals("NEM/BTC")){
            subtitle = SaveData.PriceNEM +" BTC";
        } else if(URLAddress.TOOLBAR_NAME.equals("XRP/BTC")){
            subtitle = SaveData.PriceXRP +" BTC";
        } else {
            subtitle = "NaN BTC";
        }

        getSupportActionBar().setSubtitle(subtitle);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    FragmentTabTrades tab1 = new FragmentTabTrades();
                    return tab1;
                case 1:
                    FragmentTabChart tab2 = new FragmentTabChart();
                    return tab2;
                case 2:
                    FragmentTabDepths tab3 = new FragmentTabDepths();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "LAST TRADES";
                case 1:
                    return "CHART";
                case 2:
                    return "ORDER BOOK";
            }
            return null;
        }
    }
}
