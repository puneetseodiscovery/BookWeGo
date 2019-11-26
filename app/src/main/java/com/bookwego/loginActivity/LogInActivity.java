package com.bookwego.loginActivity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.forgotPassword.ForgotPasswordActivity;
import com.bookwego.loginActivity.interfaces.ILoginActivity;
import com.bookwego.loginActivity.interfaces.IPLoginActivity;
import com.bookwego.loginActivity.loginResponseModel.LoginResponseModel;
import com.bookwego.loginActivity.loginResponseModel.SocialLoginResponseModel;
import com.bookwego.loginActivity.presenter.PLoginActivity;
import com.bookwego.loginActivity.runtimePermission.Runtimepermission;
import com.bookwego.mainActivity.MainActivity;
import com.bookwego.privacypolicy.PrivacyPolicyActivity;
import com.bookwego.profileActivity.PersonalProfileActivity;
import com.bookwego.registerActivity.RegisterActivity;
import com.bookwego.termandconditionActivity.TermConditionActivity;
import com.bookwego.utills.CommonMethods;
import com.bookwego.utills.SavePref;
import com.bookwego.utills.Utility;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogInActivity extends BaseClass implements ILoginActivity, View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {


    @BindView(R.id.edit_usereamil)
    EditText edit_usereamil;

    @BindView(R.id.edit_userpassword)
    EditText edit_userpassword;

    @BindView(R.id.tv_register)
    TextView tv_register;

    @BindView(R.id.tv_term_condition)
    TextView tv_term_condition;

    @BindView(R.id.tv_privacy_policy)
    TextView tv_privacy_policy;

    @BindView(R.id.tv_forgotpassword)
    TextView tv_forgotpassword;

    @BindView(R.id.btn_login)
    TextView btn_login;

    @BindView(R.id.signUp)
    TextView signUp;

    @BindView(R.id.img_passwordShow)
    ImageView img_passwordShow;

    @BindView(R.id.img_facebook)
    ImageView img_facebook;

    @BindView(R.id.img_google)
    ImageView img_google;

    @BindView(R.id.img_tiwtter)
    ImageView img_tiwtter;

    Context context;
    IPLoginActivity ipLoginActivity;
    boolean setVisible = false;

    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClientLocation;
    GoogleApiClient mGoogleApiClient;
    String placeCurrentLatitude = "";
    String placeCurrentLongitude = "";
    String get_DeviceToken = "";
    SavePref savePref;
    Dialog dialog;

    SignInButton google_login_button;  // google login button
    LoginButton login_button;  // facebook login button
    GoogleSignInOptions googleSignInOptions;
    private static final int RC_SIGN_IN = 0;
    String social_username = "", socail_email = "", socail_id = "", socail_image = "", socail_firstname = "", social_lastname = "";
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this); // facebook sdk initialize
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);
        context = LogInActivity.this;
        // TODO printKeyHash(this);
        new Runtimepermission(this);
        callbackManager = CallbackManager.Factory.create();
        ipLoginActivity = new PLoginActivity(this);
        savePref = new SavePref(this);

        mGoogleApiClientLocation = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds

        Initialization(); // findviewBy Id
        EventListner();

    }

    private void Initialization() {

        login_button = (LoginButton) findViewById(R.id.login_button);
        google_login_button = (SignInButton) findViewById(R.id.google_login_button);

        login_button.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        facebookLogin();

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // TODO Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();


        btn_login.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_usereamil.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_userpassword.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_register.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_term_condition.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_privacy_policy.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        signUp.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_forgotpassword.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));

        get_DeviceToken = SavePref.getDeviceToken(context, "device_token");
    }

    private void facebookLogin() {

        login_button.setReadPermissions(Arrays.asList("public_profile", "email"));
        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                dialog = Utility.ShowDialog(LogInActivity.this);
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken()
                        , new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {


                                try {
                                    socail_email = object.getString("email");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                try {
                                    socail_id = object.getString("id");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                try {
                                    socail_image = "http://graph.facebook.com/" + socail_id + "/picture?type=large";
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                try {
                                    social_username = object.getString("name");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                try {
                                    socail_firstname = object.getString("first_name");

                                } catch (JSONException e) {
                                    e.printStackTrace();

                                }
                                try {
                                    social_lastname = object.getString("last_name");


                                } catch (JSONException e) {
                                    e.printStackTrace();

                                }
                                /* TODO
                                SocaialLoginApi method call for  login and register on facebook
                                * type 2 for facebook*/
                                SocaialLoginApi(social_username, socail_email, get_DeviceToken, "2");
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,first_name,last_name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();
                dialog.dismiss();
            }

            @Override
            public void onCancel() {
                dialog.dismiss();
                String error = getString(R.string.socail_login_fail);
                Utility.ShowToast(context, error);
            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }
    /* TODO
    SocaialLoginApi method for login and register using facebook and google*/
    private void SocaialLoginApi(String social_username, String socail_email, String get_DeviceToken, String type) {
        dialog = Utility.ShowDialog(LogInActivity.this);
        /*Api for login and register using facebook and google*/
        ipLoginActivity.doSocialLogin(social_username, socail_email, placeCurrentLatitude, placeCurrentLongitude
                , get_DeviceToken, type);
    }

    private void EventListner() {

        tv_register.setOnClickListener(this);
        tv_forgotpassword.setOnClickListener(this);
        tv_term_condition.setOnClickListener(this);
        tv_privacy_policy.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        img_passwordShow.setOnClickListener(this);
        img_facebook.setOnClickListener(this);
        img_google.setOnClickListener(this);
        img_tiwtter.setOnClickListener(this);

    }

    private boolean isGpsOn() {

        boolean isGpsOn = false;

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            isGpsOn = false;

        } else {

            isGpsOn = true;

        }

        return isGpsOn;
    }

    private void showGPSDisabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage(getResources().getString(R.string.gps_disable))
                .setCancelable(false)
                .setPositiveButton(getResources().getString(R.string.turn_on),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent callGPSSettingIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                                dialog.cancel();
                            }
                        });
        /*alertDialogBuilder.setNegativeButton(getResources().getString(R.string.cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });*/
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    public void onClick(View v) {

        Intent intent = null;

        switch (v.getId()) {

            case R.id.tv_register:

                intent = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(0, R.anim.splash_faidout);

                break;

            case R.id.tv_forgotpassword:

                intent = new Intent(LogInActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
                overridePendingTransition(0, R.anim.splash_faidout);

                break;

            case R.id.tv_privacy_policy:

                intent = new Intent(LogInActivity.this, PrivacyPolicyActivity.class);
                startActivity(intent);
                overridePendingTransition(0, R.anim.splash_faidout);

                break;


            case R.id.tv_term_condition:

                intent = new Intent(LogInActivity.this, TermConditionActivity.class);
                startActivity(intent);
                overridePendingTransition(0, R.anim.splash_faidout);

                break;

            case R.id.btn_login:

                validationOnLogin();

                break;

            case R.id.img_passwordShow:

                bounceImage();

                if (edit_userpassword.getText().toString().trim().isEmpty()) {
                    String nothingToShow = getString(R.string.nothing_to_show);
                    Utility.ShowToast(context, nothingToShow);
                } else {

                    if (setVisible == false) {
                        edit_userpassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        edit_userpassword.setSelection(edit_userpassword.length());
                        img_passwordShow.setImageResource(R.drawable.ic_visibility);
                        setVisible = true;

                    } else {
                        edit_userpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        edit_userpassword.setSelection(edit_userpassword.length());
                        img_passwordShow.setImageResource(R.drawable.ic_visibility_off);
                        setVisible = false;
                    }
                }
                break;

            case R.id.img_facebook:
                if (Utility.    isNetworkConnectionAvailable(context)) {
                    /*performClick click event call for facebook login */
                    login_button.performClick();
                }
                break;
            case R.id.img_google:
                if (Utility.isNetworkConnectionAvailable(context)) {
                    /*Intent for google login */
                    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                    startActivityForResult(signInIntent, RC_SIGN_IN);
                }
                break;
            case R.id.img_tiwtter:

                if (Utility.isNetworkConnectionAvailable(context)){

                }else {

                }

                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Calling a new function to handle signin
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();

            socail_id = acct.getId();
            //social_username = acct.getDisplayName();
            social_username = acct.getGivenName();
            socail_email = acct.getEmail();
            socail_image = String.valueOf(acct.getPhotoUrl());
            /*TODO
            SocaialLoginApi method call for  login and register on google
             * type 2 for google*/
            SocaialLoginApi(social_username, socail_email, get_DeviceToken, "3");


        } else {
            Toast.makeText(context, "Google Login Cancel", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }


    @Override
    protected void onStop() {
        super.onStop();

        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        disconnectFromFacebook();
        mGoogleApiClientLocation.connect();
        if (!isGpsOn())
            showGPSDisabledAlertToUser();
    }


    private void disconnectFromFacebook() {
        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {

                LoginManager.getInstance().logOut();

            }
        }).executeAsync();
    }

    public void bounceImage() {

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        // Use bounce interpolator with amplitude 0.1 and frequency 15
        PersonalProfileActivity.MyBounceInterpolator interpolator = new PersonalProfileActivity.MyBounceInterpolator(0.1, 15);
        myAnim.setInterpolator(interpolator);
        img_passwordShow.startAnimation(myAnim);

    }

    private void validationOnLogin() {

        if (Utility.isNetworkConnectionAvailable(context)) {
            if (edit_usereamil.getText().toString().trim().isEmpty()) {
                String edit_email = getString(R.string.string_email);
                Utility.ShowToast(context, edit_email);
            } else if (!CommonMethods.isValidEmail(edit_usereamil.getText().toString())) {
                String edit_email = getString(R.string.string_validemail);
                Utility.ShowToast(context, edit_email);
            } else if (edit_userpassword.getText().toString().trim().isEmpty()) {
                String password = getString(R.string.r_password_enter);
                Utility.ShowToast(context, password);

            } else {

                dialog = Utility.ShowDialog(this);
                /*TODO
                 Api call for user login from app
                * for type 1 simple login*/
                ipLoginActivity.doLogin(edit_usereamil.getText().toString().trim(), "", edit_userpassword.getText().toString().trim(),
                        get_DeviceToken, "1", placeCurrentLatitude, placeCurrentLongitude);

            }
        }

    }

    @Override
    public void onLoginSuccessResponseFromPresenter(LoginResponseModel loginResponseModel) {

        dialog.dismiss();

        savePref.set_id(loginResponseModel.getData().get(0).getId());
        savePref.set_first_name(loginResponseModel.getData().get(0).getFirstName());
        savePref.set_last_name(loginResponseModel.getData().get(0).getLastName());
        savePref.set_email(loginResponseModel.getData().get(0).getEmail());
        savePref.set_token(loginResponseModel.getData().get(0).getToken());
        savePref.set_phone(loginResponseModel.getData().get(0).getPhone());
        savePref.set_dob(loginResponseModel.getData().get(0).getDob());
        savePref.set_gender(loginResponseModel.getData().get(0).getGender());
        savePref.set_address(loginResponseModel.getData().get(0).getAddress());
        savePref.set_address_latitude(loginResponseModel.getData().get(0).getAddressLatitude());
        savePref.set_address_logitude(loginResponseModel.getData().get(0).getAddressLogitude());
        savePref.set_citizenship(loginResponseModel.getData().get(0).getCitizenship());
        savePref.set_employment(loginResponseModel.getData().get(0).getEmployment());
        savePref.set_profile_image(loginResponseModel.getData().get(0).getProfileImage());
        savePref.set_user_type(loginResponseModel.getData().get(0).getUserType());
        savePref.set_login_type(loginResponseModel.getData().get(0).getLoginType());

        Log.e("userData++++++++++", savePref.get_id());
        Log.e("userData++++++++++", savePref.get_first_name());
        Log.e("userData++++++++++", savePref.get_last_name());
        Log.e("userData++++++++++", savePref.get_email());
        Log.e("userData++++++++++", savePref.get_phone());

        Intent intent = new Intent(LogInActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(0, R.anim.splash_faidout);
        Utility.ShowSuccessToast(context, loginResponseModel.getMessage());
    }

    @Override
    public void onLoginFailedResponseFromPresenter(String message) {

        //progressDialog.dismiss();
        dialog.dismiss();
        String error_message = message;
        Utility.ShowToast(context, error_message);

    }

    @Override
    public void onLoginInvalidRequestFromPresenter(String message) {
        //progressDialog.dismiss();
        dialog.dismiss();
        String error_message = message;
        Utility.ShowToast(context, error_message);
    }

    @Override
    public void onSocialLoginSucessResponseFromPresenter(SocialLoginResponseModel socialLoginResponseModel) {

        dialog.dismiss();
        savePref.set_id(socialLoginResponseModel.getData().get(0).getId());
        savePref.set_first_name(socialLoginResponseModel.getData().get(0).getFirstName());
        savePref.set_email(socialLoginResponseModel.getData().get(0).getEmail());
        savePref.set_token(socialLoginResponseModel.getData().get(0).getToken());
        savePref.set_token(socialLoginResponseModel.getData().get(0).getToken());
        savePref.set_phone(socialLoginResponseModel.getData().get(0).getPhone());
        savePref.set_dob(socialLoginResponseModel.getData().get(0).getDob());
        savePref.set_gender(socialLoginResponseModel.getData().get(0).getGender());
        savePref.set_address(socialLoginResponseModel.getData().get(0).getAddress());
        savePref.set_address_latitude(socialLoginResponseModel.getData().get(0).getAddressLatitude());
        savePref.set_address_logitude(socialLoginResponseModel.getData().get(0).getAddressLogitude());
        savePref.set_citizenship(socialLoginResponseModel.getData().get(0).getCitizenship());
        savePref.set_employment(socialLoginResponseModel.getData().get(0).getEmployment());
        savePref.set_profile_image(socialLoginResponseModel.getData().get(0).getProfileImage());
        savePref.set_user_type(socialLoginResponseModel.getData().get(0).getUserType());
        savePref.set_login_type(socialLoginResponseModel.getData().get(0).getLoginType());


        if (socialLoginResponseModel.getData().get(0).getId().isEmpty() || socialLoginResponseModel.getData().get(0) == null) {
            Intent intent = new Intent(LogInActivity.this, LogInActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            overridePendingTransition(0, R.anim.splash_faidout);
        } else {
            Intent intent = new Intent(LogInActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            overridePendingTransition(0, R.anim.splash_faidout);
            Utility.ShowSuccessToast(context, socialLoginResponseModel.getMessage());
        }


    }

    @Override
    public void onSocialLoginFailedResponseFromPresenter(String message) {
        dialog.dismiss();
        String error_message = message;
        Utility.ShowToast(context, error_message);
    }

    @Override
    public void onSocialLoginInvalidResponseFromPresenter(String message) {
        dialog.dismiss();
        String error_message = message;
        Utility.ShowToast(context, error_message);
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClientLocation);

        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClientLocation, mLocationRequest, this);
        } else {

            if (String.valueOf(location.getLatitude()).isEmpty()) {

            } else {

                placeCurrentLatitude = String.valueOf(location.getLatitude());
                placeCurrentLongitude = String.valueOf(location.getLongitude());

                if (placeCurrentLatitude.isEmpty()) {

                } else {


                }

            }

        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    // hash key for social login google/facebook
    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();
            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);
            Log.e("Package Name=", context.getApplicationContext().getPackageName());
            for (android.content.pm.Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));
                Log.e("Key Hash======", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }
        return key;
    }
}
