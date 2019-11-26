package com.bookwego.panelListActivity.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.mainActivity.fragments.responseModel.ResturantsResponseModel;
import com.bookwego.utills.Utility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartDiscountAdapter extends RecyclerView.Adapter<CartDiscountAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;
    List<ResturantsResponseModel.TimeSlot> timeSlots;

    public CartDiscountAdapter(Context context,List<ResturantsResponseModel.TimeSlot> timeSlots) {

        this.context = context;
        this.timeSlots = timeSlots;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.layout_cart_discount2, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {

        holder.tv_time.setText(timeSlots.get(0).getTimeSlot());
        holder.tv_discount.setText("-"+timeSlots.get(0).getDiscount()+"%");
    }

    @Override
    public int getItemCount() {

        return timeSlots.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_time)
        TextView tv_time;

        @BindView(R.id.tv_discount)
        TextView tv_discount;

        public RecyclerViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);

            tv_time.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
            tv_discount.setTypeface(Utility.typeFaceForPoppinsBoldText(context));
        }
    }
}
