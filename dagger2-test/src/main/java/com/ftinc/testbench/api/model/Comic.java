package com.ftinc.testbench.api.model;

import java.util.ArrayList;

public class Comic{
	
    public String variantDescription;
    public String modified;
    public Image thumbnail;
    public String description;
    public String title;
    public String diamondCode;
    public String issn;
    public String upc;
    public int pageCount;
    public int id;
    public String ean;
    public String format;
    public String resourceURI;
    public String isbn;
    public int issueNumber;
    public int digitalId;
    public Item series;

    public ArrayList<MarvelDate> dates;
    public ArrayList<Urls> urls;
    public ArrayList<String> variants;
    public ArrayList<String> collections;
    public ArrayList<String> collectedIssues;
    public ArrayList<Prices> prices;
    public ArrayList<Image> images;
    public ArrayList<TextObject> textObjects;

    public MetaList stories;
    public MetaList characters;
    public MetaList events;
    public MetaList creators;
    

}
