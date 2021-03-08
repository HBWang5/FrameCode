package com.iqb.navigation.view.fragment;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.iqb.navigation.R;
import com.iqb.navigation.base.presenter.BaseNavigationPresenter;
import com.iqb.navigation.contract.NavigationFrgContract;
import com.iqb.navigation.di.component.NavigationComponent;

import javax.inject.Inject;

import static com.iqb.constants.RouteFragmentURL.IQB_TEACHER_NAVIGATION_FRG;

/**
 * 导航View
 */
@Route(path = IQB_TEACHER_NAVIGATION_FRG)
public class NavigationFragment extends NavigationFrgContract.View {
    @Inject
    protected NavigationFrgContract.Presenter presenter;

    @Override
    public void injectComponent(NavigationComponent component) {
        component.inject(this);
    }

    @Override
    public int bindLayoutId() {
        return R.layout.navigation_fragment_main;
    }

    @Override
    protected void initOnClicked() {

    }



    @Override
    protected void initView(View contentView) {

    }

    @Override
    protected void initData() {
        presenter.init();
    }

    @Override
    public BaseNavigationPresenter getPresenter() {
        return presenter;
    }
}
