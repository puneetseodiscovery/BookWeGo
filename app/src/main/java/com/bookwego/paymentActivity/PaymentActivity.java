package com.bookwego.paymentActivity;

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
import com.bookwego.baseClass.BaseClass;
import com.bookwego.paymentActivity.adapters.CardAdapter;
import com.bookwego.recentviewedActivity.adapters.RestaurantsAdapter;
import com.bookwego.recentviewedActivity.adapters.ServicesAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentActivity extends BaseClass implements View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.recycler_cards)
    RecyclerView recycler_cards;

    CardAdapter cardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        ButterKnife.bind(this);
        Initialization();
        EventListner();
    }

    private void Initialization() {

        cardAdapter = new CardAdapter(this);
        recycler_cards.setLayoutManager(new LinearLayoutManager(this));
        recycler_cards.setAdapter(cardAdapter);

       /* int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
        recycler_cards.setLayoutAnimation(animation);
*/
       // runLayoutAnimation(recycler_cards);

    }

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
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
