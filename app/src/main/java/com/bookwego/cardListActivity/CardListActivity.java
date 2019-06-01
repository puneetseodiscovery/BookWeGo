package com.bookwego.cardListActivity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;

import com.bookwego.R;
import com.bookwego.cardListActivity.adapters.CardForPaymentAdapter;
import com.bookwego.paymentActivity.adapters.CardAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardListActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.recycler_cards)
    RecyclerView recycler_cards;

    CardForPaymentAdapter cardForPaymentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);
        ButterKnife.bind(this);
        Initialization();
        EventListner();
    }

    private void Initialization() {

        cardForPaymentAdapter = new CardForPaymentAdapter(this);
        recycler_cards.setLayoutManager(new LinearLayoutManager(this));
        recycler_cards.setAdapter(cardForPaymentAdapter);


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

}
