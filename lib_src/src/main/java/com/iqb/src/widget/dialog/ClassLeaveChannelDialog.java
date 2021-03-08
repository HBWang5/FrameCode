package com.iqb.src.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.iqb.src.R;


public class ClassLeaveChannelDialog extends Dialog {
    private IReadCallBack iReadCallBack;
    private final TextView dialogLeaveCancelContentTv;
    private final TextView disagreeTv;
    private final TextView consentTv;
    private Context context;

    public void show(IReadCallBack iReadCallBack) {
        this.iReadCallBack = iReadCallBack;
        ClassLeaveChannelDialog.this.show();
    }

    public ClassLeaveChannelDialog(Context context) {
        super(context, R.style.ProgressDialogStyle);
        setContentView(R.layout.dialog_leave_channel);
        this.context = context;
        dialogLeaveCancelContentTv = findViewById(R.id.dialog_leave_cancel_content_tv);
        disagreeTv = findViewById(R.id.dialog_login_cancel_tv);
        consentTv = findViewById(R.id.dialog_login_affirm_tv);
        assert disagreeTv != null;
        disagreeTv.setOnClickListener(v -> {
            if (iReadCallBack != null) {
                iReadCallBack.disagreeNext();
            }
            ClassLeaveChannelDialog.this.hide();
            ClassLeaveChannelDialog.this.dismiss();
        });
        assert consentTv != null;
        consentTv.setOnClickListener(v -> {
            if (iReadCallBack != null) {
                iReadCallBack.readNext();
            }

            ClassLeaveChannelDialog.this.hide();
            ClassLeaveChannelDialog.this.dismiss();
        });
    }

    public ClassLeaveChannelDialog setContent(String str) {
        dialogLeaveCancelContentTv.setText(str);
        return this;
    }

    public ClassLeaveChannelDialog setButtonTv(String btOne, String btTwo) {
        disagreeTv.setText(btOne);
        consentTv.setText(btTwo);
        consentTv.setTextColor(context.getResources().getColor(R.color.yellow_color));
        return this;
    }

    public interface IReadCallBack {
        default void readNext() {

        }

        default void disagreeNext() {

        }

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
}
