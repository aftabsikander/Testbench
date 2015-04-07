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
@Table("urls")
public class Urls extends Model{

    @JsonField
    @Column("type")
    public String type;

    @JsonField
    @Column("url")
    public String url;

    @JsonIgnore
    @Column("owner")
    public Model owner;

}
