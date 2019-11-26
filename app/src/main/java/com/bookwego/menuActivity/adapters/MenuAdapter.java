package com.bookwego.menuActivity.adapters;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bookwego.R;
import com.bookwego.menuActivity.MenuActivity;
import com.bookwego.menuActivity.responseModel.MenuResponseModel;
import com.bookwego.utills.CommonMethods;
import com.bookwego.utills.Utility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;
    List<MenuResponseModel.Datum> data;
    int selectedPosition=-1;
    MenuActivity menuActivity;

    public MenuAdapter(Context context, List<MenuResponseModel.Datum> data, MenuActivity menuActivity, int pos) {

        this.context = context;
        this.data = data;
        this.menuActivity = menuActivity;
        this.selectedPosition = pos;
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

        if(selectedPosition==position) {
            holder.tv_Categories.setTextColor(context.getResources().getColor(R.color.colorTextWhite));
            holder.layout_background.setBackground(context.getResources().getDrawable(R.drawable.green_bg));
        }
        else {
            holder.tv_Categories.setTextColor(context.getResources().getColor(R.color.colorTextDarkGrey));
            holder.layout_background.setBackgroundResource(android.R.color.transparent);

        }
        holder.tv_Categories.setText(CommonMethods.upperCase(data.get(position).getCategoryName()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition=position;
                notifyDataSetChanged();
                ((MenuActivity)menuActivity).onMenuItemClick(data.get(position).getId());
                Toast.makeText(context, "Menu id="+data.get(position).getId(), Toast.LENGTH_SHORT).show();
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

        @BindView(R.id.layout_background)
        RelativeLayout layout_background;

        public RecyclerViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            tv_Categories.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));


        }
    }


}
