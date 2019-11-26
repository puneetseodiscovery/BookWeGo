package com.bookwego.menuActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.cartActivity.CartActivity;
import com.bookwego.mainActivity.fragments.responseModel.ResturantsResponseModel;
import com.bookwego.menuActivity.interfaces.IMenuActivity;
import com.bookwego.menuActivity.interfaces.IPMenuActivity;
import com.bookwego.menuActivity.model.CountModel;
import com.bookwego.menuActivity.presenter.PMenuActivity;
import com.bookwego.menuActivity.responseModel.ItemAddResponseModel;
import com.bookwego.menuActivity.responseModel.MenuItemsModel;
import com.bookwego.menuActivity.responseModel.MenuResponseModel;
import com.bookwego.menuActivity.adapters.MenuAdapter;
import com.bookwego.menuActivity.adapters.MenuListsAdapter;
import com.bookwego.restaurantsDetailsActivity.RestaurantsDetailsActivity;
import com.bookwego.utills.CommonMethods;
import com.bookwego.utills.Constant;
import com.bookwego.utills.Utility;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends BaseClass implements IMenuActivity, View.OnClickListener {

    @BindView(R.id.recycler_menus)
    RecyclerView recycler_menus;

    @BindView(R.id.recycler_menu_list)
    RecyclerView recycler_menu_list;

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.img_carts)
    ImageView img_carts;

    @BindView(R.id.img_resturant)
    ImageView img_resturant;

    @BindView(R.id.tv_restaurantsName)
    TextView tv_restaurantsName;

    @BindView(R.id.tv_totalrating)
    TextView tv_totalrating;

    @BindView(R.id.tv_loaction)
    TextView tv_loaction;

    @BindView(R.id.layout_empty)
    RelativeLayout layout_empty;

    @BindView(R.id.layout_restaurants)
    RelativeLayout layout_restaurants;

    MenuAdapter menuAdapter;
    MenuListsAdapter menuListsAdapter;

    int position;
    List<ResturantsResponseModel.Datum> data;


    Context context;
    IPMenuActivity ipMenuActivity;
    Dialog progressDilaog;
    String restaurantsId = "", restaurants_Image = "", restaurants_Name = "", restaurants_Address = "", restaurants_AvrageRating = "";
    int numberofColumns = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        Initialization();
        EventListner();
    }

    private void Initialization() {

        context = MenuActivity.this;
        ipMenuActivity = new PMenuActivity(this);

        restaurantsId = getIntent().getStringExtra("restaurantsId");
        restaurants_Image = getIntent().getStringExtra("restaurants_Image");
        restaurants_AvrageRating = getIntent().getStringExtra("restaurants_AvrageRating");
        restaurants_Name = getIntent().getStringExtra("restaurants_Name");
        restaurants_Address = getIntent().getStringExtra("restaurants_Address");


        /*
        position = Integer.parseInt(getIntent().getStringExtra("position"));
        data = getIntent().getParcelableArrayListExtra("data");
*/
        tv_restaurantsName.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_totalrating.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));

        Glide.with(context).load(Constant.IMAGE_URL + restaurants_Image)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.no_image_placeholder).into(img_resturant);

        tv_restaurantsName.setText(CommonMethods.upperCase(restaurants_Name));
        tv_loaction.setText(CommonMethods.upperCase(restaurants_Address));
        tv_totalrating.setText(restaurants_AvrageRating);

        if (Utility.isNetworkConnectionAvailable(context)) {
            progressDilaog = Utility.ShowDialog(context);
            /*api call for get menu list under restaurants*/
            ipMenuActivity.dogetMenu();
        }
    }

    private void EventListner() {
        img_back.setOnClickListener(this);
        layout_restaurants.setOnClickListener(this);
        img_carts.setOnClickListener(this);
    }   

    @Override
    public void onMenuSuccessResponseFromPresenter(MenuResponseModel menuResponseModel) {
        progressDilaog.dismiss();
        //restaurants_Menu_ID=menuResponseModel.getData().get(position).getId();
        ipMenuActivity.dogetmenuItem(menuResponseModel.getData().get(position).getId(), restaurantsId);
        if (menuResponseModel != null && menuResponseModel.getData().size() > 0) {
            menuAdapter = new MenuAdapter(this, menuResponseModel.getData(), MenuActivity.this, 0);
            recycler_menus.setLayoutManager(new LinearLayoutManager(this,
                    LinearLayoutManager.HORIZONTAL, false));
            recycler_menus.setHasFixedSize(true);
            recycler_menus.setAdapter(menuAdapter);

        } else {

        }
    }

    @Override
    public void onMenuFailedResponseFromPresenter(String message) {
        progressDilaog.dismiss();

    }

    @Override
    public void onMenuItemSuccessResponseFromPresenter(MenuItemsModel menuItemsModel) {
        progressDilaog.dismiss();
        if (menuItemsModel != null && menuItemsModel.getData().size() > 0) {

            layout_empty.setVisibility(View.INVISIBLE);
            recycler_menu_list.setVisibility(View.VISIBLE);
            menuListsAdapter = new MenuListsAdapter(this, menuItemsModel.getData(), restaurantsId, ipMenuActivity);
            recycler_menu_list.setLayoutManager(new GridLayoutManager(this, numberofColumns));
            recycler_menu_list.setAdapter(menuListsAdapter);

        }
    }

    @Override
    public void onMenuItemFailedResponseFromPresenter(String message) {
        progressDilaog.dismiss();
    }

    @Override
    public void onMenuItemEmptyResponseFromPresenter(String message) {

        progressDilaog.dismiss();
        layout_empty.setVisibility(View.VISIBLE);
        recycler_menu_list.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onItemAddCartSuccessResponseFromPresenter(ItemAddResponseModel itemAddResponseModel) {

        //Toast.makeText(context, ""+  itemAddResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemAddCartFailedResponseFromPresenter(String message) {

    }

    public void onMenuItemClick(String menu_id) {

        if (Utility.isNetworkConnectionAvailable(context)) {
            progressDilaog = Utility.ShowDialog(context);
            ipMenuActivity.dogetmenuItem(menu_id, restaurantsId);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;

            case R.id.layout_restaurants:
                intent = new Intent(context, RestaurantsDetailsActivity.class);

                intent.putExtra("restaurantsId", restaurantsId);
                intent.putExtra("restaurants_AvrageRating", restaurants_AvrageRating);
                intent.putExtra("restaurants_Image", restaurants_Image);
                intent.putExtra("restaurants_Name", restaurants_Name);
                intent.putExtra("restaurants_Address", restaurants_Address);

               /* intent.putExtra("position", String.valueOf(position));
                intent.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) data);*/

                context.startActivity(intent);
                break;
            case R.id.img_carts:
                intent = new Intent(MenuActivity.this, CartActivity.class);
                startActivity(intent);
                break;
        }
    }
}
