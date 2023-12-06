package com.example.myapp.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Documentation of Movie Class: A Movie has an primary key id, title, overview,
 * original language and rating.
 * @author Cordola O'Brien
 */
@Entity(tableName = "Movie")
public class Movie {

    //id for movie
    @NonNull
    @PrimaryKey
    private int id;

    //title for the movie
    @ColumnInfo(name="Title")
    private String title;

    //brief description of movie
    @ColumnInfo(name="Overview")
    private String overview;

    //original language of movie
    @ColumnInfo(name="Language")
    private String originalLanguage;

    //rating of movie
    @ColumnInfo(name="Rating")
    private Double rating;

    public Movie(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
