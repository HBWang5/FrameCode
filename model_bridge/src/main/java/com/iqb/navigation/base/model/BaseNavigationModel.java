package com.iqb.navigation.base.model;


import com.iqb.api.base.model.BaseModel;
import com.iqb.api.base.model.IModel;
import com.iqb.api.base.model.manager.DataManager;

public class BaseNavigationModel extends BaseModel implements IModel {

    public BaseNavigationModel(DataManager mDataManager) {
        super(mDataManager);
    }

}
