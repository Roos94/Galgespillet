package com.dtu.smmac.galgespil2;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Splash_aktivitet extends Activity {

    public static Fragment f1, f2, f3;
    public static Galgelogik game;
    private ImageView img;
    private TextView lille;
    private Intent i;
    private Thread timer;
    private Runnable r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_aktivitet);

        this.game = new Galgelogik();

        this.lille = (TextView) findViewById(R.id.lilleSplash);
        this.lille.setText("Made by: SMMAC");

        // *** Sætter Fragmenter ***
        this.f1 = new GalgeSpil();
        this.f2 = new Liste();
        this.f3 = new Help();

        // *** Sætter Aktivitet ***
        this.i = new Intent(this, Main.class);

        setRunable();

        this.timer = new Thread(this.r);

        getWords();
    }


    public void getWords() {
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object... arg0) {
                try {
                    game.hentOrdFraDr();
                    return "1";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Ordene blev ikke hentet korrekt: " + e;
                }
            }

            @Override
            protected void onPostExecute(Object resultat) {
                if (resultat.equals("1")) {
                    timer.start();

                } else {
                    Toast.makeText(getApplicationContext(), "" + resultat, Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }

    public void setRunable()
    {
        this.r = new Runnable() {
            @Override
            public void run() {
                setTimer();
            }
        };


    }

    public void setTimer()
    {
        SystemClock.sleep(1000);

        startActivity(this.i);

        finish();
    }
}

