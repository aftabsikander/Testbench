package com.ftinc.testbench.api.model;

import java.util.ArrayList;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.Table;

@Table("characters")
public class Character extends Model{

    @Column("character_id")
    public int id;

    @Column("name")
    public String name;

    @Column("description")
    public String description;

    @Column("modified")
    public String modified;

    @Column("resourceURI")
    public String resourceURI;

    public Image thumbnail;

    public ArrayList<Urls> urls;

    public MetaList events;
    public MetaList series;
    public MetaList stories;
    public MetaList comics;

}
