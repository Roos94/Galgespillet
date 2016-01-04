package com.dtu.smmac.galgespil2;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main extends FragmentActivity implements View.OnClickListener {

    private Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // *** Opretter nyt Spil-objekt ***
        Splash_aktivitet.game = new Galgelogik();
        Splash_aktivitet.game.nulstil();

        // *** Sætter Knapper ***
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);


        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
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
        }
    }

}
