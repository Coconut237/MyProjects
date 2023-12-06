package com.example.myapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.data.Movie;
import com.example.myapp.data.MovieRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Documentation of MovieRecyclerViewAdapter Class: The adapter connects data to the RecyclerView.
 * It receives data and makes it displayable in a view.
 * @author Cordola O'Brien
 */
public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>{

    //Context the adapter is working in
    private Context context;

    //Movies that are going to be displayed
    private List<Movie> movies;

    //TAG for logging
    private static final String TAG = "MovieRecyclerViewAdapter";

    public MovieRecyclerViewAdapter(Context context, List<Movie> movies){
        super();
        this.context = context;
        this.movies = movies;
    }

    /**
     * Set Movie List
     * @param movies
     */
    public void setMovies(List<Movie> movies){
        this.movies = movies;
    }

    /**
     * Get Movie List
     * @return List<Movie> List of Movies
     */
    public List<Movie> getMovies(){
        return this.movies;
    }

    @Override
    public int getItemCount(){
        return movies.size();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        //Inflate the layout file for the row
        View itemView = LayoutInflater.from(this.context).inflate(R.layout.item, parent, false);

        //Store it in a ViewHolder
        MovieViewHolder viewHolder = new MovieViewHolder (itemView, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position){
        //Get the movie at position
        Movie movie = this.movies.get(position);

        //Update the movie title
        TextView title = holder.itemView.findViewById(R.id.movieItemTitle);
        title.setText(movie.getTitle());
    }

    /**
     * MovieViewHolder Class: The ViewHolder holds a view item & info about
     * its place in the RecyclerView. Each ViewHolder holds one Set of Data.
     * @author Cordola O'Brien
     */
    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //View of item
        private View itemView;
        //Associated adapter
        private MovieRecyclerViewAdapter adapter;

        public MovieViewHolder(View itemView, MovieRecyclerViewAdapter adapter){
            super(itemView);
            this.itemView = itemView;
            this.adapter = adapter;

            //Add a listener to the button in the taskItemView
            itemView.findViewById(R.id.deleteButton).setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            //Get the clicked item's position
            int position = getAdapterPosition();

            //Get the movie at that position
            Movie movie = this.adapter.movies.get(position);

            //If button is clicked, remove item from List
            if(view.getId()==R.id.deleteButton){
                Log.d("Movie_Recycler", "User deleted "+movie.getTitle());

                //Popup-Message that movie is deleted
                Toast.makeText(view.getContext(), "movie deleted", Toast.LENGTH_LONG).show();

                //Remove item
                removeAt(getPosition());

                //Remove movie from DataBase
                MovieRepository repo = MovieRepository.getRepository(context);
                repo.deleteMovie(movie);
                movies = repo.getAllMovies();
            //If clicked on item, direct to item's movie landing page
            }else{
                Log.d("Movie_Recycler", "user clicked on movie"+movie.getTitle());

                //Create Bundle to pass data to landing Page Fragment
                Bundle bundle = new Bundle();
                //Specify movie to search
                bundle.putString("MovieName", movie.getTitle());

                //Navigate to landing Page Fragment
                Navigation.findNavController(view).navigate(R.id.fragment_landingPage, bundle);
            }
        }
    }

    /**
     * Function for removing Item from position
     * @param position
     */
    public void removeAt(int position){
        movies.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, movies.size());
    }
}
