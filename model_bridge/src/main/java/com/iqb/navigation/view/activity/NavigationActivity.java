package com.iqb.navigation.view.activity;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.iqb.api.BuildConfig;
import com.iqb.api.base.view.fragment.BaseFragment;
import com.iqb.api.utils.ActivityHelper;
import com.iqb.navigation.R;
import com.iqb.navigation.di.component.NavigationComponent;
import com.iqb.navigation.contract.NavigationActContract;
import com.iqb.navigation.base.presenter.BaseNavigationPresenter;
import com.iqb.src.widget.IQBTextView;
import com.iqb.src.widget.dialog.FirstOpenDialog;

import java.util.List;

import javax.inject.Inject;

import pub.devrel.easypermissions.EasyPermissions;


/**
 * 启动页View
 */
@SuppressLint("Registered")
public class NavigationActivity extends NavigationActContract.View implements EasyPermissions.PermissionCallbacks {

    /**
     * 注入View
     */
    @Override
    public void injectComponent(NavigationComponent component) {
        component.inject(this);
    }

    /**
     * 注入P
     */
    @Inject
    protected NavigationActContract.Presenter presenter;

    @Override
    public BaseNavigationPresenter getPresenter() {
        return presenter;
    }

    /**
     * 初始化监听
     */
    @Override
    protected void initOnClicked() {

    }

    /**
     * 初始化View
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        IQBTextView versions = findViewById(R.id.versions);
        versions.setText(getString(R.string.navigation_versions_tv) + BuildConfig.VERSION_NAME);
    }


    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        if (getDataManager().getSharePreferenceHelper().isFirstOpen()) {
            new FirstOpenDialog(NavigationActivity.this).show(() -> presenter.permissionDetection());
        } else {
            presenter.permissionDetection();
        }
    }

    @Override
    protected int initFragmentId() {
        return 0;
    }

    /**
     * 挂在后台Kill掉自己
     */
    @Override
    protected void onStop() {
        super.onStop();
        ActivityHelper.getInstance().finishActivity(this);
    }

    /**
     * 加载初始化Fragment
     */
    @Override
    protected BaseFragment initFragment() {
        return null;
    }

    /**
     * 加载Layout布局文件
     */
    @Override
    protected int setLayoutResourceID() {
        return R.layout.navigation_activity_main;
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        presenter.startLoginActivity();
    }


    /**
     * 申请动态权限失败
     */
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        presenter.showPermissionsDialog(perms);
    }

    @Override
    public void onRequestPermissionsResult(int i, @NonNull String[] strings, @NonNull int[] ints) {
        EasyPermissions.onRequestPermissionsResult(i, strings, ints, this);
    }


}
