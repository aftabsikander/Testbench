package com.ftinc.testbench.api.model;

import java.util.ArrayList;
import java.util.List;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.ForeignKey;
import ollie.annotation.Table;
import ollie.query.Select;
import rx.Observable;

import static ollie.annotation.ForeignKey.ReferentialAction.CASCADE;

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

    @Column("thumbnail")
    @ForeignKey(
            onDelete = CASCADE,
            onUpdate = CASCADE
    )
    public Image thumbnail;

    @Column("events")
    @ForeignKey(
            onDelete = CASCADE,
            onUpdate = CASCADE
    )
    public MetaList events;

    @Column("series")
    @ForeignKey(
            onDelete = CASCADE,
            onUpdate = CASCADE
    )
    public MetaList series;

    @Column("stories")
    @ForeignKey(
            onDelete = CASCADE,
            onUpdate = CASCADE
    )
    public MetaList stories;

    @Column("comics")
    @ForeignKey(
            onDelete = CASCADE,
            onUpdate = CASCADE
    )
    public MetaList comics;


    /*
     * Un-annotative variables
     */
    public ArrayList<Urls> urls;

    /**
     * Get the list of associated Url's for this object
     * @return
     */
    public Observable<List<Urls>> getUrls(){
        return Select.from(Urls.class)
                .where("owner=?", id)
                .observable();
    }

    /**
     * Properly save this character item
     */
    public void saveCharacter(){
        save();

        if(events != null){
            events.saveMetaList();
        }

        if(series != null){
            series.saveMetaList();
        }

        if(stories != null){
            stories.saveMetaList();
        }

        if(comics != null){
            comics.saveMetaList();
        }

        if(urls != null && !urls.isEmpty()){
            for(Urls url: urls){
                url.owner = this;
                url.save();
            }
        }

    }

}
