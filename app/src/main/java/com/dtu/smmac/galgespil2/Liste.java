package com.dtu.smmac.galgespil2;


import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A placeholder fragment containing a simple view.
 */
public class Liste extends Fragment implements AdapterView.OnItemClickListener {

    private ListView list;
    private ArrayList<String> ord;

    public Liste() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rod = inflater.inflate(R.layout.fragment_liste, container, false);

        // *** Sætter ListView ***
        list = (ListView) rod.findViewById(R.id.listView);

        // *** Sætter ArrayList ***
        ord = Splash_aktivitet.game.getMuligeOrd();

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
