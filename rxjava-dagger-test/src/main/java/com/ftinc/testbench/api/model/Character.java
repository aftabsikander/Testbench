package com.ftinc.testbench.api.model;

import java.util.ArrayList;

public class Character {

    public double id;
    public String name;
    public String description;
    public String modified;
    public Image thumbnail;
    public String resourceURI;
    public ArrayList<Urls> urls;

    public MetaList events;
    public MetaList series;
    public MetaList stories;
    public MetaList comics;

}
