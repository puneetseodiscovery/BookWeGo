package com.bookwego.refferalProgramActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bookwego.BuildConfig;
import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReferEarnActivity extends BaseClass implements View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.btn_invite)
    Button btn_invite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refaral_earn);

        ButterKnife.bind(this);

        Initialization();
        EventListner();

    }


    private void Initialization() {

    }

    private void EventListner() {

        img_back.setOnClickListener(this);
        btn_invite.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_invite:
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage= "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
                break;
        }
    }
}
