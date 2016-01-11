package com.dtu.smmac.galgespil2.Logic;

import android.content.Context;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by SteenBartholdy on 07-01-2016.
 */
public class DAO {

    private ArrayList<Person> person;
    private Firebase myFirebaseRef;
    private Firebase con;
    private Query queryRef;

    public DAO(Context c)
    {
        this.person = new ArrayList<Person>();
        setDBContext(c);
        this.myFirebaseRef = new Firebase("https://galgehighscore.firebaseio.com/");
        con = myFirebaseRef.child("v0").child("Personer");
        queryRef = this.con.orderByChild("score");
    }

    public void setDBContext(Context c) {
        Firebase.setAndroidContext(c);
    }


    public void updateDB(Person p)
    {
        Firebase fxx = myFirebaseRef.child("v0").child("Personer").push();
        p.id = fxx.getKey();
        fxx.setValue(p);

    }

    public void getDB()
    {
        this.person.clear();

        queryRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot personSnapshot : snapshot.getChildren()) {
                    Person p = personSnapshot.getValue(Person.class);
                    person.add(p);
                }
                Collections.reverse(person);

            }

            @Override
            public void onCancelled(FirebaseError error) {
            }

        });
    }

    public List<Person> getList()
    {
        return person;
    }

}
