package com.iqb.navigation.presenter;


import android.Manifest;

import androidx.lifecycle.LifecycleOwner;

import com.alibaba.android.arouter.launcher.ARouter;
import com.iqb.api.base.app.ApiApplication;
import com.iqb.api.base.view.activity.BaseActivity;
import com.iqb.api.net.rx.TransformUtils;
import com.iqb.api.net.rx.observer.HttpRxObserver;
import com.iqb.api.permission.hal.PermissionHelper;
import com.iqb.api.utils.ActivityHelper;
import com.iqb.api.utils.ToastUtils;
import com.iqb.been.base.HttpResponseBean;
import com.iqb.been.login.LoginEntity;
import com.iqb.navigation.contract.NavigationActContract;
import com.iqb.navigation.model.NavigationModelAct;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import pub.devrel.easypermissions.AppSettingsDialog;

import static com.iqb.constants.RouteActivityURL.IQB_TEACHER_HOME_ACT;
import static com.iqb.constants.RouteActivityURL.IQB_TEACHER_LOGIN_ACT;


public class NavigationPresenterAct extends NavigationActContract.Presenter {

    private NavigationModelAct navigationModelAct;
    private LifecycleOwner lifecycleOwner;
    private BaseActivity baseActivity;
    private NavigationActContract.View view;
    private Disposable subscribe;

    /**
     * 注入P
     */
    @Inject
    NavigationPresenterAct(NavigationActContract.View view, LifecycleOwner lifecycleOwner, BaseActivity baseActivity) {
        super(view);
        this.view = view;
        this.lifecycleOwner = lifecycleOwner;
        this.baseActivity = baseActivity;
    }

    /**
     * 绑定Model层
     */
    @Override
    protected NavigationModelAct bindModel() {
        navigationModelAct = new NavigationModelAct(ApiApplication.getApp().getAppComponent().getDataManager());
        return navigationModelAct;
    }

    /**
     * 检查申请动态权限
     */
    @Override
    public void permissionDetection() {
        if (!PermissionHelper.getInstance().jurisdictionDetection(baseActivity, Manifest.permission.INTERNET, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            PermissionHelper.getInstance().jurisdictionApply(Manifest.permission.INTERNET)
                    .jurisdictionApply(Manifest.permission.CAMERA)
                    .jurisdictionApply(Manifest.permission.READ_EXTERNAL_STORAGE)
                    .jurisdictionApply(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .submit(baseActivity);
        } else {
            startLogin();
        }
    }

    /**
     * 隔两秒登录
     */
    private void startLogin() {

        subscribe = Observable.timer(2, TimeUnit.SECONDS)
                .compose(TransformUtils.ioToMain())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(baseActivity)))
                .subscribe(aLong -> startLoginActivity());
    }

    /**
     * 自动登录
     */
    public void startLoginActivity() {
        navigationModelAct.login(lifecycleOwner, new HttpRxObserver<HttpResponseBean<LoginEntity>>("login", view) {
            @Override
            protected void onFail(Exception e) {
                //失败跳转登陆页面
                ARouter.getInstance().build(IQB_TEACHER_LOGIN_ACT).navigation();
            }

            @Override
            protected void onSuccess(HttpResponseBean<LoginEntity> response) {
                if (isHTTPOk(response.isS(), response.getM())) {
                    ARouter.getInstance().build(IQB_TEACHER_LOGIN_ACT).navigation();
                    return;
                }
                if (!response.getD().isIsLive()) {
                    navigationModelAct.saveUserData(response);
                    ARouter.getInstance().build(IQB_TEACHER_HOME_ACT).navigation();
                }else {
                    ToastUtils.showShort("该账号正在上课中，请稍后重试。");
                    ARouter.getInstance().build(IQB_TEACHER_LOGIN_ACT).navigation();
                }
            }
        });
    }

    /**
     * 如果用户不同意给权限弹出提示框
     *
     * @param perms 权限列表
     */
    @Override
    public void showPermissionsDialog(List<String> perms) {
        StringBuilder sb = new StringBuilder();
        for (String str : perms) {
            sb.append(str);
            sb.append("\n");
        }
        sb.replace(sb.length() - 2, sb.length(), "");
        new AppSettingsDialog
                .Builder(baseActivity)
                .setRationale("此功能需要相机、读写权限，否则无法正常使用")
                .setPositiveButton("确认")
                .setNegativeButton("取消")
                .build()
                .show();
    }


    /**
     * 销毁时释放资源
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscribe != null && !subscribe.isDisposed()) {
            subscribe.dispose();
        }
        ActivityHelper.getInstance().finishActivity(baseActivity);
    }
}
