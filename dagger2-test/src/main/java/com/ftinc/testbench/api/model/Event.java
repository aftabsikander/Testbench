package com.ftinc.testbench.api.model;

import java.util.ArrayList;

public class Event {
	
    private int id;
    private String start;
    private String description;
    private String resourceURI;
    private String modified;
    private String title;
    private ArrayList<Urls> urls;
    private String end;
    private Image thumbnail;
    private Item next;
    private Item previous;

    private MetaList series;
    private MetaList comics;
    private MetaList creators;
    private MetaList stories;
    private MetaList characters;

}
