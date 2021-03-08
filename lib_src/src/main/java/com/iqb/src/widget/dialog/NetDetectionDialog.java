package com.iqb.src.widget.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.iqb.src.R;
import com.iqb.src.widget.IQBLoadingPointView;
import com.iqb.src.widget.IQBTextView;


public class NetDetectionDialog extends Dialog {


    private final ImageView consentImg;
    @SuppressLint("HandlerLeak")
    private Handler iqbHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                consentImg.setImageDrawable(getContext().getResources().getDrawable(R.drawable.home_network_pop_bg_two));
                iqbLoadingPointView.setVisibility(View.GONE);
                idFinishView.setVisibility(View.VISIBLE);
                netContentTv.setText("网络状况良好，请放心上课");
            } else {
                consentImg.setImageDrawable(getContext().getResources().getDrawable(R.drawable.home_network_pop_bg_three));
                idFinishView.setVisibility(View.VISIBLE);
                iqbLoadingPointView.setVisibility(View.GONE);
                netContentTv.setText("网络状况较差，可能无法保证上课效果，请更换网络");
            }
        }
    };
    private final IQBTextView idFinishView;
    private final IQBLoadingPointView iqbLoadingPointView;
    private final TextView netContentTv;

    public NetDetectionDialog(Context context) {
        super(context,R.style.ProgressDialogStyle);
        setContentView(R.layout.dialog_net_detection);
        ImageView disagreeTv = findViewById(R.id.net_detection_back_bt);
        idFinishView = findViewById(R.id.id_finish_view);
        iqbLoadingPointView = findViewById(R.id.id_loading_point_view);
        netContentTv = findViewById(R.id.net_content_tv);
        consentImg = findViewById(R.id.net_detection_content_img);
        assert disagreeTv != null;
        disagreeTv.setOnClickListener(v -> {
            NetDetectionDialog.this.hide();
            NetDetectionDialog.this.dismiss();
        });
        idFinishView.setOnClickListener(v -> {
            NetDetectionDialog.this.hide();
            NetDetectionDialog.this.dismiss();
        });
    }


    @Override
    public void show() {
        super.show();
        Window window = getWindow();
        if (window == null) {
            return;
        }
        window.setWindowAnimations(R.style.BottomDialogAnimation);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        setCanceledOnTouchOutside(false); // 点击屏幕Dialog以外的地方是否消失
    }

    public void setNetIsOk(boolean b) {
        if (b) {
            iqbHandler.sendEmptyMessage(0);
        } else {
            iqbHandler.sendEmptyMessage(1);
        }
    }
}
