package com.iqb.api.base.baseservice.model;


import android.content.Context;

import com.iqb.api.base.model.IModel;


public abstract class BaseServiceModel implements IModel {

    private Context context;

    public BaseServiceModel(Context context){
        this.context = context;
    }

    public Context getContext() {
        return context;
    }


}
