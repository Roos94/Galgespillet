package com.dtu.smmac.galgespil2;


import android.app.Fragment;
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
import java.util.List;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class Highscore extends Fragment {

    private ListView lv;
    private TextView tv;
    private List<Person> personer;

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

        this.personer.add(0, new Person("John", 1000, 1));


        return rod;
    }

}
