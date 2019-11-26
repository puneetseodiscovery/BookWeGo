package com.bookwego.termandconditionActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.termandconditionActivity.interfaces.IPTermConditionActivity;
import com.bookwego.termandconditionActivity.interfaces.ITermConditionActivity;
import com.bookwego.termandconditionActivity.presenter.PTermConditionActivity;
import com.bookwego.termandconditionActivity.responseModel.TermsConditionResponseModel;
import com.bookwego.utills.Utility;
import com.codesgood.views.JustifiedTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TermConditionActivity extends BaseClass implements ITermConditionActivity, View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.tv_termsConditions)
    JustifiedTextView tv_termsConditions;

    Context context;
    IPTermConditionActivity ipTermConditionActivity;
    Dialog progressDilaog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_condition);

        ButterKnife.bind(this);
        context = TermConditionActivity.this;
        ipTermConditionActivity = new PTermConditionActivity(this);
        Initialization();
        EventListner();
    }

    private void Initialization() {
        if (Utility.isNetworkConnectionAvailable(context)) {
            progressDilaog = Utility.ShowDialog(context);
            /*api call for get terms and conditions*/
            ipTermConditionActivity.doTermsCondition();
        }
    }

    private void EventListner() {
        img_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.img_back:
                finish();
                break;
        }
    }

    @Override
    public void onSuccessResponseFromPresenter(TermsConditionResponseModel termsConditionResponseModel) {
        progressDilaog.dismiss();
        tv_termsConditions.setText(termsConditionResponseModel.getData().getAboutUs());

    }

    @Override
    public void onFailedResponseFromPresenter(String message) {
        progressDilaog.dismiss();
    }
}
