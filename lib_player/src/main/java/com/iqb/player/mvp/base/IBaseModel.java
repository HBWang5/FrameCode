package com.iqb.player.mvp.base;

public abstract class IBaseModel<T extends IBaseLivePresenter> {
    private IBaseLivePresenter basePresenter;

    public IBaseModel(IBaseLivePresenter basePresenter) {
        this.basePresenter = basePresenter;
    }

    public IBaseLivePresenter getBasePresenter() {
        return basePresenter;
    }

    void inject(IBaseLivePresenter tmiBaseLivePresenter) {
        this.basePresenter = tmiBaseLivePresenter;
    }
}
