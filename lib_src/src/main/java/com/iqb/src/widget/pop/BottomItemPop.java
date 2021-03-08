package com.iqb.src.widget.pop;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iqb.src.R;
import com.iqb.src.widget.tools.BackgroundUtils;


public class BottomItemPop extends PopupWindow implements View.OnClickListener, PopupWindow.OnDismissListener {
    private Context mContext;
    private final TextView tvButtonOne;
    private final TextView tvButtonTwo;
    private final TextView tvCancel;


    public BottomItemPop(final Context context) {
        this.mContext = context;
        View contentView = LayoutInflater.from(context).inflate(R.layout.base_layout_pop_bottom, null);
        tvButtonOne = contentView.findViewById(R.id.tv_button_one);
        tvButtonTwo = contentView.findViewById(R.id.tv_button_two);
        tvCancel = contentView.findViewById(R.id.tv_cancel);
        tvButtonOne.setOnClickListener(this);
        tvButtonTwo.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        this.setContentView(contentView);
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setOnDismissListener(this);
        this.setOutsideTouchable(true);
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        this.setAnimationStyle(R.style.anim_pop_from_bottom);

    }

    public void setPopTopItemTv(String str){
        tvButtonOne.setText(str);
    }
    public void setPopDownItemTv(String str){
        tvButtonTwo.setText(str);
    }
    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        BackgroundUtils.backgroundAlpha(mContext, 0.6f);
        super.showAtLocation(parent, gravity, x, y);
    }

    @Override
    public void onDismiss() {
        BackgroundUtils.backgroundAlpha(mContext, 1);
        super.dismiss();
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.tv_button_one) {
            if (onTopItemClickListener != null) {
                onTopItemClickListener.onTopItemClick(v.getContext());
            }
            dismiss();
        } else if (id == R.id.tv_button_two) {
            if (onDownItemClickListener != null) {
                onDownItemClickListener.onDownItemClick(v.getContext());
            }
            dismiss();
        } else if (id == R.id.tv_cancel) {
            dismiss();
        }

    }

    public interface OnPopTopItemClickListener {
        void onTopItemClick(Context context);
    }

    public interface OnPopDownItemClickListener {
        void onDownItemClick(Context context);
    }

    private OnPopTopItemClickListener onTopItemClickListener;
    private OnPopDownItemClickListener onDownItemClickListener;

    public void setOnTopItemClickListener(OnPopTopItemClickListener listener) {
        this.onTopItemClickListener = listener;
    }

    public void setOnDownItemClickListener(OnPopDownItemClickListener listener) {
        this.onDownItemClickListener = listener;
    }
}
