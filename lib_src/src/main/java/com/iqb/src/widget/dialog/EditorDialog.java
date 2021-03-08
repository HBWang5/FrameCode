package com.iqb.src.widget.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialog;

import com.iqb.src.R;
import com.iqb.src.widget.IQBEditText;

import java.util.Objects;

public class EditorDialog<T> extends AppCompatDialog {
    private EditorDialog(Builder<T> builder) {
        super(builder.context, R.style.DialogStyles);
        setContentView(R.layout.dialog_editor);
        Objects.requireNonNull(getWindow()).setBackgroundDrawableResource(android.R.color.transparent);

        TextView dialogEditorTitleTv = findViewById(R.id.dialog_editor_title_tv);
        IQBEditText dialogEditorDataEd = findViewById(R.id.dialog_editor_data_ed);
        TextView dialogEditorSubmitTv = findViewById(R.id.dialog_editor_submit_tv);
        if (!TextUtils.isEmpty(builder.titleTv)) {
            assert dialogEditorTitleTv != null;
            dialogEditorTitleTv.setText(builder.titleTv);
        }
        assert dialogEditorSubmitTv != null;
        dialogEditorSubmitTv.setOnClickListener(v -> {
            assert dialogEditorDataEd != null;
            builder.onCountersignClickListener.OnCountersignClick(dialogEditorDataEd.getText().toString());
            EditorDialog.this.hide();
            EditorDialog.this.dismiss();
        });
    }

    @Override
    public void show() {
        super.show();
        Window window = getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        window.setWindowAnimations(R.style.BottomDialogAnimation);
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setAttributes(layoutParams);
    }

    public static class Builder<T> {
        Context context;
        private String titleTv;

        private CountersignClick onCountersignClickListener;

        public Builder initTitle(String title) {
            titleTv = title;
            return this;
        }

        public Builder initCountersignClick(CountersignClick onCountersignClickListener) {
            this.onCountersignClickListener = onCountersignClickListener;
            return this;
        }

        public Builder(Context context) {
            this.context = context;
        }

        public EditorDialog create() {
            return new EditorDialog<T>(this);
        }

        public interface CountersignClick {
            void OnCountersignClick(String str);
        }
    }


}
