package com.ftinc.testbench.api.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.ArrayList;
import java.util.List;

import hugo.weaving.DebugLog;
import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.ForeignKey;
import ollie.annotation.Table;
import ollie.query.Select;
import rx.Observable;

import static ollie.annotation.ForeignKey.ReferentialAction.CASCADE;

@JsonObject
@Table("characters")
public class Character extends Model{

    @JsonField(name = "id")
    @Column("character_id")
    public Integer characterId;

    @JsonField
    @Column("name")
    public String name;

    @JsonField
    @Column("description")
    public String description;

    @JsonField
    @Column("modified")
    public String modified;

    @JsonField
    @Column("resourceURI")
    public String resourceURI;


    /***********************************************************************************************
     *
     * These object fields cannot be annotated with columns
     *
     */

    @JsonField
    public Image thumbnail;

    @JsonField
    public MetaList events;

    @JsonField
    public MetaList series;

    @JsonField
    public MetaList stories;

    @JsonField
    public MetaList comics;

    @JsonField
    public ArrayList<Urls> urls;

    /**
     * Get the list of associated U
     * @return
     */
    public Observable<List<Urls>> getUrls(){
        return Select.from(Urls.class)
                .where("owner=?", id)
                .observable();
    }

    public Observable<Image> getThumbnail(){
        return Select.from(Image.class)
                .where("owner=?", id)
                .observableSingle();
    }

    public Observable<MetaList> getEvents(){
        return Select.from(MetaList.class)
                .where("owner=? AND type=?", id, "events")
                .observableSingle();
    }

    public Observable<MetaList> getSeries(){
        return Select.from(MetaList.class)
                .where("owner=? AND type=?", id, "series")
                .observableSingle();
    }

    public Observable<MetaList> getStories(){
        return Select.from(MetaList.class)
                .where("owner=? AND type=?", id, "stories")
                .observableSingle();
    }

    public Observable<MetaList> getComics(){
        return Select.from(MetaList.class)
                .where("owner=? AND type=?", id, "comics")
                .observableSingle();
    }

    /**
     * Properly save this character item
     */
    @DebugLog
    public long saveCharacter(){
        long result = save();

        if(thumbnail != null){
            thumbnail.owner = this;
            thumbnail.save();
        }

        if(events != null){
            events.owner = this;
            events.type = "events";
            events.saveMetaList();
        }

        if(series != null){
            series.owner = this;
            series.type = "series";
            series.saveMetaList();
        }

        if(stories != null){
            stories.owner = this;
            stories.type = "stories";
            stories.saveMetaList();
        }

        if(comics != null){
            comics.owner = this;
            comics.type = "comics";
            comics.saveMetaList();
        }

        if(urls != null && !urls.isEmpty()){
            for(Urls url: urls){
                url.owner = this;
                url.save();
            }
        }

        return result;
    }

}
