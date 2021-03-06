package com.example.pshop;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pshop.MyDialog;
import com.example.pshop.R;
import com.example.pshop.BaseFragment;
import com.example.pshop.Goods;

import java.util.ArrayList;
import java.util.Iterator;

public class car extends BaseFragment {

    private ListView cart_list;
    private TextView cart_money, cart_check_all, cart_del, cart_buy;
    private CartDBOpenHelper dbOpenHelper;
    private boolean flag = true;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_cart, null);
        cart_list = view.findViewById(R.id.cart_list);
        cart_money = view.findViewById(R.id.cart_money);
        cart_check_all = view.findViewById(R.id.cart_check_all);
        cart_del = view.findViewById(R.id.cart_del);
        cart_buy = view.findViewById(R.id.cart_buy);
        initListener();
        cart_money.performClick();
        return view;
    }

    public void initData(){

        ArrayList<CartGoods> cartGoods = listAllCartGoods();
//        cartGoods.add(new CartGoods(R.drawable.ipad,"IPad 2018","金色 128G",2399.00,1));
//        cartGoods.add(new CartGoods(R.drawable.bamai8,"宝马 i8","白蓝 2015",1999999.00,1));
//        cartGoods.add(new CartGoods(R.drawable.anmuxi,"伊利 安慕希","高端畅饮型 10瓶",69.00,3));
//        cartGoods.add(new CartGoods(R.drawable.xiaomi10,"小米 10","天之蓝 8+256",3999.00,2));
//        cartGoods.add(new CartGoods(R.drawable.huaweip40,"华为 P40 5G","白色 12+512",5999.00,5));
        cart_list.setAdapter(new CartListAdapter(mContext,cartGoods));

        super.initData();
    }

    private void initListener(){

        //全选单击事件
        cart_check_all.setOnClickListener(v -> {
            if (flag){
                dbOpenHelper = new CartDBOpenHelper(mContext);
                ContentValues values = new ContentValues();
                values.put("g_check", "true");
                //参数:表名，修改后的值，where条件，以及约束，如果不指定三四两个参数，会更改所有行
                dbOpenHelper.getWritableDatabase().update(
                        "cart",
                        values,
                        null,
                        null);
                dbOpenHelper.close();
                flag = false;
            }else {
                dbOpenHelper = new CartDBOpenHelper(mContext);
                ContentValues values = new ContentValues();
                values.put("g_check", "false");
                //参数:表名，修改后的值，where条件，以及约束，如果不指定三四两个参数，会更改所有行
                dbOpenHelper.getWritableDatabase().update(
                        "cart",
                        values,
                        null,
                        null);
                dbOpenHelper.close();
                flag = true;
            }
            initData();
            Toast.makeText(mContext, "全选！！！", Toast.LENGTH_SHORT).show();
        });

        //合计单击事件
        cart_money.setOnClickListener(v -> {
            double money = 0.0;
            ArrayList<CartGoods> goods = listAllCartGoods();
            for (CartGoods g : goods){
                if ("true".equals(g.getC_checked())){
                    money += g.getC_num() * g.getG_price();
                }
            }
            cart_money.setText("合计:￥" + money);
        });

        //删除单击事件
        cart_del.setOnClickListener(v -> {
            MyDialog myDialog = new MyDialog(mContext, R.style.MyDialog);
            myDialog.setTitle("提示")
                    .setMessage("确认删除该订单吗？")
                    .setCancel("取消", dialog -> dialog.dismiss() )
                    .setConfirm("确认", dialog -> {
                        ArrayList<CartGoods> goods = listAllCartGoods();
                        for (CartGoods g : goods){
                            if ("true".equals(g.getC_checked())){
                                delCartGoods(g.getG_id());
                            }
                        }
                        initData();
                        Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    })
                    .show();
        });

        //去结算单击事件
        cart_buy.setOnClickListener(v -> {
            double money = 0.0;
            boolean isEmp = false;
            ArrayList<CartGoods> goods = listAllCartGoods();
            for (CartGoods g : goods){
                if ("true".equals(g.getC_checked())){
                    isEmp = true;
                }
            }
            if (!isEmp){
                Toast.makeText(mContext, "您没有选中任何商品哦~", Toast.LENGTH_SHORT).show();
            }else {
                for (CartGoods g : goods){
                    if ("true".equals(g.getC_checked())){
                        money += g.getC_num() * g.getG_price();
                    }
                }
                Intent intent = new Intent();
                intent.putExtra("money",money);
                startActivity(intent);
            }
            /**
             * R.anim.slide_in_right:新的Activity进入时的动画，这里是指OtherActivity进入时的动画
             * R.anim.slide_out_left：旧的Activity出去时的动画，这里是指this进入时的动画
             */
            //getActivity().overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            //getActivity().overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            //getActivity().finish();
        });
    }

    //删除购物车中指定商品
    private void delCartGoods(int g_id){
        dbOpenHelper = new CartDBOpenHelper(mContext);
        //参数依次是表名，以及where条件与约束
        dbOpenHelper.getWritableDatabase().delete(
                "cart",
                "g_id=?",
                new String[]{String.valueOf(g_id)});
        dbOpenHelper.close();
    }

    //查询数据库中所有商品
    private ArrayList<CartGoods> listAllCartGoods(){
        dbOpenHelper = new CartDBOpenHelper(mContext);
        Cursor cursor = dbOpenHelper.getReadableDatabase().query(
                "cart",null,null,null,null,null,null
        );
        ArrayList<CartGoods> cartGoods = new ArrayList<>();
        //cart(cart_id,g_id,g_photo,g_name,g_type,g_price,g_num)
        while (cursor.moveToNext()){
            int g_id = Integer.parseInt(cursor.getString(1));
            int g_photo = Integer.parseInt(cursor.getString(2));
            String g_name = cursor.getString(3);
            String g_type = cursor.getString(4);
            double g_price = Double.parseDouble(cursor.getString(5));
            int c_num = Integer.parseInt(cursor.getString(6));
            String c_check = cursor.getString(7);
            cartGoods.add(new CartGoods(g_id,g_photo,g_name,g_type,g_price,c_num,c_check));
        }
        cursor.close();
        dbOpenHelper.close();
        return cartGoods;
    }
}