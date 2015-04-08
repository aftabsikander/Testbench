package com.ftinc.testbench.api.model.responses;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

import ollie.Model;

/**
 * Project: Testbench
 * Package: com.ftinc.testbench.api.model
 * Created by drew.heavner on 4/6/15.
 */
public class MarvelResponse<T> {

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
    public static class Data<T>{

        public int offset;
        public int limit;
        public int total;
        public int count;
        public List<T> results;

        @Override
        public String toString() {
            return String.format("Data [offset: %d, limit: %d, total: %d, count: %d]",
                    offset, limit, total, count);
        }
    }

    @Override
    public String toString() {
        return String.format("Marvel Response [%d, %s, %s, %s, %s, %s]",
                code, status, copyright, attributionText, attributionHTML, data.toString());
    }
}
