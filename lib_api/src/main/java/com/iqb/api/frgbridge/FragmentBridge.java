package com.iqb.api.frgbridge;

import com.iqb.api.base.view.fragment.BaseFragment;

/**
 * Created by Administrator on 2018/1/24.
 */

public abstract class FragmentBridge {
    public abstract FragmentBridgeImpl addFragment();
    public abstract FragmentBridgeImpl remove();
    public abstract FragmentBridgeImpl replace();
    public abstract FragmentBridgeImpl commit();
    public abstract FragmentBridgeImpl hide(BaseFragment fragment);
    public abstract FragmentBridgeImpl show(BaseFragment fragment);
    public abstract FragmentBridgeImpl init(BaseFragment fragment , int id);
}
