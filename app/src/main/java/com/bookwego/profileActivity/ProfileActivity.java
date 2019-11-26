package com.bookwego.profileActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
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
import com.bookwego.mainActivity.MainActivity;
import com.bookwego.profileActivity.adapters.EmploymentsAdapter;
import com.bookwego.profileActivity.adapters.IncomeLevelAdapters;
import com.bookwego.profileActivity.interfacesProfile.IPProfileActivity;
import com.bookwego.profileActivity.interfacesProfile.IProfileActivity;
import com.bookwego.profileActivity.presenter.PProfileActivity;
import com.bookwego.profileActivity.responseModels.EditUserProfileReponseModel;
import com.bookwego.registerActivity.RegisterNextActivity;
import com.bookwego.registerActivity.adapters.EmploymentAdapter;
import com.bookwego.registerActivity.adapters.IncomeLevelAdapter;
import com.bookwego.registerActivity.registerResponseModel.Employment_Model;
import com.bookwego.registerActivity.registerResponseModel.IncomeModel;
import com.bookwego.utills.SavePref;
import com.bookwego.utills.Utility;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.ybs.countrypicker.CountryPicker;
import com.ybs.countrypicker.CountryPickerListener;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.bookwego.profileActivity.PersonalProfileActivity.personalProfileActivity;

public class ProfileActivity extends BaseClass implements IProfileActivity, View.OnClickListener {

    @BindView(R.id.edit_gender)
    EditText edit_gender;

    @BindView(R.id.edit_address)
    EditText edit_address;

    @BindView(R.id.edit_citizinship)
    EditText edit_citizinship;


    public static EditText edit_employment;
    public static EditText edit_incomLevel;

    @BindView(R.id.edit_datejoin)
    EditText edit_datejoin;

    @BindView(R.id.btn_save)
    Button btn_save;

    @BindView(R.id.img_back)
    ImageView img_back;
    Context context;

    boolean isActive = true;
    String gender = "", address = "", citizenship = "", employment = "", incomelevel = "", dateofjoin = "", select_latitude = "", select_longitude = "";
    public static Dialog dialog;
    CountryPicker countryPicker;
    private final int PLACE_PICKER_REQUEST = 1;
    String dailCode;
    EmploymentsAdapter employmentAdapter;
    IncomeLevelAdapters incomeLevelAdapter;
    private int mYear, mMonth, mDay, mHour, mMinute;
    String dateofjoinig = "";
    IPProfileActivity ipProfileActivity;
    String firstName = "", lastName = "", email = "", phone = "", ageorbithdate = "";
    RequestBody img_ReqBody;
    MultipartBody.Part imageMultipart;
    Dialog progressDialog;
    SavePref savePref;
    String image_path = "";
    Bitmap bitmap;
    Uri image_Uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);
        context = ProfileActivity.this;
        savePref = new SavePref(this);
        ipProfileActivity = new PProfileActivity(this);
        Initialization();
        EventListner();
    }


    private void Initialization() {

        Bundle extras = getIntent().getExtras();
        image_Uri = Uri.parse(extras.getString("image_Uri"));
        image_path = getIntent().getStringExtra("image_path");
        firstName = getIntent().getStringExtra("firstName");
        lastName = getIntent().getStringExtra("lastName");
        email = getIntent().getStringExtra("email");
        phone = getIntent().getStringExtra("phone");
        ageorbithdate = getIntent().getStringExtra("ageorbithdate");
        gender = getIntent().getStringExtra("gender");
        address = getIntent().getStringExtra("address");
        citizenship = getIntent().getStringExtra("citizenship");
        employment = getIntent().getStringExtra("employment");
        incomelevel = getIntent().getStringExtra("incomelevel");
        dateofjoin = getIntent().getStringExtra("dateofjoin");
        isActive = getIntent().getExtras().getBoolean("BOOLEAN_VALUE");
        countryPicker = CountryPicker.newInstance("Select Country");  // dialog title
        decodeUriToBitmap(context,image_Uri);

        edit_employment = (EditText) findViewById(R.id.edit_employment);
        edit_incomLevel = (EditText) findViewById(R.id.edit_incomLevel);

        edit_gender.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_address.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_citizinship.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_employment.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_incomLevel.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_datejoin.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        btn_save.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));


        edit_gender.setText(gender);
        edit_address.setText(address);
        edit_citizinship.setText(citizenship);
        edit_employment.setText(employment);
        edit_incomLevel.setText(incomelevel);
        edit_datejoin.setText(dateofjoin);


        if (isActive == true) {

            edit_gender.setEnabled(true);
            edit_address.setEnabled(true);
            edit_citizinship.setEnabled(true);
            edit_employment.setEnabled(true);
            edit_incomLevel.setEnabled(true);
            edit_datejoin.setEnabled(true);
            btn_save.setVisibility(View.VISIBLE);

            edit_gender.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.botton_icon, 0);
            edit_citizinship.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.botton_icon, 0);
            edit_employment.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.botton_icon, 0);
            edit_incomLevel.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.botton_icon, 0);
            edit_datejoin.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.botton_icon, 0);


        } else {

            edit_gender.setEnabled(false);
            edit_address.setEnabled(false);
            edit_citizinship.setEnabled(false);
            edit_employment.setEnabled(false);
            edit_incomLevel.setEnabled(false);
            edit_datejoin.setEnabled(false);
            btn_save.setVisibility(View.GONE);
            edit_gender.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            edit_citizinship.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            edit_employment.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            edit_incomLevel.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            edit_datejoin.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);


        }
    }

    public  Bitmap decodeUriToBitmap(Context mContext, Uri sendUri) {

        try {
            InputStream image_stream;
            try {
                image_stream = mContext.getContentResolver().openInputStream(sendUri);
                bitmap = BitmapFactory.decodeStream(image_stream);
                imageMultipart = sendImageFileToserver(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    private void EventListner() {

        btn_save.setOnClickListener(this);
        img_back.setOnClickListener(this);
        edit_gender.setOnClickListener(this);
        edit_citizinship.setOnClickListener(this);
        edit_employment.setOnClickListener(this);
        edit_address.setOnClickListener(this);
        edit_incomLevel.setOnClickListener(this);
        edit_datejoin.setOnClickListener(this);

        countryPicker.setListener(new CountryPickerListener() {
            @Override
            public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {

                dailCode = dialCode;
                edit_citizinship.setText(name);
                countryPicker.dismiss();
            }
        });

    }

    private MultipartBody.Part sendImageFileToserver(Bitmap bitmap) {
        File filesDir = context.getFilesDir();
        File file = new File(filesDir, "image" + System.currentTimeMillis() + ".png");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bos);
        byte[] bitmapdata = bos.toByteArray();

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(bitmapdata);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("image", file.getName(), reqFile);
        img_ReqBody = RequestBody.create(MediaType.parse("text/plain"), "image");

        return fileToUpload;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.img_back:

                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_click);
                img_back.startAnimation(animation);
                finish();
                overridePendingTransition(R.anim.main_faidin, R.anim.main_faidout);
                break;
            case R.id.btn_save:
                validationonProfile();
                break;

            case R.id.edit_gender:
                genderDialog();
                break;

            case R.id.edit_citizinship:
                openicker();
                break;
            case R.id.edit_address:
                locationPicker();
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
        }

    }

    private void validationonProfile() {

        if (Utility.isNetworkConnectionAvailable(context)) {
            if (edit_gender.getText().toString().isEmpty()) {
                String edit_gender = getString(R.string.string_gender);
                Utility.ShowToast(context, edit_gender);
            } else if (edit_address.getText().toString().isEmpty()) {
                String edit_address = getString(R.string.string_address);
                Utility.ShowToast(context, edit_address);
            } else if (edit_citizinship.getText().toString().isEmpty()) {
                String edit_citizinship = getString(R.string.string_citizinship);
                Utility.ShowToast(context, edit_citizinship);
            } else if (edit_employment.getText().toString().isEmpty()) {
                String employment = getString(R.string.r_select_employment);
                Utility.ShowToast(context, employment);
            } else if (edit_incomLevel.getText().toString().isEmpty()) {
                String Income = getString(R.string.r_income);
                Utility.ShowToast(context, Income);
            } else if (edit_datejoin.getText().toString().isEmpty()) {
                String Date_Joined = getString(R.string.r_date_of_join);
                Utility.ShowToast(context, Date_Joined);
            } else {
                progressDialog = Utility.ShowDialog(this);
                /*Api call for edit and update user personal profile*/
                ipProfileActivity.doUpdateProfile(savePref.get_token(), img_ReqBody, imageMultipart, firstName, lastName, email, phone, ageorbithdate,
                        edit_gender.getText().toString().trim(), edit_address.getText().toString().trim(), edit_citizinship.getText().toString().trim(),
                        edit_employment.getText().toString().trim(), edit_incomLevel.getText().toString().trim(), edit_datejoin.getText().toString().trim());
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
                        //dateofjoinig = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        dateofjoinig = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        edit_datejoin.setText(dateofjoinig);
                        //  getAge(year, monthOfYear, dayOfMonth);


                    }
                }, mYear, mMonth, mDay);

        //datePickerDialog.setTitle("Please pick your date of birth");
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

        datePickerDialog.show();


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

        incomeLevelAdapter = new IncomeLevelAdapters(this, incomeModels, ProfileActivity.this);
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

        employmentAdapter = new EmploymentsAdapter(this, empModels, ProfileActivity.this);
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
        // edit_address.setEnabled(true);
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

    private void openicker() {
        countryPicker.show(getSupportFragmentManager(), "COUNTRY_PICKER");
    }

    private void genderDialog() {

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

    @Override
    public void onUpdateProfileSuccessResponseFromPresenter(EditUserProfileReponseModel editUserProfileReponseModel) {
        progressDialog.dismiss();
        savePref.set_profile_image(editUserProfileReponseModel.getData().get(0).getProfileImage());
        savePref.set_first_name(editUserProfileReponseModel.getData().get(0).getFirstName());
        savePref.set_last_name(editUserProfileReponseModel.getData().get(0).getLastName());
        savePref.set_email(editUserProfileReponseModel.getData().get(0).getEmail());
        savePref.set_phone(editUserProfileReponseModel.getData().get(0).getPhone());
        savePref.set_dob(editUserProfileReponseModel.getData().get(0).getDob());
        savePref.set_gender(editUserProfileReponseModel.getData().get(0).getGender());
        savePref.set_address(editUserProfileReponseModel.getData().get(0).getAddress());
        savePref.set_address_logitude(editUserProfileReponseModel.getData().get(0).getAddressLogitude());
        savePref.set_address_latitude(editUserProfileReponseModel.getData().get(0).getAddressLatitude());
        savePref.set_citizenship(editUserProfileReponseModel.getData().get(0).getCitizenship());
        savePref.set_employment(editUserProfileReponseModel.getData().get(0).getEmployment());
        savePref.set_income_level(editUserProfileReponseModel.getData().get(0).getIncomeLevel());
        savePref.set_date_joining(editUserProfileReponseModel.getData().get(0).getDateJoining());

        Utility.ShowSuccessToast(context, editUserProfileReponseModel.getMessage());
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onUpdateProfileFailedResponseFromPresenter(String message) {
        progressDialog.dismiss();
        String error_message = message;
        Utility.ShowToast(context, error_message);

    }

    @Override
    public void onUpdateProfileInvalidRequestPresenter(String message) {
        progressDialog.dismiss();
        String error_message = message;
        Utility.ShowToast(context, error_message);
    }
}
