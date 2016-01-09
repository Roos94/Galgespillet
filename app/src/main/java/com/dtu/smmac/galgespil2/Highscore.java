package com.dtu.smmac.galgespil2;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class Highscore extends Fragment {

    private ListView lv;
    private TextView tv;
    private List<Person> personer;

    public static Adapter adap;

    public Highscore() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rod = inflater.inflate(R.layout.fragment_highscore, container, false);

        lv = (ListView) rod.findViewById(R.id.lvHighscore);
        tv = (TextView) rod.findViewById(R.id.tvHighscore);

        tv.setText("Highscore liste:");

        this.personer = new ArrayList<>();

        //Splash_aktivitet.db.updateDB(new Person("Farmand", 899574, 1));


        for(int i = 0; i < Splash_aktivitet.db.getDB().size(); i++)
        {
            personer.add(Splash_aktivitet.db.getDB().get(i));
        }

        setList();

        return rod;
    }


    public void setList() {
        this.adap = new Adapter(getActivity(), personer);
        this.lv.setAdapter(adap);
    }


}
