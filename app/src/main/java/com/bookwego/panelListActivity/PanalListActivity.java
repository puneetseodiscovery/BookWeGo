package com.bookwego.panelListActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.mainActivity.fragments.responseModel.RestaurantsCategoriesResponseModel;
import com.bookwego.panelListActivity.adapters.RestaurantsListAdapter;
import com.bookwego.panelListActivity.adapters.RestaurantsPanelAdapter;
import com.bookwego.panelListActivity.adapters.ServicesListAdapter;
import com.bookwego.panelListActivity.adapters.ServicesPanelAdapter;
import com.bookwego.panelListActivity.interfaces.IPPanalListActivity;
import com.bookwego.panelListActivity.interfaces.IPanalListActivity;
import com.bookwego.panelListActivity.presenters.PPanalListActivity;
import com.bookwego.panelListActivity.responseModel.CategoriesBasedResponseModel;
import com.bookwego.panelListActivity.responseModel.responseRestaurantsCategoriesModel;
import com.bookwego.utills.CommonMethods;
import com.bookwego.utills.Constant;
import com.bookwego.utills.SavePref;
import com.bookwego.utills.Utility;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PanalListActivity extends BaseClass implements IPanalListActivity, View.OnClickListener {

    @BindView(R.id.restaurantsList_recyclerview)
    RecyclerView restaurantsList_recyclerview;

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.img_filter)
    ImageView img_filter;

    @BindView(R.id.img_resturant)
    ImageView img_resturant;

    @BindView(R.id.tv_panel)
    TextView tv_panel;

    @BindView(R.id.tv_list)
    TextView tv_list;

    @BindView(R.id.tv_restaurant_categories)
    TextView tv_restaurant_categories;

    @BindView(R.id.tv_total_restaurants)
    TextView tv_total_restaurants;

    @BindView(R.id.tv_popular_place)
    TextView tv_popular_place;

    @BindView(R.id.layout_empty)
    RelativeLayout layout_empty;

    RestaurantsListAdapter restaurantsListAdapter;
    RestaurantsPanelAdapter restaurantsPanelAdapter;
    ServicesListAdapter servicesListAdapter;
    ServicesPanelAdapter servicesPanelAdapter;

    int position;
    List<RestaurantsCategoriesResponseModel.Datum> data;
    //List<ResturantsResponseModel.Datum> data;
    Context context;
    IPPanalListActivity ipPanalListActivity;
    Dialog progressDialog;
    String recyclerType = "";
    int numberofColumns = 2;

    String Categories_type = "";// for ckeck intent comes from services or restaurants
    String restaurants_Image = "", restaurants_Name = "", restaurants_Count = "", restaurants_Categories_ID = "";
    String services_Image = "", services_Name = "", services_Count = "", services_Categories_ID = "";
    String current_Latitide = "", curret_longitude = "";
    SavePref savePref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panal_list);
        ButterKnife.bind(this);
        context = this;
        Initialization();
        EventListeners();
    }

    private void Initialization() {


        //  type 1 for restaurants and type for 2 services
        Categories_type = getIntent().getStringExtra("Categories_type");

        // get data for Restaurants fragment
        restaurants_Image = getIntent().getStringExtra("restaurants_Image");
        restaurants_Name = getIntent().getStringExtra("restaurants_Name");
        restaurants_Count = getIntent().getStringExtra("restaurants_Count");
        restaurants_Categories_ID = getIntent().getStringExtra("restaurants_Categories_ID");

        //   get data for Services fragment
        services_Image = getIntent().getStringExtra("services_Image");
        services_Name = getIntent().getStringExtra("services_Name");
        services_Count = getIntent().getStringExtra("services_Count");
        services_Categories_ID = getIntent().getStringExtra("services_Categories_ID");

        tv_restaurant_categories.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_total_restaurants.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));


        ipPanalListActivity = new PPanalListActivity(this); //Initialization of interface with presenters
        savePref = new SavePref(this);
        current_Latitide = savePref.get_address_latitude();
        curret_longitude = savePref.get_address_logitude();

        /*TODO
        Set data accourding to type
        * 1 for restaurants Categories
        * 2 for services Categories*/
        //1 for restaurants Categories
        if (Categories_type.equals("1")) {
            Glide.with(context).load(Constant.IMAGE_URL + restaurants_Image)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.no_image_placeholder).into(img_resturant);

            tv_restaurant_categories.setText(CommonMethods.upperCase(restaurants_Name));
            tv_total_restaurants.setText(" (" + restaurants_Count + " " + "Restaurants" + ") ");
            tv_popular_place.setText(getString(R.string.populer_place));

        } else {
            // 2 for services Categories
            Glide.with(context).load(Constant.IMAGE_URL + services_Image)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.no_image_placeholder).into(img_resturant);

            tv_restaurant_categories.setText(CommonMethods.upperCase(services_Name));
            tv_total_restaurants.setText(" (" + services_Count + " " + "Services" + ") ");
            tv_popular_place.setText(getString(R.string.populer_place));
        }

        /* TODO
        recyclerType 1 for grid by default
        * 1 for set data in grid view
        * 2 for set data in listview*/
        recyclerType = "1"; // by default 1 for gridview


        /*
        TODO
        Here Categories_type &&  recyclerType
        * Categories_type 1 for restaurants
        * Categories_type 2 for services
        * recyclerType 1 for set data in grid view
        * recyclerType 2for set data in listview */

        if (Categories_type.equals("1")) {
            if (recyclerType.equals("1")) {
                if (Utility.isNetworkConnectionAvailable(context)) {
                    progressDialog = Utility.ShowDialog(context);
                    // api call for get all restaurants user categories
                    ipPanalListActivity.getRestaurantsCategories(restaurants_Categories_ID,current_Latitide,curret_longitude);
                }
            }
        } else {
            if (recyclerType.equals("1")) {
                if (Utility.isNetworkConnectionAvailable(context)) {
                    progressDialog = Utility.ShowDialog(context);
                    // api call for get all restaurants user categories
                    ipPanalListActivity.getServicesCategories(services_Categories_ID, current_Latitide,curret_longitude);
                }
            }
        }


    }

    private void EventListeners() {

        img_back.setOnClickListener(this);
        tv_panel.setOnClickListener(this);
        tv_list.setOnClickListener(this);
        img_filter.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_panel:
                recyclerType = "1"; //// set data in gridview like panel view
                tv_panel.setBackgroundDrawable(getResources().getDrawable(R.drawable.pannel_bg));
                tv_list.setBackgroundColor(getResources().getColor(R.color.colorWhitebg));

                tv_list.setTextColor(getResources().getColor(R.color.colorTextGreen));
                tv_panel.setTextColor(getResources().getColor(R.color.colorTextWhite));

                /*TODO
                call api method accourding to type
                 * 1 for restaurants Categories
                 * 2 for services Categories*/

                if (Categories_type.equals("1")) {//1 for restaurants Categories

                    if (Utility.isNetworkConnectionAvailable(context)) {
                        progressDialog = Utility.ShowDialog(context);
                        // api call for get all restaurants user categories
                        ipPanalListActivity.getRestaurantsCategories(restaurants_Categories_ID,current_Latitide,curret_longitude);
                    }

                } else {//2 for services Categories
                    if (Utility.isNetworkConnectionAvailable(context)) {
                        progressDialog = Utility.ShowDialog(context);
                        // api call for get all services categories
                        ipPanalListActivity.getServicesCategories(services_Categories_ID,current_Latitide,curret_longitude);
                    }
                }


                break;
            case R.id.tv_list:

                recyclerType = "2"; // set data in like list view
                tv_list.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_list));
                tv_panel.setBackgroundColor(getResources().getColor(R.color.colorWhitebg));

                tv_list.setTextColor(getResources().getColor(R.color.colorTextWhite));
                tv_panel.setTextColor(getResources().getColor(R.color.colorTextGreen));

                /*TODO
                call api method accourding to type
                 * 1 for restaurants Categories
                 * 2 for services Categories*/

                if (Categories_type.equals("1")) { //1 for restaurants Categories

                    if (Utility.isNetworkConnectionAvailable(context)) {
                        progressDialog = Utility.ShowDialog(context);
                        // api call for get all restaurants user categories
                        ipPanalListActivity.getRestaurantsCategories(restaurants_Categories_ID,current_Latitide,curret_longitude);
                    }

                } else {//2 for services Categories*

                    if (Utility.isNetworkConnectionAvailable(context)) {
                        progressDialog = Utility.ShowDialog(context);
                        // api call for get all services categories
                        ipPanalListActivity.getServicesCategories(services_Categories_ID,current_Latitide,curret_longitude);
                    }
                }


                break;
            case R.id.img_filter:

                OpenFilterDialog(); // mehod to call filter dialog on filter click button

                break;

        }
    }

    // mehod to opn filter dialog on filter click button
    private void OpenFilterDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_filter);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.RIGHT;
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        dialog.show();

        ImageView img_close = (ImageView) dialog.findViewById(R.id.img_close);
        TextView tv_filter = (TextView) dialog.findViewById(R.id.tv_filter);
        TextView mostreserved = (TextView) dialog.findViewById(R.id.mostreserved);
        TextView mostnew = (TextView) dialog.findViewById(R.id.mostnew);
        TextView recommending = (TextView) dialog.findViewById(R.id.recommending);
        TextView tv_apply = (TextView) dialog.findViewById(R.id.tv_apply);

        EditText edit_address_location = (EditText) dialog.findViewById(R.id.edit_address_location);
        EditText edit_reserved = (EditText) dialog.findViewById(R.id.edit_reserved);
        EditText edit_mostnew = (EditText) dialog.findViewById(R.id.edit_mostnew);


        tv_filter.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        mostreserved.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        mostnew.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        recommending.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_apply.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_address_location.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_reserved.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_mostnew.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    @Override
    public void onRestaurantsCategoriesSuccessFromPresenter(responseRestaurantsCategoriesModel responseRestaurantsCategoriesModel) {
        progressDialog.dismiss();

        /*TODO
        Here set all the restaurants response with all categories in recyclerview adapter
        1. Based on recyclerview type 1 for panel list (Gridview)
        2. Based on recyclerview type 2 for list  (Listview)*/

        if (recyclerType.equals("1")) { // type 1 for set data in gridview
            if (responseRestaurantsCategoriesModel != null && responseRestaurantsCategoriesModel.getData().size() > 0) {

                restaurantsList_recyclerview.setVisibility(View.VISIBLE);
                layout_empty.setVisibility(View.INVISIBLE);
                restaurantsPanelAdapter = new RestaurantsPanelAdapter(this, responseRestaurantsCategoriesModel.getData());
                restaurantsList_recyclerview.setLayoutManager(new GridLayoutManager(this, numberofColumns));
                restaurantsList_recyclerview.setAdapter(restaurantsPanelAdapter);
            }
        } else if (recyclerType.equals("2")) { // type 2 for set data in listview
            if (responseRestaurantsCategoriesModel != null && responseRestaurantsCategoriesModel.getData().size() > 0) {

                restaurantsList_recyclerview.setVisibility(View.VISIBLE);
                layout_empty.setVisibility(View.INVISIBLE);
                restaurantsListAdapter = new RestaurantsListAdapter(this, responseRestaurantsCategoriesModel.getData());
                restaurantsList_recyclerview.setLayoutManager(new LinearLayoutManager(this));
                restaurantsList_recyclerview.setAdapter(restaurantsListAdapter);
            }
        }
    }

    @Override
    public void onRestaurantsCategoriesFailedFromPresenter(String message) {
        progressDialog.dismiss();
    }

    @Override
    public void onRestaurantsCategoriesEmptyFromPresenter(String message) {
        progressDialog.dismiss();
        restaurantsList_recyclerview.setVisibility(View.INVISIBLE);
        layout_empty.setVisibility(View.VISIBLE);

    }

    @Override
    public void categoriesBasedServicesSuccessReponseFromPresenter(CategoriesBasedResponseModel categoriesBasedResponseModel) {
        progressDialog.dismiss();

         /*TODO
        Here set all the services response with all categories in recyclerview adapter
        1. Based on recyclerview type 1 for panel list (Gridview)
        2. Based on recyclerview type 2 for list  (Listview)*/


        if (recyclerType.equals("1")) { // type 1 for set data in gridview
            if (categoriesBasedResponseModel != null && categoriesBasedResponseModel.getData().size() > 0) {

                restaurantsList_recyclerview.setVisibility(View.VISIBLE);
                layout_empty.setVisibility(View.INVISIBLE);
                servicesPanelAdapter = new ServicesPanelAdapter(this, categoriesBasedResponseModel.getData());
                restaurantsList_recyclerview.setLayoutManager(new GridLayoutManager(this, numberofColumns));
                restaurantsList_recyclerview.setAdapter(servicesPanelAdapter);


            }
        } else if (recyclerType.equals("2")) { // type 2 for set data in listview
            if (categoriesBasedResponseModel != null && categoriesBasedResponseModel.getData().size() > 0) {

                restaurantsList_recyclerview.setVisibility(View.VISIBLE);
                layout_empty.setVisibility(View.INVISIBLE);
                servicesListAdapter = new ServicesListAdapter(this, categoriesBasedResponseModel.getData());
                restaurantsList_recyclerview.setLayoutManager(new LinearLayoutManager(this));
                restaurantsList_recyclerview.setAdapter(servicesListAdapter);
            }
        }
    }

    @Override
    public void categoriesBasedServicesFailedReponseFromPresenter(String message) {
        progressDialog.dismiss();
    }

    @Override
    public void categoriesBasedServicesEmptyReponseFromPresenter(String message) {
        progressDialog.dismiss();
        /*set empty layout if response empty*/
        restaurantsList_recyclerview.setVisibility(View.INVISIBLE);
        layout_empty.setVisibility(View.VISIBLE);
    }
}
