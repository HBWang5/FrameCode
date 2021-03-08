package com.iqb.api.base.model;


import com.iqb.api.base.model.manager.BaseDataManager;
import com.iqb.api.base.model.manager.DataManager;



public class BaseModel extends BaseDataManager implements IModel {

    public BaseModel(DataManager mDataManager) {
        super(mDataManager);
    }

}
