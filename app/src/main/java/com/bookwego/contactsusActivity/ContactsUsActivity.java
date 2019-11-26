package com.bookwego.contactsusActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.contactsusActivity.contactUs.ContactUsResponseModel;
import com.bookwego.contactsusActivity.interfaces.IContactUsActivity;
import com.bookwego.contactsusActivity.interfaces.IPContactUsActivity;
import com.bookwego.contactsusActivity.presenter.PContactUsActivity;
import com.bookwego.utills.CommonMethods;
import com.bookwego.utills.SavePref;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsUsActivity extends BaseClass implements IContactUsActivity, View.OnClickListener {

    @BindView(R.id.tv_get_in_touch)
    TextView tv_get_in_touch;

    @BindView(R.id.tv_always_within_your_reach)
    TextView tv_always_within_your_reach;

    @BindView(R.id.edit_firstName)
    EditText edit_firstName;

    @BindView(R.id.edit_emails)
    EditText edit_emails;

    @BindView(R.id.edit_phone)
    EditText edit_phone;

    @BindView(R.id.edit_message)
    EditText edit_message;

    @BindView(R.id.btn_submit)
    Button btn_submit;

    @BindView(R.id.img_back)
    ImageView img_back;

    Context context;
    Dialog progressDialog;
    SavePref savePref;
    IPContactUsActivity ipContactUsActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_us);
        ButterKnife.bind(this);

        Initialization();
        EventListner();
    }

    private void Initialization() {

        context = ContactsUsActivity.this;
        savePref = new SavePref(this);
        ipContactUsActivity = new PContactUsActivity(this);
        tv_get_in_touch.setTypeface(Utility.typeFaceForProximaNovaBoldText(this));
        tv_always_within_your_reach.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_firstName.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_emails.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_phone.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_message.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        btn_submit.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_firstName.setText(savePref.get_first_name() + " " + savePref.get_last_name());
        edit_phone.setText(savePref.get_phone());

    }

    private void EventListner() {
        img_back.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_submit:
                validationOnEmail();
                break;
        }

    }

    private void validationOnEmail() {
        if (edit_emails.getText().toString().trim().isEmpty()) {
            String edit_email = getString(R.string.string_email);
            Utility.ShowToast(context, edit_email);
        } else if (!CommonMethods.isValidEmail(edit_emails.getText().toString())) {
            String edit_email = getString(R.string.string_validemail);
            Utility.ShowToast(context, edit_email);
        } else if (edit_message.getText().toString().trim().isEmpty()) {
            String edit_email = getString(R.string.write_message);
            Utility.ShowToast(context, edit_email);
        } else if (edit_message.length() < 50 || edit_message.length() > 250) {
            String lastnameLength = getString(R.string.length_of_message);
            Utility.ShowToast(context, lastnameLength);

        } else {
            if (Utility.isNetworkConnectionAvailable(context)) {
                progressDialog = Utility.ShowDialog(context);
                /*TODO
                Api call for contact with admin
                * user can simple write problem and send */
                ipContactUsActivity.doContacts(savePref.get_token(), edit_emails.getText().toString(), edit_message.getText().toString());

            }
        }
    }

    @Override
    public void successResponseFromPresenter(ContactUsResponseModel contactUsResponseModel) {
        progressDialog.dismiss();
        Utility.ShowSuccessToast(context, contactUsResponseModel.getMessage());
        edit_emails.setText("");
        edit_message.setText("");
    }

    @Override
    public void failedResponseFromPresenter(String message) {
        progressDialog.dismiss();
        String error_message = message;
        Utility.ShowToast(context, error_message);
    }

    @Override
    public void errorResponseFromPresenter(String message) {
        progressDialog.dismiss();
        String error_message = message;
        Utility.ShowToast(context, error_message);
    }
}
