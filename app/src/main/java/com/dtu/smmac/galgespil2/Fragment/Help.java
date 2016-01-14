package com.dtu.smmac.galgespil2.Fragment;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dtu.smmac.galgespil2.R;

/**
 * Created by Anders Thostrup Thomsen
 */

public class Help extends Fragment {

    private TextView tvHelp;

    public Help() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rod = inflater.inflate(R.layout.fragment_help, container, false);

        // *** TextView ***
        tvHelp = (TextView) rod.findViewById(R.id.textViewHelp);

        tvHelp.setText("Om spillet:\n" +
                "Galgespillet er et enkeltmands spil. Oprindeligt er spillet hovedsagligt blevet spillet på papir eller en på en tavle. Spillet går ud på at gætte et ord, hvor kun antallet af bogstaver er kendt, lidt i stil med Lykkehjulet. Der gættes på ét bogstav af gangen, hvor de korrekt gættede bogstaver bliver placeret på de respektive pladser i ordet. For hver gang, der gættes forkert, bliver der tegnet en del af en mand, der er ophængt i en galge. Spilleren har 6 forkerte gæt, det vil sige, at når der gættes på et forkert bogstav 7. gang, så vil spillet være tabt. Spillet går ud på at gætte hele ordet inden tegningen af manden bliver færdiggjort eller tiden løber ud." +
                "\n" + "\n" +
                "Point:\n" +
                "Når det første gæt er afgivet, starter tiden. Spilleren starter med 100000 point og har 100 sekunder til rådighed. Efter hvert sekund der går, trækkes der 1000 point fra den givne score. I tillfælde af at tiden løber ud, har spilleren tabt. Som før nævnt, har spilleren 6 forkerte gæt til rådighed, for hvert af de forkerte gæt, som spilleren ikke gør brug af, vil der blive lagt 10000 point til scoren, når ordet er gættet korrekt. Når spilleren har gættet ordet, og hermed klaret det pågældende level, kan spilleren fortsætte til næste level. Her medbringer man de opnåede point. Ved spillets afslutning lægges alle de point, som spilleren har fået for de korrekt gættede ord, sammen til den endelige score." +
                "\n" + "\n" +
                "Highscoreliste:\n" +
                        "Hvis en spiller har gættet et eller flere ord korrekt, giver dette mulighed for, at blive tildelt en plads på den eftertragtede highscoreliste. Spilleren bliver her bedt om, at indtaste sit navn. Herefter vil spillerens navn, opnåede level og point fremgå på highscorelisten. Alt efter hvor mange point, som spilleren har opnået, tildeles denne et tilsvarende trofæ, som ligeledes fremgår på highscorelisten."+
                "\n" + "\n" +
                "Ordliste:\n" +
        "Denne liste indeholde alle de hentede ord, som bruges i spillet. Ordene er hentet fra DR’s hjemmeside (https://www.dr.dk). Ordene har i processen passeret et filter, hvor alle ord under 7 og over 19, vil blive sorteret fra. Ligeledes vil alle ord der ikke indeholder en vokal blive sorteret fra. Da ordene bliver hentet fra en hjemmeside, der løbende bliver opdateret, vil der lejlighedsvis være mulighed for stavefejl, hvilket det opsatte filter ikke fanger."+
                "\n" + "\n" +
                "Udviklet af:\n" +

                "SMMAC (DTU)\n" +
                "\n" +
                "S(teen Bartholdy)\n" +

                "M(artin Roos)\n" +

                "M(ads Kjær Mortensen)\n" +

                "A(nders Thostrup Thomsen)\n" +

                "C(ristoffer John Svendsen)" +

                "\n" + "\n");

        return rod;
    }
}
