package com.iqb.api.frgbridge;


import androidx.fragment.app.FragmentActivity;

import com.iqb.api.base.view.fragment.BaseFragment;

/**
 * Created by Administrator on 2018/1/24.
 */

public class FragmentBridgeImpl extends FragmentBridge{
    private BaseFragment fragment;
    private FragmentManager fragmentManager;
    private int id;
    public FragmentBridgeImpl(FragmentActivity fragmentActivity) {
        fragmentManager = new FragmentManagerImpl().init(fragmentActivity);//初始化Fragment管理器
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    @Override
    public FragmentBridgeImpl addFragment() {
        fragmentManager.addFragment(fragment,id);
        return this;
    }

    @Override
    public FragmentBridgeImpl remove() {
        fragmentManager.remove(fragment,id);
        return this;
    }

    @Override
    public FragmentBridgeImpl replace() {
        fragmentManager.replace(fragment,id);
        commit();
        return this;
    }

    @Override
    public FragmentBridgeImpl commit() {
        fragmentManager.commit();
        return this;
    }

    @Override
    public FragmentBridgeImpl hide(BaseFragment fragment) {
        fragmentManager.hide(fragment,id);
        return this;
    }

    @Override
    public FragmentBridgeImpl show(BaseFragment fragment) {
        fragmentManager.show(fragment,id);
        return this;
    }

    @Override
    public FragmentBridgeImpl init(BaseFragment fragment , int id) {
        this.id = id;
        this.fragment = fragment;
        return this;
    }
}
