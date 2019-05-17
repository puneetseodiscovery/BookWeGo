package com.bookwego.customViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class ProximaNova_BoldEditText extends android.support.v7.widget.AppCompatEditText
{

public ProximaNova_BoldEditText(Context context, AttributeSet attrs, int defStyle)
{
    super(context, attrs, defStyle);
    init();
}

public ProximaNova_BoldEditText(Context context, AttributeSet attrs)
{
    super(context, attrs);
    init();
}

public ProximaNova_BoldEditText(Context context)
{
    super(context);
    init();
}

private void init() 
{
    if (!isInEditMode()) 
    {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/ProximaNovaBold.ttf");
        setTypeface(tf);
    }
}
}