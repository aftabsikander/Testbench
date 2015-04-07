package com.ftinc.testbench.api.model;


import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.Table;

@JsonObject
@Table("images")
public class Image extends Model{

    public enum Variant{
        PORTRAIT_SMALL,         // 50x75px
        PORTRAIT_MEDIUM,        // 100x150px
        PORTRAIT_XLARGE,        // 150x225px
        PORTRAIT_FANTASTIC,     // 168x252px
        PORTRAIT_UNCANNY,       // 300x450px
        PORTRAIT_INCREDIBLE,    // 216x324px

        STANDARD_SMALL,         // 65x45px
        STANDARD_MEDIUM,        // 100x100px
        STANDARD_LARGE,         // 140x140px
        STANDARD_XLARGE,        // 200x200px
        STANDARD_FANTASTIC,     // 250x250px
        STANDARD_AMAZING,       // 180x180px

        LANDSCAPE_SMALL,        // 120x90px
        LANDSCAPE_MEDIUM,       // 175x130px
        LANDSCAPE_LARGE,        // 190x140px
        LANDSCAPE_XLARGE,       // 270x200px
        LANDSCAPE_AMAZING,      // 250x156px
        LANDSCAPE_INCREDIBLE,   // 464x261px

        DETAIL,                 // Full, constrained to 500px wide
        FULL_SIZE;              // Full

        /**
         * Generate the Image URL to download/display it based on this give enum
         * type.
         *
         * @param image         the image to build the URL with
         * @return              the complete image url based on enum
         */
        public String createUrl(Image image){
            if(this != FULL_SIZE){
                return String.format("%s/%s.%s", image.path, name().toLowerCase(), image.extension);
            }else{
                return String.format("%s.%s", image.path, image.extension);
            }
        }

    }

    @JsonField
    @Column("path")
    public String path;

    @JsonField
    @Column("extension")
    public String extension;

    @Column("owner")
    public Model owner;

}
