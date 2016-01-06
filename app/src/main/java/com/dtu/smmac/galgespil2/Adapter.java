package com.dtu.smmac.galgespil2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by SteenBartholdy on 06-01-2016.
 */
public class Adapter extends ArrayAdapter<Person> {

    private List<Person> personer;
    private Context context;

    public Adapter (Context c, List<Person> p)
    {
        super(c, R.layout.row_highscore);
        this.personer = p;
        this.context = c;
    }

    public int getCount ()
    {
        return this.personer.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        Holder holder = new Holder();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            row = inflater.inflate(R.layout.row_highscore, null);

            ImageView img = (ImageView) row.findViewById(R.id.image);
            TextView score = (TextView) row.findViewById(R.id.score);
            TextView name = (TextView) row.findViewById(R.id.name);

            holder.scoreView = score;
            holder.nameView = name;
            holder.imgView = img;

            row.setTag(holder);
        }
        else
        {
            holder = (Holder) row.getTag();
        }

        Person p = this.personer.get(position);

        //holder.imgView.setImageResource(p.getLevel());
        holder.scoreView.setText("" + p.getScore());
        holder.nameView.setText(p.getName());

        if(p.getLevel() == 1)
        {
            holder.imgView.setImageResource(p.getLevel());
        }
        else if(p.getLevel() == 2)
        {

        }
        else if(p.getLevel() < 2)
        {

        }



        return row;
    }


    private static class Holder {
        public TextView scoreView;
        public TextView nameView;
        public ImageView imgView;
    }


}
