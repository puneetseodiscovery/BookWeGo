package com.bookwego.settingActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.changePasswordActivity.ChangePasswordActivity;
import com.bookwego.faqActivity.FAQActivity;
import com.bookwego.favouriteActvity.FavouriteActivity;
import com.bookwego.helpActivity.HelpActivity;
import com.bookwego.newandpermotions.NewsPermotionsActivity;
import com.bookwego.paymentActivity.PaymentActivity;
import com.bookwego.privacypolicy.PrivacyPolicyActivity;
import com.bookwego.recentviewedActivity.RecentlyViewedActivity;
import com.bookwego.refferalProgramActivity.ReferEarnActivity;
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

    @BindView(R.id.tv_recentviews)
    TextView tv_recentviews;

    @BindView(R.id.tv_newandpormotion)
    TextView tv_newandpormotion;

    @BindView(R.id.tv_payments)
    TextView tv_payments;

    @BindView(R.id.tv_favourite)
    TextView tv_favourite;

    @BindView(R.id.tv_referandearn)
    TextView tv_referandearn;

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
        tv_recentviews.setOnClickListener(this);
        tv_newandpormotion.setOnClickListener(this);
        tv_payments.setOnClickListener(this);
        tv_favourite.setOnClickListener(this);
        tv_referandearn.setOnClickListener(this);

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
            case R.id.tv_recentviews:
                intent = new Intent(SettingsActivity.this, RecentlyViewedActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_newandpormotion:
                intent = new Intent(SettingsActivity.this, NewsPermotionsActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_payments:
                intent = new Intent(SettingsActivity.this, PaymentActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_favourite:
                intent = new Intent(SettingsActivity.this, FavouriteActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_referandearn:
                intent = new Intent(SettingsActivity.this, ReferEarnActivity.class);
                startActivity(intent);
                break;
        }

    }
}
