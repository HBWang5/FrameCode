package com.iqb.api.base.view.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.iqb.api.base.app.ApiApplication;
import com.iqb.api.base.model.manager.DataManager;
import com.iqb.api.base.view.fragment.BaseFragment;
import com.iqb.api.dagger.component.AppComponent;
import com.iqb.api.frgbridge.FragmentBridge;
import com.iqb.api.frgbridge.FragmentBridgeImpl;
import com.iqb.api.utils.ActivityHelper;
import com.iqb.been.event.OffLineEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.iqb.constants.RouteActivityURL.IQB_TEACHER_LOGIN_ACT;

/**
 * 基础架构Activity
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Unbinder unbinder;
    private DataManager mDataManager;
    private Context mContext;
    //双击退出函数
    private Boolean isExit = false;

    protected void bindButterKnife() {
        unbinder = ButterKnife.bind(this);
    }

    protected AppComponent getAppComponent() {
        return ApiApplication.getApp().getAppComponent();
    }

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        //设置屏幕常亮
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mDataManager = getAppComponent().getDataManager();
        mContext = getAppComponent().getContext();
        initBeforeViewCreated();
        setContentView(setLayoutResourceID());
        //绑定注解
        bindButterKnife();
        EventBus.getDefault().register(this);
        if (initFragment() != null) {
            //初始化fragment
            FragmentBridge mFragmentBridgeImpl = new FragmentBridgeImpl(this);
            mFragmentBridgeImpl.init(initFragment(), initFragmentId()).addFragment().commit();
        }
        initData();
        initView();
        initOnClicked();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void offLineEvent(OffLineEvent offLineEvent) {
        ARouter.getInstance().build(IQB_TEACHER_LOGIN_ACT).navigation();
    }

    protected abstract void initOnClicked();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract int initFragmentId();

    protected abstract BaseFragment initFragment();


    /**
     * 这里不推荐在view层做数据层操作所以标记过时
     */
    public DataManager getDataManager() {
        return mDataManager;
    }

    public Context getAPPContext() {
        return mContext;
    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }

        super.onDestroy();
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    final public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //调用双击退出函数
            exitBy2Click();
        }
        return false;
    }


    protected void exitBy2Click() {
        Timer mTimer;
        if (!isExit) {
            // 准备退出
            isExit = true;
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mTimer = new Timer();
            mTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // 取消退出
                    isExit = false;
                }
                // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
            }, 2000);

        } else {
            ActivityHelper.getInstance().finishAllActivity();
            System.exit(0);
        }
    }

    protected abstract int setLayoutResourceID();


    protected abstract void initBeforeViewCreated();
}
