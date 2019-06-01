package com.bookwego.favouriteActvity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.favouriteActvity.adapters.FavouriteAdapter;
import com.bookwego.recentviewedActivity.adapters.RestaurantsAdapter;
import com.bookwego.recentviewedActivity.adapters.ServicesAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouriteActivity extends BaseClass implements View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.recycler_favourite)
    RecyclerView recycler_favourite;

    FavouriteAdapter favouriteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        ButterKnife.bind(this);
        Initialization();
        EventListner();
    }


    private void Initialization() {

        favouriteAdapter = new FavouriteAdapter(this);
        recycler_favourite.setLayoutManager(new LinearLayoutManager(this));
        recycler_favourite.setAdapter(favouriteAdapter);

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
