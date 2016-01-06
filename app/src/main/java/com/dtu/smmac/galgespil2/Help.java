package com.dtu.smmac.galgespil2;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class Help extends Fragment {

    private TextView tvHelp;

    public Help() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rod = inflater.inflate(R.layout.fragment_help, container, false);

        // *** Sætter TextView ***
        tvHelp = (TextView) rod.findViewById(R.id.textViewHelp);
        tvHelp.setText("Galgespil:\n" +
                "Spillet går ud på at gætte et skjult ord, hvor personen kun kender længden på ordet. Spilleren gætter på bogstaver, og får svar på om bogstavet er korrekt eller forkert (om bogstavet optræder i ordet). \n" +
                "Korrekte bogstaver bliver placeret på de respektive placer i ordet. \n" +
                "Hver gang der bliver gættet på et forkert bogstav, bliver der tegnet en ekstra ting på galgen/personen. \n" +
                "Spilleren har tabt når denne har brugt 7 forkerte gæt, uden ordet er gættet. \n" +
                "Spillet er vundet hvis hele ordet bliver gættet.");

        return rod;
    }
}
