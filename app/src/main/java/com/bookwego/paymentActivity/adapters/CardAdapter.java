package com.bookwego.paymentActivity.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.bookwego.R;
import com.bookwego.addCardActivity.AddCardActivity;
import com.bookwego.loginActivity.LogInActivity;
import com.bookwego.newandpermotions.MySpannableClass.MySpannable;
import com.bookwego.settingActivity.SettingsActivity;
import com.bookwego.utills.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;
    public String[] mColors = {
            "3F51B5", "FF9800", "009688", "673AB7"
    };


    public CardAdapter(Context context) {

        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.layout_cardlist, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {

        final String color = "#" + mColors[position];

        for (int c = 1; c < mColors.length; c++) {

            holder.cardView.setCardBackgroundColor(Color.parseColor(color));

            if (c == mColors.length) {
                c = 1;
            }
        }

        holder.delete_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertLogoutDialog();
            }
        });

        holder.edit_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, AddCardActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return 4;
    }

    private void AlertLogoutDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("BookWeGo");
        builder.setMessage("Are you sure you want to Delete?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.card_name)
        TextView card_name;

        @BindView(R.id.number)
        TextView number;

        @BindView(R.id.card_number)
        TextView card_number;

        @BindView(R.id.expiration)
        TextView expiration;

        @BindView(R.id.cvv)
        TextView cvv;

        @BindView(R.id.expiration_number)
        TextView expiration_number;

        @BindView(R.id.cvv_number)
        TextView cvv_number;

        @BindView(R.id.cardView)
        CardView cardView;

        @BindView(R.id.edit_Card)
        ImageView edit_Card;

        @BindView(R.id.delete_Card)
        ImageView delete_Card;

        public RecyclerViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
            name.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            card_name.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            number.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            card_number.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            expiration.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            cvv.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            expiration_number.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            cvv_number.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
        }
    }
}
