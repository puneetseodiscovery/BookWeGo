package com.bookwego.menuActivity.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.menuItemDetailsActivity.MenuItemDetailsActivity;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuListsAdapter extends RecyclerView.Adapter<MenuListsAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;

    public MenuListsAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.layout_menu_list, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {

        if (position % 2 == 0) {
            holder.tv_veg.setVisibility(View.INVISIBLE);
            holder.tv_nonveg.setVisibility(View.VISIBLE);

        } else {
            holder.tv_veg.setVisibility(View.VISIBLE);
            holder.tv_nonveg.setVisibility(View.INVISIBLE);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MenuItemDetailsActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return 10;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_nonveg)
        TextView tv_nonveg;

        @BindView(R.id.tv_veg)
        TextView tv_veg;

        @BindView(R.id.tv_price)
        TextView tv_price;

        public RecyclerViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
            tv_nonveg.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            tv_price.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));

        }
    }


}
