package com.bookwego.mainActivity.adapters;

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

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;
    List<CategoriesModel> categoriesModelArrayList;

    public CategoriesAdapter(Context context, List<CategoriesModel> categoriesModelArrayList) {

        this.context = context;
        this.categoriesModelArrayList = categoriesModelArrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.layout_restaurants_catagories, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        holder.tv_Categories.setText(categoriesModelArrayList.get(position).getCategoriesName());

        if (position == 0) {
            holder.view_tab.setVisibility(View.VISIBLE);
            holder.tv_Categories.setTextColor(Color.parseColor("#69C730"));
        } else {
            holder.view_tab.setVisibility(View.INVISIBLE);
            holder.tv_Categories.setTextColor(Color.parseColor("#848484"));
        }

    }

    @Override
    public int getItemCount() {

        return categoriesModelArrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_Categories)
        TextView tv_Categories;

        @BindView(R.id.view_tab)
        View view_tab;

        public RecyclerViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
            tv_Categories.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));


        }
    }


}
