package com.bookwego.bookingConfirmed;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.networkServices.NetworkChangeReceiver;

public class BookingConfirmedActivity extends BaseClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmed);

    }

}
