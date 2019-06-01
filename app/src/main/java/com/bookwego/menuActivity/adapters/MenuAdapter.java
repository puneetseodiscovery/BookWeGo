package com.bookwego.menuActivity.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.mainActivity.modelClasses.CategoriesModel;
import com.bookwego.menuActivity.ModelClasses.MenuModel;
import com.bookwego.utills.Utility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;
    MenuModel[] menuModelList;

    public MenuAdapter(Context context, MenuModel[] menuModelList) {

        this.context = context;
        this.menuModelList = menuModelList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.layout_menu, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        holder.tv_Categories.setText(menuModelList[position].getMenuName());

        if (position == 0) {

            holder.tv_Categories.setTextColor(Color.parseColor("#69C730"));
        } else {

            holder.tv_Categories.setTextColor(Color.parseColor("#848484"));
        }

    }

    @Override
    public int getItemCount() {

        return menuModelList.length;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_Categories)
        TextView tv_Categories;

        public RecyclerViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
            tv_Categories.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));


        }
    }


}
