package com.jh.momoswipecard;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.jh.momoswipecard.adapter.UniversalAdapter;

import java.util.List;

/**
 * Email: 1004260403@qq.com
 * Created by jinhui on 2019/1/15.
 */
public class SwipeViewCallback extends ItemTouchHelper.SimpleCallback {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private List mDatas;

    /**
     *
     * 设置支持的滑动方向
     * @param i
     * @param i1
     * @param adapter
     * @param datas
     * @param recyclerView
     */
    public SwipeViewCallback(int i, int i1, UniversalAdapter<SwipeCardBean> adapter, List<SwipeCardBean> datas, RecyclerView recyclerView) {
        super(0, ItemTouchHelper.DOWN | ItemTouchHelper.UP |
                ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT);
        this.recyclerView = recyclerView;
        mAdapter = adapter;
        mDatas = datas;
    }

    /**
     * ItemTouchHelper.Callback与ItemTouchHelper.SimpleCallback
     * 都重写下面这个方法,使卡片式滑动，
     * 需要调用makeMovementFlags(getDragDirs(recyclerView, viewHolder),
     *                 getSwipeDirs(recyclerView, viewHolder));
     *
     */
//    @Override
//    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
//        return 0;
//    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(getDragDirs(recyclerView, viewHolder),
                getSwipeDirs(recyclerView, viewHolder));
    }


    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    /**
     * 结束滑动时调用
     *
     * @param viewHolder
     * @param i
     */
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Object object = mDatas.remove(viewHolder.getLayoutPosition());
        mDatas.add(0, object);
        mAdapter.notifyItemMoved(viewHolder.getLayoutPosition(), 0);
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        double maxDistance = recyclerView.getWidth()* 0.5f;
        double distancd = Math.sqrt(dX * dX + dY * dY);
        //缩放比例
        double fraction = distancd / maxDistance;
        if (fraction > 1) {
            fraction = 1;
        }
        int itemCount = recyclerView.getChildCount();
        for (int i = 0; i < itemCount; i++) {
            View view = recyclerView.getChildAt(i);
            int lv = itemCount - i - 1;
            if (lv >= 0) {
                if (lv < ViewConfig.MAX_SHOW_COUNT - 1) {
                    view.setTranslationY((float) (ViewConfig.TRANS_Y_GAP * lv - fraction * ViewConfig.TRANS_Y_GAP));
                    view.setScaleX((float) (1 - ViewConfig.SCALE_GAP * lv + fraction * ViewConfig.SCALE_GAP));
                    view.setScaleY((float) (1 - ViewConfig.SCALE_GAP * lv + fraction * ViewConfig.SCALE_GAP));
                } else {//最后一层
                    if (lv == ViewConfig.MAX_SHOW_COUNT - 1) {
                        view.setTranslationY((float) (ViewConfig.TRANS_Y_GAP * (lv - 1)));
                        view.setScaleX((float) (1 - ViewConfig.SCALE_GAP * (lv - 1)));
                        view.setScaleY((float) (1 - ViewConfig.SCALE_GAP * (lv - 1)));
                    }
                }
            }
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public long getAnimationDuration(@NonNull RecyclerView recyclerView, int animationType, float animateDx, float animateDy) {
//        return super.getAnimationDuration(recyclerView, animationType, animateDx, animateDy);
        return 100;
    }

    @Override
    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
//        return super.getSwipeThreshold(viewHolder);
        return 0.5f;
    }
}
