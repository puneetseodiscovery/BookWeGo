package com.bookwego.helpActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.helpActivity.helpResponseModel.HelpResponseModel;
import com.bookwego.helpActivity.interfaces.IHelp;
import com.bookwego.helpActivity.interfaces.IPHelp;
import com.bookwego.helpActivity.presenter.PHelp;
import com.bookwego.utills.SavePref;
import com.bookwego.utills.Utility;
import com.codesgood.views.JustifiedTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HelpActivity extends BaseClass implements IHelp, View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.tv_help)
    JustifiedTextView  tv_help;

    Context context;
    Dialog progressDialog;
    IPHelp ipHelp;
    SavePref savePref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        ButterKnife.bind(this);

        Initialization();
        EventListner();

    }


    private void Initialization() {
        context=HelpActivity.this;
        ipHelp=new PHelp(this);
        savePref=new SavePref(this);
        if (Utility.isNetworkConnectionAvailable(context)) {
            progressDialog = Utility.ShowDialog(context);
            /*Api call for show the topic for help*/
            ipHelp.dogetHelp(savePref.get_token());
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
    public void successFromPresenter(HelpResponseModel helpResponseModel) {
        progressDialog.dismiss();
        tv_help.setText(helpResponseModel.getData().getHelp());
    }

    @Override
    public void faiedFromPresenter(String message) {
        progressDialog.dismiss();
    }
}
