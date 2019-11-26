package com.bookwego.menuActivity.adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Vibrator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bookwego.R;
import com.bookwego.menuActivity.MenuActivity;
import com.bookwego.menuActivity.interfaces.IPMenuActivity;
import com.bookwego.menuActivity.model.CountModel;
import com.bookwego.menuActivity.responseModel.MenuItemsModel;
import com.bookwego.utills.CommonMethods;
import com.bookwego.utills.Constant;
import com.bookwego.utills.Utility;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuListsAdapter extends RecyclerView.Adapter<MenuListsAdapter.RecyclerViewHolder> {

    Context context;
    LayoutInflater inflater;
    List<MenuItemsModel.Datum> data;
    IPMenuActivity ipMenuActivity;
    String restaurantsId;
    ;
    private String item_id, item_name, item_price;
    private int[] counter = new int[10];
    int count;

    public MenuListsAdapter(Context context, List<MenuItemsModel.Datum> data, String restaurantsId, IPMenuActivity ipMenuActivity
    ) {
        this.context = context;
        this.data = data;
        this.restaurantsId = restaurantsId;
        this.ipMenuActivity = ipMenuActivity;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.layout_menu_list, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {

        item_id = data.get(position).getItemId();
        item_name = data.get(position).getName();
        item_price = data.get(position).getPrice();

        Glide.with(context).load(Constant.IMAGE_URL + data.get(position).getImage())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.no_image_placeholder).into(holder.img_menu_item);
        holder.tv_price.setText("\u20B9" + "" + data.get(position).getPrice());
        holder.tv_dishName.setText(CommonMethods.upperCase(data.get(position).getName()));
        if (data.get(position).getDietCat().matches("1")) {
            holder.tv_dishName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.veg, 0, 0, 0);

        } else {
            holder.tv_dishName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.non_veg, 0, 0, 0);
        }

        holder.img_item_max.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count = Integer.parseInt(String.valueOf(holder.tv_item_Count.getText()));
                count++;
                holder.tv_item_Count.setText("" + count);
                //ToDo: working fine
               /* counter[position] += 1;
                holder.tv_item_Count.setText(counter[position] + "");*/
            }
        });

        holder.img_item_min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                count = Integer.parseInt(String.valueOf(holder.tv_item_Count.getText()));
                if (count == 0) {

                } else {
                    count -= 1;
                    holder.tv_item_Count.setText("" + count);
                }
                //ToDo: working fine
               /* if(counter[position] == 0){

                }else{
                    counter[position] -= 1;
                    holder.tv_item_Count.setText(counter[position] + "");
                }*/

            }
        });

        holder.btn_addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utility.isNetworkConnectionAvailable(context)) {

                    ipMenuActivity.addItemIntoCart(restaurantsId, item_id, item_name, String.valueOf(count), item_price);
                } else {

                }
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_dishName)
        TextView tv_dishName;

        @BindView(R.id.tv_price)
        TextView tv_price;

        @BindView(R.id.tv_item_Count)
        TextView tv_item_Count;

        @BindView(R.id.img_menu_item)
        ImageView img_menu_item;

        @BindView(R.id.img_item_min)
        ImageView img_item_min;

        @BindView(R.id.img_item_max)
        ImageView img_item_max;

        @BindView(R.id.btn_addtocart)
        Button btn_addtocart;

        public RecyclerViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                img_menu_item.setClipToOutline(true);
            }
            tv_price.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            btn_addtocart.setTypeface(Utility.typeFaceForProximaNovaRegulerText(context));
            tv_dishName.setTypeface(Utility.typeFaceForProximaNovaSemiboldText(context));

        }
    }

}
