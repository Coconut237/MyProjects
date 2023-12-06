package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapp.data.Movie;
import com.example.myapp.data.MovieRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_watchlist#newInstance} factory method to
 * create an instance of this fragment.
 * Description: This fragment enables to view the WatchList Page.
 *              The user can view the movies of his/her WatchList and can delete movies from the List.
 */
public class fragment_watchlist extends Fragment implements View.OnClickListener{

    private static final String TAG = "MovieRecyclerViewFragment";
    private ArrayList<String> movieNames;
    private List<Movie> movies;
    private MovieRecyclerViewAdapter rvAdapter;

    // The fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public fragment_watchlist() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_watchlist.
     */
    public static fragment_watchlist newInstance(String param1, String param2) {
        fragment_watchlist fragment = new fragment_watchlist();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_recycler_view_activity, container, false);

        //Set OnClickListener to ShareButton
        Button shareButton = view.findViewById(R.id.shareButton);
        shareButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        //Fetch movies stored in Database (WatchList)
        MovieRepository repo = MovieRepository.getRepository(getContext());
        movies = repo.getAllMovies();

        //Get the RecyclerView on the UI
        RecyclerView recyclerView = view.findViewById(R.id.rv_movieRecyclerView);
        //Create a new Adapter for the movies
        rvAdapter = new MovieRecyclerViewAdapter(getContext(), movies);
        //Set the recycler view's adapter
        recyclerView.setAdapter(rvAdapter);
        //Setup the Layout manager on the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onClick(View view){
        if(view.getId() == R.id.shareButton){
            //Fetch movies stored in Database (WatchList)
            MovieRepository repo = MovieRepository.getRepository(getContext());
            movies = repo.getAllMovies();

            //Get movie titles in one string to share
            String watchlistToShare = "My watchlist from MovieApp: ";
            for(Movie m : movies){
                watchlistToShare += "\n"+"• "+m.getTitle();
            }

            //Setting of Intent to share the current watchlist via email or social networking
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, watchlistToShare);
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);

        }else if (view.getId() == R.id.deleteButton){
            //Handled by ViewHolder
        }else{
            Log.d("Task_Recycler", "Item clicked");

            //Create Bundle to pass data to landing Page Fragment
            Bundle bundle = new Bundle();

            //Check if something was actually entered
            bundle.putString("MovieName", movies.get(0).getTitle());

            //Navigate to landing Page Fragment
            Navigation.findNavController(view).navigate(R.id.fragment_landingPage, bundle);
            Log.d("Task_Recycler", "item clicked");
        }
    }
}
