package com.bookwego.paymentHistoryActivity.adapters;

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

public class PaymentHistoryAdapter extends RecyclerView.Adapter<PaymentHistoryAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;

    public PaymentHistoryAdapter(Context context) {

        this.context = context;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.layout_history, parent, false);
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

        @BindView(R.id.tv_trans_id)
        TextView tv_trans_id;

        @BindView(R.id.tv_thalirate)
        TextView tv_thalirate;

        @BindView(R.id.tv_thaliname)
        TextView tv_thaliname;


        public RecyclerViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
            tv_ordername.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
            tv_orderdate.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
            tv_trans_id.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
            tv_thalirate.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
            tv_thaliname.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));

        }
    }


}
