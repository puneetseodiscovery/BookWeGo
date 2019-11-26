package com.bookwego.mainActivity.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bookwego.R;
import com.bookwego.mainActivity.fragments.RestaurantsFragment;
import com.bookwego.mainActivity.fragments.responseModel.TitleResponseModel;
import com.bookwego.utills.CommonMethods;
import com.bookwego.utills.Constant;
import com.bookwego.utills.Utility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;
    List<TitleResponseModel.Datum> data;
    RestaurantsFragment restaurantsFragment;
    int selectedPosition;


    public CategoriesAdapter(Context context, List<TitleResponseModel.Datum> data,RestaurantsFragment restaurantsFragment,int pos) {

        this.context = context;
        this.data = data;
        this.restaurantsFragment = restaurantsFragment;
        inflater = LayoutInflater.from(context);
        this.selectedPosition = pos;
}


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.layout_restaurants_catagories, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {

        if(selectedPosition==position) {
            Constant.SELECTED_ITEM="0";
            holder.tv_Categories.setTextColor(Color.parseColor("#69C730"));
            holder.view_tab.setVisibility(View.VISIBLE);
        }
        else {
            holder.tv_Categories.setTextColor(Color.parseColor("#848484"));
            holder.view_tab.setVisibility(View.INVISIBLE);
        }
        holder.tv_Categories.setText(CommonMethods.upperCase(data.get(position).getName()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition=position;
                notifyDataSetChanged();

                ((RestaurantsFragment)restaurantsFragment).OnTitleClick(data.get(position).getId());

            }
        });

    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_Categories)
        TextView tv_Categories;

        @BindView(R.id.view_tab)
        View view_tab;

        public RecyclerViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
            tv_Categories.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));

        }
    }


}
