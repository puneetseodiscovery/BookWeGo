package com.bookwego.profileActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends BaseClass implements View.OnClickListener {

    @BindView(R.id.edit_gender)
    EditText edit_gender;

    @BindView(R.id.edit_address)
    EditText edit_address;

    @BindView(R.id.edit_citizinship)
    EditText edit_citizinship;

    @BindView(R.id.edit_employment)
    EditText edit_employment;

    @BindView(R.id.edit_incomLevel)
    EditText edit_incomLevel;

    @BindView(R.id.edit_datejoin)
    EditText edit_datejoin;

    @BindView(R.id.btn_save)
    Button btn_save;

    @BindView(R.id.img_back)
    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);

        Initialization();
        EventListner();
    }


    private void Initialization() {

        edit_gender.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_address.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_citizinship.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_employment.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_incomLevel.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_datejoin.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        btn_save.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));

    }

    private void EventListner() {

        btn_save.setOnClickListener(this);
        img_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.img_back:

                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_click);
                img_back.startAnimation(animation);
                finish();
                overridePendingTransition(R.anim.main_faidin, R.anim.main_faidout);
                break;
            case R.id.btn_save:

                Animation animation01 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_click);
                img_back.startAnimation(animation01);
                finish();
                overridePendingTransition(R.anim.main_faidin, R.anim.main_faidout);
                break;
        }

    }
}
