package com.dtu.smmac.galgespil2;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Splash_aktivitet extends Activity {

    public static Fragment f1, f2, f3, f4;
    public static Galgelogik game;
    private ImageView img;
    private TextView lille;
    private Intent i, n;
    private Thread timer;
    private Runnable r, sp2, sp3, sp4, sp5, sp6, sp7, sp8, sp9, sp10, sp11, sp12, sp13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_aktivitet);

        this.game = new Galgelogik();

        this.lille = (TextView) findViewById(R.id.lilleSplash);
        this.lille.setText("Made by: SMMAC");

        this.img = (ImageView) findViewById(R.id.img);
        this.img.setImageResource(R.mipmap.p1);

        // *** Sætter Fragmenter ***
        this.f1 = new GalgeSpil();
        this.f2 = new Liste();
        this.f3 = new Help();
        this.f4 = new Highscore();

        // *** Sætter Aktivitet ***
        this.i = new Intent(this, Main.class);
        this.n = new Intent(this, NoInternet.class);

        if(isNetworkAvailable() == true) {

            setRunable();

            this.timer = new Thread(this.r);

            getWords();
        }
        else
        {
            startActivity(this.n);
        }
    }


    public void getWords() {
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object... arg0) {
                try {
                    game.hentOrd();
                    return "1";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Ordene blev ikke hentet korrekt: " + e;
                }
            }

            @Override
            protected void onPostExecute(Object resultat) {
                if (resultat.equals("1")) {
                    game.removeWords();
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

        this.sp2 = new Runnable() {
            @Override
            public void run() {
                img.setImageResource(R.mipmap.p2);
            }
        };

        this.sp3 = new Runnable() {
            @Override
            public void run() {
                img.setImageResource(R.mipmap.p3);
            }
        };

        this.sp4 = new Runnable() {
            @Override
            public void run() {
                img.setImageResource(R.mipmap.p4);
            }
        };

        this.sp5 = new Runnable() {
            @Override
            public void run() {
                img.setImageResource(R.mipmap.p5);
            }
        };

        this.sp6 = new Runnable() {
            @Override
            public void run() {
                img.setImageResource(R.mipmap.p6);
            }
        };

        this.sp7 = new Runnable() {
            @Override
            public void run() {
                img.setImageResource(R.mipmap.p7);
            }
        };

        this.sp8 = new Runnable() {
            @Override
            public void run() {
                img.setImageResource(R.mipmap.p8);
            }
        };

        this.sp9 = new Runnable() {
            @Override
            public void run() {
                img.setImageResource(R.mipmap.p9);
            }
        };

        this.sp10 = new Runnable() {
            @Override
            public void run() {
                img.setImageResource(R.mipmap.p10);
            }
        };

        this.sp11 = new Runnable() {
            @Override
            public void run() {
                img.setImageResource(R.mipmap.p11);
            }
        };

        this.sp12 = new Runnable() {
            @Override
            public void run() {
                img.setImageResource(R.mipmap.p12);
            }
        };

        this.sp13 = new Runnable() {
            @Override
            public void run() {
                img.setImageResource(R.mipmap.p13);
            }
        };

    }

    public void setTimer()
    {
        SystemClock.sleep(200);

        runOnUiThread(this.sp2);
        SystemClock.sleep(200);

        runOnUiThread(this.sp3);
        SystemClock.sleep(200);

        runOnUiThread(this.sp4);
        SystemClock.sleep(200);

        runOnUiThread(this.sp5);
        SystemClock.sleep(200);

        runOnUiThread(this.sp6);
        SystemClock.sleep(200);

        runOnUiThread(this.sp7);
        SystemClock.sleep(200);

        runOnUiThread(this.sp8);
        SystemClock.sleep(200);

        runOnUiThread(this.sp9);
        SystemClock.sleep(200);

        runOnUiThread(this.sp10);
        SystemClock.sleep(200);

        runOnUiThread(this.sp11);
        SystemClock.sleep(200);

        runOnUiThread(this.sp12);
        SystemClock.sleep(200);

        runOnUiThread(this.sp13);
        SystemClock.sleep(300);

        startActivity(this.i);

        finish();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

