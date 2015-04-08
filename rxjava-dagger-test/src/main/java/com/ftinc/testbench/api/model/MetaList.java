package com.ftinc.testbench.api.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonIgnore;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.ArrayList;
import java.util.List;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.Table;
import ollie.query.Select;
import rx.Observable;

@JsonObject
@Table("metalists")
public class  MetaList extends Model{

    @JsonField
    @Column("returned")
    public Integer returned;

    @JsonField
    @Column("collectionURI")
    public String collectionURI;

    @JsonField
    @Column("available")
    public Integer available;

    @JsonIgnore
    public String type;

    @JsonIgnore
    @Column("owner")
    public  Model owner;

    @JsonField
    ArrayList<Item> items;

    /**
     * Get an observable for the list of items linked to this
     * meta list
     *
     * @return      the observable for this class' items
     */
    public Observable<List<Item>> getItems(){
        return Select.from(Item.class)
                .where("owner=?", id)
                .observable();
    }

    /**
     * Save this metalist item
     */
    public void saveMetaList(){
        save();
        for(Item item: items){
            item.owner = this;
            item.save();
        }
    }

}
