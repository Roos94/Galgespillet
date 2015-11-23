package com.dtu.smmac.galgespil2;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main extends FragmentActivity implements View.OnClickListener {

    public static Galgelogik game;
    private Button b1, b2, b3;
    private Fragment f1, f2, f3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // *** Opretter nyt Spil-objekt ***
        game = new Galgelogik();
        game.nulstil();

        // *** Sætter Knapper ***
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);

        b1.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);

        // *** Sætter Fragmenter ***
        f1 = new GalgeSpil();
        f2 = new Liste();
        f3 = new Help();

        // *** Hent ord fra DR ***
        getDRwords();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.b1:
                getFragmentManager().beginTransaction().replace(R.id.fl, f1).commit();
                game.nulstil();
                break;
            case R.id.b2:
                getFragmentManager().beginTransaction().replace(R.id.fl, f2).commit();
                break;

            case R.id.b3:
                getFragmentManager().beginTransaction().replace(R.id.fl, f3).commit();
                break;
        }
    }

    public void getDRwords()
    {
        Toast.makeText(getApplicationContext(), "Henter ord fra DRs server....", Toast.LENGTH_LONG).show();

        new AsyncTask() {
            @Override
            protected Object doInBackground(Object... arg0) {
                try {
                    Main.game.hentOrdFraDr();
                    return "1";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Ordene blev ikke hentet korrekt: " + e;
                }
            }

            @Override
            protected void onPostExecute(Object resultat) {
                if (resultat.equals("1"))
                {
                    Toast.makeText(getApplicationContext(), "Ordene blev korrekt hentet fra DR's server.", Toast.LENGTH_LONG).show();
                    getFragmentManager().beginTransaction().replace(R.id.fl,f1).commit();
                    b1.setVisibility(View.VISIBLE);
                    b2.setVisibility(View.VISIBLE);
                }
                else {
                    Toast.makeText(getApplicationContext(), "" + resultat, Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }
}
