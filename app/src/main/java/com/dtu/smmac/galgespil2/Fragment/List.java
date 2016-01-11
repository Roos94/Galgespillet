package com.dtu.smmac.galgespil2.Fragment;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.dtu.smmac.galgespil2.Activity.Splash_activity;
import com.dtu.smmac.galgespil2.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A placeholder fragment containing a simple view.
 */
public class List extends Fragment implements AdapterView.OnItemClickListener {

    private ListView list;
    private ArrayList<String> ord;

    public List() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rod = inflater.inflate(R.layout.fragment_list, container, false);

        // *** Sætter ListView ***
        list = (ListView) rod.findViewById(R.id.listView);

        // *** Sætter ArrayList ***
        Splash_activity.game.removeWords();
        ord = Splash_activity.game.getMuligeOrd();

        // *** Sort ArrayList A-Z ***
        Collections.sort(ord, String.CASE_INSENSITIVE_ORDER);

        // *** Sætter ArrayList ind i ListView ***
        list.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, ord));

        // *** Sætter Click på ListView ***
        list.setOnItemClickListener(this);

        return rod;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // *** Alert ved klik på item ***
        Toast.makeText(getActivity(), "Valgt ord: " + list.getItemAtPosition(position), Toast.LENGTH_LONG).show();
    }
}
