package com.iqb.src.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.iqb.src.R;
import com.wang.avi.AVLoadingIndicatorView;


public class MProgressDialog extends Dialog {
    private Context mContext;
    private String loadingTips;
    private AVLoadingIndicatorView avi;


    public MProgressDialog(Context context) {
        this(context, R.style.ProgressDialogStyle, null);
    }

    public MProgressDialog(Context context, String loadingTips) {
        this(context, R.style.ProgressDialogStyle, loadingTips);
        this.loadingTips = loadingTips;
    }

    private MProgressDialog(Context context, int theme, String loadingTips) {
        super(context, theme);
        this.mContext = context;
        this.loadingTips = loadingTips;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        avi = findViewById(R.id.avi);
        ViewGroup.LayoutParams layoutParams = avi.getLayoutParams();
        layoutParams.width = mContext.getResources().getDimensionPixelSize(R.dimen.x140);
        layoutParams.height = mContext.getResources().getDimensionPixelSize(R.dimen.x140);
        avi.setLayoutParams(layoutParams);
        avi.setPadding(mContext.getResources().getDimensionPixelSize(R.dimen.x20), mContext.getResources().getDimensionPixelSize(R.dimen.x20), mContext.getResources().getDimensionPixelSize(R.dimen.x20), mContext.getResources().getDimensionPixelSize(R.dimen.x20));
        avi.setIndicator("BallClipRotatePulseIndicator");
        avi.setIndicatorColor(mContext.getResources().getColor(R.color.white_color));
        avi.setBackgroundResource(R.drawable.base_shape_loading_dialog);
        init();
    }

    private void init() {
        setTitle("");
        setShowMessage(loadingTips);
        // 按返回键是否取消
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        // 设置居中
        Window window = getWindow();
        if (window != null) {
            window.getAttributes().gravity = Gravity.CENTER;
            WindowManager.LayoutParams lp = window.getAttributes();
            // 设置背景层透明度
            lp.dimAmount = 0.0f;
            window.setAttributes(lp);
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
    }

    @Override
    public void show() {
        super.show();
        avi.show();
    }

    public void show(String showMessage) {
        showLoading();
    }

    private void setShowMessage(String showMessage) {
    }

    private void showLoading() {
        startAnim();
    }

    private void startAnim() {
        avi.show();  //显示
        // or avi.smoothToShow();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        stopAnim();
    }

    private void stopAnim() {
        avi.hide();   //隐藏
        // or avi.smoothToHide();
    }


}