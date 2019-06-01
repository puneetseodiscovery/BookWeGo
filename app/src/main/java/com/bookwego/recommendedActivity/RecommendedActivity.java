package com.bookwego.recommendedActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.faqActivity.adapter.FAQAdapter;
import com.bookwego.menuItemDetailsActivity.adapters.ReviewsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecommendedActivity extends BaseClass implements View.OnClickListener {

    @BindView(R.id.recycler_recommanded)
    RecyclerView recycler_recommanded;

    @BindView(R.id.img_back)
    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended);
        ButterKnife.bind(this);

        Initialization();
        EventListner();
    }


    private void Initialization() {

        ReviewsAdapter reviewsAdapter = new ReviewsAdapter(this);
        recycler_recommanded.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recycler_recommanded.setAdapter(reviewsAdapter);

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
