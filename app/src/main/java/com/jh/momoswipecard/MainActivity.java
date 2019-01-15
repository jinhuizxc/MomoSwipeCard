package com.jh.momoswipecard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.ImageView;

import com.jh.momoswipecard.adapter.UniversalAdapter;
import com.jh.momoswipecard.adapter.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 *  仿陌陌，滑动选择Card 评分:
 * 利用RecyclerView自定义layoutmanager实现仿陌陌滑动选择图片
 * https://download.csdn.net/download/liujiayut800/9884160
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UniversalAdapter<SwipeCardBean> adapter;
    private List<SwipeCardBean> list = new ArrayList<>();
    private ViewHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        recyclerView = findViewById(R.id.recyclerView);
//        // 初始化数据
//        list = SwipeCardBean.initData();
//        initView();

        recyclerView = new RecyclerView(this);
        list =  SwipeCardBean.initData();
        setContentView(recyclerView);
        initView();

    }

    private void initView() {
        recyclerView.setLayoutManager(new SwipeViewLayoutManager());
        adapter = new UniversalAdapter<SwipeCardBean>(this, list, R.layout.item_swipe_card) {
            @Override
            protected void convert(ViewHolder holder, SwipeCardBean swipeCardBean) {
                Picasso.with(MainActivity.this).load(swipeCardBean.getResId()).into((ImageView) holder.getView(R.id.iv));
            }
        };

        recyclerView.setAdapter(adapter);
        ViewConfig.initConfig(this);

        SwipeViewCallback callback = new SwipeViewCallback(0, 0, adapter, list, recyclerView);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
