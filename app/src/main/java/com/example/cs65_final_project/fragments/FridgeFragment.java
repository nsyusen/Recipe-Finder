package com.example.cs65_final_project.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.cs65_final_project.Ingredient;
import com.example.cs65_final_project.R;
import com.example.cs65_final_project.activities.ManualAddIngredientActivity;
import com.example.cs65_final_project.activities.SearchAddIngredientActivity;
import com.example.cs65_final_project.adapters.FridgeListViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class FridgeFragment extends Fragment implements View.OnClickListener{

    private ArrayList<Ingredient> ingredients;
    private FloatingActionButton searchAdd;
    private FloatingActionButton manualAdd;

    public FridgeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fridge, container, false);

        //Demo ingredients
        ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Tomatoes", 5));
        ingredients.add(new Ingredient("Chicken", 200));
        ingredients.add(new Ingredient("Onion", 2));
        ingredients.add(new Ingredient("Salt", 300));
        ingredients.add(new Ingredient("Cabbage", 2));

        ListView listView = view.findViewById(R.id.list_fridge);
        FridgeListViewAdapter demoAdapter = new FridgeListViewAdapter(getActivity(), ingredients);
        listView.setAdapter(demoAdapter);

        searchAdd = view.findViewById(R.id.search_add_ingredient);
        manualAdd = view.findViewById(R.id.manual_add_ingredient);
        searchAdd.setOnClickListener(this);
        manualAdd.setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (searchAdd != null){
            searchAdd.setVisibility(View.GONE);
        }
    }

    /** Animation for spinning manual add button and showing search add button*/
    private void animateButtons(){
        searchAdd.setVisibility(View.VISIBLE);
        searchAdd.setAlpha(0f);
        searchAdd.animate().setDuration(200).alpha(1f).start();

        manualAdd.animate().setDuration(200).rotation(180f).start();
    }

    /** Handles onClick for the floating buttons*/
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.manual_add_ingredient){
            // Second time
            if(searchAdd.getVisibility() != View.GONE){
                Intent intent = new Intent(getActivity(), ManualAddIngredientActivity.class);
                startActivity(intent);
            } else{ // First time it's clicked
                animateButtons();
            }
        }
        else if(view.getId() == R.id.search_add_ingredient){
            Intent intent = new Intent(getActivity(), SearchAddIngredientActivity.class);
            startActivity(intent);
        }
    }
}