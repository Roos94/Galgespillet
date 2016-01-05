package com.dtu.smmac.galgespil2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NoInternet extends Activity {

    private TextView noInternet;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);

        this.noInternet = (TextView) findViewById(R.id.noInternet);

        this.noInternet.setText("Du har ikke forbindelse til internette og vil derfor ikke få det fulde ud af denne applikation." + "\n" +
                "Vil du fortsætte med at bruge denne applikation med en mindre ordliste?");

        this.i = new Intent(this, Main.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_topbar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void done(MenuItem item)
    {
        startActivity(i);
    }



}
