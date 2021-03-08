package com.iqb.player.adapter;


import android.annotation.SuppressLint;

import com.iqb.api.adapter.BaseRecycleAdapter;
import com.iqb.api.adapter.BaseViewHolder;
import com.iqb.api.base.app.ApiApplication;
import com.iqb.api.utils.ConvertUtils;
import com.iqb.api.utils.ToastUtils;
import com.iqb.player.R;
import com.iqb.src.widget.IQBPlayerImageView;
import com.iqb.src.widget.IQBPlayerScrollTextView;
import com.iqb.src.widget.IQBTextView;

import java.util.List;

public class LiveContentMsgAdapter extends BaseRecycleAdapter<String> {
    public LiveContentMsgAdapter(List<String> data) {
        super(data);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.class_live_msg_item;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position) {
        holder.getView(R.id.live_msg_tv).setFocusable(true);
        holder.getView(R.id.live_msg_tv).setFocusableInTouchMode(true);
        holder.getView(R.id.live_msg_tv).requestFocus();
    }


    public void addString(String path) {
        if (getData().size() >= 1) {
            if (getData().get(getData().size() - 1).equals(path)) {
                ToastUtils.showShort("等待教师反馈，请稍后再试！");
                return;
            }
        }
        if (getData().size() == 0 && path.equals("举手过于频繁，请三十秒后再试")) {
            ToastUtils.showShort("等待教师反馈，请稍后再试！");
            return;
        }
        getData().add(path);
        notifyItemChanged(getData().size() - 1);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void bindData(BaseViewHolder holder, String data) {
        ((IQBTextView) holder.getView(R.id.live_msg_tv)).setText(data);
        if (data.contains("成功")) {
            ((IQBPlayerImageView) holder.getView(R.id.live_msg_icon)).setImageDrawable(ApiApplication.getApplication().getResources().getDrawable(R.drawable.live_content_succeed));
        } else {
            ((IQBPlayerImageView) holder.getView(R.id.live_msg_icon)).setImageDrawable(ApiApplication.getApplication().getResources().getDrawable(R.drawable.live_content_faild));
        }
        holder.getView(R.id.live_msg_tv).setFocusable(true); // 获取焦点
        holder.getView(R.id.live_msg_tv).setFocusableInTouchMode(true);
        holder.getView(R.id.live_msg_tv).requestFocus();
    }

    public void clearData() {
        getData().clear();
        notifyDataSetChanged();
    }
    @Override
    public Object getData(int position) {
        return getData().get(position);
    }

}
