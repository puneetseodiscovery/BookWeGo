package com.bookwego.mainActivity.fragments;


import android.content.Context;
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
import com.bookwego.mainActivity.adapters.ServicesCategoriesAdapter;
import com.bookwego.mainActivity.adapters.ServicesSubCategoriesAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends Fragment {


    View view;

    RecyclerView recycler_services_categories;
    RecyclerView recycler_services_sub_categories;

    List<CategoriesModel> categoriesModelArrayList = new ArrayList<>();
    CategoriesModel categoriesModel;

    ServicesCategoriesAdapter servicesCategoriesAdapter;
    ServicesSubCategoriesAdapter servicesSubCategoriesAdapter;

    public ServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_services, container, false);

        Initialization();
        EventListner();

        return view;
    }


    private void Initialization() {

        recycler_services_categories = (RecyclerView) view.findViewById(R.id.recycler_services_categories);
        recycler_services_sub_categories = (RecyclerView) view.findViewById(R.id.recycler_services_sub_categories);

        servicesCategoriesAdapter = new ServicesCategoriesAdapter(getActivity(), categoriesModelArrayList);
        recycler_services_categories.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        recycler_services_categories.setAdapter(servicesCategoriesAdapter);

        prepareCategoriesData();

        servicesSubCategoriesAdapter = new ServicesSubCategoriesAdapter(getActivity());
        recycler_services_sub_categories.setLayoutManager(new
                LinearLayoutManager(getActivity()));
        recycler_services_sub_categories.setAdapter(servicesSubCategoriesAdapter);
        runLayoutAnimation(recycler_services_sub_categories);


    }

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
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

        servicesCategoriesAdapter.notifyDataSetChanged();
    }

    private void EventListner() {

    }


}
