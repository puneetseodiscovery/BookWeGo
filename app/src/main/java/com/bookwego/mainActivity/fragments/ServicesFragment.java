package com.bookwego.mainActivity.fragments;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bookwego.R;
import com.bookwego.mainActivity.adapters.CategoriesAdapter;
import com.bookwego.mainActivity.adapters.ServicesResturantsAdapter;
import com.bookwego.mainActivity.fragments.interfaces.IPServicesFragment;
import com.bookwego.mainActivity.fragments.interfaces.IServicesFragment;
import com.bookwego.mainActivity.fragments.presenter.PServicesFragment;
import com.bookwego.mainActivity.fragments.responseModel.ResturantsResponseModel;
import com.bookwego.mainActivity.fragments.responseModel.ServicesCategoriesResponseModel;
import com.bookwego.mainActivity.fragments.responseModel.ServicesResturantsReponseModel;
import com.bookwego.mainActivity.fragments.responseModel.TitleResponseModel;
import com.bookwego.mainActivity.modelClasses.CategoriesModel;
import com.bookwego.mainActivity.adapters.ServicesCategoriesAdapter;
import com.bookwego.mainActivity.adapters.ServicesSubCategoriesAdapter;
import com.bookwego.utills.SavePref;
import com.bookwego.utills.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends Fragment implements IServicesFragment {
    View view;
    RecyclerView recycler_services_categories;
    RecyclerView recycler_services_sub_categories;
    ServicesCategoriesAdapter servicesCategoriesAdapter;
    ServicesSubCategoriesAdapter servicesSubCategoriesAdapter;
    Dialog progressDilaog;
    IPServicesFragment ipServicesFragment;
    Context context;
    SavePref savePref;
    ServicesResturantsAdapter servicesResturantsAdapter;
    String curretLatitude,curretLongitude;
    RelativeLayout layout_empty;
    public ServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_services, container, false);
        context = getActivity();
        savePref=new SavePref(context);
        Initialization();
        EventListner();

        return view;
    }

    private void Initialization() {

        curretLatitude=savePref.get_address_latitude();
        curretLongitude=savePref.get_address_logitude();

        ipServicesFragment = new PServicesFragment(this);

        recycler_services_categories = (RecyclerView) view.findViewById(R.id.recycler_services_categories);
        recycler_services_sub_categories = (RecyclerView) view.findViewById(R.id.recycler_services_sub_categories);
        layout_empty = (RelativeLayout) view.findViewById(R.id.layout_empty);

        if (Utility.isNetworkConnectionAvailable(getActivity())) {
            progressDilaog = Utility.ShowDialog(getActivity());
            /*api for getting all servies tittle*/
            ipServicesFragment.dogetTitle();

        }

    }

    private void EventListner() {

    }

    @Override
    public void titleSuccessResponseFromPresenter(TitleResponseModel titleResponseModel) {
        progressDilaog.dismiss();
        /* TODO api call at 0 position to show the 1st item seleced by default and show services categoies on categories based*/
        ipServicesFragment.dogetCategories(titleResponseModel.getData().get(0).getId(),curretLatitude,curretLongitude);

        if (titleResponseModel != null && titleResponseModel.getData().size() > 0) {

            servicesCategoriesAdapter = new ServicesCategoriesAdapter(getActivity(), titleResponseModel.getData(), ServicesFragment.this,0);
            recycler_services_categories.setLayoutManager(new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.HORIZONTAL, false));
            recycler_services_categories.setAdapter(servicesCategoriesAdapter);

        }
    }

    @Override
    public void titleFailedResponseFromPresenter(String message) {
        progressDilaog.dismiss();
    }

    @Override
    public void categoriesSuccessReponseFromPresenter(ServicesCategoriesResponseModel servicesCategoriesResponseModel) {
        progressDilaog.dismiss();

        if (servicesCategoriesResponseModel != null && servicesCategoriesResponseModel.getData().size() > 0) {
            layout_empty.setVisibility(View.INVISIBLE);
            recycler_services_sub_categories.setVisibility(View.VISIBLE);
            servicesSubCategoriesAdapter = new ServicesSubCategoriesAdapter(getActivity(), servicesCategoriesResponseModel.getData());
            recycler_services_sub_categories.setLayoutManager(new
                    LinearLayoutManager(getActivity()));
            recycler_services_sub_categories.setAdapter(servicesSubCategoriesAdapter);

        } else {
            layout_empty.setVisibility(View.VISIBLE);
            recycler_services_sub_categories.setVisibility(View.INVISIBLE);
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
        recycler_services_sub_categories.setVisibility(View.INVISIBLE);

    }

    @Override
    public void ServicesResturantsSuccessReponseFromPresenter(ServicesResturantsReponseModel resturantsResponseModel) {
        progressDilaog.dismiss();
        if (resturantsResponseModel != null && resturantsResponseModel.getData().size() > 0) {
            layout_empty.setVisibility(View.INVISIBLE);
            recycler_services_sub_categories.setVisibility(View.VISIBLE);
            servicesResturantsAdapter = new ServicesResturantsAdapter(getActivity(), resturantsResponseModel.getData());
            recycler_services_sub_categories.setLayoutManager(new
                    LinearLayoutManager(getActivity()));
            recycler_services_sub_categories.setAdapter(servicesResturantsAdapter);

        } else {
            layout_empty.setVisibility(View.VISIBLE);
            recycler_services_sub_categories.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void ServicesResturantsfailedReponseFromPresenter(String message) {
        progressDilaog.dismiss();

    }

    @Override
    public void ServicesResturantsEmptyReponseFromPresenter(String message) {

        progressDilaog.dismiss();
        layout_empty.setVisibility(View.VISIBLE);
        recycler_services_sub_categories.setVisibility(View.INVISIBLE);
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();

    }
    /*TODO
    onTitleClickListner mehod call in ServicesCategoriesAdapter
    * by default 0 position link_id type 1 selected and shows all the services categories listing
    * link_id type 1 at 0 pos api call get services categories listing
    * another all types api call get all services resturants this shows all the services restaurats listing based on loaction, new , etc*/
    public void onTitleClickListner(String link_id) {
        if (link_id.equals("1")) {
            if (Utility.isNetworkConnectionAvailable(getActivity())) {
                progressDilaog = Utility.ShowDialog(getActivity());
                /*api call when link_id = 1 for getting the list of services categories*/
                ipServicesFragment.dogetCategories(link_id,curretLatitude,curretLongitude);
            }

        } else {

            if (Utility.isNetworkConnectionAvailable(getActivity())) {
                progressDilaog = Utility.ShowDialog(getActivity());
                /*api call when link_id not equal ==1  for getting the list of services restaurants */
                ipServicesFragment.dogetServicesResturants(link_id,curretLatitude,curretLongitude);
            }
        }
    }
}
