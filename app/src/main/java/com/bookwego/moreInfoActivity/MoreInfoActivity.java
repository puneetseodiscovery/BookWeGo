package com.bookwego.moreInfoActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.cartActivity.CartActivity;
import com.bookwego.moreInfoActivity.interfaces.IMoreInfoActivity;
import com.bookwego.moreInfoActivity.interfaces.IPMoreInfoActivity;
import com.bookwego.moreInfoActivity.presenter.PMoreInfoActivity;
import com.bookwego.moreInfoActivity.responseModel.MoreInfoResponseModel;
import com.bookwego.utills.CommonMethods;
import com.bookwego.utills.Constant;
import com.bookwego.utills.Utility;
import com.bumptech.glide.Glide;
import com.codesgood.views.JustifiedTextView;
import com.facebook.shimmer.ShimmerFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreInfoActivity extends BaseClass implements IMoreInfoActivity, View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.img_cart)
    ImageView img_cart;

    @BindView(R.id.img_resturant)
    ImageView img_resturant;

    @BindView(R.id.tv_restaurantName)
    TextView tv_restaurantName;

    @BindView(R.id.tv_loaction)
    TextView tv_loaction;

    @BindView(R.id.tv_totalrating)
    TextView tv_totalrating;

    @BindView(R.id.tv_openingHours)
    TextView tv_openingHours;

    @BindView(R.id.tv_Hours)
    TextView tv_Hours;

    @BindView(R.id.tv_atmosphere)
    TextView tv_atmosphere;

    @BindView(R.id.atmosphere)
    TextView atmosphere;

    @BindView(R.id.tv_facilities_services)
    TextView tv_facilities_services;

    @BindView(R.id.tv_language)
    TextView tv_language;

    @BindView(R.id.tv_credit_card)
    TextView tv_credit_card;

    @BindView(R.id.facilities_services)
    TextView facilities_services;

    @BindView(R.id.spoken_english)
    TextView spoken_english;

    @BindView(R.id.credit_card)
    TextView credit_card;

    @BindView(R.id.others_miscinfo)
    TextView others_miscinfo;

    @BindView(R.id.tv_others_miscinfo)
    JustifiedTextView tv_others_miscinfo;

    @BindView(R.id.layout_shimmer)
    ShimmerFrameLayout layout_shimmer;

    @BindView(R.id.layout_view)
    ScrollView layout_view;

    IPMoreInfoActivity ipMoreInfoActivity;
    Context context;
    Dialog progressDialog;
    String restaurantsId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        ButterKnife.bind(this);

        Initialization();
        EventListner();

    }

    private void Initialization() {
        restaurantsId = getIntent().getStringExtra("restaurantsId");
        context = MoreInfoActivity.this;
        ipMoreInfoActivity = new PMoreInfoActivity(this);

        tv_restaurantName.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_totalrating.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_openingHours.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        atmosphere.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        facilities_services.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        spoken_english.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        credit_card.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        others_miscinfo.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_others_miscinfo.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));


        if (Utility.isNetworkConnectionAvailable(context)) {
            /*Api call for get more info of  restaurants */
            ipMoreInfoActivity.getMoreInfo(restaurantsId);
        }
    }

    private void EventListner() {

        img_back.setOnClickListener(this);
        img_cart.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent intent = null;

        switch (v.getId()) {

            case R.id.img_back:

                Animation animation01 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_click);
                img_back.startAnimation(animation01);
                finish();
                overridePendingTransition(R.anim.main_faidin, R.anim.main_faidout);

                break;

            case R.id.img_cart:

                Intent intent1 = new Intent(MoreInfoActivity.this, CartActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.main_faidin, R.anim.main_faidout);

                break;

        }

    }

    @Override
    public void onSuccessResponseFromPresenter(MoreInfoResponseModel moreInfoResponseModel) {

        if (moreInfoResponseModel != null) {
            layout_shimmer.stopShimmer();
            layout_shimmer.setVisibility(View.GONE);
            layout_view.setVisibility(View.VISIBLE);
            Glide.with(context).load(Constant.IMAGE_URL+moreInfoResponseModel.getData().get(0).getImage())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.no_image_placeholder)
                    .into(img_resturant);
            tv_restaurantName.setText(CommonMethods.upperCase(moreInfoResponseModel.getData().get(0).getName()));
            tv_loaction.setText(CommonMethods.upperCase(moreInfoResponseModel.getData().get(0).getAddress()));
            tv_Hours.setText(CommonMethods.upperCase(moreInfoResponseModel.getData().get(0).getOpeningHours()));
            tv_atmosphere.setText(CommonMethods.upperCase(moreInfoResponseModel.getData().get(0).getAtmosphere()));
            tv_credit_card.setText(CommonMethods.upperCase(moreInfoResponseModel.getData().get(0).getCreditCard()));
            tv_language.setText(CommonMethods.upperCase(moreInfoResponseModel.getData().get(0).getLanguage()));
            tv_facilities_services.setText(CommonMethods.upperCase(moreInfoResponseModel.getData().get(0).getFacilitiesServices()));
            tv_totalrating.setText(moreInfoResponseModel.getData().get(0).getAvgRating());
            tv_totalrating.setBackgroundResource(R.drawable.custom_cart_bg);
            tv_others_miscinfo.setText(moreInfoResponseModel.getData().get(0).getMiscInfo());
        } else {

        }
    }

    @Override
    public void onFailedResponseFromPresenter(String message) {

    }

    @Override
    public void onResume() {
        super.onResume();
        layout_shimmer.stopShimmer();
    }

    @Override
    public void onPause() {
        layout_shimmer.stopShimmer();
        super.onPause();
    }

}
