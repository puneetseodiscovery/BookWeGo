package com.bookwego.reservationDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bookwego.BuildConfig;
import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.restaurantsDetailsActivity.adapters.SpecialConditionsAdapter;
import com.bookwego.utills.SingletonClass;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReservationDetailsActivity extends BaseClass {

    @BindView(R.id.recycler_specialConditions)
    RecyclerView recycler_specialConditions;

    @BindView(R.id.recycler_termconditionds)
    RecyclerView recycler_termconditionds;

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.tv_share)
    TextView tv_share;

    @BindView(R.id.tv_reservation)
    TextView tv_reservation;

    @BindView(R.id.tv_datepiker)
    TextView tv_datepiker;

    @BindView(R.id.resturant)
    TextView resturant;

    @BindView(R.id.tv_resturantsName)
    TextView tv_resturantsName;

    @BindView(R.id.resturantno)
    TextView resturantno;

    @BindView(R.id.tv_resturantsno)
    TextView tv_resturantsno;

    @BindView(R.id.date)
    TextView date;

    @BindView(R.id.tv_date)
    TextView tv_date;

    @BindView(R.id.timediscount)
    TextView timediscount;

    @BindView(R.id.tv_timediscount)
    TextView tv_timediscount;

    @BindView(R.id.pax)
    TextView pax;

    @BindView(R.id.tv_noofpax)
    TextView tv_noofpax;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.tv_username)
    TextView tv_username;

    @BindView(R.id.email)
    TextView email;

    @BindView(R.id.tv_useremail)
    TextView tv_useremail;

    @BindView(R.id.btn_editReservation)
    Button btn_editReservation;

    @BindView(R.id.btn_cancelReservation)
    Button btn_cancelReservation;

    @BindView(R.id.tv_policy)
    TextView tv_policy;

    @BindView(R.id.tv_termconditions)
    TextView tv_termconditions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_details);
        ButterKnife.bind(this);

        tv_reservation.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_datepiker.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        resturant.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_resturantsName.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        resturantno.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_resturantsno.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        date.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_date.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        timediscount.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_timediscount.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        pax.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        name.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_username.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        email.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_useremail.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        btn_editReservation.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        btn_cancelReservation.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_policy.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_termconditions.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        tv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage = "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                    //e.toString();
                }
            }
        });

        SpecialConditionsAdapter specialConditionsAdapter = new SpecialConditionsAdapter(this);
        recycler_specialConditions.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recycler_specialConditions.setAdapter(specialConditionsAdapter);

        SpecialConditionsAdapter specialConditionsAdapter2 = new SpecialConditionsAdapter(this);
        recycler_termconditionds.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recycler_termconditionds.setAdapter(specialConditionsAdapter2);
    }


}
