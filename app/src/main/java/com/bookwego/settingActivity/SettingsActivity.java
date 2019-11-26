package com.bookwego.settingActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.changePasswordActivity.ChangePasswordActivity;
import com.bookwego.faqActivity.FAQActivity;
import com.bookwego.favouriteActvity.FavouriteActivity;
import com.bookwego.helpActivity.HelpActivity;
import com.bookwego.loginActivity.LogInActivity;
import com.bookwego.newandpermotions.NewsPermotionsActivity;
import com.bookwego.paymentActivity.PaymentActivity;
import com.bookwego.privacypolicy.PrivacyPolicyActivity;
import com.bookwego.recentviewedActivity.RecentlyViewedActivity;
import com.bookwego.refferalProgramActivity.ReferEarnActivity;
import com.bookwego.settingActivity.interfaces.IPSettingActivity;
import com.bookwego.settingActivity.interfaces.ISettingsActivity;
import com.bookwego.settingActivity.presenter.PSettingActivity;
import com.bookwego.settingActivity.responseModel.LogOutResponseModel;
import com.bookwego.settingActivity.responseModel.RatingResponseModel;
import com.bookwego.termandconditionActivity.TermConditionActivity;
import com.bookwego.utills.SavePref;
import com.bookwego.utills.Utility;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends BaseClass implements ISettingsActivity, View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.img_switch)
    ImageView img_switch;

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

    @BindView(R.id.tv_rateapp)
    TextView tv_rateapp;

    @BindView(R.id.tv_referandearn)
    TextView tv_referandearn;

    Context context;
    Dialog progressDialog;
    IPSettingActivity ipSettingActivity;
    SavePref savePref;
    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInOptions googleSignInOptions;

    /*Dialog inputs*/
    public Button btn_submit;
    public RatingBar ratingbar;
    public EditText edit_comment;
    private String totalRating = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ButterKnife.bind(this);
        context = SettingsActivity.this;
        savePref = new SavePref(this);
        ipSettingActivity = new PSettingActivity(this);
        Initialization();
        EventListner();

    }


    private void Initialization() {
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();

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
        img_switch.setOnClickListener(this);
        tv_rateapp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent intent = null;
        switch (v.getId()) {

            case R.id.img_back:

                finish();

                break;

            case R.id.img_switch:

                AlertLogoutDialog();

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

            case R.id.tv_rateapp:
                rateOurAppDialog();
                break;
        }

    }

    private void rateOurAppDialog() {

        final Dialog dialog;
        dialog = new Dialog(SettingsActivity.this, R.style.Theme_Dialog);
        dialog.setContentView(R.layout.layout_rate_our_app);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        btn_submit = dialog.findViewById(R.id.btn_submit);
        ratingbar = dialog.findViewById(R.id.ratingbar);
        edit_comment = dialog.findViewById(R.id.edit_comment);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (Utility.isNetworkConnectionAvailable(context)) {
                    // api call for give rating to the app by user
                    progressDialog = Utility.ShowDialog(context);
                    ipSettingActivity.giveRating(totalRating, edit_comment.getText().toString().trim(), savePref.get_id());
                }


            }
        });
        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {


                    totalRating = String.valueOf(rating);



            }
        });
        dialog.show();
    }

    private void AlertLogoutDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
        builder.setTitle("BookWeGo");
        builder.setMessage("Are you sure you want to Logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        if (Utility.isNetworkConnectionAvailable(context)) {
                            progressDialog = Utility.ShowDialog(context);
                            /*api call for user logout from app*/
                            ipSettingActivity.doLogout(savePref.get_token());
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onLogoutSuccessFromPresenter(LogOutResponseModel logOutResponseModel) {

        progressDialog.dismiss();
        savePref.clearPreferences();
        facebookLogout();// logout from facebook
        signOut();// logout from google
        Utility.ShowSuccessToast(context, logOutResponseModel.getMessage());
        Intent intent = new Intent(SettingsActivity.this, LogInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onLogoutFailedFromPresenter(String message) {
        progressDialog.dismiss();
        String error_message = message;
        Utility.ShowToast(context, error_message);

    }

    @Override
    public void onRatingSuccessFromPresenter(RatingResponseModel responseModel) {
        progressDialog.dismiss();

    }

    @Override
    public void onRatingFailedFromPresenter(String message) {
        progressDialog.dismiss();

    }

    private void facebookLogout() {

        LoginManager.getInstance().logOut();
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    private void signOut() {
        if (mGoogleApiClient.isConnected()) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient);
            mGoogleApiClient.disconnect();
            mGoogleApiClient.connect();
        }
    }
}
