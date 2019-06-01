package com.bookwego.menuItemDetailsActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.aboutRestaurantsActivity.AboutRestaurantsActivity;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.menuItemDetailsActivity.adapters.ReviewsAdapter;
import com.bookwego.menuItemDetailsActivity.adapters.SimilarRestaurantsAdapter;
import com.bookwego.menuItemDetailsActivity.adapters.SpecialConditionsAdapter;
import com.bookwego.moreInfoActivity.MoreInfoActivity;
import com.bookwego.panelListActivity.adapters.CartDiscountAdapter;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuItemDetailsActivity extends BaseClass implements View.OnClickListener {

    @BindView(R.id.recycler_bookingDiscount)
    RecyclerView recycler_bookingDiscount;

    @BindView(R.id.recycler_specialConditions)
    RecyclerView recycler_specialConditions;

    @BindView(R.id.recycler_reviews)
    RecyclerView recycler_reviews;

    @BindView(R.id.recycler_similar_restaurants)
    RecyclerView recycler_similar_restaurants;

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.tv_dishName)
    TextView tv_dishName;

    @BindView(R.id.tv_bookyoutable)
    TextView tv_bookyoutable;

    @BindView(R.id.tv_booktimeanddiscount)
    TextView tv_booktimeanddiscount;

    @BindView(R.id.tv_foodtakeaway)
    TextView tv_foodtakeaway;

    @BindView(R.id.tv_specialcondition)
    TextView tv_specialcondition;

    @BindView(R.id.tv_reviews)
    TextView tv_reviews;

    @BindView(R.id.tv_similarRestaurant)
    TextView tv_similarRestaurant;

    @BindView(R.id.tv_about)
    TextView tv_about;

    @BindView(R.id.tv_menu)
    TextView tv_menu;

    @BindView(R.id.tv_moreinfo)
    TextView tv_moreinfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item_details);

        ButterKnife.bind(this);

        Initialization();

        EventListner();
    }


    private void Initialization() {


        tv_dishName.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_bookyoutable.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_booktimeanddiscount.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_foodtakeaway.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_specialcondition.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_reviews.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_similarRestaurant.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_about.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_menu.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_moreinfo.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));


        CartDiscountAdapter cartDiscountAdapter = new CartDiscountAdapter(this);
        recycler_bookingDiscount.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        recycler_bookingDiscount.setAdapter(cartDiscountAdapter);


        SpecialConditionsAdapter specialConditionsAdapter = new SpecialConditionsAdapter(this);
        recycler_specialConditions.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recycler_specialConditions.setAdapter(specialConditionsAdapter);

        ReviewsAdapter reviewsAdapter = new ReviewsAdapter(this);
        recycler_reviews.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recycler_reviews.setAdapter(reviewsAdapter);

        SimilarRestaurantsAdapter similarRestaurantsAdapter = new SimilarRestaurantsAdapter(this);
        recycler_similar_restaurants.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        recycler_similar_restaurants.setAdapter(similarRestaurantsAdapter);


    }

    private void EventListner() {
        img_back.setOnClickListener(this);
        tv_about.setOnClickListener(this);
        tv_moreinfo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;

            case R.id.tv_about:
                intent = new Intent(MenuItemDetailsActivity.this, AboutRestaurantsActivity.class);
                startActivity(intent);
                break;

            case R.id.tv_moreinfo:
                intent = new Intent(MenuItemDetailsActivity.this, MoreInfoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
