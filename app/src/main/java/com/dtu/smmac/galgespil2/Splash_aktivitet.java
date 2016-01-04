package com.dtu.smmac.galgespil2;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Splash_aktivitet extends AppCompatActivity {

    public static Fragment f1, f2, f3;
    public static Galgelogik game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_aktivitet);

        // *** Sætter Fragmenter ***
        f1 = new GalgeSpil();
        f2 = new Liste();
        f3 = new Help();

        getDRwords();

        setTimer();
    }


    public void getDRwords() {
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
                    getFragmentManager().beginTransaction().replace(R.id.fl, f1).commit();

                } else {
                    Toast.makeText(getApplicationContext(), "" + resultat, Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }

    public void setTimer()
    {
        SystemClock.sleep(1000);

        finish();
    }
    }

