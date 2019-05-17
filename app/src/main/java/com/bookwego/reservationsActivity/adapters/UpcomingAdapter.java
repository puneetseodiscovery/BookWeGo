package com.bookwego.reservationsActivity.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.mainActivity.modelClasses.CategoriesModel;
import com.bookwego.utills.Utility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;

    public UpcomingAdapter(Context context) {

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

    }

    @Override
    public int getItemCount() {

        return 10;
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
