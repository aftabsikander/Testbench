package com.ftinc.testbench.api.model;

import java.util.ArrayList;
import java.util.List;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.Table;
import ollie.query.Select;
import rx.Observable;


@Table("metalists")
public class MetaList extends Model{

    @Column("returned")
    public int returned;

    @Column("collectionURI")
    public String collectionURI;

    @Column("available")
    public int available;

    /*
     * This is the list of items that is parsed by LoganSquare
     */
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
//        save();
        for(Item item: items){
            item.owner = this;
            item.save();
        }
    }

}
