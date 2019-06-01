package com.bookwego.panelListActivity.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.menuActivity.MenuActivity;
import com.bookwego.trackOrderActivity.TrackOrderActivity;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantsListAdapter extends RecyclerView.Adapter<RestaurantsListAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;


    public RestaurantsListAdapter(Context context) {

        this.context = context;

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


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MenuActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return 4;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_orderName)
        TextView tv_orderName;

        @BindView(R.id.tv_loaction)
        TextView tv_loaction;

        @BindView(R.id.tv_totalrating)
        TextView tv_totalrating;


        @BindView(R.id.recycler_discounts)
        RecyclerView recycler_discounts;

        public RecyclerViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);

            tv_orderName.setTypeface(Utility.typeFaceForProximaNovaBoldText(context));
            tv_loaction.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
            tv_totalrating.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));

            CartDiscountAdapter cartDiscountAdapter = new CartDiscountAdapter(context);
            recycler_discounts.setLayoutManager(new LinearLayoutManager(context,
                    LinearLayoutManager.HORIZONTAL, false));
            recycler_discounts.setAdapter(cartDiscountAdapter);


        }
    }


}
