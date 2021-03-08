package com.iqb.src.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.lang.reflect.Method;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by Administrator on 2018/7/27.
 */

@SuppressLint("AppCompatCustomView")
public class IQBEditText extends EditText implements View.OnClickListener, View.OnFocusChangeListener {

    public IQBEditText(Context context) {
        super(context);
        disableShowSoftInput(this);

    }

    public IQBEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        disableShowSoftInput(this);
    }

    public IQBEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        disableShowSoftInput(this);
    }


    public void disableShowSoftInput(EditText tvInputNumber) {
        this.setOnClickListener(this);
        this.setOnFocusChangeListener(this);
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            tvInputNumber.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method method;
            try {
                method = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(tvInputNumber, false);
            } catch (Exception e) {
            }

//            try {
//                method = cls.getMethod("setSoftInputShownOnFocus", boolean.class);
//                method.setAccessible(true);
//                method.invoke(tvInputNumber, false);
//            } catch (Exception e) {
//            }
        }
    }
    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == 1) {
            super.onKeyPreIme(keyCode, event);
//            onKeyBoardHideListener.onKeyHide(keyCode, com.iqb.been.event);
            return false;
        }
        return super.onKeyPreIme(keyCode, event);
    }


    /**
     *键盘监听接口
     */
    OnKeyBoardHideListener onKeyBoardHideListener;
    public void setOnKeyBoardHideListener(OnKeyBoardHideListener onKeyBoardHideListener) {
        this.onKeyBoardHideListener = onKeyBoardHideListener;
    }

    @Override
    public final void onClick(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
    }


    public interface OnKeyBoardHideListener{
        void onKeyHide(int keyCode, KeyEvent event);
    }
}
