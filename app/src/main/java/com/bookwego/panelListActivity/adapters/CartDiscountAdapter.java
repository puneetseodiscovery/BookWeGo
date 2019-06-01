package com.bookwego.panelListActivity.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartDiscountAdapter extends RecyclerView.Adapter<CartDiscountAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;

    public CartDiscountAdapter(Context context) {

        this.context = context;

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

    }

    @Override
    public int getItemCount() {

        return 10;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_upto)
        TextView tv_upto;

        @BindView(R.id.tv_discount)
        TextView tv_discount;

        public RecyclerViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);

            tv_upto.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
            tv_discount.setTypeface(Utility.typeFaceForPoppinsBoldText(context));

        }
    }


}
