package com.bookwego.faqActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.faqActivity.adapter.FAQAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FAQActivity extends BaseClass implements View.OnClickListener {

    @BindView(R.id.recycler_faq)
    RecyclerView recycler_faq;

    @BindView(R.id.img_back)
    ImageView img_back;

    FAQAdapter faqAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        ButterKnife.bind(this);

        Initialization();
        EventListner();
    }


    private void Initialization() {

        faqAdapter = new FAQAdapter(this);
        recycler_faq.setLayoutManager(new LinearLayoutManager(this));
        recycler_faq.setAdapter(faqAdapter);

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
