package com.iqb.src.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.iqb.src.R;
import com.iqb.src.widget.IQBButton;
import com.iqb.src.widget.IQBEditText;


public class AddClassDialog extends Dialog {
    private IReadCallBack iReadCallBack;

    public void show(IReadCallBack iReadCallBack) {
        this.iReadCallBack = iReadCallBack;
        AddClassDialog.this.show();
    }

    public AddClassDialog(Context context) {
        super(context, R.style.ProgressDialogStyle);
        setContentView(R.layout.dialog_add_class);
        IQBButton nextBt = findViewById(R.id.next_bt);
        IQBEditText classId = findViewById(R.id.add_class_id_ed);
        IQBEditText classPassword = findViewById(R.id.add_class_password_ed);
        assert nextBt != null;
        nextBt.setOnClickListener(v -> {
            if (iReadCallBack != null) {
                iReadCallBack.readNext(classId.getText().toString().trim(), classPassword.getText().toString().trim());
            }
            AddClassDialog.this.hide();
            AddClassDialog.this.dismiss();
        });
    }

    public interface IReadCallBack {
        void readNext(String trim, String s);
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
