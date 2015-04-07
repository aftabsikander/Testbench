package com.ftinc.testbench.api;

import com.ftinc.testbench.api.model.Character;
import com.ftinc.testbench.api.model.Comic;
import com.ftinc.testbench.api.model.Creator;
import com.ftinc.testbench.api.model.Event;
import com.ftinc.testbench.api.model.MarvelResponse;
import com.ftinc.testbench.api.model.Series;
import com.ftinc.testbench.api.model.Story;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * This is the api service definition for retrofit. Here we will define the entire API out
 * using the Annotated methods of retrofit.
 */
public interface ApiService {

    /***********************************************************************************************
     *
     * Main Requests
     *
     */

    @GET("/characters")
    Observable<MarvelResponse<Character>>
    getCharacters(@Query("name") String name,
                  @Query("nameStartsWith") String nameStartsWith,
                  @Query("limit") int limit,
                  @Query("offset") int offset);

    @GET("/comics")
    Observable<MarvelResponse<Comic>>
    getComics(@Query("title") String title,
              @Query("titleStartsWith") String titleStartsWith,
              @Query("issueNumber") int issueNumber,
              @Query("characters") String characters,
              @Query("limit") int limit,
              @Query("offset") int offset);

    @GET("/creators")
    Observable<MarvelResponse<Creator>>
    getCreators(@Query("firstName") String firstName,
                @Query("middleName") String middleName,
                @Query("lastName") String lastName,
                @Query("suffix") String suffix,
                @Query("limit") int limit,
                @Query("offset") int offset);

    @GET("/events")
    Observable<MarvelResponse<Event>>
    getEvents(@Query("name") String name,
              @Query("nameStartsWith") String nameStartsWith,
              @Query("characters") String characters,
              @Query("limit") int limit,
              @Query("offset") int offset);

    @GET("/series")
    Observable<MarvelResponse<Series>>
    getSeries(@Query("title") String title,
              @Query("titleStartsWith") String titleStartsWith,
              @Query("characters") String characters,
              @Query("limit") int limit,
              @Query("offset") int offset);

    @GET("/stories")
    Observable<MarvelResponse<Story>>
    getStories(@Query("limit") int limit,
               @Query("offset") int offset,
               @Query("comics") String comics,
               @Query("characters") String characters);

    /***********************************************************************************************
     *
     * Character Endpoints
     *
     */

    @GET("/characters/{id}")
    Observable<MarvelResponse<Character>>
    getCharacter(@Path("id") int charId);

    @GET("/characters/{id]/comics")
    Observable<MarvelResponse<Comic>>
    getComicsByCharacter(@Path("id") int charId,
                         @Query("title") String title,
                         @Query("titleStartsWith") String titleStartsWith,
                         @Query("issueNumber") int issueNumber,
                         @Query("characters") String characters,
                         @Query("limit") int limit,
                         @Query("offset") int offset);

    @GET("/characters/{id}/events")
    Observable<MarvelResponse<Event>>
    getEventsByCharacter(@Path("id") int charId,
                         @Query("name") String name,
                         @Query("nameStartsWith") String nameStartsWith,
                         @Query("characters") String characters,
                         @Query("limit") int limit,
                         @Query("offset") int offset);

    @GET("/characters/{id}/series")
    Observable<MarvelResponse<Series>>
    getSeriesByCharacter(@Path("id") int charId,
                         @Query("title") String title,
                         @Query("titleStartsWith") String titleStartsWith,
                         @Query("characters") String characters,
                         @Query("limit") int limit,
                         @Query("offset") int offset);

    @GET("/characters/{id}/stories")
    Observable<MarvelResponse<Story>>
    getStoriesByCharacter(@Path("id") int charId,
                          @Query("limit") int limit,
                          @Query("offset") int offset,
                          @Query("comics") String comics,
                          @Query("characters") String characters);

    /***********************************************************************************************
     *
     * Comic Endpoints
     *
     */

    @GET("/comics/{id}")
    Observable<MarvelResponse<Comic>>
    getComic(@Path("id") int comicId);

    @GET("/comics/{id}/characters")
    Observable<MarvelResponse<Character>>
    getCharactersByComic(@Path("id") int comicId,
                         @Query("name") String name,
                         @Query("nameStartsWith") String nameStartsWith,
                         @Query("limit") int limit,
                         @Query("offset") int offset);

    @GET("/comics/{id}/creators")
    Observable<MarvelResponse<Creator>>
    getCreatorsByComic(@Path("id") int comicId,
                       @Query("firstName") String firstName,
                       @Query("middleName") String middleName,
                       @Query("lastName") String lastName,
                       @Query("suffix") String suffix,
                       @Query("limit") int limit,
                       @Query("offset") int offset);

    @GET("/comics/{id}/events")
    Observable<MarvelResponse<Event>>
    getEventsByComic(@Path("id") int comicId,
                     @Query("name") String name,
                     @Query("nameStartsWith") String nameStartsWith,
                     @Query("characters") String characters,
                     @Query("limit") int limit,
                     @Query("offset") int offset);

    @GET("/comics/{id}/stories")
    Observable<MarvelResponse<Story>>
    getStoriesByComic(@Path("id") int comicId,
                      @Query("limit") int limit,
                      @Query("offset") int offset,
                      @Query("comics") String comics,
                      @Query("characters") String characters);

    /***********************************************************************************************
     *
     * Creator Endpoints
     *
     */


    /***********************************************************************************************
     *
     * Event Endpoints
     *
     */


    /***********************************************************************************************
     *
     * Series Endpoints
     *
     */


    /***********************************************************************************************
     *
     * Story Endpoints
     *
     */




}
