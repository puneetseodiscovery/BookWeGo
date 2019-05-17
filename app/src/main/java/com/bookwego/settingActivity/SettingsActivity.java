package com.bookwego.settingActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.changePasswordActivity.ChangePasswordActivity;
import com.bookwego.faqActivity.FAQActivity;
import com.bookwego.helpActivity.HelpActivity;
import com.bookwego.privacypolicy.PrivacyPolicyActivity;
import com.bookwego.termandconditionActivity.TermConditionActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends BaseClass implements View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.tv_help)
    TextView tv_help;

    @BindView(R.id.tv_terms)
    TextView tv_terms;

    @BindView(R.id.tv_privacy_policy)
    TextView tv_privacy_policy;

    @BindView(R.id.tv_changepassword)
    TextView tv_changepassword;

    @BindView(R.id.tv_faq)
    TextView tv_faq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ButterKnife.bind(this);

        Initialization();
        EventListner();

    }


    private void Initialization() {
    }

    private void EventListner() {
        img_back.setOnClickListener(this);
        tv_help.setOnClickListener(this);
        tv_terms.setOnClickListener(this);
        tv_privacy_policy.setOnClickListener(this);
        tv_changepassword.setOnClickListener(this);
        tv_faq.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent intent = null;
        switch (v.getId()) {

            case R.id.img_back:

                finish();

                break;

            case R.id.tv_help:
                intent = new Intent(SettingsActivity.this, HelpActivity.class);
                startActivity(intent);
                break;

            case R.id.tv_terms:
                intent = new Intent(SettingsActivity.this, TermConditionActivity.class);
                startActivity(intent);
                break;

            case R.id.tv_privacy_policy:
                intent = new Intent(SettingsActivity.this, PrivacyPolicyActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_changepassword:
                intent = new Intent(SettingsActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
                break;

            case R.id.tv_faq:
                intent = new Intent(SettingsActivity.this, FAQActivity.class);
                startActivity(intent);
                break;
        }

    }
}
