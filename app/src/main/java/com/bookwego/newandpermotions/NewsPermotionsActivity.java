package com.bookwego.newandpermotions;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.newandpermotions.adapters.NewsPermotionsAdapter;
import com.bookwego.newandpermotions.interfaces.INewsPromotionsActivty;
import com.bookwego.newandpermotions.interfaces.IPNewsPromotionsActivty;
import com.bookwego.newandpermotions.presenter.PNewsPermotionsActivity;
import com.bookwego.newandpermotions.responseModel.PromotionNewsResponseModel;
import com.bookwego.utills.CommonMethods;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsPermotionsActivity extends BaseClass implements INewsPromotionsActivty,View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.recycler_newspermotions)
    RecyclerView recycler_newspermotions;

    @BindView(R.id.layout_empty)
    RelativeLayout layout_empty;

    NewsPermotionsAdapter newsPermotionsAdapter;

    Context context;
    IPNewsPromotionsActivty ipNewsPromotionsActivty;
    Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_permotions);
        ButterKnife.bind(this);
        Initialization();
        EventListner();
    }


    private void Initialization() {

        context=NewsPermotionsActivity.this;
        ipNewsPromotionsActivty=new PNewsPermotionsActivity(this);

        // api call get list of news and promotion restaurants
        if (Utility.isNetworkConnectionAvailable(context)){
            progressDialog=Utility.ShowDialog(context);
            ipNewsPromotionsActivty.getListOfRestaurants();
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
    public void getNewsPromotionsSuccessFromPresenter(PromotionNewsResponseModel promotionNewsResponseModel) {

        if (promotionNewsResponseModel.getData()!=null && promotionNewsResponseModel.getData().size()>0){

            progressDialog.dismiss();
            layout_empty.setVisibility(View.INVISIBLE);
            recycler_newspermotions.setVisibility(View.VISIBLE);

            newsPermotionsAdapter = new NewsPermotionsAdapter(this,promotionNewsResponseModel.getData());
            recycler_newspermotions.setLayoutManager(new LinearLayoutManager(this));
            recycler_newspermotions.setAdapter(newsPermotionsAdapter);

        }else {
            layout_empty.setVisibility(View.VISIBLE);
            recycler_newspermotions.setVisibility(View.GONE);
        }
    }

    @Override
    public void getNewsPromotionsFailedFromPresenter(String message) {
        progressDialog.dismiss();
    }

    @Override
    public void getNewsPromotionsEmptyFromPresenter(String message) {
        progressDialog.dismiss();
        layout_empty.setVisibility(View.VISIBLE);
        recycler_newspermotions.setVisibility(View.GONE);
    }
}
