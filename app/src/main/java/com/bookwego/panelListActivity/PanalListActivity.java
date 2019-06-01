package com.bookwego.panelListActivity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.orderActivity.adapters.OrdersAdapter;
import com.bookwego.panelListActivity.adapters.RestaurantsListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PanalListActivity extends BaseClass implements View.OnClickListener {


    @BindView(R.id.restaurantsList_recyclerview)
    RecyclerView restaurantsList_recyclerview;

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.img_filter)
    ImageView img_filter;

    @BindView(R.id.tv_panel)
    TextView tv_panel;

    @BindView(R.id.tv_list)
    TextView tv_list;

    RestaurantsListAdapter restaurantsListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panal_list);
        ButterKnife.bind(this);

        Initialization();
        EventListeners();
    }


    private void Initialization() {

        restaurantsListAdapter = new RestaurantsListAdapter(this);
        restaurantsList_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        restaurantsList_recyclerview.setAdapter(restaurantsListAdapter);
    }

    private void EventListeners() {

        img_back.setOnClickListener(this);
        tv_panel.setOnClickListener(this);
        tv_list.setOnClickListener(this);
        img_filter.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_panel:

                tv_panel.setBackgroundColor(getResources().getColor(R.color.colorTextGreen));
                tv_list.setBackgroundColor(getResources().getColor(R.color.colorTextWhite));
                break;
            case R.id.tv_list:

                tv_list.setBackgroundColor(getResources().getColor(R.color.colorTextGreen));
                tv_panel.setBackgroundColor(getResources().getColor(R.color.colorTextWhite));

                break;
            case R.id.img_filter:

                OpenFilterDialog();

                break;

        }
    }

    private void OpenFilterDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_filter);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.TOP | Gravity.RIGHT;

        wlp.width = RecyclerView.LayoutParams.MATCH_PARENT;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(true);


        ImageView img_close = (ImageView) dialog.findViewById(R.id.img_close);
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

}
