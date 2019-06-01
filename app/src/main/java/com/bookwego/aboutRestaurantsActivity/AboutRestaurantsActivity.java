package com.bookwego.aboutRestaurantsActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutRestaurantsActivity extends BaseClass implements View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_restaurants);
        ButterKnife.bind(this);

        Initialization();
        EventListner();

    }


    private void Initialization() {

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
