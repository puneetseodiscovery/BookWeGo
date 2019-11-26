package com.bookwego.faqActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.faqActivity.adapter.FAQAdapter;
import com.bookwego.faqActivity.interfaces.IFAQ;
import com.bookwego.faqActivity.interfaces.IPFAQ;
import com.bookwego.faqActivity.presenter.PFAQ;
import com.bookwego.faqActivity.responseModel.FAQResponseModel;
import com.bookwego.utills.SavePref;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FAQActivity extends BaseClass implements IFAQ, View.OnClickListener {

    @BindView(R.id.recycler_faq)
    RecyclerView recycler_faq;

    @BindView(R.id.img_back)
    ImageView img_back;

    FAQAdapter faqAdapter;
    Context context;
    SavePref savePref;
    IPFAQ ipfaq;
    Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        ButterKnife.bind(this);
        Initialization();
        EventListner();

    }

    private void Initialization() {

        context = FAQActivity.this;
        savePref = new SavePref(this);
        ipfaq = new PFAQ(this);
        if (Utility.isNetworkConnectionAvailable(context)) {
            progressDialog = Utility.ShowDialog(context);
            /* TODO Api call for frequentaly asked question*/
            ipfaq.doFaq(savePref.get_token());
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
    public void successResponseFromPresentr(FAQResponseModel responseModel) {
        progressDialog.dismiss();
        if (responseModel != null && responseModel.getData().size() > 0) {
            faqAdapter = new FAQAdapter(this, responseModel.getData());
            recycler_faq.setLayoutManager(new LinearLayoutManager(this));
            recycler_faq.setAdapter(faqAdapter);
        } else {

        }

    }

    @Override
    public void failedResponseFromPresenter(String message) {
        progressDialog.dismiss();
    }

    @Override
    public void errorResponseFromPresenter(String message) {
        progressDialog.dismiss();
    }
}
