package com.bookwego.favouriteActvity.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;
    public static int Count = 0;


    public FavouriteAdapter(Context context) {

        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.layout_favourite, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        holder.img_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Count == 0) {
                    holder.img_heart.setBackgroundResource(R.drawable.heart_red);
                    Count = 1;
                } else {
                    holder.img_heart.setBackgroundResource(R.drawable.heart);
                    Count = 0;
                }
            }
        });

    }

    @Override
    public int getItemCount() {

        return 2;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_resturantname)
        TextView tv_resturantname;

        @BindView(R.id.tv_location)
        TextView tv_location;

        @BindView(R.id.tv_distance)
        TextView tv_distance;

        @BindView(R.id.tv_totalrecomandaton)
        TextView tv_totalrecomandaton;

        @BindView(R.id.tv_viewRecommended)
        TextView tv_viewRecommended;

        @BindView(R.id.img_heart)
        ImageView img_heart;


        public RecyclerViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
            tv_resturantname.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            tv_location.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            tv_distance.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            tv_totalrecomandaton.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            tv_viewRecommended.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));


        }
    }


}
