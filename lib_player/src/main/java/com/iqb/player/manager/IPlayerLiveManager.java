package com.iqb.player.manager;

import android.view.ViewGroup;

import com.iqb.player.mvp.mediacontroller.view.IQBLiveControllerView;

public interface IPlayerLiveManager {

    void bindViewForLivePlayer(ViewGroup viewGroup);

    IQBLiveControllerView getMediaController();
}
