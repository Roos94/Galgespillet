package com.dtu.smmac.galgespil2.Fragment;


import android.app.Activity;
import android.media.MediaPlayer;
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
import com.dtu.smmac.galgespil2.Activity.Splash;
import com.dtu.smmac.galgespil2.Logic.Person;
import com.dtu.smmac.galgespil2.R;

/**
 *  *** Created by Anders Thostrup Thomsen (S140996) ***
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

    // *** Soundeffects ***ø
    private MediaPlayer applauseEffect, wrongLetterEffect, correctLetterEffect, gameOverEffect, usedLetterEffect, noLetterEffect, timesUpEffect;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_game, container, false);

        // *** Restart ***
        Splash.game.nulstil();
        Splash.game.removeWords();

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
        tv1.setText(Splash.game.getSynligtOrd());
        tv2.setText("Velkommen til Galgespillet");
        tv3.setText("Forkerte gæt tilbage: 6");
        tv4.setText("Brugte bogstaver: ");

        // *** EditText field ***
        et1 = (EditText) root.findViewById(R.id.editTextGuess);
        et1.setText("");
        et1.setHint("Indtast Bogstav");

        // *** Soundeffects ***
        applauseEffect = MediaPlayer.create(getActivity(), R.raw.applause);
        wrongLetterEffect = MediaPlayer.create(getActivity(), R.raw.wrongletter);
        correctLetterEffect = MediaPlayer.create(getActivity(), R.raw.correctletter);
        gameOverEffect = MediaPlayer.create(getActivity(), R.raw.gameover);
        usedLetterEffect = MediaPlayer.create(getActivity(), R.raw.usedletter);
        noLetterEffect = MediaPlayer.create(getActivity(), R.raw.noletter);
        timesUpEffect = MediaPlayer.create(getActivity(), R.raw.timesup);

        // *** Start Check / Control of timer after return from other tab ***
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
                    if (!Splash.game.getBrugteBogstaver().contains(et1.getText().toString()))
                    {
                        Splash.game.gætBogstav(et1.getText().toString());

                        // *** Wrong letter ***
                        if (Splash.game.erSidsteBogstavKorrekt() == false)
                        {
                            wrongLetter();
                            playWrongLetter();

                            // *** Game over ***
                            if (Splash.game.erSpilletTabt())
                            {
                                gameOver();
                                playGameOver();
                            }

                        }   // *** Correct letter ***
                        else if (Splash.game.erSidsteBogstavKorrekt() == true)
                        {
                            tv2.setText("Flot! Godt gættet!");
                            playCorrectLetter();
                            hideSoftKeyboard(getActivity());

                            // *** Game won ***
                            if (Splash.game.erSpilletVundet())
                            {
                                gameWon();
                                playApplause();
                            }
                        }
                        // *** Show the word if game is over ***
                        showWord();
                    }
                    else
                    {
                        tv2.setText("Bogstavet er brugt!");
                        playUsedLetter();
                        hideSoftKeyboard(getActivity());
                    }
                }
                else
                {
                    tv2.setText("Indtast venligst et bogstav");
                    playNoLetter();
                    hideSoftKeyboard(getActivity());
                }

                et1.setText("");
                tv4.setText("Brugte bogstaver: " + "\n" + Splash.game.getBrugteBogstaver());
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
        if (combinedHighscore > 100000 && Splash.f4 != null)
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
            tv1.setText(Splash.game.getOrdet());
        }
        else if (100000 > combinedHighscore && combinedHighscore > 0)
        {
            tv2.setText("Desværre! Du har tabt!");
            iv1.setImageResource(R.mipmap.tabt);
            tv3.setText("Din score blev: " + this.combinedHighscore);
            b1.setText("NYT SPIL");
            et1.setVisibility(View.INVISIBLE);
            tv4.setText("");
            countDownTimer.cancel();
            tv5.setText("Du nåede til level: " + playerLevel);
            tv1.setText(Splash.game.getOrdet());
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
            tv1.setText(Splash.game.getOrdet());
        }
    }

    public void gameWon()
    {
        highscore = tempHighscore + ((6 - Splash.game.getAntalForkerteBogstaver())*10000);
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
        Splash.game.nulstil();
        countDownTimer.cancel();
        timerStartet = false;
        tempHighscore = 100000;
        highscore = 0;
        playerLevel = 0;
        combinedHighscore = 0;
        iv1.setImageResource(R.mipmap.galge);
        tv1.setText(Splash.game.getSynligtOrd());
        tv2.setText("Nyt spil!");
        tv4.setText("");
        b1.setText("GÆT");
        tv3.setText("Forkerte gæt tilbage: " + (6 - Splash.game.getAntalForkerteBogstaver()));
        et1.setVisibility(View.VISIBLE);
        tv5.setText("");
    }

    public void nextLevel()
    {
        Splash.game.nulstil();
        countDownTimer.cancel();
        timerStartet = false;
        tempHighscore = 100000;
        highscore = 0;
        iv1.setImageResource(R.mipmap.galge);
        tv1.setText(Splash.game.getSynligtOrd());
        tv2.setText("Fortsæt spil!");
        tv4.setText("");
        b1.setText("GÆT");
        tv3.setText("Forkerte gæt tilbage: " + (6 - Splash.game.getAntalForkerteBogstaver()));
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
        else if (scoreName.length() > 25)
        {
            tv2.setText("Indtast max. 25 tegn!");
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

            // *** save highscore to database ***
            Splash.db.updateDB(new Person(scoreName, this.combinedHighscore, this.playerLevel));

        }
    }

    public void wrongLetter()
    {
        if (Splash.game.getAntalForkerteBogstaver() == 1) {
            iv1.setImageResource(R.mipmap.forkert1);
        } else if (Splash.game.getAntalForkerteBogstaver() == 2) {
            iv1.setImageResource(R.mipmap.forkert2);
        } else if (Splash.game.getAntalForkerteBogstaver() == 3) {
            iv1.setImageResource(R.mipmap.forkert3);
        } else if (Splash.game.getAntalForkerteBogstaver() == 4) {
            iv1.setImageResource(R.mipmap.forkert4);
        } else if (Splash.game.getAntalForkerteBogstaver() == 5) {
            iv1.setImageResource(R.mipmap.forkert5);
        } else if (Splash.game.getAntalForkerteBogstaver() == 6) {
            iv1.setImageResource(R.mipmap.forkert6);
        }

        tv2.setText("Desværre! Forkert bogstav!");
        tv3.setText("Forkerte gæt tilbage: " + (6 - Splash.game.getAntalForkerteBogstaver()));
        hideSoftKeyboard(getActivity());
    }

    public void showWord()
    {
        if (Splash.game.erSpilletTabt()) {
            tv1.setText(Splash.game.getOrdet());
        }
        else
        {
            tv1.setText(Splash.game.getSynligtOrd());
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

    // *** Sound-effects ***
    public void playApplause()
    {
        applauseEffect.start();
    }

    public void playWrongLetter()
    {
        wrongLetterEffect.start();
    }

    public void playCorrectLetter()
    {
        correctLetterEffect.start();
    }

    public void playGameOver()
    {
        gameOverEffect.start();
    }

    public void playUsedLetter()
    {
        usedLetterEffect.start();
    }

    public void playNoLetter()
    {
        noLetterEffect.start();
    }

    public void playTimesUp()
    {
        timesUpEffect.start();
    }

    public void hideSoftKeyboard(Activity activity)
    {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);

    }

    // *** countdowntimer used for highscore ***

    public class HighscoreTimer extends CountDownTimer
    {

        public HighscoreTimer( long startTime, long intervalTime )
        {
            super( startTime, intervalTime );
        }

        @Override
        public void onFinish()
        {
            playGameOver();
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
            if (millisUntilFinished/1000 == 1)
            {
                playTimesUp();
            }
        }

    }
}
