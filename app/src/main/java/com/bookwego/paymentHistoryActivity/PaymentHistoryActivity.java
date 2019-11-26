package com.bookwego.paymentHistoryActivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.paymentHistoryActivity.adapters.PaymentHistoryAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentHistoryActivity extends BaseClass implements View.OnClickListener {

    @BindView(R.id.payment_history_recyclerview)
    RecyclerView payment_history_recyclerview;

    @BindView(R.id.img_back)
    ImageView img_back;

    PaymentHistoryAdapter paymentHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);
        ButterKnife.bind(this);
        Initialization();
        EventListeners();
    }

    private void Initialization() {
        paymentHistoryAdapter = new PaymentHistoryAdapter(this);
        payment_history_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        payment_history_recyclerview.setAdapter(paymentHistoryAdapter);


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
