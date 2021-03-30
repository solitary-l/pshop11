package com.example.pshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShopListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Shop> shopList;

    public ShopListAdapter(Context mContext, ArrayList<Shop> shopList) {
        this.mContext = mContext;
        this.shopList = shopList;
    }

    @Override
    public int getCount() {
        return shopList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.community_item, parent, false);
        ImageView shop_icon = view.findViewById(R.id.shop_icon);
        TextView shop_name = view.findViewById(R.id.shop_name);
        ImageView goods1_photo = view.findViewById(R.id.goods1);
        TextView goods1_name = view.findViewById(R.id.goods1_name);
        TextView goods1_price = view.findViewById(R.id.goods1_price);
        ImageView goods2_photo = view.findViewById(R.id.goods2);
        TextView goods2_name = view.findViewById(R.id.goods2_name);
        TextView goods2_price = view.findViewById(R.id.goods2_price);
        ImageView goods3_photo = view.findViewById(R.id.goods3);
        TextView goods3_name = view.findViewById(R.id.goods3_name);
        TextView goods3_price = view.findViewById(R.id.goods3_price);

        shop_icon.setBackgroundResource(shopList.get(position).getShop_icon());
        shop_name.setText(shopList.get(position).getShop_name());
        goods1_photo.setBackgroundResource(shopList.get(position).getGoods1().getG_photo());
        goods1_name.setText(shopList.get(position).getGoods1().getG_name());
        goods1_price.setText(String.valueOf(shopList.get(position).getGoods1().getG_price()));
        goods2_photo.setBackgroundResource(shopList.get(position).getGoods2().getG_photo());
        goods2_name.setText(shopList.get(position).getGoods2().getG_name());
        goods2_price.setText(String.valueOf(shopList.get(position).getGoods2().getG_price()));
        goods3_photo.setBackgroundResource(shopList.get(position).getGoods3().getG_photo());
        goods3_name.setText(shopList.get(position).getGoods3().getG_name());
        goods3_price.setText(String.valueOf(shopList.get(position).getGoods3().getG_price()));
        return view;
    }
}
