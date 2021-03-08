package com.iqb.src.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.iqb.src.R;

public class PromptDialog extends Dialog {
    private IConsentCallBack iConsentCallBack;
    private final TextView titleTv;
    private final TextView contentTv;

    public void show(IConsentCallBack iConsentCallBack) {
        this.iConsentCallBack = iConsentCallBack;
        PromptDialog.this.show();
    }

    public PromptDialog(Context context) {
        super(context, R.style.ProgressDialogStyle);
        setContentView(R.layout.dialog_prompt);
        titleTv = findViewById(R.id.dialog_prompt_title_tv);
        contentTv = findViewById(R.id.dialog_prompt_content_tv);
        TextView disagreeTv = findViewById(R.id.dialog_prompt_disagree_tv);
        TextView consentTv = findViewById(R.id.dialog_prompt_consent_tv);



        assert disagreeTv != null;
        disagreeTv.setOnClickListener(v -> {
            PromptDialog.this.hide();
            PromptDialog.this.dismiss();
        });
        assert consentTv != null;
        consentTv.setOnClickListener(v -> {
            if (iConsentCallBack != null) {
                iConsentCallBack.consent();
            }

            PromptDialog.this.hide();
            PromptDialog.this.dismiss();
        });
    }

    public interface IConsentCallBack {
        void consent();
    }

    public PromptDialog setTitle(String str){
        titleTv.setText(str);
        return this;
    }

    public PromptDialog setContent(String str){
        contentTv.setText(str);
        return this;
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
