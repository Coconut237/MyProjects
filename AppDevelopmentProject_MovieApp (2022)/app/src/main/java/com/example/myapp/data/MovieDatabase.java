package com.example.myapp.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Documentation of DataBase Class: The DataBase is a local Room database
 * that stores the movies for the WatchList.
 * @author Cordola O'Brien
 */
@Database(entities={Movie.class}, version=1)
public abstract class MovieDatabase extends RoomDatabase {

    //Dao for the database
    public abstract MovieDao movieDao();
    //database singelton instance
    private static MovieDatabase INSTANCE;

    public static MovieDatabase getDatabase(final Context context){
        //check if database exists before creating it
        if(INSTANCE==null){
            synchronized (MovieDatabase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(), MovieDatabase.class, "movie_database") //Specify Room database class and database name
                            //Specify migration strategy callback
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            //builds database
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
