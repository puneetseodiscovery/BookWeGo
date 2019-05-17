package com.bookwego.orderActivity.adapters;

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

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;

    public OrdersAdapter(Context context) {

        this.context = context;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.layout_orders, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {

        return 4;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_ordername)
        TextView tv_ordername;

        @BindView(R.id.tv_orderdate)
        TextView tv_orderdate;

        @BindView(R.id.tv_thali_name)
        TextView tv_thali_name;

        @BindView(R.id.tv_thali_price)
        TextView tv_thali_price;

        @BindView(R.id.tv_tack_order)
        TextView tv_tack_order;

        @BindView(R.id.tv_reorder)
        TextView tv_reorder;

        public RecyclerViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);

            tv_ordername.setTypeface(Utility.typeFaceForProximaNovaBoldText(context));
            tv_orderdate.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
            tv_thali_name.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
            tv_thali_price.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
            tv_tack_order.setTypeface(Utility.typeFaceForPoppinsRegulerText(context));
            tv_reorder.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));

        }
    }


}
