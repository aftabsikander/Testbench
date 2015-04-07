package com.ftinc.testbench.api.model;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.ForeignKey;
import ollie.annotation.Table;

import static ollie.annotation.ForeignKey.ReferentialAction.CASCADE;

@Table("urls")
public class Urls extends Model{

    @Column("type")
    public String type;

    @Column("url")
    public String url;

    @Column("owner")
    @ForeignKey(
            onDelete = CASCADE,
            onUpdate = CASCADE
    )
    public Model owner;

}
