package com.dtu.smmac.galgespil2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NoInternet extends Activity implements View.OnClickListener {

    private Button accept, close;
    private TextView noInternet;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);

        accept = (Button) findViewById(R.id.acceptB);

        close = (Button) findViewById(R.id.close);

        noInternet = (TextView) findViewById(R.id.noInternet);


        accept.setOnClickListener(this);

        close.setOnClickListener(this);

        noInternet.setText("Du har ikke forbindelse til internette og vil derfor ikke få det fulde ud af denne applikation." + "\n" +
                "Vil du fortsætte med at bruge denne applikation med en mindre ordliste?");

        i = new Intent(this, Main.class);
    }

    @Override
    public void onClick(View v) {

        if(v == accept) {

            startActivity(i);
        }
        else if (v == close)
        {
            finish();
            System.exit(0);
        }
    }
}
