package com.iqb.api.frgbridge;


import androidx.fragment.app.FragmentActivity;

import com.iqb.api.base.view.fragment.BaseFragment;

/**
 * Created by Administrator on 2018/1/24.
 */
public abstract class FragmentManager {
    abstract void addFragment(BaseFragment fragment , int id);
    abstract void remove(BaseFragment fragment , int id);
    abstract void replace(BaseFragment fragment , int id);
    abstract void hide(BaseFragment fragment , int id);
    abstract void show(BaseFragment fragment , int id);
    abstract FragmentManagerImpl init(FragmentActivity activity);
    public abstract void commit();
}
