package com.jh.momoswipecard;

import java.util.ArrayList;
import java.util.List;

/**
 * Email: 1004260403@qq.com
 * Created by jinhui on 2019/1/15.
 */
public class SwipeCardBean {

    private int position;
    private int resId;
    private String name;

    public SwipeCardBean(int position, int resId, String name) {
        this.position = position;
        this.resId = resId;
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public SwipeCardBean setPosition(int position) {
        this.position = position;
        return this;
    }

    public int getResId() {
        return resId;
    }

    public SwipeCardBean setResId(int resId) {
        this.resId = resId;
        return this;
    }

    public String getName() {
        return name;
    }

    public SwipeCardBean setName(String name) {
        this.name = name;
        return this;
    }

    public static List<SwipeCardBean> initData(){
        List<SwipeCardBean> datas = new ArrayList<>();
        int i = 1;
        datas.add(new SwipeCardBean(i++, R.drawable.b1, "蜥蜴"));
        datas.add(new SwipeCardBean(i++, R.drawable.b2, "鱼"));
        datas.add(new SwipeCardBean(i++, R.drawable.b1, "蜥蜴"));
        datas.add(new SwipeCardBean(i++, R.drawable.b2, "鱼"));
        datas.add(new SwipeCardBean(i++, R.drawable.b1, "蜥蜴"));
        datas.add(new SwipeCardBean(i++, R.drawable.b2, "鱼"));
        datas.add(new SwipeCardBean(i++, R.drawable.b1, "蜥蜴"));
        datas.add(new SwipeCardBean(i++, R.drawable.b2,"鱼"));
        datas.add(new SwipeCardBean(i++, R.drawable.b1,"蜥蜴"));
        datas.add(new SwipeCardBean(i++, R.drawable.b2,"鱼"));
        datas.add(new SwipeCardBean(i++, R.drawable.b1,"蜥蜴"));
        return datas;
    }
}
