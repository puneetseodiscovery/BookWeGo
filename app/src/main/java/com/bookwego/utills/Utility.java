package com.bookwego.utills;

import android.content.Context;
import android.graphics.Typeface;

public class Utility {

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


}
