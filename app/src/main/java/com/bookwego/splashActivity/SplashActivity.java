package com.bookwego.splashActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.forgotPassword.interfaces.IForgotPassword;
import com.bookwego.loginActivity.LogInActivity;
import com.bookwego.mainActivity.MainActivity;
import com.bookwego.otpActivity.OtpActivity;
import com.bookwego.utills.MyFirebaseMessagingService;
import com.bookwego.utills.SavePref;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SplashActivity extends BaseClass {

    private static int SPLASH_TIME_OUT = 3000;
    private SparseIntArray mErrorString;
    private static final int REQUEST_PERMISSIONS = 20;


    @BindView(R.id.tv_discount)
    TextView tv_discount;


    @BindView(R.id.tv_upto)
    TextView tv_upto;

    @BindView(R.id.tv_off)
    TextView tv_off;

    @BindView(R.id.tv_savetime)
    TextView tv_savetime;

    SavePref savePref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        savePref=new SavePref(this);
        tv_discount.setTypeface(Utility.typeFaceForPoppinsBoldText(this));
        tv_upto.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_off.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_savetime.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));

        mErrorString = new SparseIntArray();

        int currentapiVersion = Build.VERSION.SDK_INT;
        // if current version is M or greater than M
        if (currentapiVersion >= Build.VERSION_CODES.M) {
            String[] array = {
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA,
            };

            requestAppPermissions(array, R.string.permission, REQUEST_PERMISSIONS);
        } else {
            onPermissionsGranted(REQUEST_PERMISSIONS);
        }
    }

    // check requested permissions are on or off
    public void requestAppPermissions(final String[] requestedPermissions, final int stringId, final int requestCode) {
        mErrorString.put(requestCode, stringId);
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        boolean shouldShowRequestPermissionRationale = false;
        for (String permission : requestedPermissions) {
            permissionCheck = permissionCheck + ContextCompat.checkSelfPermission(this, permission);
            shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale || ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
        }
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale) {
                Snackbar snack = Snackbar.make(findViewById(android.R.id.content), stringId, Snackbar.LENGTH_INDEFINITE);
                View view = snack.getView();
                TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                tv.setTextColor(Color.WHITE);
                snack.setAction("GRANT", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityCompat.requestPermissions(SplashActivity.this, requestedPermissions, requestCode);
                    }
                }).show();
            } else {
                ActivityCompat.requestPermissions(this, requestedPermissions, requestCode);
            }
        } else {
            onPermissionsGranted(requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int permission : grantResults) {
            permissionCheck = permissionCheck + permission;
        }
        if ((grantResults.length > 0) && permissionCheck == PackageManager.PERMISSION_GRANTED) {
            onPermissionsGranted(requestCode);
        } else {
        }
    }


    // if permissions granted succesfully
    private void onPermissionsGranted(int requestcode) {

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        startTimer();

      /*  new Handler().postDelayed(new Runnable() {

            *//*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             *//*

            @Override
            public void run() {


            }
        }, SPLASH_TIME_OUT);*/
    }

    private void startTimer() {
        startService(new Intent(SplashActivity.this, MyFirebaseMessagingService.class));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                    if (savePref.get_id().equals("") || savePref.get_id() == null){
                        Intent intent = new Intent(SplashActivity.this, LogInActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }else {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        overridePendingTransition(0, R.anim.splash_faidout);
                        finish();
                    }

            }
        }, SPLASH_TIME_OUT);

    }
}
