package com.example.pshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<CartGoods> cartGoods;
    private CartDBOpenHelper dbOpenHelper;

    public CartListAdapter(Context mContext, ArrayList<CartGoods> cartGoods) {
        this.mContext = mContext;
        this.cartGoods = cartGoods;
    }

    @Override
    public int getCount() {
        return cartGoods.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        ImageView cart_photo;
        TextView cart_name;
        TextView cart_type;
        TextView cart_price;
        TextView cart_num;
        ImageView cart_jian, cart_jia;
        CheckBox cart_check;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.cart_item, parent, false);
            holder = new ViewHolder();
            holder.cart_photo = convertView.findViewById(R.id.cart_photo);
            holder.cart_name = convertView.findViewById(R.id.cart_name);
            holder.cart_type = convertView.findViewById(R.id.cart_type);
            holder.cart_price = convertView.findViewById(R.id.cart_price);
            holder.cart_num = convertView.findViewById(R.id.cart_num);
            holder.cart_jian = convertView.findViewById(R.id.cart_jian);
            holder.cart_jia = convertView.findViewById(R.id.cart_jia);
            holder.cart_check = convertView.findViewById(R.id.cart_check);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.cart_photo.setBackgroundResource(cartGoods.get(position).getG_photo());
        holder.cart_name.setText(cartGoods.get(position).getG_name());
        holder.cart_type.setText(cartGoods.get(position).getG_type());
        holder.cart_price.setText(String.valueOf(cartGoods.get(position).getG_price()));
        holder.cart_num.setText(String.valueOf(cartGoods.get(position).getC_num()));
        if("true".equals(cartGoods.get(position).getC_checked())){
            holder.cart_check.setChecked(true);
        }

        holder.cart_jia.setOnClickListener(v -> {
            int g_id = cartGoods.get(position).getG_id();
            int num = getGoodsNum(g_id);
            holder.cart_num.setText(String.valueOf(num + 1));
            uptGoodsNum(g_id,num + 1);
        });

        holder.cart_jian.setOnClickListener(v -> {
            int g_id = cartGoods.get(position).getG_id();
            int num = getGoodsNum(g_id);
            if (num == 1){
                //delCartGoods(g_id);
                //notifyDataSetChanged();
                Toast.makeText(mContext, "?????????????????????????????????~", Toast.LENGTH_SHORT).show();
            }else {
                holder.cart_num.setText(String.valueOf(num - 1));
                uptGoodsNum(g_id,num - 1);
            }
        });

        holder.cart_check.setOnCheckedChangeListener((buttonView, isChecked) -> {
            int g_id = cartGoods.get(position).getG_id();
            if (holder.cart_check.isChecked()){
                uptGoodsChecked(g_id,"true");
                //Toast.makeText(mContext, "??????true", Toast.LENGTH_SHORT).show();
            }else {
                //Toast.makeText(mContext, "??????false", Toast.LENGTH_SHORT).show();
                uptGoodsChecked(g_id,"false");
            }
        });

        return convertView;
    }

    //????????????????????????
    private int getGoodsNum(int g_id) {
        dbOpenHelper = new CartDBOpenHelper(mContext);
        //??????????????????where???????????????where???????????????????????????????????????group by????????????????????????
        Cursor cursor = dbOpenHelper.getReadableDatabase().query(
                "cart",
                new String[]{"g_num"},
                "g_id=?",
                new String[]{String.valueOf(g_id)},
                null,
                null,
                null);
        cursor.moveToFirst();
        String str = cursor.getString(0);
        cursor.close();
        dbOpenHelper.close();
        return Integer.parseInt(str);
    }

    //??????????????????
    private void uptGoodsNum(int g_id, int g_num) {
        dbOpenHelper = new CartDBOpenHelper(mContext);
        ContentValues values = new ContentValues();
        values.put("g_num", g_num);
        //??????:???????????????????????????where??????????????????????????????????????????????????????????????????????????????
        dbOpenHelper.getWritableDatabase().update(
                "cart",
                values,
                "g_id=?",
                new String[]{String.valueOf(g_id)});
        dbOpenHelper.close();
    }

    //???????????????????????????
    private void uptGoodsChecked(int g_id, String isChecked){
        dbOpenHelper = new CartDBOpenHelper(mContext);
        ContentValues values = new ContentValues();
        values.put("g_check", isChecked);
        //??????:???????????????????????????where??????????????????????????????????????????????????????????????????????????????
        dbOpenHelper.getWritableDatabase().update(
                "cart",
                values,
                "g_id=?",
                new String[]{String.valueOf(g_id)});
        dbOpenHelper.close();
    }
}
