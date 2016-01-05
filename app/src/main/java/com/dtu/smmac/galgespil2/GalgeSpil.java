package com.dtu.smmac.galgespil2;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.CountDownTimer;

/**
 * A placeholder fragment containing a simple view.
 *
 *
 *
 */
public class GalgeSpil extends Fragment implements View.OnClickListener {

    private ImageView iv1;
    private Button b1;
    private TextView tv1, tv2, tv3, tv4;
    private EditText et1;

    // *** Highscore ***

    private HighscoreTimer countDownTimer;
    private final long startTime = 100000;
    private final long intervalTime = 1000;
    private long highscore;
    private long finalHighscore;


    public GalgeSpil() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rod = inflater.inflate(R.layout.fragment_galgespil, container, false);

        // *** Sætter ImageView og Startbillede ***
        iv1 = (ImageView) rod.findViewById(R.id.imageView);
        iv1.setImageResource(R.mipmap.galge);

        // *** Sætter Knap ***
        b1 = (Button) rod.findViewById(R.id.buttonGuess);
        b1.setOnClickListener(this);
        b1.setText("GÆT");

        // *** Sætter TextViews ***
        tv1 = (TextView) rod.findViewById(R.id.textViewWord);
        tv2 = (TextView) rod.findViewById(R.id.textViewInfo);
        tv3 = (TextView) rod.findViewById(R.id.textViewCount);
        tv4 = (TextView) rod.findViewById(R.id.textViewUsed);

        tv1.setText(Splash_aktivitet.game.getSynligtOrd());
        tv2.setText("Velkommen til Galge-Spillet");
        tv3.setText("Forkerte gæt tilbage: 6");

        // *** Sætter EditText ***
        et1 = (EditText) rod.findViewById(R.id.editTextGuess);
        et1.setText("");
        et1.setHint("Indtast Bogstav");

        // *** Highscore ***
        countDownTimer = new HighscoreTimer(startTime, intervalTime);

        return rod;
    }

    @Override
    public void onClick(View v) {
        // *** Start spil ***
        if (b1.getText().equals("GÆT"))
        {
            countDownTimer.cancel();
            countDownTimer.start();

         // *** Giver fejlbesked ved intet indtastet ***
            if (!et1.getText().toString().matches("")) {
                // *** Opretter char variabel ***
                char c = et1.getText().toString().charAt(0);
                // *** Checker for korrekt indtastning ***
                if (et1.getText().toString().length() == 1 && Character.isLetter(c)) {
                    // *** Tjekker om bogstav er brugt ***
                    if (!Splash_aktivitet.game.getBrugteBogstaver().contains(et1.getText().toString())) {
                        Splash_aktivitet.game.gætBogstav(et1.getText().toString());
                        // *** Ved forkert bogstav ***
                        if (Splash_aktivitet.game.erSidsteBogstavKorrekt() == false) {
                            if (Splash_aktivitet.game.getAntalForkerteBogstaver() == 1) {
                                iv1.setImageResource(R.mipmap.forkert1);
                            } else if (Splash_aktivitet.game.getAntalForkerteBogstaver() == 2) {
                                iv1.setImageResource(R.mipmap.forkert2);
                            } else if (Splash_aktivitet.game.getAntalForkerteBogstaver() == 3) {
                                iv1.setImageResource(R.mipmap.forkert3);
                            } else if (Splash_aktivitet.game.getAntalForkerteBogstaver() == 4) {
                                iv1.setImageResource(R.mipmap.forkert4);
                            } else if (Splash_aktivitet.game.getAntalForkerteBogstaver() == 5) {
                                iv1.setImageResource(R.mipmap.forkert5);
                            } else if (Splash_aktivitet.game.getAntalForkerteBogstaver() == 6) {
                                iv1.setImageResource(R.mipmap.forkert6);
                            }

                            tv2.setText("Desværre! Forkert bogstav!");
                            tv3.setText("Forkerte gæt tilbage: " + (6 - Splash_aktivitet.game.getAntalForkerteBogstaver()));
                            hideSoftKeyboard(getActivity());

                            // *** Når spillet er tabt ***
                            if (Splash_aktivitet.game.erSpilletTabt()) {
                                finalHighscore = 0;
                                System.out.println(finalHighscore);
                                tv2.setText("Desværre! Du har tabt!");
                                iv1.setImageResource(R.mipmap.tabt);
                                tv3.setText("Ordet var: " + Splash_aktivitet.game.getOrdet());
                                b1.setText("NYT SPIL");
                                et1.setVisibility(View.INVISIBLE);
                            }
                            // *** Korrekt gættet bogstav ***
                        } else if (Splash_aktivitet.game.erSidsteBogstavKorrekt() == true) {
                            tv2.setText("Flot! Godt gættet!");
                            hideSoftKeyboard(getActivity());

                            // *** Spillet er vundet ***
                            if (Splash_aktivitet.game.erSpilletVundet()) {
                                finalHighscore = highscore;
                                System.out.println(finalHighscore);
                                tv2.setText("Tillykke! Du har vundet!");
                                iv1.setImageResource(R.mipmap.vundet);
                                if(this.finalHighscore > 0) {
                                    tv3.setText("Highscore: " + this.finalHighscore);
                                    b1.setText("GEM");
                                    et1.setVisibility(View.VISIBLE);
                                    et1.setHint("Indtast Navn");
                                }
                                else
                                {
                                    b1.setText("NYT SPIL");
                                    et1.setVisibility(View.INVISIBLE);
                                }

                            }
                        }
                        tv1.setText(Splash_aktivitet.game.getSynligtOrd());
                    } else {
                        tv2.setText("Bogstavet er brugt!");
                        hideSoftKeyboard(getActivity());
                    }

                } else {
                    tv2.setText("Indtast venligst et bogstav");
                    hideSoftKeyboard(getActivity());
                }

                et1.setText("");
                tv4.setText("Brugte bogstaver: " + "\n" + Splash_aktivitet.game.getBrugteBogstaver());
            }
            else
            {
                tv2.setText("Indtast venligst et bogstav");
                hideSoftKeyboard(getActivity());
            }
        }
        else if (b1.getText().equals("NYT SPIL"))
        {
            // *** Start et nyt spil funktion ***
            Splash_aktivitet.game.nulstil();
            iv1.setImageResource(R.mipmap.galge);
            tv1.setText(Splash_aktivitet.game.getSynligtOrd());
            tv2.setText("Nyt spil!");
            tv4.setText("");
            b1.setText("GÆT");
            tv3.setText("Forkerte gæt tilbage: " + (6 - Splash_aktivitet.game.getAntalForkerteBogstaver()));
            et1.setVisibility(View.VISIBLE);
        }
        else if (b1.getText().equals("GEM"))
        {
            String scoreName = et1.getText().toString();

            if (scoreName.equals(""))
            {
                tv2.setText("Skriv ét navn!");
            }
            else
            {
                tv2.setText("Tillykke! Du har vundet!");
                b1.setText("NYT SPIL");
                et1.setVisibility(View.INVISIBLE);
                et1.setHint("Indtast Bogstav");
                et1.setText("");
                tv3.setText(scoreName + ": " + this.finalHighscore);

                // Gem high score

            }

        }

    }

    public void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);

    }

    public class HighscoreTimer extends CountDownTimer
    {

        public HighscoreTimer( long startTime, long intervalTime )
        {
            super( startTime, intervalTime );
        }

        @Override
        public void onFinish()
        {
            highscore = 0;
        }

        @Override
        public void onTick(long millisUntilFinished)
        {
            highscore = millisUntilFinished - ( Splash_aktivitet.game.getAntalForkerteBogstaver() * 10000 );
        }

    }
}
