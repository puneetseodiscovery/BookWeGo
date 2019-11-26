package com.bookwego.mainActivity.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bookwego.R;
import com.bookwego.mainActivity.fragments.responseModel.RestaurantsCategoriesResponseModel;
import com.bookwego.menuActivity.MenuActivity;
import com.bookwego.panelListActivity.PanalListActivity;
import com.bookwego.utills.CommonMethods;
import com.bookwego.utills.Constant;
import com.bookwego.utills.Utility;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubCategoriesAdapter  extends RecyclerView.Adapter<SubCategoriesAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;
    List<RestaurantsCategoriesResponseModel.Datum> data;

    public SubCategoriesAdapter(Context context,List<RestaurantsCategoriesResponseModel.Datum> data) {

        this.context = context;
        this.data = data;
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

        Glide.with(context).load(Constant.IMAGE_URL + data.get(position).getImage())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.no_image_placeholder)
                .into(holder.img_resturant);
        holder.tv_restaurant_categories.setText(CommonMethods.upperCase(data.get(position).getCatName()));
        holder.tv_total_restaurants.setText(" (" + data.get(position).getCount() + " " + "Restaurants" + ") ");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, PanalListActivity.class);

                intent.putExtra("restaurants_Image",data.get(position).getImage());
                intent.putExtra("restaurants_Name",data.get(position).getCatName());
                intent.putExtra("restaurants_Count",data.get(position).getCount());
                intent.putExtra("Categories_type","1");
                intent.putExtra("restaurants_Categories_ID",String.valueOf(data.get(position).getCatId()));

                // send complete data

              /*  intent.putExtra("position", String.valueOf(position));
                intent.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) data);*/
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_restaurant_categories)
        TextView tv_restaurant_categories;

        @BindView(R.id.tv_total_restaurants)
        TextView tv_total_restaurants;

        @BindView(R.id.img_resturant)
        ImageView img_resturant;

        public RecyclerViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
            tv_restaurant_categories.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
            tv_total_restaurants.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));

        }
    }


}
