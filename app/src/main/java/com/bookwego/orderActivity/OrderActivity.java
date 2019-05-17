package com.bookwego.orderActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.orderActivity.adapters.OrdersAdapter;
import com.bookwego.reservationsActivity.adapters.HistoryAdapter;

import java.util.EventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends BaseClass implements View.OnClickListener {

    @BindView(R.id.order_recyclerview)
    RecyclerView order_recyclerview;

    @BindView(R.id.img_back)
    ImageView img_back;

    OrdersAdapter ordersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ButterKnife.bind(this);


        Initialization();
        EventListeners();
    }


    private void Initialization() {

        ordersAdapter = new OrdersAdapter(this);
        order_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        order_recyclerview.setAdapter(ordersAdapter);
    }

    private void EventListeners() {
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
}
