package com.bookwego.faqActivity.adapter;

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

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;

    public FAQAdapter(Context context) {

        this.context = context;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.layout_faq, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {

        holder.img_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.img_down.setVisibility(View.INVISIBLE);
                holder.img_up.setVisibility(View.VISIBLE);
                holder.view_faq.setVisibility(View.VISIBLE);
                holder.tv_answer.setVisibility(View.VISIBLE);
            }
        });

        holder.img_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.img_up.setVisibility(View.INVISIBLE);
                holder.img_down.setVisibility(View.VISIBLE);
                holder.view_faq.setVisibility(View.INVISIBLE);
                holder.tv_answer.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {

        return 4;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_bookwego)
        TextView tv_bookwego;

        @BindView(R.id.tv_answer)
        TextView tv_answer;

        @BindView(R.id.view_faq)
        View view_faq;

        @BindView(R.id.img_down)
        ImageView img_down;

        @BindView(R.id.img_up)
        ImageView img_up;


        public RecyclerViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
            tv_bookwego.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));
            tv_answer.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));

        }
    }


}
