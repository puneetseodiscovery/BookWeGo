package com.bookwego.baseClass;

import android.app.Application;


import com.bookwego.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MyApp extends Application {

    private static MyApp sAppContext;

    public static synchronized MyApp getInstance() {
        return sAppContext;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        sAppContext = this;
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/ProximaNovaRegular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );


    }

}
