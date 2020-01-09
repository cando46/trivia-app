package com.mentalsegment.triviaapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mentalsegment.triviaapp.ItemModel;
import com.mentalsegment.triviaapp.R;

import java.util.ArrayList;
import java.util.zip.CheckedOutputStream;

public class ShopItemAdapter extends RecyclerView.Adapter<ShopItemAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<ItemModel> mList;

    public ShopItemAdapter(Context context, ArrayList<ItemModel> list){
        mContext=context;
        mList=list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view=layoutInflater.inflate(R.layout.shop_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ItemModel Imodel=mList.get(position);
        ImageView image=holder.itemImage;
        TextView name=holder.itemName;
        TextView price=holder.itemPrice;

        image.setImageResource(Imodel.getImage());
        name.setText(Imodel.getName());
        price.setText(Imodel.getPrice());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage;
        TextView itemName;
        TextView itemPrice;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage=itemView.findViewById(R.id.imv_shop_itemImage);
            itemName=itemView.findViewById(R.id.tv_shop_itemName);
            itemPrice=itemView.findViewById(R.id.tv_shop_itemPrice);


        }
    }
}
