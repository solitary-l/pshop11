package com.example.pshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pshop.CartDBOpenHelper;

import java.util.ArrayList;

public class GoodsListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<Goods> goodsList;
    private CartDBOpenHelper dbOpenHelper;
    private homeDBOpenHelper homeDBOpenHelper;


    public GoodsListAdapter(Context mContext, ArrayList<Goods> goodsList) {
        this.mContext = mContext;
        this.goodsList = goodsList;
    }

    @Override
    public int getCount() {
        return goodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder{
        ImageView goods_photo;
        TextView goods_name;
        TextView goods_price;
        TextView goods_sales;
        TextView goods_shop;
        ImageView goods_buy;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;


        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.goods_item, parent, false);
            holder = new ViewHolder();
            holder.goods_photo = convertView.findViewById(R.id.goods_item_photo);
            holder.goods_name = convertView.findViewById(R.id.goods_item_name);
            holder.goods_price = convertView.findViewById(R.id.goods_item_price);
            holder.goods_sales = convertView.findViewById(R.id.goods_item_sales);
            holder.goods_shop = convertView.findViewById(R.id.goods_item_shop);
            holder.goods_buy = convertView.findViewById(R.id.goods_item_buy);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.goods_photo.setBackgroundResource(goodsList.get(position).getG_photo());
        holder.goods_name.setText(goodsList.get(position).getG_name());
        String price = goodsList.get(position).getG_price() + "";
        holder.goods_price.setText(price);
        String sales = goodsList.get(position).getG_sales() + "???+????????? 99%??????";
        holder.goods_sales.setText(sales);
        holder.goods_shop.setText(goodsList.get(position).getG_shop());

        holder.goods_buy.setOnClickListener(v ->{
            long i = -1;
            //???????????????
            dbOpenHelper = new CartDBOpenHelper(mContext);
            homeDBOpenHelper=new homeDBOpenHelper(mContext);
            ContentValues values1 = new ContentValues();
            //cart(g_id,g_photo,g_name,g_type,g_price,g_num,g_check)
            values1.put("home_id",String.valueOf(goodsList.get(position).getG_id()));
            values1.put("h_photo",String.valueOf(goodsList.get(position).getG_photo()));
            values1.put("h_name",goodsList.get(position).getG_name());
            values1.put("h_type",goodsList.get(position).getG_type());
            values1.put("h_price",String.valueOf(goodsList.get(position).getG_price()));
            values1.put("h_num","1");
            values1.put("h_check","false");
            homeDBOpenHelper.getWritableDatabase().insert("home", null, values1);
            //????????????
            int goods_num = isExist(goodsList.get(position).getG_id());
            if (goods_num == 0){
                //???????????????????????????????????????
                //Toast.makeText(mContext,"????????????????????????????????????", Toast.LENGTH_SHORT).show();
                ContentValues values = new ContentValues();
                //cart(g_id,g_photo,g_name,g_type,g_price,g_num,g_check)
                values.put("g_id",String.valueOf(goodsList.get(position).getG_id()));
                values.put("g_photo",String.valueOf(goodsList.get(position).getG_photo()));
                values.put("g_name",goodsList.get(position).getG_name());
                values.put("g_type",goodsList.get(position).getG_type());
                values.put("g_price",String.valueOf(goodsList.get(position).getG_price()));
                values.put("g_num","1");
                values.put("g_check","false");
                i = dbOpenHelper.getWritableDatabase().insert("cart", null, values);

            }else {
                //????????????????????????????????????
                //Toast.makeText(mContext,"???????????????????????????????????????", Toast.LENGTH_SHORT).show();
                ContentValues values = new ContentValues();
                values.put("g_num",goods_num + 1);
                //??????:???????????????????????????where??????????????????????????????????????????????????????????????????????????????
                i = dbOpenHelper.getWritableDatabase().update(
                        "cart",
                        values,
                        "g_id=?",
                        new String[]{String.valueOf(goodsList.get(position).getG_id())});
            }
            if (i != -1)
                Toast.makeText(mContext, goodsList.get(position).getG_name() + "????????????????????????", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(mContext, "????????????????????????", Toast.LENGTH_SHORT).show();
            dbOpenHelper.close();
        });
        return convertView;
    }

    //????????????id????????????,??????????????????????????????????????????0
    private int isExist(int g_id){
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
        boolean idIsExist = cursor.moveToFirst();
        if (idIsExist){
            String str = cursor.getString(0);
            cursor.close();
            dbOpenHelper.close();
            //Toast.makeText(mContext,"???????????????????????????????????????" + str, Toast.LENGTH_SHORT).show();
            return Integer.parseInt(str);
        }
        //Toast.makeText(mContext,"????????????????????????????????????", Toast.LENGTH_SHORT).show();
        cursor.close();
        dbOpenHelper.close();
        return 0;
    }
}
