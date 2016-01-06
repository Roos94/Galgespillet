package com.dtu.smmac.galgespil2;

/**
 * Created by SteenBartholdy on 06-01-2016.
 */
public class Person {

    private String name;
    private long score;
    private int level;

    public Person(String name, long score, int level)
    {
        this.name = name;
        this.score = score;
        this.level = level;
    }

    public String getName()
    {
        return this.name;
    }

    public long getScore()
    {
        return score;
    }

    public int getLevel()
    {
        return level;
    }




}
