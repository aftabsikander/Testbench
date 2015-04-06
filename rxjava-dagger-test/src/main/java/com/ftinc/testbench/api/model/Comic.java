package com.ftinc.testbench.api.model;

import java.util.ArrayList;

public class Comic{
	
    private String variantDescription;
    private String modified;
    private Image thumbnail;
    private String description;
    private String title;
    private String diamondCode;
    private String issn;
    private String upc;
    private double pageCount;
    private double id;
    private String ean;
    private String format;
    private String resourceURI;
    private String isbn;
    private double issueNumber;
    private double digitalId;

    private ArrayList<MarvelDate> dates;
    private ArrayList<Urls> urls;
    private ArrayList<String> variants;
    private ArrayList<String> collections;
    private ArrayList<String> collectedIssues;
    private ArrayList<Prices> prices;
    private ArrayList<Image> images;
    private ArrayList<TextObject> textObjects;

    private Series series;
    private MetaList stories;
    private MetaList characters;
    private MetaList events;
    private MetaList creators;
    

}
