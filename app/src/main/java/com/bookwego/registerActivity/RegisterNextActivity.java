package com.bookwego.registerActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.loginActivity.LogInActivity;
import com.bookwego.privacypolicy.PrivacyPolicyActivity;
import com.bookwego.termandconditionActivity.TermConditionActivity;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterNextActivity extends BaseClass implements View.OnClickListener {
    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.btn_register)
    Button btn_register;

    @BindView(R.id.tv_login)
    TextView tv_login;


    @BindView(R.id.edit_employment)
    EditText edit_employment;

    @BindView(R.id.edit_incomLevel)
    EditText edit_incomLevel;

    @BindView(R.id.edit_datejoin)
    EditText edit_datejoin;

    @BindView(R.id.edit_likebest)
    EditText edit_likebest;

    @BindView(R.id.edit_services)
    EditText edit_services;

    @BindView(R.id.edit_password)
    EditText edit_password;

    @BindView(R.id.edit_confirm_password)
    EditText edit_confirm_password;

    @BindView(R.id.tv_term_condition)
    TextView tv_term_condition;

    @BindView(R.id.tv_privacy_policy)
    TextView tv_privacy_policy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_next);

        ButterKnife.bind(this);

        Initialization();
        EventListner();
    }


    private void Initialization() {

        edit_employment.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_incomLevel.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_datejoin.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_likebest.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_services.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_password.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_confirm_password.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_term_condition.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_privacy_policy.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_login.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        btn_register.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));


    }

    private void EventListner() {

        img_back.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        tv_login.setOnClickListener(this);
        tv_privacy_policy.setOnClickListener(this);
        tv_term_condition.setOnClickListener(this);

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
            case R.id.btn_register:

                intent = new Intent(RegisterNextActivity.this, LogInActivity.class);
                startActivity(intent);
                overridePendingTransition(0, R.anim.splash_faidout);

                break;
            case R.id.tv_login:

                intent = new Intent(RegisterNextActivity.this, LogInActivity.class);
                startActivity(intent);
                overridePendingTransition(0, R.anim.splash_faidout);

                break;

            case R.id.tv_privacy_policy:
                intent = new Intent(RegisterNextActivity.this, PrivacyPolicyActivity.class);
                startActivity(intent);
                overridePendingTransition(0, R.anim.splash_faidout);

                break;


            case R.id.tv_term_condition:
                intent = new Intent(RegisterNextActivity.this, TermConditionActivity.class);
                startActivity(intent);
                overridePendingTransition(0, R.anim.splash_faidout);

                break;
        }

    }

}
