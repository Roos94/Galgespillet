package com.dtu.smmac.galgespil2;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

// Hej

public class Main extends FragmentActivity {//implements View.OnClickListener {

    private Button b1, b2, b3, b4;
    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
/*}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // *** Opretter nyt Spil-objekt ***
        getFragmentManager().beginTransaction().replace(R.id.fl, Splash_aktivitet.f1).commit();
        Splash_aktivitet.game.nulstil();

        // *** Sætter Knapper ***
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.b1:
                getFragmentManager().beginTransaction().replace(R.id.fl, Splash_aktivitet.f1).commit();
                Splash_aktivitet.game.nulstil();
                break;
            case R.id.b2:
                getFragmentManager().beginTransaction().replace(R.id.fl, Splash_aktivitet.f2).commit();
                break;

            case R.id.b3:
                getFragmentManager().beginTransaction().replace(R.id.fl, Splash_aktivitet.f3).commit();
                break;

            case R.id.b4:
                getFragmentManager().beginTransaction().replace(R.id.fl, Splash_aktivitet.f4).commit();
                break;
        }
    }*/
