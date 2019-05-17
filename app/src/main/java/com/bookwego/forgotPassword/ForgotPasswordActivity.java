package com.bookwego.forgotPassword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.tv_forgotpass)
    TextView tv_forgotpass;

    @BindView(R.id.tv_restinst)
    TextView tv_restinst;

    @BindView(R.id.edit_usereamil)
    EditText edit_usereamil;

    @BindView(R.id.btn_submit)
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);


        Initialization();
        EventListner();
    }


    private void Initialization() {
        tv_forgotpass.setTypeface(Utility.typeFaceForProximaNovaBoldText(this));

      //  tv_login.setTypeface(Utility.typeFaceForProximaNovaBoldText(this));
        tv_restinst.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_usereamil.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        btn_submit.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));

    }

    private void EventListner() {
        img_back.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
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
