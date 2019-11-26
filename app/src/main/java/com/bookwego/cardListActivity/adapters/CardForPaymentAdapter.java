package com.bookwego.cardListActivity.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardForPaymentAdapter extends RecyclerView.Adapter<CardForPaymentAdapter.RecyclerViewHolder> {

    Context context; // context
    LayoutInflater inflater;
    public String[] mColors = {
            "3F51B5", "FF9800", "009688", "673AB7"
    };


    public CardForPaymentAdapter(Context context) {

        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.layout_payment_list, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {

        String color = "#" + mColors[position];

        for (int c = 1; c < mColors.length; c++) {

            holder.cardView.setCardBackgroundColor(Color.parseColor(color));

            if (c == mColors.length) {
                c = 1;
            }
        }
    }

    @Override
    public int getItemCount() {

        return 4;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.card_name)
        TextView card_name;

        @BindView(R.id.number)
        TextView number;

        @BindView(R.id.card_number)
        TextView card_number;

        @BindView(R.id.expiration)
        TextView expiration;

        @BindView(R.id.cvv)
        TextView cvv;

        @BindView(R.id.expiration_number)
        TextView expiration_number;

        @BindView(R.id.cvv_number)
        TextView cvv_number;

        @BindView(R.id.cardView)
        CardView cardView;

        public RecyclerViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
            name.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            card_name.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            number.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            card_number.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            expiration.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            cvv.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            expiration_number.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            cvv_number.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
        }
    }
}
