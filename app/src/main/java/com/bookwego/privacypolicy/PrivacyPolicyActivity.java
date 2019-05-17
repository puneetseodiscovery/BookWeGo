package com.bookwego.privacypolicy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.forgotPassword.ForgotPasswordActivity;
import com.bookwego.loginActivity.LogInActivity;
import com.bookwego.registerActivity.RegisterActivity;
import com.bookwego.termandconditionActivity.TermConditionActivity;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PrivacyPolicyActivity extends BaseClass implements View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

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

                Animation animation01 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_click);
                img_back.startAnimation(animation01);
                finish();
                overridePendingTransition(R.anim.main_faidin, R.anim.main_faidout);
                break;

        }

    }
}
