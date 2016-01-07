package com.dtu.smmac.galgespil2;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.MotionEvent;
import android.widget.TabHost;

// *** Created by Mads Mortensen on 06-01-2016 ***

public class Main extends FragmentActivity {//implements View.OnClickListener {

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
    @Override
    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            // when user first touches the screen to swap
            case MotionEvent.ACTION_DOWN:

            {
                lastX = touchevent.getX();
                break;
            }
            case MotionEvent.ACTION_UP: {
                float currentX = touchevent.getX();

                // if left to right swipe on screen
                if (lastX > currentX) {

                    switchTabs(false);
                }

                // if right to left swipe on screen
                if (lastX < currentX) {
                    switchTabs(true);
                }

                break;
            }
        }
        return false;
    }

    public void switchTabs(boolean direction) {
        if (direction) // true = move left
        {
            if (mTabHost.getCurrentTab()==0)
               mTabHost.setCurrentTab(mTabHost.getTabWidget().getTabCount() - 1);
            else
                mTabHost.setCurrentTab(mTabHost.getCurrentTab() - 1);
        } else
        // move right
        {
            if (mTabHost.getCurrentTab() != (mTabHost.getTabWidget().getTabCount() - 1))
                mTabHost.setCurrentTab(mTabHost.getCurrentTab() + 1);
            else
                mTabHost.setCurrentTab(0);
        }
    }


}
