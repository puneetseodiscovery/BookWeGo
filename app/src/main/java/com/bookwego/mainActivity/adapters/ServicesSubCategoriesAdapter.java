package com.bookwego.mainActivity.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.mainActivity.fragments.responseModel.ServicesCategoriesResponseModel;
import com.bookwego.matrixActivity.MatrixActivity;
import com.bookwego.menuActivity.MenuActivity;
import com.bookwego.panelListActivity.PanalListActivity;
import com.bookwego.utills.Constant;
import com.bookwego.utills.Utility;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServicesSubCategoriesAdapter extends RecyclerView.Adapter<ServicesSubCategoriesAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;
    List<ServicesCategoriesResponseModel.Datum> datumList;

    public ServicesSubCategoriesAdapter(Context context, List<ServicesCategoriesResponseModel.Datum> datumList) {

        this.context = context;
        this.datumList = datumList;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.layout_services_subcategories, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {

        Glide.with(context).load(Constant.IMAGE_URL + datumList.get(position).getImage())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.no_image_placeholder).into(holder.img_services);
        holder.tv_services_categories.setText(datumList.get(position).getCatName());
        holder.tv_total_services.setText(" (" + datumList.get(position).getCount() + " " + "Services" + ") ");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PanalListActivity.class);
                intent.putExtra("services_Image",datumList.get(position).getImage());
                intent.putExtra("services_Name",datumList.get(position).getCatName());
                intent.putExtra("services_Count",datumList.get(position).getCount());
                intent.putExtra("Categories_type","2");
                intent.putExtra("services_Categories_ID",String.valueOf(datumList.get(position).getCatId()));

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return datumList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_services_categories)
        TextView tv_services_categories;

        @BindView(R.id.tv_total_services)
        TextView tv_total_services;

        @BindView(R.id.img_services)
        ImageView img_services;

        public RecyclerViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
            tv_services_categories.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
            tv_total_services.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));

        }
    }


}
