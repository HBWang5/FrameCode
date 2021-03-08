package com.iqb.player.mvp.mediacontroller.listener;

import android.app.Activity;
import android.view.View;

import com.iqb.player.R;
import com.iqb.player.mvp.mediacontroller.view.IQBVideoControllerView;
import com.iqb.player.playerstatus.PlayerStatus;
import com.iqb.player.playerstatus.PlayerValue;

import static com.iqb.player.playerstatus.PlayerStatus.PLAYER_IS_FULL;

/**
 * ----------Dragon be here!----------/
 * Created by HBWang on 2019/3/4-10:55
 */
public class IQBVideoControllerOnClickListener implements View.OnClickListener{

    private IQBVideoControllerView mIIQBMediaController;
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.player_pause) {

            if (PlayerStatus.PLAY_STATUS == PlayerValue.PLAY_PLAYYING) {
                mIIQBMediaController.pause();
            } else {
                mIIQBMediaController.resume();
            }

        } else if (i == R.id.player_full) {
            PLAYER_IS_FULL = !PLAYER_IS_FULL;
            mIIQBMediaController.playerFull((Activity)v.getContext());
        }

    }

    public View.OnClickListener bindController(IQBVideoControllerView mIIQBMediaController) {

        this.mIIQBMediaController = mIIQBMediaController;
        return this;
    }
}
