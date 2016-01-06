package com.dtu.smmac.galgespil2;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;

// *** Created by Mads Mortensen on 06-01-2016 ***

public class Main extends FragmentActivity {//implements View.OnClickListener {

    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // *** Tilføjer fire tabs til TabHost der hver især indlæser et fragment. ***

        setContentView(R.layout.activity_main);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(
                mTabHost.newTabSpec("tab1").setIndicator("Spil", null),
                GalgeSpil.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab2").setIndicator("Ordliste", null),
                Liste.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab3").setIndicator("Highscore", null),
                Highscore.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab4").setIndicator("Hjælp", null),
                Help.class, null);
    }
    }
