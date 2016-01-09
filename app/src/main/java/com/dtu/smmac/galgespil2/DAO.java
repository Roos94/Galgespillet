package com.dtu.smmac.galgespil2;

import android.content.Context;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by SteenBartholdy on 07-01-2016.
 */
public class DAO {

    private List<Person> personer;
    private ArrayList<Person> nyPersoner;
    private Firebase myFirebaseRef;
    private Query queryRef;

    public DAO(Context c)
    {
        this.personer = new ArrayList<Person>();
        this.nyPersoner = new ArrayList<Person>();
        setDBContext(c);
        this.myFirebaseRef = new Firebase("https://galgehighscore.firebaseio.com/");
    }

    public List<Person> getDao()
    {
        return personer;
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

    public List<Person> getDB()
    {
        myFirebaseRef.child("v0").child("Personer").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {

                  for (DataSnapshot personSnapshot: snapshot.getChildren()) {
                    Person p = personSnapshot.getValue(Person.class);
                    nyPersoner.add(p);
                }

            }

            @Override
            public void onCancelled(FirebaseError error) {
            }

        });

        return nyPersoner;
    }



}

