package com.bookwego.profileActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Html;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
import com.bookwego.profileActivity.interfacesPersonalProfile.IPPersonalProfileActivity;
import com.bookwego.profileActivity.interfacesPersonalProfile.IPersonalProfileActivity;
import com.bookwego.profileActivity.presenter.PPersonalProfileActivity;
import com.bookwego.profileActivity.responseModels.GetUserProfileResponseModel;
import com.bookwego.utills.CommonMethods;
import com.bookwego.utills.Constant;
import com.bookwego.utills.SavePref;
import com.bookwego.utills.Utility;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PersonalProfileActivity extends BaseClass implements IPersonalProfileActivity, IPickResult, View.OnClickListener {

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

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.img_edit_profile)
    ImageView img_edit_profile;


    @BindView(R.id.img_user)
    CircleImageView img_user;

    Context context;
    private int MEDIA_TYPE_CAPTURE = 2;
    private static final int SELECT_FILE = 200;

    String image_path = "";
    IPPersonalProfileActivity ipPersonalProfileActivity;
    SavePref savePref;
    public static PersonalProfileActivity personalProfileActivity;
    ImageView img_edit;
    boolean isClick = false;
    Dialog progressDialog;
    String gender = "", address = "", citizenship = "", employment = "", incomelevel = "", dateofjoin = "", user_image = "";
    private int mYear, mMonth, mDay;
    String dateofbirth = "";
    Bitmap bitmap;
    Uri image_Uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);

        ButterKnife.bind(this);
        context = PersonalProfileActivity.this;
        personalProfileActivity = this;
        savePref = new SavePref(this);
        ipPersonalProfileActivity = new PPersonalProfileActivity(this);
        Initialization();
        EventListner();

    }

    private void Initialization() {

        img_edit = (ImageView) findViewById(R.id.img_edit);
        edit_firstName.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_lastName.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_email.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_phone.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        edit_ageorbithdate.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));
        btn_next.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(this));

        edit_firstName.setEnabled(false);
        edit_lastName.setEnabled(false);
        edit_email.setEnabled(false);
        edit_phone.setEnabled(false);
        edit_firstName.setEnabled(false);
        edit_ageorbithdate.setEnabled(false);


        if (Utility.isNetworkConnectionAvailable(context)) {
            progressDialog = Utility.ShowDialog(context);
            /*Api call for get user personal profile*/
                ipPersonalProfileActivity.doGetUserProfile(savePref.get_token());

        }

    }

    private void EventListner() {

        btn_next.setOnClickListener(this);
        img_back.setOnClickListener(this);
        img_edit.setOnClickListener(this);
        img_edit_profile.setOnClickListener(this);
        edit_ageorbithdate.setOnClickListener(this);

    }


    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_edit:

                PickImageDialog.build(new PickSetup().setButtonOrientation(LinearLayoutCompat.HORIZONTAL)
                        /*.setIconGravity(Gravity.TOP)
                        .setGalleryIcon(R.drawable.landscape)
                                .setCameraIcon(R.drawable.img_photo_camera)*/).show(this);

                break;
            case R.id.btn_next:

                validationPersonalProfile();

                break;

            case R.id.img_edit_profile:

                if (isClick == false) {
                    img_edit.setVisibility(View.VISIBLE);
                    edit_firstName.setEnabled(true);
                    edit_lastName.setEnabled(true);
                    edit_email.setEnabled(true);
                    edit_phone.setEnabled(true);
                    edit_ageorbithdate.setEnabled(true);
                    edit_ageorbithdate.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.botton_icon, 0);
                    isClick = true;
                } else {
                    edit_firstName.setEnabled(false);
                    edit_lastName.setEnabled(false);
                    edit_email.setEnabled(false);
                    edit_phone.setEnabled(false);
                    edit_ageorbithdate.setEnabled(false);
                    img_edit.setVisibility(View.INVISIBLE);
                    edit_ageorbithdate.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    isClick = false;
                }
                break;

            case R.id.edit_ageorbithdate:
                OpenDatePickerDialog();
                break;
        }
    }


    private void validationPersonalProfile() {

        if (Utility.isNetworkConnectionAvailable(context)) {
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
            } else if (edit_phone.getText().toString().trim().isEmpty()) {
                String edit_phone = getString(R.string.string_phone_number);
                Utility.ShowToast(context, edit_phone);
            } else if (!CommonMethods.isValidMobile(edit_phone.getText().toString())) {
                String vaild_phone = getString(R.string.enter_valid_phone_number);
                Utility.ShowToast(context, vaild_phone);
            } else if (edit_ageorbithdate.getText().toString().isEmpty()) {
                String edit_ageorbithdate = getString(R.string.string_dateofbirth);
                Utility.ShowToast(context, edit_ageorbithdate);
            } else {

                Intent intent = new Intent(PersonalProfileActivity.this, ProfileActivity.class);
                intent.putExtra("BOOLEAN_VALUE", isClick);
                intent.putExtra("firstName", edit_firstName.getText().toString().trim());
                intent.putExtra("lastName", edit_lastName.getText().toString().trim());
                intent.putExtra("email", edit_email.getText().toString().trim());
                intent.putExtra("phone", edit_phone.getText().toString().trim());
                intent.putExtra("ageorbithdate", edit_ageorbithdate.getText().toString());
                intent.putExtra("gender", gender);
                intent.putExtra("address", address);
                intent.putExtra("citizenship", citizenship);
                intent.putExtra("employment", employment);
                intent.putExtra("incomelevel", incomelevel);
                intent.putExtra("dateofjoin", dateofjoin);
                intent.putExtra("image_Uri",image_Uri.toString());

                startActivity(intent);
                overridePendingTransition(0, R.anim.splash_faidout);
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
                        dateofbirth = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        edit_ageorbithdate.setText(dateofbirth);
                        //  getAge(year, monthOfYear, dayOfMonth);


                    }
                }, mYear, mMonth, mDay);


        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

        datePickerDialog.show();


    }

    private void openMediaDialog() {
        final Dialog dialog;
        dialog = new Dialog(context, R.style.DialogThemes);
        dialog.setContentView(R.layout.camera_dialog);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        //dialog.getWindow().getAttributes().windowAnimations = R.style.DialogThemeUpload;


        TextView tv_cancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        TextView tv_camera = (TextView) dialog.findViewById(R.id.tv_camera);
        TextView tv_gallery = (TextView) dialog.findViewById(R.id.tv_gallery);

        tv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, MEDIA_TYPE_CAPTURE);
                dialog.dismiss();
            }
        });

        tv_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(profile, SELECT_FILE);
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
    public void onGetProfileSuccessResponseFromPresenter(GetUserProfileResponseModel getUserProfileResponseModel) {
        progressDialog.dismiss();

        if (!getUserProfileResponseModel.getData().get(0).getProfileImage().equals("")) {
            Glide.with(context).load(Constant.IMAGE_URL + getUserProfileResponseModel.getData().get(0).getProfileImage())
                    .placeholder(R.drawable.user_p)
                    .into(img_user);
            image_Uri= Uri.parse(Constant.IMAGE_URL + getUserProfileResponseModel.getData().get(0).getProfileImage());

        } else {

        }

        edit_firstName.setText(CommonMethods.upperCase(getUserProfileResponseModel.getData().get(0).getFirstName()));
        edit_lastName.setText(CommonMethods.upperCase(getUserProfileResponseModel.getData().get(0).getLastName()));

        edit_email.setText(getUserProfileResponseModel.getData().get(0).getEmail());
        edit_phone.setText(getUserProfileResponseModel.getData().get(0).getPhone());
        edit_ageorbithdate.setText(getUserProfileResponseModel.getData().get(0).getDob());
        gender = getUserProfileResponseModel.getData().get(0).getGender();
        address = getUserProfileResponseModel.getData().get(0).getAddress();
        citizenship = getUserProfileResponseModel.getData().get(0).getCitizenship();
        employment = getUserProfileResponseModel.getData().get(0).getEmployment();
        incomelevel = getUserProfileResponseModel.getData().get(0).getIncomeLevel();
        dateofjoin = getUserProfileResponseModel.getData().get(0).getDateJoining();

    }

    @Override
    public void onGetProfileFailedResponseFromPresenter(String message) {
        progressDialog.dismiss();
    }


    public static class MyBounceInterpolator implements android.view.animation.Interpolator {
        private double mAmplitude = 1;
        private double mFrequency = 10;

        public MyBounceInterpolator(double amplitude, double frequency) {
            mAmplitude = amplitude;
            mFrequency = frequency;
        }

        public float getInterpolation(float time) {
            return (float) (-1 * Math.pow(Math.E, -time / mAmplitude) *
                    Math.cos(mFrequency * time) + 1);
        }
    }

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {
            //If you want the Uri.
            //Mandatory to refresh image from Uri.
            //getImageView().setImageURI(null);

            //Setting the real returned image.
            //getImageView().setImageURI(r.getUri());
            image_Uri = r.getUri();

            //If you want the Bitmap.
            //image_Bitmap=r.getBitmap();
            img_user.setImageBitmap(r.getBitmap());
            //Image path
            //r.getPath();
            image_path = r.getPath();


        } else {
            //Handle possible errors
            //TODO: do what you have to do with r.getError();
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }


}
