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

public class SimilarRestaurantsAdapter extends RecyclerView.Adapter<SimilarRestaurantsAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;

    public SimilarRestaurantsAdapter(Context context) {

        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.layout_similar_restaurants, parent, false);
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

        @BindView(R.id.tv_rest_name)
        TextView tv_rest_name;

        @BindView(R.id.tv_rest_desc)
        TextView tv_rest_desc;


        public RecyclerViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
            tv_rest_name.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
            tv_rest_desc.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));

        }
    }
}
