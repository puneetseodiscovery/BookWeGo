package com.bookwego.forgotPassword;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bookwego.R;
import com.bookwego.forgotPassword.forgotPasswordResponse.ForgotPasswordResponse;
import com.bookwego.forgotPassword.interfaces.IForgotPassword;
import com.bookwego.forgotPassword.interfaces.IPForgotPassword;
import com.bookwego.forgotPassword.presenter.PForgotPassword;
import com.bookwego.utills.CommonMethods;
import com.bookwego.utills.Utility;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgotPasswordActivity extends AppCompatActivity implements IForgotPassword, View.OnClickListener {

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

    Context context;
    IPForgotPassword ipForgotPassword;
    Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        context = ForgotPasswordActivity.this;
        ipForgotPassword = new PForgotPassword(this);

        Initialization();
        EventListner();
    }


    private void Initialization() {

        tv_forgotpass.setTypeface(Utility.typeFaceForProximaNovaBoldText(this));
        tv_restinst.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_usereamil.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        btn_submit.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));

    }

    private void EventListner() {

        img_back.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

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

            case R.id.btn_submit:

                validationOnEmail();

                break;
        }

    }

    private void validationOnEmail() {

        if (Utility.isNetworkConnectionAvailable(context)) {
            if (edit_usereamil.getText().toString().trim().isEmpty()) {
                String edit_email = getString(R.string.string_email);
                Utility.ShowToast(context, edit_email);
            } else if (!CommonMethods.isValidEmail(edit_usereamil.getText().toString())) {
                String edit_email = getString(R.string.string_validemail);
                Utility.ShowToast(context, edit_email);
            } else {
                progressDialog = Utility.ShowDialog(this);
                /*TODO
                Api call for reset the password
                * password reset link send on entered email address*/
                ipForgotPassword.doForgotPassword(edit_usereamil.getText().toString().trim());
            }
        }
    }

    @Override
    public void onForgotPasswordSuccessResponseFromPresenter(ForgotPasswordResponse forgotPasswordResponse) {

        progressDialog.dismiss();
        finish();
        Utility.ShowSuccessToast(context, forgotPasswordResponse.getMessage());
    }

    @Override
    public void onForgotPasswordFailedResponseFromPresenter(String message) {

        progressDialog.dismiss();
        String error_message = message;
        Utility.ShowToast(context, error_message);

    }

    @Override
    public void onForgotPasswordIvalideRequestFromPresenter(String message) {
        progressDialog.dismiss();
        String error_message = message;
        Utility.ShowToast(context, error_message);
    }
}
