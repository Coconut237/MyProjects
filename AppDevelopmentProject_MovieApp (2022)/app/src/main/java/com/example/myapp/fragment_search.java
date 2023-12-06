package com.example.myapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_search#newInstance} factory method to
 * create an instance of this fragment.
 * Description: This fragment enables to view the SearchPage. The user can type in a movie to search.
 *              By clicking on the searchButton, the movie is searched in the API Library.
 *              If searched movie found: directing to LandingPage
 *              If searched movie not found: redirecting to SearchPage
 */
public class fragment_search extends Fragment implements View.OnClickListener{

    // The fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public fragment_search() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_search.
     */
    public static fragment_search newInstance(String param1, String param2) {
        fragment_search fragment = new fragment_search();
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        //Set OnClickListener to SearchButton
        Button getForecastHereButton = view.findViewById(R.id.searchButton);
        getForecastHereButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view){
        if (view.getId() == R.id.searchButton){

            //Get movie searched by user
            EditText movieSearched = getView().findViewById(R.id.searchText);
            String movieName = movieSearched.getText().toString();

            //Create Bundle to pass data to landing Page Fragment
            Bundle bundle = new Bundle();

            //Check if something was actually entered
            if(movieName.length() > 0){
                bundle.putString("MovieName", movieName);
            }
            
            //Navigate to landing Page Fragment
            Navigation.findNavController(view).navigate(R.id.fragment_landingPage, bundle);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
    }

}