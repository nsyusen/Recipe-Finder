package com.example.cs65_final_project.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.cs65_final_project.FirebaseDatabaseHelper;
import com.example.cs65_final_project.Ingredient;
import com.example.cs65_final_project.R;
import com.example.cs65_final_project.activities.ManualAddIngredientActivity;
import com.example.cs65_final_project.activities.SearchAddIngredientActivity;
import com.example.cs65_final_project.adapters.FridgeListViewAdapter;
import com.example.cs65_final_project.adapters.FridgePagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class FridgeFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ArrayList<Ingredient> ingredients;
    private FloatingActionButton searchAdd;
    private FloatingActionButton manualAdd;
    private ListView listView;

    public static final String CATEGORIES_TAG = "categories tag";

    public FridgeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fridge, container, false);

//        listView = view.findViewById(R.id.list_fridge);
        searchAdd = view.findViewById(R.id.search_add_ingredient);
        manualAdd = view.findViewById(R.id.manual_add_ingredient);
        searchAdd.setOnClickListener(this);
        manualAdd.setOnClickListener(this);

        Fragment categories = new FridgeCategoryFragment();
        FragmentManager fm = getChildFragmentManager();
        fm.beginTransaction().add(R.id.current_fridge, categories, CATEGORIES_TAG).commit();

        return view;
    }

    @Override
    public void onResume() {
        Log.d("RecipeFinder", "onResume() called");
        super.onResume();
        if (searchAdd != null){
            searchAdd.setVisibility(View.GONE);
        }
        getIngredientList(); // Retrieve ingredients from Firebase
    }

    /** Animation for spinning manual add button and showing search add button*/
    private void animateButtons(){
        searchAdd.setVisibility(View.VISIBLE);
        searchAdd.setAlpha(0f);
        searchAdd.animate().setDuration(200).alpha(1f).start();

        manualAdd.animate().setDuration(200).rotation(180f).start();
    }

    /**
     * Retrieves ingredients from the database and populates the listView
     */
    public void getIngredientList() {
        // Set up the adapter
//        ingredients = new ArrayList<>();
//        FridgeListViewAdapter demoAdapter = new FridgeListViewAdapter(getActivity(), ingredients);
//        listView.setAdapter(demoAdapter);

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
//                R.array.aisles, android.R.layout.simple_list_item_1);
//        listView.setAdapter(adapter);

        // Update the adapter with the retrieved ingredients
//        FirebaseDatabaseHelper.getIngredients(ingredients, demoAdapter);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}