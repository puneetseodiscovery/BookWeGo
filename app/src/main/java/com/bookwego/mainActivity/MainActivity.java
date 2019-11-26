package com.bookwego.mainActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bookwego.IndexableRecyclerView.IndexableRecyclerViewActivity;
import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.cartActivity.CartActivity;
import com.bookwego.contactsusActivity.ContactsUsActivity;
import com.bookwego.livechatActivity.LiveChatActivity;
import com.bookwego.mainActivity.fragments.RestaurantsFragment;
import com.bookwego.mainActivity.fragments.ServicesFragment;
import com.bookwego.mainActivity.interfaces.IMainActivity;
import com.bookwego.mainActivity.interfaces.IPMainActivity;
import com.bookwego.mainActivity.presenter.PMainActivity;
import com.bookwego.mainActivity.responseModel.CityUpdateResponseModel;
import com.bookwego.mainActivity.responseModel.GetCityResponseModel;
import com.bookwego.orderActivity.OrderActivity;
import com.bookwego.paymentHistoryActivity.PaymentHistoryActivity;
import com.bookwego.profileActivity.PersonalProfileActivity;
import com.bookwego.reservationsActivity.ReservationsActivity;
import com.bookwego.settingActivity.SettingsActivity;
import com.bookwego.utills.CommonMethods;
import com.bookwego.utills.Constant;
import com.bookwego.utills.SavePref;
import com.bookwego.utills.Utility;
import com.bumptech.glide.Glide;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends BaseClass implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, IMainActivity, View.OnClickListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;

    @BindView(R.id.menu)
    ImageView menu;

    @BindView(R.id.img_userimage)
    CircleImageView img_userimage;

    @BindView(R.id.tv_whatsnow)
    TextView tv_whatsnow;

    @BindView(R.id.relative_home)
    RelativeLayout relative_home;

    @BindView(R.id.relative_profile)
    RelativeLayout relative_profile;

    @BindView(R.id.relative_livechat)
    RelativeLayout relative_livechat;

    @BindView(R.id.relative_contactsus)
    RelativeLayout relative_contactsus;

    @BindView(R.id.relative_reservations)
    RelativeLayout relative_reservations;

    @BindView(R.id.relative_orders)
    RelativeLayout relative_orders;

    @BindView(R.id.relative_cart)
    RelativeLayout relative_cart;

    @BindView(R.id.relative_payment)
    RelativeLayout relative_payment;

    @BindView(R.id.relative_setting)
    RelativeLayout relative_setting;

    @BindView(R.id.view_home)
    View view_home;

    @BindView(R.id.view_profile)
    View view_profile;

    @BindView(R.id.view_livechat)
    View view_livechat;

    @BindView(R.id.view_contactsus)
    View view_contactsus;

    @BindView(R.id.view_reservations)
    View view_reservations;

    @BindView(R.id.view_orders)
    View view_orders;

    @BindView(R.id.view_cart)
    View view_cart;

    @BindView(R.id.tv_username)
    TextView tv_username;

    @BindView(R.id.tv_useremail)
    TextView tv_useremail;

    @BindView(R.id.home)
    TextView home;

    @BindView(R.id.profile)
    TextView profile;

    @BindView(R.id.livechat)
    TextView livechat;

    @BindView(R.id.contactsus)
    TextView contactsus;

    @BindView(R.id.reservations)
    TextView reservations;

    @BindView(R.id.orders)
    TextView orders;

    @BindView(R.id.cart)
    TextView cart;

    @BindView(R.id.payment)
    TextView payment;

    @BindView(R.id.setting)
    TextView setting;

    public static TextView tv_cityName;

    @BindView(R.id.view_payment)
    View view_payment;

    @BindView(R.id.view_setting)
    View view_setting;


    TabLayout tabLayout;
    ViewPager viewPager;

    boolean doubleBackToExitPressedOnce = false;

    SavePref savePref;
    Context context;

    String currentLatitude = "";
    String curretLongitude = "";

    double latitude;
    double longitude;

    IPMainActivity ipMainActivity;
    String countryNameCode = "";
    Dialog progressDialog;

    private PlaceAutocompleteFragment autocompleteFragment;
    private String TAG = MainActivity.class.getSimpleName();
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private GoogleApiClient mGoogleApiClient;
    private String current_CityName = "", current_city_Address = "", current_Latitude = "", current_Longitude = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Initialization();
        EventListner();
    }

    private void Initialization() {
        context = MainActivity.this;
        savePref = new SavePref(this);
        ipMainActivity = new PMainActivity(this);

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        view_home.setVisibility(View.VISIBLE);
        tv_cityName = (TextView) findViewById(R.id.tv_cityName);


        tv_whatsnow.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_cityName.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_username.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_useremail.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        home.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        home.setTextSize(18);

        String first_name = CommonMethods.upperCase(savePref.get_first_name());
        String last_name = CommonMethods.upperCase(savePref.get_last_name());

        currentLatitude = savePref.get_address_latitude();
        curretLongitude = savePref.get_address_logitude();

        if (!currentLatitude.equals("")) {
            latitude = Double.parseDouble(currentLatitude);
        }
        if (!curretLongitude.equals("")) {
            longitude = Double.parseDouble(curretLongitude);
        }

        getAddress(context, latitude, longitude); // method call for get address form user current latitude or longitude

        tv_username.setText(first_name + " " + last_name);

        tv_useremail.setText(savePref.get_email());

        if (savePref.get_profile_image() != null) {
            Glide.with(context).load(Constant.IMAGE_URL + savePref.get_profile_image())
                    .into(img_userimage);
        }

        ipMainActivity.getCity(savePref.get_id());


        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RestaurantsFragment(), "Restaurants");
        adapter.addFragment(new ServicesFragment(), "Services");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //autocompleteFragment.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.i(TAG, "Place:" + place.getAddress());
                Log.i(TAG, "Place:" + place.getLatLng());
                Log.i(TAG, "Place:" + place.getName());
                Log.i(TAG, "Place:" + place.toString());

                current_CityName = place.getAddress().toString();
                current_city_Address = place.getName().toString();
                current_Latitude = place.getLatLng().toString();
                current_Longitude = place.getLatLng().toString();


                if (current_CityName.isEmpty() && current_CityName == null) {
                    tv_cityName.setText(current_CityName);
                } else {
                    tv_cityName.setText(current_city_Address);
                }
                if (Utility.isNetworkConnectionAvailable(context)){
                    ipMainActivity.updateCity(savePref.get_id(),tv_cityName.getText().toString(),"","");
                }

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Log.i(TAG, status.getStatusMessage());
            } else if (requestCode == RESULT_CANCELED) {

            }
        }
    }

    @Override
    public void onGetCitySuccessResponseFromPresenter(GetCityResponseModel getCityResponseModel) {
        if (getCityResponseModel!=null && getCityResponseModel.getData().size()>0){
            tv_cityName.setText(getCityResponseModel.getData().get(0).getCurrentCity());
        }
    }

    @Override
    public void onGetCityFailedResponseFromPresenter(String message) {

    }

    @Override
    public void onUpdateCitySuccessResponseFromPresenter(CityUpdateResponseModel cityUpdateResponseModel) {

        if (cityUpdateResponseModel!=null && cityUpdateResponseModel.getData().size()>0){

        }
    }

    @Override
    public void onUpdateCityFailedResponseFromPresenter(String message) {

    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    private void EventListner() {

        relative_home.setOnClickListener(this);
        relative_profile.setOnClickListener(this);
        relative_livechat.setOnClickListener(this);
        relative_contactsus.setOnClickListener(this);
        relative_reservations.setOnClickListener(this);
        relative_orders.setOnClickListener(this);
        relative_cart.setOnClickListener(this);
        relative_payment.setOnClickListener(this);
        relative_setting.setOnClickListener(this);
        tv_whatsnow.setOnClickListener(this);
        menu.setOnClickListener(this);
        tv_cityName.setOnClickListener(this);

    }

    // getAddress method to get address from latitude,longitude
    public void getAddress(Context context, double currentLatitude, double curretLongitude) {

        //Set Address
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(currentLatitude, curretLongitude, 1);
            if (addresses != null && addresses.size() > 0) {


                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                countryNameCode = addresses.get(0).getCountryCode();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
                String curretCity = addresses.get(0).getSubAdminArea();
                String curretLocation = addresses.get(0).getSubLocality();

                if (city.equals("")) {

                } else {
                    tv_cityName.setText(city); // set city name
                }

                /*Log.d("getAddress+++++", address);
                Log.d("getAddress+++++", city);
                Log.d("getAddress+++++", state);
                Log.d("getAddress+++++", postalCode);
                Log.d("getAddress+++++", knownName);
                Log.d("getAddress+++++", country);
                Log.d("getAddress+++++", curretCity);

                Log.d("getAddress+++++", curretLocation);
                Log.d("getAddress+++++", countryNameCode);*/

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    @Override
    public void onClick(View v) {

        Intent intent = null;

        switch (v.getId()) {

            case R.id.menu:

                openCloseDrawer();

                break;

            case R.id.tv_cityName:

                callPlaceSearchIntent();

                // chooseStateDialog(); // method call for choose city from popup

                break;
            case R.id.tv_whatsnow:

                intent = new Intent(MainActivity.this, IndexableRecyclerViewActivity.class);
                startActivity(intent);


                break;
            case R.id.relative_home:

                view_home.setVisibility(View.VISIBLE);
                view_profile.setVisibility(View.INVISIBLE);
                view_livechat.setVisibility(View.INVISIBLE);
                view_contactsus.setVisibility(View.INVISIBLE);
                view_reservations.setVisibility(View.INVISIBLE);
                view_orders.setVisibility(View.INVISIBLE);
                view_cart.setVisibility(View.INVISIBLE);
                view_payment.setVisibility(View.INVISIBLE);
                view_setting.setVisibility(View.INVISIBLE);

                home.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
                profile.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                livechat.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                contactsus.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                reservations.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                orders.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                cart.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                payment.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                setting.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));

                home.setTextSize(18);
                profile.setTextSize(16);
                livechat.setTextSize(16);
                contactsus.setTextSize(16);
                reservations.setTextSize(16);
                orders.setTextSize(16);
                cart.setTextSize(16);
                payment.setTextSize(16);
                setting.setTextSize(16);

                intent = new Intent(MainActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                openCloseDrawer();


                break;

            case R.id.relative_profile:
                view_profile.setVisibility(View.VISIBLE);
                view_home.setVisibility(View.INVISIBLE);
                view_livechat.setVisibility(View.INVISIBLE);
                view_contactsus.setVisibility(View.INVISIBLE);
                view_reservations.setVisibility(View.INVISIBLE);
                view_orders.setVisibility(View.INVISIBLE);
                view_cart.setVisibility(View.INVISIBLE);
                view_payment.setVisibility(View.INVISIBLE);
                view_setting.setVisibility(View.INVISIBLE);

                profile.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
                home.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                livechat.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                contactsus.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                reservations.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                orders.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                cart.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                payment.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                setting.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));

                profile.setTextSize(18);
                home.setTextSize(16);
                livechat.setTextSize(16);
                contactsus.setTextSize(16);
                reservations.setTextSize(16);
                orders.setTextSize(16);
                cart.setTextSize(16);
                payment.setTextSize(16);
                setting.setTextSize(16);


                intent = new Intent(MainActivity.this, PersonalProfileActivity.class);
                startActivity(intent);

                //  openCloseDrawer();

                break;
            case R.id.relative_livechat:

                view_livechat.setVisibility(View.VISIBLE);
                view_home.setVisibility(View.INVISIBLE);
                view_profile.setVisibility(View.INVISIBLE);
                view_contactsus.setVisibility(View.INVISIBLE);
                view_reservations.setVisibility(View.INVISIBLE);
                view_orders.setVisibility(View.INVISIBLE);
                view_cart.setVisibility(View.INVISIBLE);
                view_payment.setVisibility(View.INVISIBLE);
                view_setting.setVisibility(View.INVISIBLE);

                livechat.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
                home.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                profile.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                contactsus.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                reservations.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                orders.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                cart.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                payment.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                setting.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));

                livechat.setTextSize(18);
                profile.setTextSize(16);
                home.setTextSize(16);
                contactsus.setTextSize(16);
                reservations.setTextSize(16);
                orders.setTextSize(16);
                cart.setTextSize(16);
                payment.setTextSize(16);
                setting.setTextSize(16);


                intent = new Intent(MainActivity.this, LiveChatActivity.class);
                startActivity(intent);

                //  openCloseDrawer();
                break;
            case R.id.relative_contactsus:
                view_contactsus.setVisibility(View.VISIBLE);
                view_home.setVisibility(View.INVISIBLE);
                view_profile.setVisibility(View.INVISIBLE);
                view_livechat.setVisibility(View.INVISIBLE);
                view_reservations.setVisibility(View.INVISIBLE);
                view_orders.setVisibility(View.INVISIBLE);
                view_cart.setVisibility(View.INVISIBLE);
                view_payment.setVisibility(View.INVISIBLE);
                view_setting.setVisibility(View.INVISIBLE);

                contactsus.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
                home.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                profile.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                livechat.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                reservations.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                orders.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                cart.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                payment.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                setting.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));

                contactsus.setTextSize(18);
                profile.setTextSize(16);
                home.setTextSize(16);
                livechat.setTextSize(16);
                reservations.setTextSize(16);
                orders.setTextSize(16);
                cart.setTextSize(16);
                payment.setTextSize(16);
                setting.setTextSize(16);


                intent = new Intent(MainActivity.this, ContactsUsActivity.class);
                startActivity(intent);


                break;
            case R.id.relative_reservations:

                view_reservations.setVisibility(View.VISIBLE);
                view_home.setVisibility(View.INVISIBLE);
                view_profile.setVisibility(View.INVISIBLE);
                view_livechat.setVisibility(View.INVISIBLE);
                view_contactsus.setVisibility(View.INVISIBLE);
                view_orders.setVisibility(View.INVISIBLE);
                view_cart.setVisibility(View.INVISIBLE);
                view_payment.setVisibility(View.INVISIBLE);
                view_setting.setVisibility(View.INVISIBLE);

                reservations.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
                home.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                profile.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                livechat.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                contactsus.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                orders.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                cart.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                payment.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                setting.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));

                reservations.setTextSize(18);
                profile.setTextSize(16);
                home.setTextSize(16);
                livechat.setTextSize(16);
                contactsus.setTextSize(16);
                orders.setTextSize(16);
                cart.setTextSize(16);
                payment.setTextSize(16);
                setting.setTextSize(16);


                intent = new Intent(MainActivity.this, ReservationsActivity.class);
                startActivity(intent);

                break;
            case R.id.relative_orders:
                view_orders.setVisibility(View.VISIBLE);
                view_home.setVisibility(View.INVISIBLE);
                view_profile.setVisibility(View.INVISIBLE);
                view_livechat.setVisibility(View.INVISIBLE);
                view_contactsus.setVisibility(View.INVISIBLE);
                view_reservations.setVisibility(View.INVISIBLE);
                view_cart.setVisibility(View.INVISIBLE);
                view_payment.setVisibility(View.INVISIBLE);
                view_setting.setVisibility(View.INVISIBLE);

                orders.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
                home.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                profile.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                livechat.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                contactsus.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                reservations.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                cart.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                payment.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                setting.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));

                orders.setTextSize(18);
                profile.setTextSize(16);
                home.setTextSize(16);
                livechat.setTextSize(16);
                contactsus.setTextSize(16);
                reservations.setTextSize(16);
                cart.setTextSize(16);
                payment.setTextSize(16);
                setting.setTextSize(16);


                intent = new Intent(MainActivity.this, OrderActivity.class);
                startActivity(intent);


                break;
            case R.id.relative_cart:

                view_cart.setVisibility(View.VISIBLE);
                view_home.setVisibility(View.INVISIBLE);
                view_profile.setVisibility(View.INVISIBLE);
                view_livechat.setVisibility(View.INVISIBLE);
                view_contactsus.setVisibility(View.INVISIBLE);
                view_reservations.setVisibility(View.INVISIBLE);
                view_orders.setVisibility(View.INVISIBLE);
                view_payment.setVisibility(View.INVISIBLE);
                view_setting.setVisibility(View.INVISIBLE);

                cart.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
                home.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                profile.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                livechat.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                contactsus.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                reservations.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                orders.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                payment.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                setting.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));

                cart.setTextSize(18);
                orders.setTextSize(16);
                profile.setTextSize(16);
                home.setTextSize(16);
                livechat.setTextSize(16);
                contactsus.setTextSize(16);
                reservations.setTextSize(16);
                payment.setTextSize(16);
                setting.setTextSize(16);


                intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);


                break;
            case R.id.relative_payment:
                view_payment.setVisibility(View.VISIBLE);
                view_home.setVisibility(View.INVISIBLE);
                view_profile.setVisibility(View.INVISIBLE);
                view_livechat.setVisibility(View.INVISIBLE);
                view_contactsus.setVisibility(View.INVISIBLE);
                view_reservations.setVisibility(View.INVISIBLE);
                view_orders.setVisibility(View.INVISIBLE);
                view_cart.setVisibility(View.INVISIBLE);
                view_setting.setVisibility(View.INVISIBLE);

                payment.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
                home.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                profile.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                livechat.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                contactsus.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                reservations.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                orders.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                cart.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                setting.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));


                payment.setTextSize(18);
                cart.setTextSize(16);
                orders.setTextSize(16);
                profile.setTextSize(16);
                home.setTextSize(16);
                livechat.setTextSize(16);
                contactsus.setTextSize(16);
                reservations.setTextSize(16);
                setting.setTextSize(16);


                intent = new Intent(MainActivity.this, PaymentHistoryActivity.class);
                startActivity(intent);


                break;
            case R.id.relative_setting:

                view_setting.setVisibility(View.VISIBLE);
                view_home.setVisibility(View.INVISIBLE);
                view_profile.setVisibility(View.INVISIBLE);
                view_livechat.setVisibility(View.INVISIBLE);
                view_contactsus.setVisibility(View.INVISIBLE);
                view_reservations.setVisibility(View.INVISIBLE);
                view_orders.setVisibility(View.INVISIBLE);
                view_cart.setVisibility(View.INVISIBLE);
                view_payment.setVisibility(View.INVISIBLE);

                setting.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
                home.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                profile.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                livechat.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                contactsus.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                reservations.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                orders.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                cart.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));
                payment.setTypeface(Utility.typeFaceForProximaNovaRegulerText(this));

                setting.setTextSize(18);
                payment.setTextSize(16);
                cart.setTextSize(16);
                orders.setTextSize(16);
                profile.setTextSize(16);
                home.setTextSize(16);
                livechat.setTextSize(16);
                contactsus.setTextSize(16);
                reservations.setTextSize(16);

                intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);

                break;

        }

    }

    private void callPlaceSearchIntent() {
        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }
    }

    // Close or open drawer menu
    private void openCloseDrawer() {

        if (drawer_layout.isDrawerOpen(Gravity.LEFT)) {
            drawer_layout.closeDrawer(Gravity.LEFT);

        } else {
            drawer_layout.openDrawer(Gravity.LEFT);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);

    }


}
