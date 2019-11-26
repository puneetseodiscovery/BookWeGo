package com.bookwego.favouriteActvity;

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
import com.bookwego.favouriteActvity.adapters.FavouriteAdapter;
import com.bookwego.favouriteActvity.interfaces.IFavouriteActivity;
import com.bookwego.favouriteActvity.interfaces.IPFavouriteActivity;
import com.bookwego.favouriteActvity.presenter.PFavouriteActivity;
import com.bookwego.favouriteActvity.responseModel.FavrouitListingResponseModel;
import com.bookwego.utills.SavePref;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouriteActivity extends BaseClass implements IFavouriteActivity, View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.recycler_favourite)
    RecyclerView recycler_favourite;

    @BindView(R.id.layout_empty)
    RelativeLayout layout_empty;

    FavouriteAdapter favouriteAdapter;

    Context context;
    SavePref savePref;
    Dialog progressDialog;
    IPFavouriteActivity ipFavouriteActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        ButterKnife.bind(this);
        Initialization();
        EventListner();
    }


    private void Initialization() {

        context=FavouriteActivity.this;
        savePref=new SavePref(this);
        ipFavouriteActivity=new PFavouriteActivity(this);

        if (Utility.isNetworkConnectionAvailable(context)){
            progressDialog=Utility.ShowDialog(context);
            /*api call for get all listing of favrouite restaurants*/
            ipFavouriteActivity.getFavrouiteListing(savePref.get_id());
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
    public void onSuccessResponseFromPresenter(FavrouitListingResponseModel favrouitListingResponseModel) {
        progressDialog.dismiss();
        if (favrouitListingResponseModel!=null && favrouitListingResponseModel.getData().size()>0){
            layout_empty.setVisibility(View.INVISIBLE);
            recycler_favourite.setVisibility(View.VISIBLE);
            favouriteAdapter = new FavouriteAdapter(this,favrouitListingResponseModel.getData());
            recycler_favourite.setLayoutManager(new LinearLayoutManager(this));
            recycler_favourite.setAdapter(favouriteAdapter);

        }else {

        }

        
    }

    @Override
    public void onFailedResponseFromPresenter(String message) {
        progressDialog.dismiss();
    }

    @Override
    public void onEmptyResponseFromPresenter(String message) {
        progressDialog.dismiss();
        layout_empty.setVisibility(View.VISIBLE);
        recycler_favourite.setVisibility(View.INVISIBLE);
    }
}
