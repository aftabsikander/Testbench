package com.ftinc.testbench.api.model;

import java.util.List;

import ollie.Model;

/**
 * Project: Testbench
 * Package: com.ftinc.testbench.api.model
 * Created by drew.heavner on 4/6/15.
 */
public class MarvelResponse<T extends Model> {

    public int code;
    public String status;
    public String copyright;
    public String attributionText;
    public String attributionHTML;
    public Data<T> data;

    /**
     * Request Data Container
     *
     * @param <T>
     */
    public static class Data<T extends Model>{

        public int offset;
        public int limit;
        public int total;
        public int count;
        public List<T> results;

    }

}
