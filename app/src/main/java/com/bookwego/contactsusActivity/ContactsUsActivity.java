package com.bookwego.contactsusActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsUsActivity extends BaseClass implements View.OnClickListener {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_us);
        ButterKnife.bind(this);

        Initialization();
        EventListner();


    }


    private void Initialization() {
        tv_get_in_touch.setTypeface(Utility.typeFaceForProximaNovaBoldText(this));
        tv_always_within_your_reach.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
        edit_firstName.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_emails.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_phone.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_message.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        btn_submit.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));


    }

    private void EventListner() {
        img_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
        }

    }

}
