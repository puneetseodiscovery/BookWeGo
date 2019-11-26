package com.bookwego.trackOrderActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrackOrderActivity extends BaseClass implements View.OnClickListener {
    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.tv_ordername)
    TextView tv_ordername;

    @BindView(R.id.tv_orderstatus)
    TextView tv_orderstatus;

    @BindView(R.id.tv_orderrecived)
    TextView tv_orderrecived;

    @BindView(R.id.tv_orderprepared)
    TextView tv_orderprepared;

    @BindView(R.id.tv_orderpacked)
    TextView tv_orderpacked;

    @BindView(R.id.tv_orderdelivery)
    TextView tv_orderdelivery;

    @BindView(R.id.tv_orderdelivered)
    TextView tv_orderdelivered;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_order);
        ButterKnife.bind(this);

        Initialization();
        EventListner();

    }


    private void Initialization() {

        tv_ordername.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_orderstatus.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_orderrecived.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_orderprepared.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_orderpacked.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_orderdelivery.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_orderdelivered.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));

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


}
