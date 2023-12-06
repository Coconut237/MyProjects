package com.example.myapp.data;

import android.content.Context;
import java.util.List;

/**
 * Documentation of MovieRepository Class: The Repository manages the data sources.
 * @author Cordola O'Brien
 */
public class MovieRepository {

    //stores MovieDao for performing CRUD operations on the database
    private MovieDao movieDao;

    //repository singelton instance
    private static MovieRepository INSTANCE;

    //Current context
    private Context context;

    /**
     * Constructor for MovieRepository Class
     * @param context
     * @return MovieRepository
     */
    public static MovieRepository getRepository(Context context){
        if(INSTANCE==null){
            synchronized (MovieRepository.class){
                if(INSTANCE==null){
                    INSTANCE=new MovieRepository();
                    MovieDatabase db = MovieDatabase.getDatabase(context);
                    INSTANCE.movieDao = db.movieDao();
                    INSTANCE.context=context;
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Store movie
     * @param movie
     */
    public void storeMovie(Movie movie){
        movieDao.insert(movie);
    }

    /**
     * Delete movie
     * @param movie
     */
    public void deleteMovie(Movie movie){
        movieDao.delete(movie);
    }

    /**
     * Get List of movies
     * @return List<Movie> List of Movies
     */
    public List<Movie> getAllMovies(){
        return movieDao.getAllMovies();
    }
}
