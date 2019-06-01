package com.bookwego.newandpermotions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.newandpermotions.adapters.NewsPermotionsAdapter;
import com.bookwego.recentviewedActivity.adapters.RestaurantsAdapter;
import com.bookwego.recentviewedActivity.adapters.ServicesAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsPermotionsActivity extends BaseClass implements View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.recycler_newspermotions)
    RecyclerView recycler_newspermotions;

    NewsPermotionsAdapter newsPermotionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_permotions);
        ButterKnife.bind(this);
        Initialization();
        EventListner();
    }


    private void Initialization() {
        newsPermotionsAdapter = new NewsPermotionsAdapter(this);
        recycler_newspermotions.setLayoutManager(new LinearLayoutManager(this));
        recycler_newspermotions.setAdapter(newsPermotionsAdapter);


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
