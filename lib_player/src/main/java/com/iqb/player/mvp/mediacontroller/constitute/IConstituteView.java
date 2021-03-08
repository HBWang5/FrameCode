package com.iqb.player.mvp.mediacontroller.constitute;

import android.view.ViewGroup;

/**
 * ----------Dragon be here!----------/
 * Created by HBWang on 2019/2/13-17:51
 * 控制层组件整合
 */
public interface IConstituteView {
    /**
     * （控制View添加）
     * 添加下栏目（进度/全屏。。。。）
     */
    default IConstituteView addBelowColumnView() {
        return this;
    }

    /**
     * 添加上栏目（标题）
     */
    default IConstituteView addTopColumnView() {
        return this;
    }

    /**
     * 添加左栏目（亮度）
     */
    default IConstituteView addLeftColumnView() {
        return this;
    }

    /**
     * 添加右栏目（声音）
     */

    default IConstituteView addRightColumnView() {
        return this;
    }

    /**
     * ================================================================================================================
     * <p>
     * 添加加载View（动画）
     */
    default IConstituteView addLoadingView() {
        return this;
    }

    /**
     * ================================================================================================================
     * （各种插件）
     * 添加弹幕View
     */
    default IConstituteView addBarrageView() {
        return this;
    }

    /**
     * 添加广告View
     */
    default IConstituteView addAdvertisingView() {
        return this;
    }

    /**
     * 添加导航View
     */
    default IConstituteView addNavigationView() {
        return this;
    }

    /**
     * ================================================================================================================
     * 初始化
     */
    default IConstituteView init(ViewGroup viewGroup) {
        return this;
    }

    default void resetTools() {

    }

    default void resetToolsInit() {
    }

}
