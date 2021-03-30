package com.example.pshop;

public class Shop {
    private int shop_icon;
    private String shop_name;
    private Goods goods1, goods2, goods3;

    public Shop(int shop_icon, String shop_name, Goods goods1, Goods goods2, Goods goods3) {
        this.shop_icon = shop_icon;
        this.shop_name = shop_name;
        this.goods1 = goods1;
        this.goods2 = goods2;
        this.goods3 = goods3;
    }

    public int getShop_icon() {
        return shop_icon;
    }

    public String getShop_name() {
        return shop_name;
    }

    public Goods getGoods1() {
        return goods1;
    }

    public Goods getGoods2() {
        return goods2;
    }

    public Goods getGoods3() {
        return goods3;
    }
}
