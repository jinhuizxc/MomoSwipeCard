package com.jh.momoswipecard.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Email: 1004260403@qq.com
 * Created by jinhui on 2019/1/15.
 */
public abstract class UniversalAdapter <T> extends RecyclerView.Adapter<ViewHolder> {

    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected ViewGroup viewGroup;
    private OnItemClickListener mOnItemClickListener;

    public UniversalAdapter setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
        return this;
    }

    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    // 构造方法
    public UniversalAdapter(Context context, List<T> datas, int layoutId){
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mLayoutId = layoutId;
        this.mDatas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder viewHolder = ViewHolder.get(this.mContext, null, viewGroup, this.mLayoutId);
        if (null == this.viewGroup){
            this.viewGroup = viewGroup;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        this.setListener(position, holder);
        this.convert(holder, this.mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return this.mDatas != null ? this.mDatas.size() : 0;
    }

    protected abstract void convert(ViewHolder holder, T t);


    protected boolean isEnabled(int viewType) {
        return true;
    }

    /** @deprecated */
    @Deprecated
    protected void setListener(final ViewGroup parent, final ViewHolder viewHolder, int viewType) {
        if(this.isEnabled(viewType)) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(UniversalAdapter.this.mOnItemClickListener != null) {
                        int position = UniversalAdapter.this.getPosition(viewHolder);
                        UniversalAdapter.this.mOnItemClickListener.onItemClick(parent, v, UniversalAdapter.this.mDatas.get(position), position);
                    }

                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    if(UniversalAdapter.this.mOnItemClickListener != null) {
                        int position = UniversalAdapter.this.getPosition(viewHolder);
                        return UniversalAdapter.this.mOnItemClickListener.onItemLongClick(parent, v, UniversalAdapter.this.mDatas.get(position), position);
                    } else {
                        return false;
                    }
                }
            });
        }
    }
    private void setListener(final int position, final ViewHolder holder) {
        if (this.isEnabled(this.getItemViewType(position))){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (UniversalAdapter.this.mOnItemClickListener != null){
                        UniversalAdapter.this.mOnItemClickListener.onItemClick(
                                UniversalAdapter.this.viewGroup,
                                v,
                                UniversalAdapter.this.mDatas.get(position),
                                position);
                    }
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(UniversalAdapter.this.mOnItemClickListener != null) {
                        int position = UniversalAdapter.this.getPosition(holder);
                        return UniversalAdapter.this.mOnItemClickListener.onItemLongClick(
                                UniversalAdapter.this.viewGroup, v, UniversalAdapter.this.mDatas.get(position), position);
                    }else {
                        return false;
                    }
                }
            });
        }
    }

    private int getPosition(ViewHolder holder) {
        return holder.getAdapterPosition();
    }

    public void setmDatas(List<T> list){
        if (this.mDatas != null){
            if (null != list){
                ArrayList temp = new ArrayList();
                temp.addAll(list);
                this.mDatas.clear();
                this.mDatas.addAll(temp);
            }else {
                this.mDatas.clear();
            }
        }else {
            this.mDatas = list;
        }
        this.notifyDataSetChanged();
    }

    public void remove(int position){
        if (null != this.mDatas && this.mDatas.size() > position && position > -1){
            this.mDatas.remove(position);
            this.notifyDataSetChanged();
        }
    }

    public void addDatas(List<T> list){
        if (null != list){
            ArrayList temp = new ArrayList();
            temp.addAll(list);
            if (this.mDatas != null){
                this.mDatas.addAll(temp);
            }else {
                this.mDatas = temp;
            }
            this.notifyDataSetChanged();
        }
    }

    public List<T> getDatas() {
        return mDatas;
    }

    public T getItem(int position) {
        return position > -1 && null != this.mDatas
                && this.mDatas.size() > position ? this.mDatas.get(position) : null;
    }
}
