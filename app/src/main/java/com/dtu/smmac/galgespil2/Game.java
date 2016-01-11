package com.dtu.smmac.galgespil2;


import android.app.Activity;
import android.support.v4.app.Fragment;
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
 * Created by Anders Thostrup Thomsen
 */

public class Game extends Fragment implements View.OnClickListener {

    // *** GUI ***
    private ImageView iv1;
    private Button b1;
    private TextView tv1, tv2, tv3, tv4, tv5;
    private EditText et1;

    // *** Highscore ***
    private HighscoreTimer countDownTimer;
    private final long startTime = 100000;
    private final long intervalTime = 1000;
    static long tempHighscore = 100000;
    private long highscore = 0;
    private boolean timerStartet = false;
    private int playerLevel = 0;
    private long combinedHighscore = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_game, container, false);

        // *** Restart ***
        Splash_activity.game.nulstil();
        Splash_activity.game.removeWords();

        // *** ImageView and Start-screen ***
        iv1 = (ImageView) root.findViewById(R.id.imageView);
        iv1.setImageResource(R.mipmap.galge);

        // *** Button ***
        b1 = (Button) root.findViewById(R.id.buttonGuess);
        b1.setOnClickListener(this);
        b1.setText("GÆT");

        // *** TextViews fields ***
        tv1 = (TextView) root.findViewById(R.id.textViewWord);
        tv2 = (TextView) root.findViewById(R.id.textViewInfo);
        tv3 = (TextView) root.findViewById(R.id.textViewCount);
        tv4 = (TextView) root.findViewById(R.id.textViewUsed);
        tv5 = (TextView) root.findViewById(R.id.textViewTime);

        // *** Start-text in textViews ***
        tv1.setText(Splash_activity.game.getSynligtOrd());
        tv2.setText("Velkommen til Galgespillet");
        tv3.setText("Forkerte gæt tilbage: 6");
        tv4.setText("Brugte bogstaver: ");

        // *** EditText field ***
        et1 = (EditText) root.findViewById(R.id.editTextGuess);
        et1.setText("");
        et1.setHint("Indtast Bogstav");

        // *** Start Check / Control timer after return from other tab ***
        startUpCheck();

        return root;
    }

    @Override
    public void onClick(View v)
    {
        // *** Start game ***
        if (b1.getText().equals("GÆT"))
        {
            timerCheck();

         // *** Error after miss-type (no typing) ***
            if (!et1.getText().toString().matches(""))
            {
                // *** Creating char variable ***
                char c = et1.getText().toString().charAt(0);

                // *** Error after miss-type (anything but single letter) ***
                if (et1.getText().toString().length() == 1 && Character.isLetter(c))
                {
                    // *** Letter used? ***
                    if (!Splash_activity.game.getBrugteBogstaver().contains(et1.getText().toString()))
                    {
                        Splash_activity.game.gætBogstav(et1.getText().toString());

                        // *** Wrong letter ***
                        if (Splash_activity.game.erSidsteBogstavKorrekt() == false)
                        {
                            wrongLetter();

                            // *** Game over ***
                            if (Splash_activity.game.erSpilletTabt())
                            {
                                gameOver();
                            }

                        }   // *** Correct letter ***
                        else if (Splash_activity.game.erSidsteBogstavKorrekt() == true)
                        {
                            tv2.setText("Flot! Godt gættet!");
                            hideSoftKeyboard(getActivity());

                            // *** Game won ***
                            if (Splash_activity.game.erSpilletVundet())
                            {
                                gameWon();
                            }
                        }
                        // *** Show the word if game is over ***
                        showWord();
                    }
                    else
                    {
                        tv2.setText("Bogstavet er brugt!");
                        hideSoftKeyboard(getActivity());
                    }
                }
                else
                {
                    tv2.setText("Indtast venligst et bogstav");
                    hideSoftKeyboard(getActivity());
                }

                et1.setText("");
                tv4.setText("Brugte bogstaver: " + "\n" + Splash_activity.game.getBrugteBogstaver());
            }
            else
            {
                tv2.setText("Indtast venligst et bogstav");
                hideSoftKeyboard(getActivity());
            }
        }
        else if (b1.getText().equals("NYT SPIL"))
        {
            newGame();
        }
        else if (b1.getText().equals("FORTSÆT"))
        {
            nextLevel();
        }
        else if (b1.getText().equals("GEM"))
        {
            saveScore();
            hideSoftKeyboard(getActivity());
        }
    }

    public void gameOver()
    {
        if (combinedHighscore > 0)
        {
            tv2.setText("Desværre! Du har tabt!");
            tv3.setText("Din score blev: " + this.combinedHighscore);
            b1.setText("GEM");
            et1.setVisibility(View.VISIBLE);
            et1.setHint("Indtast Navn");
            tv4.setText("");
            iv1.setImageResource(R.mipmap.tabt);
            countDownTimer.cancel();
            tv5.setText("Du nåede til level: " + playerLevel);
            tv1.setText(Splash_activity.game.getOrdet());
        }
        else
        {
            tv2.setText("Desværre! Du har tabt!");
            iv1.setImageResource(R.mipmap.tabt);
            tv3.setText("");
            b1.setText("NYT SPIL");
            et1.setVisibility(View.INVISIBLE);
            countDownTimer.cancel();
            tv5.setText("");
            tv1.setText(Splash_activity.game.getOrdet());
        }
    }

    public void gameWon()
    {
        highscore = tempHighscore + ((6 - Splash_activity.game.getAntalForkerteBogstaver())*10000);
        combinedHighscore = combinedHighscore + highscore;
        tv2.setText("Tillykke! Du har gættet ordet!!");
        iv1.setImageResource(R.mipmap.vundet);
        playerLevel++;
        tv4.setText("");
        tv3.setText("Dine point: " + combinedHighscore);
        b1.setText("FORTSÆT");
        et1.setVisibility(View.INVISIBLE);
        countDownTimer.cancel();
        tv5.setText("Du har klaret level: " + playerLevel);
    }

    public void newGame()
    {
        Splash_activity.game.nulstil();
        countDownTimer.cancel();
        timerStartet = false;
        tempHighscore = 100000;
        highscore = 0;
        playerLevel = 0;
        combinedHighscore = 0;
        iv1.setImageResource(R.mipmap.galge);
        tv1.setText(Splash_activity.game.getSynligtOrd());
        tv2.setText("Nyt spil!");
        tv4.setText("");
        b1.setText("GÆT");
        tv3.setText("Forkerte gæt tilbage: " + (6 - Splash_activity.game.getAntalForkerteBogstaver()));
        et1.setVisibility(View.VISIBLE);
        tv5.setText("");
    }

    public void nextLevel()
    {
        Splash_activity.game.nulstil();
        countDownTimer.cancel();
        timerStartet = false;
        tempHighscore = 100000;
        highscore = 0;
        iv1.setImageResource(R.mipmap.galge);
        tv1.setText(Splash_activity.game.getSynligtOrd());
        tv2.setText("Fortsæt spil!");
        tv4.setText("");
        b1.setText("GÆT");
        tv3.setText("Forkerte gæt tilbage: " + (6 - Splash_activity.game.getAntalForkerteBogstaver()));
        et1.setVisibility(View.VISIBLE);
        tv5.setText("");
    }

    public void saveScore()
    {
        String scoreName = et1.getText().toString();

        if (scoreName.equals(""))
        {
            tv2.setText("Skriv ét navn!");
        }
        else
        {
            tv2.setText("Spillet er slut!");
            b1.setText("NYT SPIL");
            et1.setVisibility(View.INVISIBLE);
            et1.setHint("Indtast Bogstav");
            et1.setText("");
            tv3.setText(scoreName + ": " + this.combinedHighscore);
            tv4.setText("");
            tv5.setText("");

            // Gem high score
            Splash_activity.db.updateDB(new Person(scoreName, this.combinedHighscore, this.playerLevel));

        }
    }

    public void wrongLetter()
    {
        if (Splash_activity.game.getAntalForkerteBogstaver() == 1) {
            iv1.setImageResource(R.mipmap.forkert1);
        } else if (Splash_activity.game.getAntalForkerteBogstaver() == 2) {
            iv1.setImageResource(R.mipmap.forkert2);
        } else if (Splash_activity.game.getAntalForkerteBogstaver() == 3) {
            iv1.setImageResource(R.mipmap.forkert3);
        } else if (Splash_activity.game.getAntalForkerteBogstaver() == 4) {
            iv1.setImageResource(R.mipmap.forkert4);
        } else if (Splash_activity.game.getAntalForkerteBogstaver() == 5) {
            iv1.setImageResource(R.mipmap.forkert5);
        } else if (Splash_activity.game.getAntalForkerteBogstaver() == 6) {
            iv1.setImageResource(R.mipmap.forkert6);
        }

        tv2.setText("Desværre! Forkert bogstav!");
        tv3.setText("Forkerte gæt tilbage: " + (6 - Splash_activity.game.getAntalForkerteBogstaver()));
        hideSoftKeyboard(getActivity());
    }

    public void showWord()
    {
        if (Splash_activity.game.erSpilletTabt())
        {
            tv1.setText(Splash_activity.game.getOrdet());
        }
        else
        {
            tv1.setText(Splash_activity.game.getSynligtOrd());
        }
    }

    public void startUpCheck()
    {
        if (countDownTimer == null)
        {
            countDownTimer = new HighscoreTimer(startTime, intervalTime);
            timerStartet = false;
        }
        else
        {
            countDownTimer.cancel();
            timerStartet = false;
        }

        tempHighscore = 100000;
        highscore = 0;
        playerLevel = 0;
        combinedHighscore = 0;
    }

    public void timerCheck()
    {
        if (timerStartet == false)
        {
            countDownTimer.start();
            timerStartet = true;
        }
    }

    public void hideSoftKeyboard(Activity activity)
    {
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
            tempHighscore = 0;
            gameOver();
            if (combinedHighscore > 0)
            {
                tv5.setText("Tid: " + 0);
            }
            else
            {
                tv5.setText("Tid: " + 0 + "   Point: " + 0);
            }

        }

        @Override
        public void onTick(long millisUntilFinished)
        {
            tempHighscore = millisUntilFinished;
            tv5.setText("Tid: " + millisUntilFinished / 1000 + "   Point: " + tempHighscore);
        }

    }
}
