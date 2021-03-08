package com.iqb.api.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by Administrator on 2018/3/13.
 */

public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> implements View.OnClickListener {
    //Type
    private int TYPE_NORMAL = 1000;
    private int TYPE_HEADER = 1001;
    private int TYPE_FOOTER = 1002;
    // 数据源
    private List<T> data;
    private OnItemClickListener mItemClickListener;
    // 构造方法，传入
    public BaseRecycleAdapter(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    protected abstract int getLayoutId();


    @Override
    public int getItemCount() {
        int count = (data == null ? 0 : data.size());
        if (VIEW_FOOTER != null) {
            count++;
        }
        if (VIEW_HEADER != null) {
            count++;
        }
        return count;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("BaseRecycleAdapter", "viewType:" + viewType);
        if (viewType == TYPE_FOOTER) {
            return new BaseViewHolder(VIEW_FOOTER);
        } else if (viewType == TYPE_HEADER) {
            return new BaseViewHolder(VIEW_HEADER);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
            view.setOnClickListener(this);
            return new BaseViewHolder(view);
        }

    }



    @Override
    public final void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        // 子类实现数据绑定
        holder.itemView.setTag(position);
        if (!isHeaderView(position) && !isFooterView(position)) {
            if (haveHeaderView()) position--;
            bindData(holder, position);
            bindData(holder, data.get(position));
        }

    }

    @Override
    public void onClick(View v) {
        if (mItemClickListener != null) {
            mItemClickListener.onItemClick((Integer) v.getTag());
        }
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void addString(String path) {
        this.data.add((T) path);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    /**
     * 刷新数据
     */
    public void refresh(List data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }


    /**
     * 添加数据
     */
    public void addData(List<T> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 删除数据
     */
    public void remove(int position) {
        data.remove(position);
        notifyItemChanged(position);
    }


    /**
     * 绑定数据
     *
     * @param holder   具体的viewHolder
     * @param position 对应的索引
     */
    protected abstract void bindData(BaseViewHolder holder, int position);

    /**
     * 绑定的数据
     *
     * @param holder 具体的ViewHolder
     * @param data   具体的数据
     */
    protected abstract void bindData(BaseViewHolder holder, T data);

    /**
     *
     */

    public abstract Object getData(int position);


    private View VIEW_FOOTER;
    private View VIEW_HEADER;
    private RecyclerView mRecyclerView;

    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return TYPE_HEADER;
        } else if (isFooterView(position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try {
            if (mRecyclerView == null && null != recyclerView) {
                mRecyclerView = recyclerView;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addHeaderView(View headerView) {
        if (haveHeaderView()) {
            throw new IllegalStateException("hearview has already exists!");
        } else {
            //避免出现宽度自适应
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            headerView.setLayoutParams(params);
            VIEW_HEADER = headerView;
            notifyItemInserted(0);
        }

    }

    public void addFooterView(View footerView) {
        if (haveFooterView()) {
            throw new IllegalStateException("footerView has already exists!");
        } else {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            footerView.setLayoutParams(params);
            VIEW_FOOTER = footerView;
            notifyItemInserted(getItemCount() - 1);
        }
    }


    private boolean isHeaderView(int position) {
        return haveHeaderView() && position == 0;
    }

    private boolean haveHeaderView() {
        return VIEW_HEADER != null;
    }

    private boolean isFooterView(int position) {
        return haveFooterView() && position == getItemCount() - 1;
    }

    public boolean haveFooterView() {
        return VIEW_FOOTER != null;
    }

}
