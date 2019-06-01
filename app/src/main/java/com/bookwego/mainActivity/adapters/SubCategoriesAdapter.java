package com.bookwego.mainActivity.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.panelListActivity.PanalListActivity;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubCategoriesAdapter extends RecyclerView.Adapter<SubCategoriesAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;

    public SubCategoriesAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.layout_restaurant_subcategories, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PanalListActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return 10;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_restaurant_categories)
        TextView tv_restaurant_categories;

        @BindView(R.id.tv_total_restaurants)
        TextView tv_total_restaurants;

        public RecyclerViewHolder(View view) {
            super(view);


            ButterKnife.bind(this, view);
            tv_restaurant_categories.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            tv_total_restaurants.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));

        }
    }


}
