package com.bookwego.otpActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.loginActivity.LogInActivity;
import com.bookwego.otpActivity.interfaces.IOtpActivity;
import com.bookwego.otpActivity.interfaces.IPOtpActivity;
import com.bookwego.otpActivity.presenter.POtpActivity;
import com.bookwego.otpActivity.responseModel.OtpmatchResponseModel;
import com.bookwego.otpActivity.responseModel.ResendOtpResponseModel;
import com.bookwego.utills.Utility;
import com.chaos.view.PinView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtpActivity extends BaseClass implements IOtpActivity, View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.tv_verifyingnumber)
    TextView tv_verifyingnumber;

    @BindView(R.id.tv_resendotp)
    TextView tv_resendotp;

    @BindView(R.id.tv_mynumber)
    TextView tv_mynumber;

    @BindView(R.id.tv_send_otp)
    TextView tv_send_otp;

    @BindView(R.id.edit_OTP)
    PinView edit_OTP;

    String user_id = "", user_phone = "", user_email = "";
    Context context;
    Dialog progressDialog;
    IPOtpActivity ipOtpActivity;

    String otp="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
        context = OtpActivity.this;
        ipOtpActivity = new POtpActivity(this);
        Initialization();
        EventListner();

    }

    private void Initialization() {
        user_id = getIntent().getStringExtra("user_id");
        user_phone = getIntent().getStringExtra("user_phone");
        user_email = getIntent().getStringExtra("user_email");
        tv_mynumber.setText(user_phone);
        tv_verifyingnumber.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));

    }

    private void EventListner() {

        img_back.setOnClickListener(this);
        tv_send_otp.setOnClickListener(this);
        tv_resendotp.setOnClickListener(this);
        otp=edit_OTP.getText().toString().trim();

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

            case R.id.tv_send_otp:
                validationOnOTP();
                break;
            case R.id.tv_resendotp:
                if (Utility.isNetworkConnectionAvailable(context)){
                    /*api call for resend otp on register email or phone number*/
                    ipOtpActivity.doResendOtp(user_id,user_phone,user_email);
                }
                break;

        }

    }

    private void validationOnOTP() {

        if (Utility.isNetworkConnectionAvailable(context)) {
            if (edit_OTP.getText().toString().trim().isEmpty()) {
                String error_message = getString(R.string.enter_otp);
                Utility.ShowToast(context, error_message);

            } else {
                progressDialog = Utility.ShowDialog(this);
                /*api call for otp on register email or phone number*/
                ipOtpActivity.doEnterOtp(user_id, edit_OTP.getText().toString().trim());

            }
        }


    }

    @Override
    public void OtpSuccessResponseFromPresenter(OtpmatchResponseModel otpmatchResponseModel) {
        progressDialog.dismiss();
        Intent intent = new Intent(OtpActivity.this, LogInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        Utility.ShowSuccessToast(context, otpmatchResponseModel.getMessage());
    }

    @Override
    public void OtpFailedResponseFromPresenter(String message) {
        progressDialog.dismiss();
        String error_message = message;
        Utility.ShowToast(context, error_message);
    }

    @Override
    public void OtpSendInvalidRequest(String message) {
        progressDialog.dismiss();
        String error_message = message;
        Utility.ShowToast(context, error_message);
    }

    @Override
    public void OtpResendResponseFromPresenter(ResendOtpResponseModel resendOtpResponseModel) {
        progressDialog.dismiss();
        Utility.ShowSuccessToast(context, resendOtpResponseModel.getMessage());
    }

    @Override
    public void OtpResendFailedResponseFromPresenter(String message) {
        progressDialog.dismiss();
        String error_message = message;
        Utility.ShowToast(context, error_message);

    }

    @Override
    public void OtpReSendInvalidRequest(String message) {
        progressDialog.dismiss();
        String error_message = message;
        Utility.ShowToast(context, error_message);
    }
}
