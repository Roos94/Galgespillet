package com.dtu.smmac.galgespil2.Activity;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;

import com.dtu.smmac.galgespil2.Fragment.Game;
import com.dtu.smmac.galgespil2.Fragment.Help;
import com.dtu.smmac.galgespil2.Fragment.Highscore;
import com.dtu.smmac.galgespil2.Fragment.List;
import com.dtu.smmac.galgespil2.R;


// *** Created by Mads Mortensen on 06-01-2016 ***

public class Main extends FragmentActivity {

    private FragmentTabHost mTabHost;
    private HorizontalScrollView hsv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // *** Tilføjer fire tabs til TabHost der hver især indlæser et fragment. ***

        setContentView(R.layout.activity_main);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        hsv = (HorizontalScrollView) findViewById(R.id.hsv);

        mTabHost.addTab(
                mTabHost.newTabSpec("tab1").setIndicator("Spil", null),
                Game.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab2").setIndicator("Ordliste", null),
                List.class, null);
        if (Splash.f4 != null) {
            mTabHost.addTab(
                    mTabHost.newTabSpec("tab3").setIndicator("Highscore", null),
                    Highscore.class, null);
        }
        mTabHost.addTab(
                mTabHost.newTabSpec("tab4").setIndicator("Hjælp", null),
                Help.class, null);

        for (int i = 0; i < 4; i++) {
            mTabHost.setCurrentTab(i);
            mTabHost.getCurrentTabView().setFocusableInTouchMode(true);
        }

        mTabHost.setCurrentTab(0);

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (mTabHost.getCurrentTab() == 1) {
                    hsv.scrollTo(20,0);
                }
                if (mTabHost.getCurrentTab() == 2) {
                    hsv.scrollTo(120,0);
                }
            }
        });

    }

}