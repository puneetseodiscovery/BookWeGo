package com.bookwego.profileActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

public class PersonalProfileActivity extends BaseClass implements View.OnClickListener {

    @BindView(R.id.btn_next)
    Button btn_next;

    @BindView(R.id.edit_firstName)
    EditText edit_firstName;

    @BindView(R.id.edit_lastName)
    EditText edit_lastName;

    @BindView(R.id.edit_email)
    EditText edit_email;

    @BindView(R.id.edit_phone)
    EditText edit_phone;

    @BindView(R.id.edit_ageorbithdate)
    EditText edit_ageorbithdate;

    @BindView(R.id.img_back)
    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);

        ButterKnife.bind(this);

        Initialization();
        EventListner();
    }


    private void Initialization() {

        edit_firstName.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_lastName.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_email.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_phone.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_ageorbithdate.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        btn_next.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));

    }

    private void EventListner() {

        btn_next.setOnClickListener(this);
        img_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.img_back:

                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_click);
                img_back.startAnimation(animation);
                finish();
                overridePendingTransition(R.anim.main_faidin, R.anim.main_faidout);
                break;
            case R.id.btn_next:

                Intent intent = new Intent(PersonalProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
                overridePendingTransition(0, R.anim.splash_faidout);

                break;
        }


    }

}
