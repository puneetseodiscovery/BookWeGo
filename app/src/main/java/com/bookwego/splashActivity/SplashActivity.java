package com.bookwego.splashActivity;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.loginActivity.LogInActivity;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SplashActivity extends BaseClass {
    private static int SPLASH_TIME_OUT = 3000;

    @BindView(R.id.tv_discount)
    TextView tv_discount;


    @BindView(R.id.tv_upto)
    TextView tv_upto;

    @BindView(R.id.tv_off)
    TextView tv_off;

    @BindView(R.id.tv_savetime)
    TextView tv_savetime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        tv_discount.setTypeface(Utility.typeFaceForPoppinsBoldText(this));
        tv_upto.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_off.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_savetime.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
               Intent i = new Intent(SplashActivity.this, LogInActivity.class);
                startActivity(i);
                overridePendingTransition(0, R.anim.splash_faidout);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
