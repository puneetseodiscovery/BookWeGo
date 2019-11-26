package com.bookwego.panelListActivity.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.menuActivity.MenuActivity;
import com.bookwego.panelListActivity.responseModel.responseRestaurantsCategoriesModel;
import com.bookwego.utills.CommonMethods;
import com.bookwego.utills.Constant;
import com.bookwego.utills.Utility;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantsListAdapter extends RecyclerView.Adapter<RestaurantsListAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;
    List<responseRestaurantsCategoriesModel.Datum> data;

    public RestaurantsListAdapter(Context context, List<responseRestaurantsCategoriesModel.Datum> data) {

        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.layout_resturantslist, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {

        Glide.with(context).load(Constant.IMAGE_URL + data.get(position).getImage()).placeholder(R.drawable.placeholder)
                .error(R.drawable.no_image_placeholder).into(holder.img_resturant);
        holder.tv_resturantsName.setText(CommonMethods.upperCase(data.get(position).getName()));
        holder.tv_loaction.setText(CommonMethods.upperCase(data.get(position).getAddress()));
        holder.tv_totalrating.setText(CommonMethods.upperCase(data.get(position).getAvgRating()));


        ListDiscountAdapter listDiscountAdapter = new ListDiscountAdapter(context, data.get(position).getTimeSlots());
        holder.recycler_discounts.setLayoutManager(new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false));
        holder.recycler_discounts.setAdapter(listDiscountAdapter);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MenuActivity.class);

                intent.putExtra("restaurantsId", data.get(position).getId());
                intent.putExtra("restaurants_Image", data.get(position).getImage());
                intent.putExtra("restaurants_Name", data.get(position).getName());
                intent.putExtra("restaurants_Address", data.get(position).getAddress());
                intent.putExtra("restaurants_AvrageRating", data.get(position).getAvgRating());

               /* intent.putExtra("position", String.valueOf(position));
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

        @BindView(R.id.tv_resturantsName)
        TextView tv_resturantsName;

        @BindView(R.id.tv_loaction)
        TextView tv_loaction;

        @BindView(R.id.tv_totalrating)
        TextView tv_totalrating;

        @BindView(R.id.img_resturant)
        ImageView img_resturant;


        @BindView(R.id.recycler_discounts)
        RecyclerView recycler_discounts;

        public RecyclerViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);

            tv_resturantsName.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
            tv_loaction.setTypeface(Utility.typeFaceForPoppinsRegulerText(context));
            tv_totalrating.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));


        }
    }
}
