package com.jh.momoswipecard;

import android.content.Context;
import android.util.TypedValue;

/**
 * Email: 1004260403@qq.com
 * Created by jinhui on 2019/1/15.
 */
public class ViewConfig {

    // 屏幕上最多同时显示几个Item
    public static int MAX_SHOW_COUNT;
    // 每一级Scale相差0.05f，translationY相差7dp左右
    public static float SCALE_GAP;
    public static int TRANS_Y_GAP;

    public static void initConfig(Context context) {
        MAX_SHOW_COUNT = 4;
        SCALE_GAP = 0.05f;  //缩放比例
        TRANS_Y_GAP = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                15,
                context.getResources().getDisplayMetrics());  //偏移
    }
}
