package com.bookwego.applyCouponActivity.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;

    public CouponAdapter(Context context) {

        this.context = context;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.layout_coupon, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {

        holder.tv_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context,
                        android.R.style.Theme_Translucent_NoTitleBar);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.CENTER);

                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dialog.setTitle(null);
                dialog.setContentView(R.layout.dialog_applythiscoupons);
                dialog.setCancelable(true);

                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {

        return 10;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_ordername)
        TextView tv_ordername;

        @BindView(R.id.tv_apply)
        TextView tv_apply;

        @BindView(R.id.tv_discount)
        TextView tv_discount;

        @BindView(R.id.tv_aboutdiscount)
        TextView tv_aboutdiscount;

        @BindView(R.id.tv_moredetails)
        TextView tv_moredetails;

        public RecyclerViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);

            tv_ordername.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
            tv_discount.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
            tv_aboutdiscount.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
            tv_moredetails.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
            tv_apply.setTypeface(Utility.typeFaceForPoppinsBoldText(context));

        }
    }
}
