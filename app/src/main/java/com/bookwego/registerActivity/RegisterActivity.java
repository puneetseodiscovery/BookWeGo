package com.bookwego.registerActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bookwego.R;
import com.bookwego.baseClass.BaseClass;
import com.bookwego.utills.CommonMethods;
import com.bookwego.utills.Utility;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.ybs.countrypicker.CountryPicker;
import com.ybs.countrypicker.CountryPickerListener;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseClass implements View.OnClickListener {

    @BindView(R.id.btn_next)
    Button btn_next;

    @BindView(R.id.edit_firstName)
    EditText edit_firstName;

    @BindView(R.id.edit_lastName)
    EditText edit_lastName;

    @BindView(R.id.edit_email)
    EditText edit_email;

    @BindView(R.id.edit_phone)
    EditText edit_phone;

    @BindView(R.id.edit_ageorbithdate)
    EditText edit_ageorbithdate;

    @BindView(R.id.edit_gender)
    EditText edit_gender;

    @BindView(R.id.edit_address)
    EditText edit_address;

    @BindView(R.id.edit_citizinship)
    EditText edit_citizinship;

    @BindView(R.id.edit_countryCode)
    EditText edit_countryCode;

    Context context;
    CountryPicker countryPicker;
    CountryPicker codePicker;
    private final int PLACE_PICKER_REQUEST = 1;
    String select_latitude = "", select_longitude = "", date_of_birth = "";
    String dailCode;
    private int mYear, mMonth, mDay, mHour, mMinute;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        ButterKnife.bind(this);
        context = RegisterActivity.this;
        Initialization();
        EventListner();
    }


    private void Initialization() {

        countryPicker = CountryPicker.newInstance("Select Country");  // dialog title
        codePicker = CountryPicker.newInstance("Select Country");  // dialog title
        btn_next.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_firstName.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_lastName.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_email.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_phone.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_ageorbithdate.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_gender.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_address.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_citizinship.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_countryCode.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));

    }

    private void EventListner() {

        btn_next.setOnClickListener(this);
        edit_ageorbithdate.setOnClickListener(this);
        edit_gender.setOnClickListener(this);
        edit_address.setOnClickListener(this);
        edit_citizinship.setOnClickListener(this);
        edit_countryCode.setOnClickListener(this);

        countryPicker.setListener(new CountryPickerListener() {
            @Override
            public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {

                edit_citizinship.setText(name);
                countryPicker.dismiss();
            }
        });

        codePicker.setListener(new CountryPickerListener() {
            @Override
            public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {

                dailCode = dialCode;
                edit_countryCode.setText(dailCode);
                codePicker.dismiss();

            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_next:
                valiationOnRegister(); // method call on btn_next click
                break;
            case R.id.edit_ageorbithdate:
                OpenDatePickerDialog();
                break;
            case R.id.edit_gender:
                GenderDialog();
                break;
            case R.id.edit_citizinship:
                openCountryPicker();
                break;
            case R.id.edit_address:

                locationPicker();
                break;
            case R.id.edit_countryCode:

                openCountryCodePicker();
                break;
        }

    }

    private void locationPicker() {

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {

            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(context, data);
                Log.e("Tag", "Place: "
                        + place.getAddress()
                        + place.getPhoneNumber()
                        + place.getLatLng().latitude);
                select_latitude = String.valueOf(place.getLatLng().latitude);
                select_longitude = String.valueOf(place.getLatLng().longitude);

                edit_address.setText(place.getAddress().toString());


            }
        } else if (requestCode == 2) {
            if (data != null) {
            }
        }
    }


    public void openCountryPicker() {

        countryPicker.show(getSupportFragmentManager(), "COUNTRY_PICKER");

    }

    public void openCountryCodePicker() {

        codePicker.show(getSupportFragmentManager(), "COUNTRY_PICKER");

    }

    private void GenderDialog() {

        dialog = new Dialog(context, R.style.DialogThemes);
        dialog.setContentView(R.layout.dialog_gender);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);

        TextView tv_cancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        TextView tv_male = (TextView) dialog.findViewById(R.id.tv_male);
        TextView tv_female = (TextView) dialog.findViewById(R.id.tv_female);

        tv_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_gender.setText(getString(R.string.g_male));
                dialog.dismiss();
            }
        });

        tv_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_gender.setText(getString(R.string.g_female));
                dialog.dismiss();
            }
        });

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
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
                        date_of_birth = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        edit_ageorbithdate.setText(date_of_birth);
                        //  getAge(year, monthOfYear, dayOfMonth);


                    }
                }, mYear, mMonth, mDay);

        //datePickerDialog.setTitle("Please pick your date of birth");
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

        datePickerDialog.show();


    }


    private String getAge(int year, int month, int day) {

        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        Integer ageInt = new Integer(age);
        String my_age = ageInt.toString();
        edit_ageorbithdate.setText(my_age + " " + "Years");

        return my_age;
    }

    private void valiationOnRegister() {

        if (edit_firstName.getText().toString().trim().isEmpty()) {
            String edit_firstName = getString(R.string.string_first_name);
            Utility.ShowToast(context, edit_firstName);
        } else if (edit_firstName.length() < 3 || edit_firstName.length() > 15) {
            String fisrstnameLength = getString(R.string.username_length);
            Utility.ShowToast(context, fisrstnameLength);

        } else if (edit_lastName.getText().toString().trim().isEmpty()) {
            String edit_lastName = getString(R.string.string_last_name);
            Utility.ShowToast(context, edit_lastName);
        } else if (edit_lastName.length() < 3 || edit_lastName.length() > 15) {
            String lastnameLength = getString(R.string.lastname_length);
            Utility.ShowToast(context, lastnameLength);

        } else if (edit_email.getText().toString().trim().isEmpty()) {
            String edit_email = getString(R.string.string_email);
            Utility.ShowToast(context, edit_email);
        } else if (!CommonMethods.isValidEmail(edit_email.getText().toString())) {
            String edit_email = getString(R.string.string_validemail);
            Utility.ShowToast(context, edit_email);
        } else if (edit_countryCode.getText().toString().isEmpty()) {
            String edit_phone = getString(R.string.country_code);
            Utility.ShowToast(context, edit_phone);
        } else if (edit_phone.getText().toString().trim().isEmpty()) {
            String edit_phone = getString(R.string.string_phone_number);
            Utility.ShowToast(context, edit_phone);
        } else if (!CommonMethods.isValidMobile(edit_phone.getText().toString())) {
            String vaild_phone = getString(R.string.enter_valid_phone_number);
            Utility.ShowToast(context, vaild_phone);
        } else if (edit_ageorbithdate.getText().toString().isEmpty()) {
            String edit_ageorbithdate = getString(R.string.string_dateofbirth);
            Utility.ShowToast(context, edit_ageorbithdate);
        } else if (edit_gender.getText().toString().isEmpty()) {
            String edit_gender = getString(R.string.string_gender);
            Utility.ShowToast(context, edit_gender);
        } else if (edit_address.getText().toString().isEmpty()) {
            String edit_address = getString(R.string.string_address);
            Utility.ShowToast(context, edit_address);
        } else if (edit_citizinship.getText().toString().isEmpty()) {
            String edit_citizinship = getString(R.string.string_citizinship);
            Utility.ShowToast(context, edit_citizinship);
        } else {

            /*Passing intent to new activity  RegisterNextActivity
             * send the RegisterActivity data to RegisterNextActivity with intent*/
            Intent intent = new Intent(RegisterActivity.this, RegisterNextActivity.class);
            intent.putExtra("firstName", edit_firstName.getText().toString().trim());
            intent.putExtra("lastName", edit_lastName.getText().toString().trim());
            intent.putExtra("email", edit_email.getText().toString().trim());
            intent.putExtra("phone", dailCode + edit_phone.getText().toString().trim());
            intent.putExtra("ageorbithdate", edit_ageorbithdate.getText().toString().trim());
            intent.putExtra("date_of_birth", date_of_birth.toString().trim());
            intent.putExtra("gender", edit_gender.getText().toString().trim());
            intent.putExtra("address", edit_address.getText().toString().trim());
            intent.putExtra("citizinship", edit_citizinship.getText().toString().trim());
            startActivity(intent);
            overridePendingTransition(0, R.anim.splash_faidout);
        }
    }
}
