package com.iqb.api.frgbridge;

import android.annotation.SuppressLint;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.iqb.api.base.view.fragment.BaseFragment;

/**
 * Created by Administrator on 2018/1/24.
 */

public class FragmentManagerImpl extends FragmentManager {

    private FragmentTransaction fragmentTransaction;

    public FragmentTransaction getFragmentTransaction() {
        return fragmentTransaction;
    }

    @Override
    void addFragment(BaseFragment fragment , int id) {
        fragmentTransaction.add(id, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName());
    }

    @Override
    void remove(BaseFragment fragment , int id) {
        fragmentTransaction.remove(fragment).disallowAddToBackStack();
    }

    @Override
    void replace(BaseFragment fragment , int id) {
        fragmentTransaction .replace(id, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
        ;
    }

    @Override
    void hide(BaseFragment fragment , int id) {
        if (fragment != null) {
            fragmentTransaction.hide(fragment);
        }
    }

    @Override
    void show(BaseFragment fragment , int id) {
        if (fragment != null) {
            fragmentTransaction.show(fragment);
        }
    }


    @SuppressLint("CommitTransaction")
    @Override
    FragmentManagerImpl init(FragmentActivity activity) {
        fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        return this;
    }

    @Override
    public void commit() {
        fragmentTransaction.commitAllowingStateLoss();
    }
}
