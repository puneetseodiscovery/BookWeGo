package com.bookwego.recentviewedActivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.recentviewedActivity.adapters.RestaurantsAdapter;
import com.bookwego.recentviewedActivity.adapters.ServicesAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecentlyViewedActivity extends BaseClass implements View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.recycler_restaurants)
    RecyclerView recycler_restaurants;

    RestaurantsAdapter restaurantsAdapter;

    @BindView(R.id.recycler_services)
    RecyclerView recycler_services;

    ServicesAdapter servicesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recently_viewed);
        ButterKnife.bind(this);
        Initialization();
        EventListner();
    }


    private void Initialization() {
        restaurantsAdapter = new RestaurantsAdapter(this);
        recycler_restaurants.setLayoutManager(new LinearLayoutManager(this));
        recycler_restaurants.setAdapter(restaurantsAdapter);


        servicesAdapter = new ServicesAdapter(this);
        recycler_services.setLayoutManager(new LinearLayoutManager(this));
        recycler_services.setAdapter(servicesAdapter);

    }

    private void EventListner() {
        img_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }
}
