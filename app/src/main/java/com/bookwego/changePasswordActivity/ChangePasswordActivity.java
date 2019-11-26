package com.bookwego.changePasswordActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.changePasswordActivity.interfaces.IChangePasswordActivity;
import com.bookwego.changePasswordActivity.interfaces.IPChangePasswordActivity;
import com.bookwego.changePasswordActivity.presenter.PChnagePassword;
import com.bookwego.changePasswordActivity.responseModel.ChangePasswordResponse;
import com.bookwego.utills.CommonMethods;
import com.bookwego.utills.SavePref;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangePasswordActivity extends BaseClass implements IChangePasswordActivity, View.OnClickListener {

    @BindView(R.id.btn_update)
    Button btn_update;

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.edit_previous_password)
    EditText edit_previous_password;

    @BindView(R.id.edit_newpassword)
    EditText edit_newpassword;

    @BindView(R.id.edit_confirm_password)
    EditText edit_confirm_password;

    Context context;
    Dialog progressDialog;
    IPChangePasswordActivity ipChangePasswordActivity;
    SavePref savePref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        ButterKnife.bind(this);
        context = ChangePasswordActivity.this;
        savePref = new SavePref(this);
        ipChangePasswordActivity = new PChnagePassword(this);

        Initialization();
        EventListner();
    }


    private void Initialization() {

        btn_update.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_previous_password.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_newpassword.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_confirm_password.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));

    }

    private void EventListner() {

        btn_update.setOnClickListener(this);
        img_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_update:

                validationOnUpdatePassword();

                break;

            case R.id.img_back:

                finish();
                break;
        }
    }

    private void validationOnUpdatePassword() {

        if (Utility.isNetworkConnectionAvailable(context)) {

            if (edit_previous_password.getText().toString().trim().isEmpty()) {
                String password = getString(R.string.old_password);
                Utility.ShowToast(context, password);

            } else if (edit_newpassword.getText().toString().trim().isEmpty()) {
                String password = getString(R.string.new_passwords);
                Utility.ShowToast(context, password);

            } else if (edit_newpassword.length() < 6 || edit_newpassword.length() > 16) {
                String passwordlength = getString(R.string.password_length);
                Utility.ShowToast(context, passwordlength);

            } else if (!CommonMethods.isValidPassword(edit_newpassword.getText().toString().trim())) {
                String hint_password = getString(R.string.password_pattern_hint);
                Utility.ShowToast(context, hint_password);

            } else if (edit_confirm_password.getText().toString().trim().isEmpty()) {

                String confirm_password = getString(R.string.confirmnew_passwords);
                Utility.ShowToast(context, confirm_password);

            } else if (!edit_confirm_password.getText().toString().trim().matches(edit_newpassword.getText().toString().trim())) {
                String not_match = getString(R.string.r_password_not_match);
                Utility.ShowToast(context, not_match);

            } else {
                progressDialog = Utility.ShowDialog(this);
                /*TODO
                Api call for change password
                with old password to new password*/
                ipChangePasswordActivity.doChangePassword(edit_previous_password.getText().toString().trim(),
                        edit_confirm_password.getText().toString().trim(), savePref.get_token());
            }
        }
    }

    @Override
    public void onSucessResponseFromPresenter(ChangePasswordResponse changePasswordResponse) {
        progressDialog.dismiss();
        finish();
        Utility.ShowSuccessToast(context, changePasswordResponse.getMessage());
    }

    @Override
    public void onFailedResponseFromPresenter(String message) {
        progressDialog.dismiss();
        String error_message = message;
        Utility.ShowToast(context, error_message);


    }

    @Override
    public void onInvalideRequestFromPresenter(String message) {
        progressDialog.dismiss();
        String error_message = message;
        Utility.ShowToast(context, error_message);
    }
}
