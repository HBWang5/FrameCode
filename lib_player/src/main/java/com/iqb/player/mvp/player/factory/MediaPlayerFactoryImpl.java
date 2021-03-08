package com.iqb.player.mvp.player.factory;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MediaPlayerFactoryImpl implements IMediaPlayerFactory {
    @Override
    public IjkMediaPlayer initMediaPlayer(int type) {
        switch (type) {
            case 0:
                return new IjkMediaPlayer();
            case 1:
                return null;
        }
        return null;
    }
}
