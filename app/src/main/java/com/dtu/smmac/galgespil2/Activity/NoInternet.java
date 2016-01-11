package com.dtu.smmac.galgespil2.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.dtu.smmac.galgespil2.Activity.Main;
import com.dtu.smmac.galgespil2.R;

public class NoInternet extends Activity {

    private TextView noInternet;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);

        this.noInternet = (TextView) findViewById(R.id.noInternet);

        this.noInternet.setText("Du har ikke forbindelse til internettet og vil derfor ikke få det fulde ud af denne applikation." + "\n" +
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
