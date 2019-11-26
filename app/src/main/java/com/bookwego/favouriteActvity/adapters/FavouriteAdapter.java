package com.bookwego.favouriteActvity.adapters;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.favouriteActvity.FavouriteActivity;
import com.bookwego.favouriteActvity.responseModel.FavrouitListingResponseModel;
import com.bookwego.utills.CommonMethods;
import com.bookwego.utills.Constant;
import com.bookwego.utills.Utility;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;
    List<FavrouitListingResponseModel.Datum> data;



    public FavouriteAdapter(Context context,List<FavrouitListingResponseModel.Datum> data) {

        this.context = context;
        this.data = data;
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

        Glide.with(context).load(Constant.IMAGE_URL+data.get(position).getImage())
                .placeholder(R.drawable.placeholder).error(R.drawable.no_image_placeholder).into(holder.img_resturant);
        holder.tv_resturantname.setText(CommonMethods.upperCase(data.get(position).getName()));
        holder.tv_location.setText(CommonMethods.upperCase(data.get(position).getAddress()));
        holder.tv_totalrecomandaton.setText(data.get(position).getRecommend()+" "+"Person Recommended");
    }

    @Override
    public int getItemCount() {

        return data.size();
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

        @BindView(R.id.img_resturant)
        ImageView img_resturant;

        @BindView(R.id.img_selectedheart)
        ImageView img_selectedheart;


        public RecyclerViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                img_resturant.setClipToOutline(true);
            }

            tv_resturantname.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            tv_location.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            tv_distance.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            tv_totalrecomandaton.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            tv_viewRecommended.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));

        }
    }


}
