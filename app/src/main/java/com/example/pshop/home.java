package com.example.pshop;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pshop.R;
import com.example.pshop.BaseFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class home extends BaseFragment  {

    private ListView lv_home;
    private ImageView ib_top;
    private TextView tv_search_home;
    private Button search;
    private homeDBOpenHelper mDBOpenHelper;



    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        lv_home = view.findViewById(R.id.lv_home);
        ib_top = view.findViewById(R.id.ib_top);
        tv_search_home = view.findViewById(R.id.tv_search_home); //搜索框
        search = view.findViewById(R.id.search);   //搜索按钮
        initListener();
        mDBOpenHelper = new homeDBOpenHelper(getActivity());

        return view;
    }

    public void initData(){
        super.initData();
//        List data =Arrays.asList("第1个", "第2个", "第3个", "第4个", "第5个", "第6个", "第7个", "第8个", "第9个", "第10个", "第11个"
//                , "第12个", "第13个", "第14个", "第15个", "第16个", "第17个");
//        ArrayAdapter adapter = new ArrayAdapter<String>(
//                mContext,
//                android.R.layout.simple_list_item_1,
//                data
//        );
//        lv_home.setAdapter(adapter);
        ArrayList<Goods> goods = new ArrayList<>();
        //int g_id, int g_photo, String g_name, String g_type, double g_price, int g_sales, String g_shop
        goods.add(new Goods(1,R.drawable.ipad,"Ipad 2018金","128G 金色",2999.00,9,"Apple产品自营旗舰店"));
        goods.add(new Goods(2,R.drawable.ipad2,"Ipad 2018银","32G 银色",2499.00,99,"Apple产品自营旗舰店"));
        goods.add(new Goods(3,R.drawable.ipad3,"Ipad Pro","11英寸",6599.00,5,"Apple产品自营旗舰店"));
        goods.add(new Goods(4,R.drawable.xiaomi10,"小米（MI）10","蓝色 8+256",3799.00,19,"小米自营旗舰店"));
        goods.add(new Goods(5,R.drawable.huaweip40,"华为P40 5G手机","白色 12+512",4488.00,18,"华为自营旗舰店"));
        goods.add(new Goods(6,R.drawable.anmuxi,"安慕希 高端畅饮型","10瓶装",59.90,12,"超市"));
        goods.add(new Goods(7,R.drawable.kouhong,"MAC 口红","牛血色",188.00,1,"化妆品专营店"));
        goods.add(new Goods(8,R.drawable.bamai8,"BMW i8跑车","创新敞篷版",9.9,99999999,"BMW旗舰店"));

        GoodsListAdapter adapter = new GoodsListAdapter(mContext, goods);
        lv_home.setAdapter(adapter);
    }

    private void initListener() {

        ib_top.setOnClickListener(v -> {
            //滑动回到顶部
            lv_home.smoothScrollToPosition(0);
        });

        search.setOnClickListener(v -> {

            ArrayList<Goods> goods = new ArrayList<>();
            goods.clear();
            try {
                String s1 = tv_search_home.getText().toString();
                SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();

                String ins = "select * from home where h_name like ? ";
                Cursor cursor = db.rawQuery(ins, new String[]{"%" + s1 + "%"});
                if (cursor.moveToFirst()) {
                    do {
                        int photo = cursor.getInt(cursor.getColumnIndex("h_photo"));
                        String name = cursor.getString(cursor.getColumnIndex("h_name"));
                        Double num = cursor.getDouble(cursor.getColumnIndex("h_num"));
                        String type = cursor.getString(cursor.getColumnIndex("h_type"));
                        String check = cursor.getString(cursor.getColumnIndex("h_check"));
                        double price = cursor.getDouble(cursor.getColumnIndex("h_price"));
                        Goods g1 = new Goods(photo, name, price, num, type, check);
                        goods.add(g1);
                    } while (cursor.moveToNext());
                }
                cursor.close();
                GoodsListAdapter adapter = new GoodsListAdapter(mContext, goods);
                lv_home.setAdapter(adapter);
            }
                catch(Exception e){
                    Toast.makeText(mContext.getApplicationContext(), "查询失败", Toast.LENGTH_LONG).show();
                }

        });

    }

}


