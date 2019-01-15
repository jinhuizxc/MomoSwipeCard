package com.jh.momoswipecard;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Email: 1004260403@qq.com
 * Created by jinhui on 2019/1/15.
 */
public class SwipeViewLayoutManager extends RecyclerView.LayoutManager {

    /*
     * 在linearlayoutManager中generateDefaultLayoutParams()方法默认返回WRAP_CONTENT
     * 在这里我们也返回WRAP_CONTENT
     */
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    /**
     * onLayoutChildren()方法负责item的布局，必须实现
     * @param recycler
     * @param state
     */
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        // detachAndScrapAttachedViews方法是RecyclerVIew的缓存机制
        detachAndScrapAttachedViews(recycler);
        int itemCount = getItemCount();
        int initPosition;
        // 当照片数不够最多显示数时，全部初始化
        if (itemCount < ViewConfig.MAX_SHOW_COUNT){
            initPosition = 0;
        }else {
            initPosition = itemCount - ViewConfig.MAX_SHOW_COUNT;
        }

        for (int position = initPosition; position < itemCount; position++) {
            View view = recycler.getViewForPosition(position);
            addView(view);
            // 绘制View，坐标0,0
            measureChild(view, 0, 0);
            // getDecoratedMeasuredWidth方法会计算分割线的宽度
            int widthSpace = getWidth() - getDecoratedMeasuredWidth(view);
            int heightSpace = getHeight() - getDecoratedMeasuredHeight(view);
            // 左上右下, //让item居中显示
            layoutDecorated(view,
                    widthSpace / 2,
                    heightSpace / 2,
                    getDecoratedMeasuredWidth(view) + widthSpace / 2,
                    getDecoratedMeasuredHeight(view) + heightSpace / 2);
            // 缩放等级
            int lv = itemCount - position - 1;
            // 越靠前View显示的越大
            if (lv > 0){
                view.setScaleX(1 - ViewConfig.SCALE_GAP * lv);
                view.setScaleY(1 - ViewConfig.SCALE_GAP * lv);
                // 判断是不是已经显示出来的最后一张，如果不是就将view向下偏移
                if (lv < ViewConfig.MAX_SHOW_COUNT - 1){
                    view.setTranslationY(ViewConfig.TRANS_Y_GAP * lv);
                }else {
                    view.setTranslationY(ViewConfig.TRANS_Y_GAP * (lv - 1));
                }
            }
        }
    }
}
