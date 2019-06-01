package com.bookwego.mainActivity.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.bookwego.R;
import com.bookwego.mainActivity.modelClasses.CategoriesModel;
import com.bookwego.mainActivity.adapters.CategoriesAdapter;
import com.bookwego.mainActivity.adapters.SubCategoriesAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantsFragment extends Fragment {

    View view;
    RestaurantsFragment context;
    RecyclerView recycler_restaurants_categories;
    RecyclerView recycler_restaurants_sub_categories;
    CategoriesAdapter categoriesAdapter;
    SubCategoriesAdapter subCategoriesAdapter;
    List<CategoriesModel> categoriesModelArrayList = new ArrayList<>();
    CategoriesModel categoriesModel;

    public RestaurantsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_restaurants, container, false);
        Initialization();
        EventListner();
        return view;
    }


    private void Initialization() {

        recycler_restaurants_categories = (RecyclerView) view.findViewById(R.id.recycler_restaurants_categories);
        recycler_restaurants_sub_categories = (RecyclerView) view.findViewById(R.id.recycler_restaurants_sub_categories);

        categoriesAdapter = new CategoriesAdapter(getActivity(), categoriesModelArrayList);
        recycler_restaurants_categories.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        recycler_restaurants_categories.setAdapter(categoriesAdapter);

        prepareCategoriesData();

        subCategoriesAdapter = new SubCategoriesAdapter(getActivity());
        recycler_restaurants_sub_categories.setLayoutManager(new
                LinearLayoutManager(getActivity()));
        recycler_restaurants_sub_categories.setAdapter(subCategoriesAdapter);


    }

    private void prepareCategoriesData() {

        categoriesModel = new CategoriesModel("Categories");
        categoriesModelArrayList.add(categoriesModel);

        categoriesModel = new CategoriesModel("Locations");
        categoriesModelArrayList.add(categoriesModel);

        categoriesModel = new CategoriesModel("Top 50");
        categoriesModelArrayList.add(categoriesModel);

        categoriesModel = new CategoriesModel("New");
        categoriesModelArrayList.add(categoriesModel);

        categoriesModel = new CategoriesModel("A to Z");
        categoriesModelArrayList.add(categoriesModel);

        categoriesAdapter.notifyDataSetChanged();
    }

    private void EventListner() {
    }

}
