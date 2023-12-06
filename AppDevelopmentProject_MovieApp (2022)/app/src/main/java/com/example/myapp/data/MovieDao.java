package com.example.myapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Documentation of MovieDao Interface: This interface defines an API for storing,
 * querying, updating, and deleting Movies in a Room database.
 * @author Cordola O'Brien
 */

@Dao
public interface MovieDao {
    //insert & store movie
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    public void insert(Movie movie);

    //get all movies ordered by title
    @Query("SELECT * from Movie ORDER BY Title ASC")
    public List<Movie> getAllMovies();

    //update movie
    @Update
    public void update(Movie movie);

    //delete movie
    @Delete
    public void delete (Movie movie);
}
