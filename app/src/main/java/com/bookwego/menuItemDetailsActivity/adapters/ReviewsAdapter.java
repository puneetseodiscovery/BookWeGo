package com.bookwego.menuItemDetailsActivity.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;

    public ReviewsAdapter(Context context) {

        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.layout_reviews, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {


    }

    @Override
    public int getItemCount() {

        return 7;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_userName)
        TextView tv_userName;

        @BindView(R.id.tv_reviews)
        TextView tv_reviews;

        @BindView(R.id.tv_recommending)
        TextView tv_recommending;

        @BindView(R.id.tv_time)
        TextView tv_time;

        public RecyclerViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
            tv_userName.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
            tv_reviews.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            tv_recommending.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            tv_time.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
        }
    }
}
