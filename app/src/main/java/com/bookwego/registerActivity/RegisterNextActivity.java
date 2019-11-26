package com.bookwego.registerActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.loginActivity.LogInActivity;
import com.bookwego.otpActivity.OtpActivity;
import com.bookwego.privacypolicy.PrivacyPolicyActivity;
import com.bookwego.registerActivity.adapters.EmploymentAdapter;
import com.bookwego.registerActivity.adapters.FoodAdapter;
import com.bookwego.registerActivity.adapters.IncomeLevelAdapter;
import com.bookwego.registerActivity.adapters.ServicesAdapter;
import com.bookwego.registerActivity.interfaces.IPRegisterNextActivity;
import com.bookwego.registerActivity.interfaces.IRegisterNextActivity;
import com.bookwego.registerActivity.registerResponseModel.Employment_Model;
import com.bookwego.registerActivity.registerResponseModel.FoodModel;
import com.bookwego.registerActivity.registerResponseModel.IncomeModel;
import com.bookwego.registerActivity.registerResponseModel.ServiceModel;
import com.bookwego.registerActivity.presenter.PRegisterNextActivity;
import com.bookwego.registerActivity.registerResponseModel.RegisterationResponseModel;
import com.bookwego.termandconditionActivity.TermConditionActivity;
import com.bookwego.utills.CommonMethods;

import com.bookwego.utills.SavePref;
import com.bookwego.utills.Utility;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterNextActivity extends BaseClass implements IRegisterNextActivity, View.OnClickListener {
    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.btn_register)
    Button btn_register;

    @BindView(R.id.tv_login)
    TextView tv_login;

    @BindView(R.id.haveanaccount)
    TextView haveanaccount;

    @BindView(R.id.edit_datejoin)
    EditText edit_datejoin;

    @BindView(R.id.edit_password)
    EditText edit_password;

    @BindView(R.id.edit_confirm_password)
    EditText edit_confirm_password;

    @BindView(R.id.tv_term_condition)
    TextView tv_term_condition;

    @BindView(R.id.tv_privacy_policy)
    TextView tv_privacy_policy;

    EmploymentAdapter employmentAdapter;
    IncomeLevelAdapter incomeLevelAdapter;
    FoodAdapter foodAdapter;
    ServicesAdapter servicesAdapter;

    Context context;
    public static EditText edit_employment;
    public static EditText edit_incomLevel;
    public static EditText edit_likebest;
    public static EditText edit_services;
    public static Dialog dialog;
    private int mYear, mMonth, mDay, mHour, mMinute;
    IPRegisterNextActivity ipRegisterNextActivity;
    String firstName = "", lastName = "", email = "", phone = "", ageorbithdate = "", date_of_birth = "", gender = "", address = "", citizinship = "";
    String joining_Date = "",user_id="",user_phone="",user_email="";
    Dialog progressDialog;
    SavePref savePref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_next);

        ButterKnife.bind(this);
        context = RegisterNextActivity.this;
        ipRegisterNextActivity = new PRegisterNextActivity(this);
        savePref=new SavePref(this);
        Initialization();
        EventListner();
    }

    private void Initialization() {

        firstName = getIntent().getStringExtra("firstName");
        lastName = getIntent().getStringExtra("lastName");
        email = getIntent().getStringExtra("email");
        phone = getIntent().getStringExtra("phone");
        ageorbithdate = getIntent().getStringExtra("ageorbithdate");
        date_of_birth = getIntent().getStringExtra("date_of_birth");
        gender = getIntent().getStringExtra("gender");
        address = getIntent().getStringExtra("address");
        citizinship = getIntent().getStringExtra("citizinship");

       /* String timestamp= String.valueOf(CommonMethods.calender_date_to_timestamp(date_of_birth));
        dob_time_Stamp=timestamp;*/

        edit_employment = (EditText) findViewById(R.id.edit_employment);
        edit_incomLevel = (EditText) findViewById(R.id.edit_incomLevel);
        edit_likebest = (EditText) findViewById(R.id.edit_likebest);
        edit_services = (EditText) findViewById(R.id.edit_services);

        edit_employment.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_incomLevel.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_datejoin.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_likebest.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_services.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_password.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_confirm_password.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_term_condition.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_privacy_policy.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        tv_login.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        btn_register.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        haveanaccount.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));

       /* String datejoin= String.valueOf(CommonMethods.calender_date_to_timestamp(String.valueOf(tv_datejoin)));
        datejoining_time_Stamp=datejoin;*/

    }

    private void EventListner() {

        img_back.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        tv_login.setOnClickListener(this);
        tv_privacy_policy.setOnClickListener(this);
        tv_term_condition.setOnClickListener(this);
        edit_employment.setOnClickListener(this);
        edit_incomLevel.setOnClickListener(this);
        edit_likebest.setOnClickListener(this);
        edit_services.setOnClickListener(this);
        edit_datejoin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent intent = null;

        switch (v.getId()) {
            case R.id.img_back:
                Animation animation01 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_click);
                img_back.startAnimation(animation01);
                finish();
                overridePendingTransition(R.anim.main_faidin, R.anim.main_faidout);
                break;
            case R.id.btn_register:

                validationOnRegister();

                break;
            case R.id.tv_login:

                intent = new Intent(RegisterNextActivity.this, LogInActivity.class);
                startActivity(intent);
                overridePendingTransition(0, R.anim.splash_faidout);

                break;

            case R.id.tv_privacy_policy:
                intent = new Intent(RegisterNextActivity.this, PrivacyPolicyActivity.class);
                startActivity(intent);
                overridePendingTransition(0, R.anim.splash_faidout);

                break;


            case R.id.tv_term_condition:
                intent = new Intent(RegisterNextActivity.this, TermConditionActivity.class);
                startActivity(intent);
                overridePendingTransition(0, R.anim.splash_faidout);

                break;
            case R.id.edit_employment:
                employmnetDialog();

                break;
            case R.id.edit_incomLevel:
                incomeLevelDialog();

                break;

            case R.id.edit_datejoin:

                OpenDatePickerDialog();
                break;
            case R.id.edit_likebest:

                OpenfoodlikeDialog();
                break;
            case R.id.edit_services:

                OpenserviceDialog();
                break;
        }

    }

    private void validationOnRegister() {

        if (Utility.isNetworkConnectionAvailable(context)){

            if (edit_employment.getText().toString().isEmpty()) {
                String employment = getString(R.string.r_select_employment);
                Utility.ShowToast(context, employment);

            } else if (edit_incomLevel.getText().toString().isEmpty()) {
                String Income = getString(R.string.r_income);
                Utility.ShowToast(context, Income);

            } else if (edit_datejoin.getText().toString().isEmpty()) {
                String Date_Joined = getString(R.string.r_date_of_join);
                Utility.ShowToast(context, Date_Joined);


            } else if (edit_likebest.getText().toString().isEmpty()) {
                String food = getString(R.string.r_food_like_best);
                Utility.ShowToast(context, food);

            } else if (edit_services.getText().toString().isEmpty()) {
                String Service = getString(R.string.r_service_like);
                Utility.ShowToast(context, Service);

            } else if (edit_password.getText().toString().trim().isEmpty()) {
                String password = getString(R.string.r_password_enter);
                Utility.ShowToast(context, password);

            } else if (edit_password.length() < 6 || edit_password.length() > 16) {
                String passwordlength = getString(R.string.password_length);
                Utility.ShowToast(context, passwordlength);

            } else if (!CommonMethods.isValidPassword(edit_password.getText().toString().trim())) {
                String hint_password = getString(R.string.password_pattern_hint);
                Utility.ShowToast(context, hint_password);

            } else if (edit_confirm_password.getText().toString().trim().isEmpty()) {

                String confirm_password = getString(R.string.r_confirm_password);
                Utility.ShowToast(context, confirm_password);

            } else if (!edit_confirm_password.getText().toString().trim().matches(edit_password.getText().toString().trim())) {
                String not_match = getString(R.string.r_password_not_match);
                Utility.ShowToast(context, not_match);

            } else {

                progressDialog = Utility.ShowDialog(this);
                /*api call for user regisetration */
                ipRegisterNextActivity.doRegistration(firstName, lastName, email, phone, date_of_birth, gender, address, citizinship,
                        edit_employment.getText().toString().trim(),
                        edit_incomLevel.getText().toString().trim(),
                        edit_datejoin.getText().toString().trim(),
                        edit_likebest.getText().toString().trim(),
                        edit_services.getText().toString().trim(),
                        edit_confirm_password.getText().toString().trim());

            }

        }
    }


    private void OpenDatePickerDialog() {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        joining_Date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        edit_datejoin.setText(joining_Date);

                    }
                }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

        datePickerDialog.show();


    }


    private void employmnetDialog() {

        dialog = new Dialog(context, R.style.Theme_Dialog);
        dialog.setContentView(R.layout.dialog_employment);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        RecyclerView employment_recyclerview = (RecyclerView) dialog.findViewById(R.id.employment_recyclerview);
        ImageView btnCancel = (ImageView) dialog.findViewById(R.id.btnCancel);
        Employment_Model[] empModels = new Employment_Model[]{
                new Employment_Model("Goverment Employee"),
                new Employment_Model("Private Job"),
                new Employment_Model("Business Man"),
                new Employment_Model("Own Business"),
                new Employment_Model("Under Contract"),


        };

        employmentAdapter = new EmploymentAdapter(this, empModels, RegisterNextActivity.this);
        employment_recyclerview.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        employment_recyclerview.setHasFixedSize(true);
        employment_recyclerview.setAdapter(employmentAdapter);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    private void incomeLevelDialog() {

        dialog = new Dialog(context, R.style.Theme_Dialog);
        dialog.setContentView(R.layout.dialog_income);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        RecyclerView income_recyclerview = (RecyclerView) dialog.findViewById(R.id.income_recyclerview);
        ImageView btnCancel = (ImageView) dialog.findViewById(R.id.btnCancel);
        IncomeModel[] incomeModels = new IncomeModel[]{
                new IncomeModel("8000-20000"),
                new IncomeModel("21000-40000"),
                new IncomeModel("41000-60000"),
                new IncomeModel("61000-80000"),
                new IncomeModel("81000-100000 Above"),


        };

        incomeLevelAdapter = new IncomeLevelAdapter(this, incomeModels, RegisterNextActivity.this);
        income_recyclerview.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        income_recyclerview.setHasFixedSize(true);
        income_recyclerview.setAdapter(incomeLevelAdapter);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void OpenfoodlikeDialog() {

        dialog = new Dialog(context, R.style.Theme_Dialog);
        dialog.setContentView(R.layout.dialog_foodlikebest);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        RecyclerView food_recyclerview = (RecyclerView) dialog.findViewById(R.id.food_recyclerview);
        ImageView btnCancel = (ImageView) dialog.findViewById(R.id.btnCancel);
        FoodModel[] foodModels = new FoodModel[]{
                new FoodModel("Vegetarian food"),
                new FoodModel("Non-Vegetarian food"),


        };

        foodAdapter = new FoodAdapter(this, foodModels, RegisterNextActivity.this);
        food_recyclerview.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        food_recyclerview.setHasFixedSize(true);
        food_recyclerview.setAdapter(foodAdapter);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void OpenserviceDialog() {

        dialog = new Dialog(context, R.style.Theme_Dialog);
        dialog.setContentView(R.layout.dialog_servicelikebest);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        RecyclerView service_recyclerview = (RecyclerView) dialog.findViewById(R.id.service_recyclerview);
        ImageView btnCancel = (ImageView) dialog.findViewById(R.id.btnCancel);
        ServiceModel[] serviceModels = new ServiceModel[]{
                new ServiceModel("Being Prompt and Attentive"),
                new ServiceModel("Service with a Smile"),
                new ServiceModel("Being Available"),
                new ServiceModel("Being Exceptional"),


        };

        servicesAdapter = new ServicesAdapter(this, serviceModels, RegisterNextActivity.this);
        service_recyclerview.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        service_recyclerview.setHasFixedSize(true);
        service_recyclerview.setAdapter(servicesAdapter);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }


    @Override
    public void registerSuccessfulResponseFromPresenter(RegisterationResponseModel registerationResponseModel) {

        progressDialog.dismiss();
        savePref.set_otp_status(String.valueOf(registerationResponseModel.getData().getOtpStatus()));
        user_id= String.valueOf(registerationResponseModel.getData().getId());
        user_phone=registerationResponseModel.getData().getPhone();
        user_email=registerationResponseModel.getData().getEmail();

        Intent intent = new Intent(RegisterNextActivity.this, OtpActivity.class);
        intent.putExtra("user_id",user_id);
        intent.putExtra("user_phone",user_phone);
        intent.putExtra("user_email",user_email);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(0, R.anim.splash_faidout);
        Utility.ShowSuccessToast(context, registerationResponseModel.getMessage());


    }

    @Override
    public void registerFaildResponseFromPresenter(String message) {
        progressDialog.dismiss();
        String error_message = message;
        Utility.ShowToast(context, error_message);

    }

    @Override
    public void onRegistrationInvalidRequestFromPresenter(String message) {
        progressDialog.dismiss();
        String error_message = message;
        Utility.ShowToast(context, error_message);
    }
}
