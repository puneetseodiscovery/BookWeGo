package com.bookwego.mainActivity.fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bookwego.R;
import com.bookwego.mainActivity.adapters.ResturantsAdapter;
import com.bookwego.mainActivity.fragments.interfaces.IPRestaurantsFragment;
import com.bookwego.mainActivity.fragments.interfaces.IRestaurantsFragment;
import com.bookwego.mainActivity.fragments.presenter.PRestaurantsFragment;
import com.bookwego.mainActivity.fragments.responseModel.RestaurantsCategoriesResponseModel;
import com.bookwego.mainActivity.fragments.responseModel.ResturantsResponseModel;
import com.bookwego.mainActivity.fragments.responseModel.TitleResponseModel;
import com.bookwego.mainActivity.modelClasses.CategoriesModel;
import com.bookwego.mainActivity.adapters.CategoriesAdapter;
import com.bookwego.mainActivity.adapters.SubCategoriesAdapter;
import com.bookwego.utills.SavePref;
import com.bookwego.utills.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantsFragment extends Fragment implements IRestaurantsFragment {

    View view;
    RestaurantsFragment restaurantsFragment;
    RecyclerView recycler_restaurants_categories;
    RecyclerView recycler_restaurants_sub_categories;
    SwipeRefreshLayout SwipeRefreshLayout;
    CategoriesAdapter categoriesAdapter;
    SubCategoriesAdapter subCategoriesAdapter;
    ResturantsAdapter resturantsAdapter;
    Dialog progressDilaog;
    IPRestaurantsFragment ipRestaurantsFragment;
    RelativeLayout layout_empty;
    SavePref savePref;
    String curretLatitude,curretLongitude, link_id="";
    int position;
    public RestaurantsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_restaurants, container, false);
        restaurantsFragment=this;
        savePref=new SavePref(getActivity());
        ipRestaurantsFragment=new PRestaurantsFragment(this);
        Initialization();
        EventListner();
        return view;
    }

    private void Initialization() {

        curretLatitude=savePref.get_address_latitude();
        curretLongitude=savePref.get_address_logitude();

        recycler_restaurants_categories = (RecyclerView) view.findViewById(R.id.recycler_restaurants_categories);
        SwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.SwipeRefreshLayout);
        layout_empty = (RelativeLayout) view.findViewById(R.id.layout_empty);
        recycler_restaurants_sub_categories = (RecyclerView) view.findViewById(R.id.recycler_restaurants_sub_categories);

        if (Utility.isNetworkConnectionAvailable(getActivity())) {
            progressDilaog = Utility.ShowDialog(getActivity());
            /*api call for get resturants title*/
            ipRestaurantsFragment.dogetTitle();

        }

    }

    private void EventListner() {

        SwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,
                android.R.color.holo_green_dark,
                android.R.color.holo_blue_dark);

        SwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (Utility.isNetworkConnectionAvailable(getActivity())) {
                    ipRestaurantsFragment.dogetCategories(link_id);
                    refreshItems();
                }
            }
        });

    }

    void refreshItems() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SwipeRefreshLayout.setRefreshing(false);
            }
        }, 3000);
    }

    @Override
    public void categoriesSuccessReponseFromPresenter(RestaurantsCategoriesResponseModel restaurantsCategoriesResponseModel) {
        progressDilaog.dismiss();

        if (restaurantsCategoriesResponseModel !=null &&  restaurantsCategoriesResponseModel.getData().size()>0){
            layout_empty.setVisibility(View.INVISIBLE);
            recycler_restaurants_sub_categories.setVisibility(View.VISIBLE);
            subCategoriesAdapter = new SubCategoriesAdapter(getActivity(),restaurantsCategoriesResponseModel.getData());
            recycler_restaurants_sub_categories.setLayoutManager(new
                    LinearLayoutManager(getActivity()));
            recycler_restaurants_sub_categories.setAdapter(subCategoriesAdapter);

        }else {
            layout_empty.setVisibility(View.VISIBLE);
            recycler_restaurants_sub_categories.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void categoriesfailedReponseFromPresenter(String message) {
        progressDilaog.dismiss();
    }

    @Override
    public void categoriesEmptyReponseFromPresenter(String message) {
        progressDilaog.dismiss();

        layout_empty.setVisibility(View.VISIBLE);
        recycler_restaurants_sub_categories.setVisibility(View.INVISIBLE);

    }

    @Override
    public void titleSuccessResponseFromPresenter(TitleResponseModel titleResponseModel) {
        progressDilaog.dismiss();
        /* TODO api call for get resturants categories at 0 position for by default */
        ipRestaurantsFragment.dogetCategories(titleResponseModel.getData().get(0).getId());

        if (titleResponseModel!=null && titleResponseModel.getData().size()>0){

            categoriesAdapter = new CategoriesAdapter(getActivity(), titleResponseModel.getData(),RestaurantsFragment.this,0);
            recycler_restaurants_categories.setLayoutManager(new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.HORIZONTAL, false));
            recycler_restaurants_categories.setAdapter(categoriesAdapter);
        }
    }

    @Override
    public void titleFailedResponseFromPresenter(String message) {
        progressDilaog.dismiss();
    }

    @Override
    public void resturantsSuccessReponseFromPresenter(ResturantsResponseModel resturantsResponseModel) {
        progressDilaog.dismiss();

        if (resturantsResponseModel !=null &&  resturantsResponseModel.getData().size()>0){
            layout_empty.setVisibility(View.INVISIBLE);
            recycler_restaurants_sub_categories.setVisibility(View.VISIBLE);
            resturantsAdapter = new ResturantsAdapter(getActivity(),resturantsResponseModel.getData());
            recycler_restaurants_sub_categories.setLayoutManager(new
                    LinearLayoutManager(getActivity()));
            recycler_restaurants_sub_categories.setAdapter(resturantsAdapter);

        }else {
            layout_empty.setVisibility(View.VISIBLE);
            recycler_restaurants_sub_categories.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void resturantsfailedReponseFromPresenter(String message) {
        progressDilaog.dismiss();
    }

    @Override
    public void resturantsEmptyReponseFromPresenter(String message) {
        progressDilaog.dismiss();
        layout_empty.setVisibility(View.VISIBLE);
        recycler_restaurants_sub_categories.setVisibility(View.INVISIBLE);
    }

    /*TODO
    OnTitleClick mehod call in Categories adapter
    * on categories item click by default it call 0 postion
    * for type 1 at 0 position dogetCategories api call for getting all restaurants categories name
    *  (else) for other all postion  get listing of all restaurants based on tittle response and id's like top 50 , new etc*/
    public void OnTitleClick(String link_id) {
        /* TODO
        type 1 for get listing of restaurants categores name, and all call only if type 1
        * else get listing of all restaurants based on tittle response and id's like top 50 , new etc*/
        if (link_id.equals("1")){
            if (Utility.isNetworkConnectionAvailable(getActivity())) {
                progressDilaog = Utility.ShowDialog(getActivity());
                /*type 1 for get listing of restaurants categories name, and all call only if type 1*/
                ipRestaurantsFragment.dogetCategories(link_id);
            }
        }else {
            if (Utility.isNetworkConnectionAvailable(getActivity())) {
                progressDilaog = Utility.ShowDialog(getActivity());
                /*Api call (else) get listing of all restaurants based on tittle response and id's like top 50 , new etc*/
                ipRestaurantsFragment.dogetResturants(link_id,curretLatitude,curretLongitude);
            }
        }
    }

}
