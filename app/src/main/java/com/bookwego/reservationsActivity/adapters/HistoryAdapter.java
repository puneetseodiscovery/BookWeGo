package com.bookwego.reservationsActivity.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.reservationDetails.ReservationDetailsActivity;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;

    public HistoryAdapter(Context context) {

        this.context = context;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.layout_upcoming, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ReservationDetailsActivity.class);
                context.startActivity(intent);
            }
        });


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

        @BindView(R.id.tv_forpeople)
        TextView tv_forpeople;

        @BindView(R.id.tv_ordercode)
        TextView tv_ordercode;

        public RecyclerViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);

            tv_ordername.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
            tv_orderdate.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
            tv_forpeople.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
            tv_ordercode.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));

        }
    }


}
