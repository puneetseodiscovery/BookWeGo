package com.bookwego.mainActivity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.cartActivity.CartActivity;
import com.bookwego.contactsusActivity.ContactsUsActivity;
import com.bookwego.livechatActivity.LiveChatActivity;
import com.bookwego.mainActivity.fragments.RestaurantsFragment;
import com.bookwego.mainActivity.fragments.ServicesFragment;
import com.bookwego.orderActivity.OrderActivity;
import com.bookwego.paymentHistoryActivity.PaymentHistoryActivity;
import com.bookwego.profileActivity.PersonalProfileActivity;
import com.bookwego.reservationsActivity.ReservationsActivity;
import com.bookwego.settingActivity.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseClass implements View.OnClickListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;

    @BindView(R.id.menu)
    ImageView menu;

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

    @BindView(R.id.view_payment)
    View view_payment;

    @BindView(R.id.view_setting)
    View view_setting;


    TabLayout tabLayout;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        Initialization();
        EventListner();
    }

    private void Initialization() {

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        view_home.setVisibility(View.VISIBLE);


    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RestaurantsFragment(), "Restaurants");
        adapter.addFragment(new ServicesFragment(), "Services");
        viewPager.setAdapter(adapter);
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
        menu.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        Intent intent = null;

        switch (v.getId()) {

            case R.id.menu:

                openCloseDrawer();

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

                intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);


                break;

        }

    }

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
        view_home.setVisibility(View.VISIBLE);

    }
}
