package com.bookwego.menuItemDetailsActivity.adapters;

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

public class SpecialConditionsAdapter extends RecyclerView.Adapter<SpecialConditionsAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;

    public SpecialConditionsAdapter(Context context) {

        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.layout_special_condition, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {


    }

    @Override
    public int getItemCount() {

        return 7;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_conditions)
        TextView tv_conditions;

        public RecyclerViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
            tv_conditions.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
        }
    }
}
