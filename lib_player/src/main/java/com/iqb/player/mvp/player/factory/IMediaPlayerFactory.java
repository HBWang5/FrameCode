package com.iqb.player.mvp.player.factory;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * 播放器内核工厂（拓展以后更换播放器）
 */
public interface IMediaPlayerFactory {

    IjkMediaPlayer initMediaPlayer(int type);

}
