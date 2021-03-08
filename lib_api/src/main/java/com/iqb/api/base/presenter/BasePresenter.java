package com.iqb.api.base.presenter;

import com.iqb.api.base.ui.BaseView;
import com.iqb.api.base.baseservice.model.BaseServiceModel;

public interface BasePresenter<T extends BaseView,M extends BaseServiceModel> {
    void destroy();
}