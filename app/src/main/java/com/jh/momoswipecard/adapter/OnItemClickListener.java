package com.jh.momoswipecard.adapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Email: 1004260403@qq.com
 * Created by jinhui on 2019/1/15.
 */
public interface OnItemClickListener<T> {

    void onItemClick(ViewGroup var1, View var2, T var3, int var4);

    boolean onItemLongClick(ViewGroup var1, View var2, T var3, int var4);

}
