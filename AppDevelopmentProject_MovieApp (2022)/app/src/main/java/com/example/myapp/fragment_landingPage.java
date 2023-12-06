package com.example.myapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapp.data.Movie;
import com.example.myapp.data.MovieRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_landingPage#newInstance} factory method to
 * create an instance of this fragment.
 * Description: This fragment enables to view the LandingPage with related information of the current movie searched.
 *              It shows the title, rating & overview of the movie searched. A AddButton enables to add the movie to the WatchList.
 */
public class fragment_landingPage extends Fragment implements View.OnClickListener{

    private static final String TAG = "Fragment_LandingPage";
    private Movie currentMovie;
    private List<Movie> movies;

    // The fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public fragment_landingPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_landingPage.
     */
    public static fragment_landingPage newInstance(String param1, String param2) {
        fragment_landingPage fragment = new fragment_landingPage();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_landing_page, container, false);

        //Set OnClickListener to AddButton
        Button addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(this);

        //Fetch name of searched movie by Bundle and apply API_method
        Bundle bundle = this.getArguments();
        if (bundle != null && bundle.getString("MovieName")!=null) {
            String movieToSearch = bundle.getString("MovieName");
                getMovieFromAPI(movieToSearch);
                Log.d("Landing Page", movieToSearch+"");
        }else{
            //If no input from user redirect to SearchPage
            NavController navController = Navigation.findNavController(getActivity().findViewById(R.id.nav_host_fragment));
            navController.navigate(R.id.action_fragment_landingPage_to_fragment_search);
            Toast.makeText(getContext(), "Type a movie to search", Toast.LENGTH_LONG).show();
        }
        return view;
    }


    @Override
    public void onClick(View view){
        if (view.getId() == R.id.addButton){
            //Popup message for movie added successfully
            Toast.makeText(getActivity(), "movie added", Toast.LENGTH_LONG).show();

            //Add movie to
            MovieRepository repo = MovieRepository.getRepository(getContext());
            repo.storeMovie(currentMovie);
            movies = repo.getAllMovies();

            //Make addButton invisible when movie is added to List
            View b = view.findViewById(R.id.addButton);
            b.setVisibility(View.GONE);
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Function to get Data from API
     * @param movieSearched
     */
    private void getMovieFromAPI(String movieSearched){
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());

            //Set URL to get information of movie searched (in JSON Format)
            String url = "https://api.themoviedb.org/3/search/movie?api_key=bbda3e50cde196aeb27c5e7b3372ec02&query=";
            url += movieSearched;

            //Initialise or reset currentMovie
            currentMovie = new Movie();
            Log.d(TAG, "Movie searched: " + movieSearched);

            try {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {

                                    //Fetch provided information from API for specific movie searched
                                    JSONArray resultsArray = response.getJSONArray("results");
                                    JSONObject object = resultsArray.getJSONObject((0));
                                    currentMovie.setId(object.getInt("id"));
                                    currentMovie.setTitle(object.getString("title"));
                                    currentMovie.setOverview(object.getString("overview"));
                                    currentMovie.setOriginalLanguage(object.getString("original_language"));
                                    currentMovie.setRating(object.getDouble("vote_average"));

                                    //Display movie information on LandingPage
                                    setPageView(currentMovie);

                                    //Hide Button if Movie already exists on WatchList
                                    hideAddButtonIfMovieAlreadOnWatchList(currentMovie);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    //If movie not found, redirect to SearchPage
                                    Navigation.findNavController(getView()).navigate(R.id.action_fragment_landingPage_to_fragment_search);
                                    Toast.makeText(getContext(), "Movie not found", Toast.LENGTH_LONG).show();
                                }
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, "Error Response: " + error.toString());
                                if (error instanceof NoConnectionError) {
                                    Toast.makeText(getContext(), "No connection!", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getContext(), "Error - Try again!", Toast.LENGTH_LONG).show();
                                }
                                Navigation.findNavController(getView()).navigate(R.id.action_fragment_landingPage_to_fragment_search);
                            }
                        });

                requestQueue.add(jsonObjectRequest);
            } catch (Exception e) {
                Log.d(TAG, "Exception: " + e.toString());
                Toast.makeText(getContext(), "Error - Try again!", Toast.LENGTH_LONG).show();
            }
    }

    /**
     * Display movie information in View
     * @param currentMovie
     */
    public void setPageView(Movie currentMovie){
        //Set title view
        TextView title = (TextView) getActivity().findViewById(R.id.movieTitle);
        title.setText(currentMovie.getTitle());
        //Set overview view
        TextView overview = (TextView) getActivity().findViewById(R.id.movieOverview);
        overview.setText(currentMovie.getOverview());
        //Set rating view
        TextView rating = (TextView) getActivity().findViewById(R.id.movieRating);
        rating.setText((currentMovie.getRating()).toString());
    }

    /**
     * Check if movie is added to WatchList
     * If movie added: Hide AddButton
     * @param currentMovie
     */
    public void hideAddButtonIfMovieAlreadOnWatchList(Movie currentMovie){
        // Fetch movies from WatchList stored in Room DataBase
        MovieRepository repo = MovieRepository.getRepository(getContext());
        movies = repo.getAllMovies();
        // Hide AddButton if current movie is already added to MovieList
        for (Movie m : movies)
        {
            if(currentMovie.getId()==m.getId()){
                View b = getView().findViewById(R.id.addButton);
                b.setVisibility(View.GONE);
            }
        }
    }
}