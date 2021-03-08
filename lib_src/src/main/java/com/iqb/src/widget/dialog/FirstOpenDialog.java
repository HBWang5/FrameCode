package com.iqb.src.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.iqb.src.R;

public class FirstOpenDialog extends Dialog {
    private IReadCallBack iReadCallBack;

    public void show(IReadCallBack iReadCallBack) {
        this.iReadCallBack = iReadCallBack;
        FirstOpenDialog.this.show();
    }

    public FirstOpenDialog(Context context) {
        super(context, R.style.ProgressDialogStyle);
        setContentView(R.layout.dialog_first_open);
        TextView disagreeTv = findViewById(R.id.dialog_first_open_disagree_tv);
        TextView consentTv = findViewById(R.id.dialog_first_open_consent_tv);

        TextView dialogFirstOpenOneTv = findViewById(R.id.dialog_first_open_one_tv);
        TextView dialogFirstOpenTwoTv = findViewById(R.id.dialog_first_open_two_tv);
        TextView dialogFirstOpenThreeTv = findViewById(R.id.dialog_first_open_three_tv);
        dialogFirstOpenOneTv.setText(Html.fromHtml("欢迎使用“琴伴”！我们非常重视为您提供音乐在线教育和隐私保护，在您使用“琴伴”之前，请仔细阅读<font color=#FFBA15>《琴伴隐私政策》</font>。"));
        dialogFirstOpenThreeTv.setText(Html.fromHtml("如果同意<font color=#FFBA15>《琴伴隐私政策》</font>，请点击“同意”开始使用我们的产品和服务。"));

        dialogFirstOpenOneTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iReadCallBack != null) {
                    iReadCallBack.intent();
                }
            }
        });
        dialogFirstOpenTwoTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iReadCallBack.intent();
            }
        });
        dialogFirstOpenThreeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iReadCallBack.intent();
            }
        });
        assert disagreeTv != null;
        disagreeTv.setOnClickListener(v -> {
            if (iReadCallBack != null) {
                iReadCallBack.disagree();
            }
            FirstOpenDialog.this.hide();
            FirstOpenDialog.this.dismiss();
        });
        assert consentTv != null;
        consentTv.setOnClickListener(v -> {
            if (iReadCallBack != null) {
                iReadCallBack.readNext();
            }

            FirstOpenDialog.this.hide();
            FirstOpenDialog.this.dismiss();
        });
    }

    public interface IReadCallBack {
        void readNext();

        default void disagree() {
        }

        default void intent() {
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
