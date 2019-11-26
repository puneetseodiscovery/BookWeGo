package com.bookwego.privacypolicy;

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
import com.bookwego.privacypolicy.interfaces.IPPrivacy_Policy;
import com.bookwego.privacypolicy.interfaces.IPrivacy_Policy;
import com.bookwego.privacypolicy.presenter.PPrivacy_Policy;
import com.bookwego.privacypolicy.responseModel.PirvacyPolicyResponseModel;
import com.bookwego.utills.Utility;
import com.codesgood.views.JustifiedTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PrivacyPolicyActivity extends BaseClass implements IPrivacy_Policy, View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.tv_privacy_policy)
    JustifiedTextView tv_privacy_policy;

    Context context;
    IPPrivacy_Policy ipPrivacy_policy;
    Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        ButterKnife.bind(this);
        context = PrivacyPolicyActivity.this;
        ipPrivacy_policy = new PPrivacy_Policy(this);

        Initialization();
        EventListner();

    }


    private void Initialization() {
        if (Utility.isNetworkConnectionAvailable(context)) {
            progressDialog = Utility.ShowDialog(context);
            /*Api call for get privacy polciy*/
            ipPrivacy_policy.doPrivacyPolicy();
        }
    }

    private void EventListner() {

        img_back.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        Intent intent = null;

        switch (v.getId()) {

            case R.id.img_back:
                finish();
                break;

        }

    }

    @Override
    public void onPrivacyPolicySuccessFormPresenter(PirvacyPolicyResponseModel pirvacyPolicyResponseModel) {
        progressDialog.dismiss();
        tv_privacy_policy.setText(pirvacyPolicyResponseModel.getData().getPrivacyPolicy());
    }

    @Override
    public void onPrivacyPolicyFailedFormPresenter(String message) {
        progressDialog.dismiss();
    }
}
