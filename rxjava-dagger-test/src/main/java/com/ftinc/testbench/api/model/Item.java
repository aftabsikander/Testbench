package com.ftinc.testbench.api.model;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.ForeignKey;
import ollie.annotation.Table;

import static ollie.annotation.ForeignKey.ReferentialAction.CASCADE;

@Table("items")
public class Item extends Model{

    @Column("name")
    public String name;

    @Column("resourceURI")
    public String resourceURI;

    @Column("type")
    public String type;

    @Column("owner")
    @ForeignKey(
            onDelete = CASCADE,
            onUpdate = CASCADE
    )
    public Model owner;

}
