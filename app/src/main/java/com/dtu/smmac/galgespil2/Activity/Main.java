package com.dtu.smmac.galgespil2.Activity;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;

import com.dtu.smmac.galgespil2.Fragment.Game;
import com.dtu.smmac.galgespil2.Fragment.Help;
import com.dtu.smmac.galgespil2.Fragment.Highscore;
import com.dtu.smmac.galgespil2.Fragment.List;
import com.dtu.smmac.galgespil2.R;


// *** Created by Mads Mortensen on 06-01-2016 ***

public class Main extends FragmentActivity {

    private FragmentTabHost mTabHost;
    private float lastX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // *** Tilføjer fire tabs til TabHost der hver især indlæser et fragment. ***

        setContentView(R.layout.activity_main);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(
                mTabHost.newTabSpec("tab1").setIndicator("Spil", null),
                Game.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab2").setIndicator("Ordliste", null),
                List.class, null);
        if (Splash_activity.f4 != null) {
            mTabHost.addTab(
                    mTabHost.newTabSpec("tab3").setIndicator("Highscore", null),
                    Highscore.class, null);
        }
        mTabHost.addTab(
                mTabHost.newTabSpec("tab4").setIndicator("Hjælp", null),
                Help.class, null);

    }
   /* @Override
    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            // Ved første tryk fra brugeren
            case MotionEvent.ACTION_DOWN:

            {
                lastX = touchevent.getX();
                break;
            }
            case MotionEvent.ACTION_UP: {
                float currentX = touchevent.getX();

                // Hvis der swipes fra venstre mod højre
                if (lastX > currentX) {

                    switchTabs(false);
                }

                // Hvis der swipes fra højre mod venstre
                if (lastX < currentX) {
                    switchTabs(true);
                }

                break;
            }
        }
        return false;
    }

    public void switchTabs(boolean direction) {
        if (direction) // true = flyt til venstre
        {
            if (mTabHost.getCurrentTab()==0)
               mTabHost.setCurrentTab(mTabHost.getTabWidget().getTabCount() - 1);
            else
                mTabHost.setCurrentTab(mTabHost.getCurrentTab() - 1);
        } else
        // flyt til højre
        {
            if (mTabHost.getCurrentTab() != (mTabHost.getTabWidget().getTabCount() - 1))
                mTabHost.setCurrentTab(mTabHost.getCurrentTab() + 1);
            else
                mTabHost.setCurrentTab(0);
        }
    }*/


}
