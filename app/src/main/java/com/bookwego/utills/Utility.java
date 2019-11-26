package com.bookwego.utills;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bookwego.R;
import com.wang.avi.AVLoadingIndicatorView;

public class Utility {

    public static  ProgressDialog progressDialog;
    public static  StringBuilder rackingSystemSb;

    public static boolean isNetworkConnectionAvailable(Context context){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();

        if(isConnected) {
            Log.d("Network", "Connected");

            return true;
        }
        else{
            //checkNetworkConnection(context);
            networkDialog(context);
            Log.d("Network","Not Connected");
            return false;
        }
    }

    public static void checkNetworkConnection(final Context context){
        AlertDialog.Builder builder =new AlertDialog.Builder(context);
        builder.setTitle(Html.fromHtml("<b>"+"No internet Connection"+"</b>"));
        builder.setMessage("Please check your internet Setting and try again");
        builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isNetworkConnectionAvailable(context);
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public static Dialog networkDialog (final Context context) {

        final Dialog dialog;
        dialog = new Dialog(context, R.style.Theme_Dialog);
        dialog.setContentView(R.layout.layout_nointernet_connection);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        TextView tv_tryAgain=(TextView)dialog.findViewById(R.id.tv_tryAgain);
        TextView tv_oops=(TextView)dialog.findViewById(R.id.tv_oops);
        TextView tv_slowInternet=(TextView)dialog.findViewById(R.id.tv_slowInternet);
        TextView tv_checkSetting=(TextView)dialog.findViewById(R.id.tv_checkSetting);
        tv_oops.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
        tv_slowInternet.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
        tv_tryAgain.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
        tv_checkSetting.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
        tv_tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNetworkConnectionAvailable(context);
                dialog.dismiss();
            }
        });
        dialog.show();

        return dialog;
    }

    public static Typeface typeFaceForProximaNovaBoldText(Context context) {

        return Typeface.createFromAsset(context.getAssets(),
                "fonts/ProximaNovaBold.ttf");
    }


    public static Typeface typeFaceForPoppinsBoldText(Context context) {

        return Typeface.createFromAsset(context.getAssets(),
                "fonts/PoppinsBold.ttf");
    }

    public static Typeface typeFaceForPoppinsRegulerText(Context context) {

        return Typeface.createFromAsset(context.getAssets(),
                "fonts/PoppinsRegular.ttf");
    }

    public static Typeface typeFaceForProximaNovaRegulerText(Context context) {

        return Typeface.createFromAsset(context.getAssets(),
                "fonts/ProximaNovaRegular.ttf");
    }

    public static Typeface typeFaceForProximaNovaSemiboldText(Context context) {

        return Typeface.createFromAsset(context.getAssets(),
                "fonts/ProximaNovaSemibold.ttf");
    }

    public static void ShowToast(Context context, String message) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate( R.layout.layout_error_toast, null );
        TextView textView=(TextView) layout.findViewById(R.id.tv_toast);
        textView.setText(message);
        textView.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
        Toast toast=new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,50);
        toast.setView(layout);
        toast.show();
    }

    public static void ShowSuccessToast(Context context, String message) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate( R.layout.layout_success, null );
        TextView textView=(TextView) layout.findViewById(R.id.tv_toast);
        textView.setText(message);
        textView.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
        Toast toast=new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,50);
        toast.setView(layout);
        toast.show();

    }

    public static Dialog ShowDialog (Context context) {

        final Dialog dialog;
        dialog = new Dialog(context, R.style.Theme_Dialog);
        dialog.setContentView(R.layout.layout_custom_dialog);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

        return dialog;
    }


    public static ProgressDialog showLoader(Context context) {
        progressDialog = new ProgressDialog(context);
        try {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        catch (Exception e)
        {
            e.fillInStackTrace();
        }
        progressDialog.setCancelable(false);

        progressDialog.show();
        progressDialog.setContentView(R.layout.layout_progress);
        return progressDialog;
    }

    public static boolean isValidPassword(final String password) {
        String pattern= "^[a-zA-Z0-9]*$";
        return password.matches(pattern);

    }
}
