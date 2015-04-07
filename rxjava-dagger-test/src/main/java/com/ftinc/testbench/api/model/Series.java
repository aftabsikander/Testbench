package com.ftinc.testbench.api.model;

import java.util.ArrayList;

public class Series {

    public int id;
    public String rating;
    public String modified;
    public String resourceURI;
    public String description;
    public String type;
    public int startYear;
    public int endYear;
    public ArrayList<Urls> urls;
    public String title;
    public Item previous;
    public Item next;
    public Image thumbnail;

    public MetaList characters;
    public MetaList events;
    public MetaList stories;
    public MetaList comics;
    public MetaList creators;

}
