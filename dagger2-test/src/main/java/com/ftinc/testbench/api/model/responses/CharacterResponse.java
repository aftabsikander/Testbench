package com.ftinc.testbench.api.model.responses;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.ftinc.testbench.api.model.Character;

import java.util.List;

/**
 * This is a response wrapper w/o generics for the LoganSquare retrofit converter since it
 * isn't compatible with generics
 *
 * Project: Testbench
 * Package: com.ftinc.testbench.api.model
 * Created by drew.heavner on 4/6/15.
 */
@JsonObject
public class CharacterResponse {

    @JsonField public int code;
    @JsonField public String status;
    @JsonField public String copyright;
    @JsonField public String attributionText;
    @JsonField public String attributionHTML;
    @JsonField public Data data;

    /**
     * Request Data Container
     *
     * @param <T>
     */
    @JsonObject
    public static class Data{

        @JsonField public int offset;
        @JsonField public int limit;
        @JsonField public int total;
        @JsonField public int count;
        @JsonField public List<Character> results;

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
