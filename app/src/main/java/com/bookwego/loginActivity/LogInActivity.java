package com.bookwego.loginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.forgotPassword.ForgotPasswordActivity;
import com.bookwego.mainActivity.MainActivity;
import com.bookwego.privacypolicy.PrivacyPolicyActivity;
import com.bookwego.registerActivity.RegisterActivity;
import com.bookwego.termandconditionActivity.TermConditionActivity;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogInActivity extends BaseClass implements View.OnClickListener {


    @BindView(R.id.edit_usereamil)
    EditText edit_usereamil;

    @BindView(R.id.edit_userpassword)
    EditText edit_userpassword;

    @BindView(R.id.tv_register)
    TextView tv_register;

    @BindView(R.id.tv_term_condition)
    TextView tv_term_condition;

    @BindView(R.id.tv_privacy_policy)
    TextView tv_privacy_policy;

    @BindView(R.id.tv_forgotpassword)
    TextView tv_forgotpassword;

    @BindView(R.id.btn_login)
    TextView btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        ButterKnife.bind(this);

        Initialization(); // findviewBy Id

        EventListner();
    }


    private void Initialization() {

        btn_login.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_usereamil.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_userpassword.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_register.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_term_condition.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_privacy_policy.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
    }

    private void EventListner() {

        tv_register.setOnClickListener(this);
        tv_forgotpassword.setOnClickListener(this);
        tv_term_condition.setOnClickListener(this);
        tv_privacy_policy.setOnClickListener(this);
        btn_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent intent = null;

        switch (v.getId()) {

            case R.id.tv_register:

                intent = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(0, R.anim.splash_faidout);

                break;

            case R.id.tv_forgotpassword:
                intent = new Intent(LogInActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
                overridePendingTransition(0, R.anim.splash_faidout);

                break;

            case R.id.tv_privacy_policy:
                intent = new Intent(LogInActivity.this, PrivacyPolicyActivity.class);
                startActivity(intent);
                overridePendingTransition(0, R.anim.splash_faidout);

                break;


            case R.id.tv_term_condition:
                intent = new Intent(LogInActivity.this, TermConditionActivity.class);
                startActivity(intent);
                overridePendingTransition(0, R.anim.splash_faidout);

                break;

                case R.id.btn_login:

                intent = new Intent(LogInActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, R.anim.splash_faidout);

                break;
        }

    }

}
