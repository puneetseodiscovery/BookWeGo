package com.bookwego.customViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class ProximaNova_RegularText extends android.support.v7.widget.AppCompatTextView{

public ProximaNova_RegularText(Context context, AttributeSet attrs, int defStyle)
{
    super(context, attrs, defStyle);
    init();
}

public ProximaNova_RegularText(Context context, AttributeSet attrs)
{
    super(context, attrs);
    init();
}

public ProximaNova_RegularText(Context context)
{
    super(context);
    init();
}

private void init() 
{
    if (!isInEditMode()) 
    {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/ProximaNovaRegular.ttf");
        setTypeface(tf);
    }
}
}