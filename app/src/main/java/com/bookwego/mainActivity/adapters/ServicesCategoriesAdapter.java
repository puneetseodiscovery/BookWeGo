package com.bookwego.mainActivity.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bookwego.R;
import com.bookwego.mainActivity.fragments.ServicesFragment;
import com.bookwego.mainActivity.fragments.responseModel.TitleResponseModel;
import com.bookwego.mainActivity.modelClasses.CategoriesModel;
import com.bookwego.utills.CommonMethods;
import com.bookwego.utills.Utility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServicesCategoriesAdapter extends RecyclerView.Adapter<ServicesCategoriesAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;
    List<TitleResponseModel.Datum> data;
    ServicesFragment servicesFragment;
    int selectedPosition=-1;

    public ServicesCategoriesAdapter(Context context, List<TitleResponseModel.Datum> data, ServicesFragment servicesFragment,int pos) {
        this.context = context;
        this.data = data;
        this.servicesFragment = servicesFragment;
        inflater = LayoutInflater.from(context);
        this.selectedPosition=pos;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.layout_services_catagories, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {

        if(selectedPosition==position) {
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
                ((ServicesFragment)servicesFragment).onTitleClickListner(data.get(position).getId());

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
