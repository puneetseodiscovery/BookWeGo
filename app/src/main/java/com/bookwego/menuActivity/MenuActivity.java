package com.bookwego.menuActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.mainActivity.adapters.CategoriesAdapter;
import com.bookwego.mainActivity.adapters.SubCategoriesAdapter;
import com.bookwego.mainActivity.modelClasses.CategoriesModel;
import com.bookwego.menuActivity.ModelClasses.MenuModel;
import com.bookwego.menuActivity.adapters.MenuAdapter;
import com.bookwego.menuActivity.adapters.MenuListsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends BaseClass {

    @BindView(R.id.recycler_menus)
    RecyclerView recycler_menus;

    @BindView(R.id.recycler_menu_list)
    RecyclerView recycler_menu_list;

    @BindView(R.id.img_back)
    ImageView img_back;

    MenuModel menuModel;
    MenuAdapter menuAdapter;

    MenuListsAdapter menuListsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ButterKnife.bind(this);

        Initialization();
        EventListner();
    }


    private void Initialization() {
        MenuModel[] menuModels = new MenuModel[]{
                new MenuModel("Main Courses"),
                new MenuModel("Appetizers"),
                new MenuModel("Desserts"),
                new MenuModel("Soups"),
                new MenuModel("Soups"),

        };
        menuAdapter = new MenuAdapter(this, menuModels);
        recycler_menus.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        recycler_menus.setHasFixedSize(true);
        recycler_menus.setAdapter(menuAdapter);


        int numberofColumns = 2;
        menuListsAdapter = new MenuListsAdapter(this);
        recycler_menu_list.setLayoutManager(new GridLayoutManager(this, numberofColumns));
        recycler_menu_list.setAdapter(menuListsAdapter);


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void EventListner() {

    }

}
