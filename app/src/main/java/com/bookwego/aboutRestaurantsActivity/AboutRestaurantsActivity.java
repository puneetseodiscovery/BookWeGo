package com.bookwego.aboutRestaurantsActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.aboutRestaurantsActivity.interfaces.IAboutRestaurantsActivity;
import com.bookwego.aboutRestaurantsActivity.interfaces.IPAboutRestaurantsActivity;
import com.bookwego.aboutRestaurantsActivity.presenter.PAboutRestaurantsActivity;
import com.bookwego.aboutRestaurantsActivity.responseModel.AboutRestaurantsResponseModel;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.utills.CommonMethods;
import com.bookwego.utills.Constant;
import com.bookwego.utills.Utility;
import com.bumptech.glide.Glide;
import com.codesgood.views.JustifiedTextView;
import com.facebook.shimmer.ShimmerFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutRestaurantsActivity extends BaseClass implements IAboutRestaurantsActivity, View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.img_resturant)
    ImageView img_resturant;

    @BindView(R.id.tv_restaurantName)
    TextView tv_restaurantName;

    @BindView(R.id.tv_totalrating)
    TextView tv_totalrating;

    @BindView(R.id.tv_loaction)
    TextView tv_loaction;

    @BindView(R.id.tv_about)
    JustifiedTextView tv_about;

    @BindView(R.id.layout_shimmer)
    ShimmerFrameLayout layout_shimmer;

    Context context;
    IPAboutRestaurantsActivity ipAboutRestaurantsActivity;
    Dialog progressDialog;
    String restaurantsId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_restaurants);

        ButterKnife.bind(this);
        Initialization();
        EventListner();

    }

    private void Initialization() {
        restaurantsId = getIntent().getStringExtra("restaurantsId");
        context = AboutRestaurantsActivity.this;
        ipAboutRestaurantsActivity = new PAboutRestaurantsActivity(this);
        tv_restaurantName.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_totalrating.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));

        if (Utility.isNetworkConnectionAvailable(context)) {
            /*Api call for get about restaurants details*/
            ipAboutRestaurantsActivity.getDetailsAboutRestaurants(restaurantsId);
        }

    }

    private void EventListner() {

        img_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent intent = null;

        switch (v.getId()) {

            case R.id.img_back:
                finish();
                break;
        }
    }

    @SuppressLint("ResourceType")
    @Override
    public void onSuccessResponseFromPresenter(AboutRestaurantsResponseModel aboutRestaurantsResponseModel) {
        if (aboutRestaurantsResponseModel != null) {
            // stop animating Shimmer and hide the layout
            layout_shimmer.stopShimmer();
            layout_shimmer.setVisibility(View.GONE);
            Glide.with(context).load(Constant.IMAGE_URL + aboutRestaurantsResponseModel.getData().get(0).getImage())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.no_image_placeholder)
                    .into(img_resturant);
            tv_restaurantName.setText(CommonMethods.upperCase(aboutRestaurantsResponseModel.getData().get(0).getName()));
            tv_totalrating.setText(aboutRestaurantsResponseModel.getData().get(0).getAvgRating());;
            tv_totalrating.setBackgroundResource(R.drawable.custom_cart_bg);
            tv_loaction.setText(CommonMethods.upperCase(aboutRestaurantsResponseModel.getData().get(0).getAddress()));
            tv_about.setText(aboutRestaurantsResponseModel.getData().get(0).getAbout());
        } else {

        }

    }

    @Override
    public void onFailedResponseFromPresenter(String message) {

    }

}
