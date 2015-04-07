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


    /*
     * Un-annotative variables
     */
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

    /**
     * Properly save this character item
     */
    @DebugLog
    public long saveCharacter(){
        long result = save();

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

        return result;
    }

}
