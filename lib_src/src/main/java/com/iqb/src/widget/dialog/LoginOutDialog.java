package com.iqb.src.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.iqb.src.R;


public class LoginOutDialog extends Dialog {
    private IReadCallBack iReadCallBack;

    public void show(IReadCallBack iReadCallBack) {
        this.iReadCallBack = iReadCallBack;
        LoginOutDialog.this.show();
    }

    public LoginOutDialog(Context context) {
        super(context, R.style.ProgressDialogStyle);
        setContentView(R.layout.dialog_login_out);
        TextView disagreeTv = findViewById(R.id.dialog_login_cancel_tv);
        TextView consentTv = findViewById(R.id.dialog_login_affirm_tv);
        assert disagreeTv != null;
        disagreeTv.setOnClickListener(v -> {
            LoginOutDialog.this.hide();
            LoginOutDialog.this.dismiss();
        });
        assert consentTv != null;
        consentTv.setOnClickListener(v -> {
            if (iReadCallBack != null) {
                iReadCallBack.readNext();
            }

            LoginOutDialog.this.hide();
            LoginOutDialog.this.dismiss();
        });
    }

    public interface IReadCallBack {
        void readNext();
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
