package com.bookwego.changePasswordActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.registerActivity.RegisterActivity;
import com.bookwego.registerActivity.RegisterNextActivity;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangePasswordActivity extends BaseClass implements View.OnClickListener {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        ButterKnife.bind(this);

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
            case R.id.btn_next:

                finish();
                break;

            case R.id.img_back:

                finish();
                break;
        }


    }

}
