package com.bookwego.cartActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bookwego.R;
import com.bookwego.applyCouponActivity.ApplyCouponActivity;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.cardListActivity.CardListActivity;
import com.bookwego.cartActivity.adapter.CartDiscountAdapter;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends BaseClass implements View.OnClickListener {

    @BindView(R.id.recycler_discounts)
    RecyclerView recycler_discounts;


    @BindView(R.id.tv_orderName)
    TextView tv_orderName;

    @BindView(R.id.btn_proceed_to_pay)
    Button btn_proceed_to_pay;

    @BindView(R.id.img_back)
    ImageView img_back;

    CartDiscountAdapter cartDiscountAdapter;

    @BindView(R.id.tv_havecopun)
    TextView tv_havecopun;

    @BindView(R.id.tv_bookcollection)
    TextView tv_bookcollection;

    @BindView(R.id.tv_ordertype)
    TextView tv_ordertype;

    @BindView(R.id.tv_SelfCollection)
    TextView tv_SelfCollection;

    @BindView(R.id.tv_address)
    TextView tv_address;

    @BindView(R.id.tv_myaddress)
    TextView tv_myaddress;

    @BindView(R.id.tv_topay)
    TextView tv_topay;

    @BindView(R.id.tv_deleveryfee)
    TextView tv_deleveryfee;

    @BindView(R.id.tv_totalrupees)
    TextView tv_totalrupees;

    @BindView(R.id.tv_itemtotal)
    TextView tv_itemtotal;

    @BindView(R.id.tv_morecopun)
    TextView tv_morecopun;

    @BindView(R.id.tv_restbill)
    TextView tv_restbill;

    @BindView(R.id.tv_change_address)
    TextView tv_change_address;


    @BindView(R.id.tv_tellrestruent)
    TextView tv_tellrestruent;

    @BindView(R.id.tv_total_pay)
    TextView tv_total_pay;

    @BindView(R.id.tv_total_deleveryfee)
    TextView tv_total_deleveryfee;

    @BindView(R.id.tv_order_takeaway)
    TextView tv_order_takeaway;


    @BindView(R.id.tv_order_price)
    TextView tv_order_price;

    @BindView(R.id.tv_ovegprice)
    TextView tv_ovegprice;

    @BindView(R.id.tv_thisresturant)
    TextView tv_thisresturant;


    @BindView(R.id.img_nonveg_min)
    ImageView img_nonveg_min;

    @BindView(R.id.img_nonveg_max)
    ImageView img_nonveg_max;

    @BindView(R.id.tv_nonvegCount)
    TextView tv_nonvegCount;

    @BindView(R.id.tv_vegCount)
    TextView tv_vegCount;

    @BindView(R.id.tv_totalrating)
    TextView tv_totalrating;

    @BindView(R.id.tv_orderCount)
    TextView tv_orderCount;

    @BindView(R.id.tv_nonveg)
    TextView tv_nonveg;

    @BindView(R.id.tv_veg)
    TextView tv_veg;


    @BindView(R.id.img_vegmin)
    ImageView img_vegmin;

    @BindView(R.id.img_vegmax)
    ImageView img_vegmax;

    @BindView(R.id.img_orderMin)
    ImageView img_orderMin;

    @BindView(R.id.img_orderMax)
    ImageView img_orderMax;

    int i = 0;
    String _stringVal;

    int j = 0;
    String _vegVal;


    int k = 0;
    String _orderVal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);

        Initialization();
        EventListner();
    }


    private void Initialization() {

        tv_orderName.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_havecopun.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_bookcollection.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_ordertype.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_nonveg.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_veg.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_totalrating.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_SelfCollection.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_address.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_myaddress.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_topay.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_deleveryfee.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_totalrupees.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_itemtotal.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_restbill.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_change_address.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_tellrestruent.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_total_pay.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_thisresturant.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_order_takeaway.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));

        tv_total_pay.setText("\u20B9 280.00");
        tv_total_deleveryfee.setText("\u20B9 20.00");
        tv_totalrupees.setText("\u20B9 260.00");
        tv_ovegprice.setText("\u20B9 100");
        tv_order_price.setText("\u20B9 250");

        cartDiscountAdapter = new CartDiscountAdapter(this);
        recycler_discounts.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        recycler_discounts.setAdapter(cartDiscountAdapter);


    }

    private void EventListner() {

        img_back.setOnClickListener(this);
        img_nonveg_min.setOnClickListener(this);
        img_nonveg_max.setOnClickListener(this);
        img_vegmin.setOnClickListener(this);
        img_vegmax.setOnClickListener(this);
        img_orderMin.setOnClickListener(this);
        img_orderMax.setOnClickListener(this);
        tv_morecopun.setOnClickListener(this);
        btn_proceed_to_pay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_nonveg_min:

                Log.d("src", "Decreasing value...");
                if (i > 0) {
                    i = i - 1;
                    _stringVal = String.valueOf(i);
                    tv_nonvegCount.setText(_stringVal);
                } else {
                    Log.d("src", "Value can't be less than 0");
                    Toast.makeText(this, "Value can't be less than 0", Toast.LENGTH_SHORT).show();
                    Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(50);
                }

                break;
            case R.id.img_nonveg_max:

                Log.d("src", "Increasing value...");
                i = i + 1;
                _stringVal = String.valueOf(i);
                tv_nonvegCount.setText(_stringVal);

                break;

            case R.id.img_vegmin:

                Log.d("src", "Decreasing value...");
                if (j > 0) {
                    j = j - 1;
                    _vegVal = String.valueOf(j);
                    tv_vegCount.setText(_vegVal);
                } else {
                    Log.d("src", "Value can't be less than 0");
                    Toast.makeText(this, "Value can't be less than 0", Toast.LENGTH_SHORT).show();
                    Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(50);
                }

                break;

            case R.id.img_vegmax:

                Log.d("src", "Increasing value...");
                j = j + 1;
                _vegVal = String.valueOf(j);
                tv_vegCount.setText(_vegVal);

                break;

            case R.id.img_orderMin:

                Log.d("src", "Decreasing value...");
                if (k > 0) {
                    k = k - 1;
                    _orderVal = String.valueOf(k);
                    tv_orderCount.setText(_orderVal);
                } else {
                    Log.d("src", "Value can't be less than 0");
                    Toast.makeText(this, "Value can't be less than 0", Toast.LENGTH_SHORT).show();
                    Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(50);
                }

                break;

            case R.id.img_orderMax:

                Log.d("src", "Increasing value...");
                k = k + 1;
                _orderVal = String.valueOf(k);
                tv_orderCount.setText(_orderVal);

                break;

            case R.id.tv_morecopun:

                Intent intent = new Intent(CartActivity.this, ApplyCouponActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_proceed_to_pay:

                Intent intentpay = new Intent(CartActivity.this, CardListActivity.class);
                startActivity(intentpay);
                break;
        }

    }
}
