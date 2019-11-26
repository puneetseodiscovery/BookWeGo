package com.bookwego.addCardActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddCardActivity extends BaseClass implements View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.edit_cardholder_Name)
    EditText edit_cardholder_Name;

    @BindView(R.id.edit_card_number)
    EditText edit_card_number;

    @BindView(R.id.edit_month)
    EditText edit_month;

    @BindView(R.id.edit_year)
    EditText edit_year;

    @BindView(R.id.edit_cvv)
    EditText edit_cvv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        ButterKnife.bind(this);

        Initialization();
        EventListner();

    }

    private void Initialization() {

        edit_cardholder_Name.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_card_number.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_month.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_year.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_cvv.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
    }

    private void EventListner() {

        img_back.setOnClickListener(this);

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

        }

    }
}
