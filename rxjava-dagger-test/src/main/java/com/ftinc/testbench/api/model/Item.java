package com.ftinc.testbench.api.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonIgnore;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.ForeignKey;
import ollie.annotation.Table;

import static ollie.annotation.ForeignKey.ReferentialAction.CASCADE;

@JsonObject
@Table("items")
public class Item extends Model{

    @JsonField
    @Column("name")
    public String name;

    @JsonField
    @Column("resourceURI")
    public String resourceURI;

    @JsonField
    @Column("type")
    public String type;

    @JsonIgnore
    @Column("owner")
    public Model owner;

}
