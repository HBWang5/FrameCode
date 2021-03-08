package com.iqb.player.mvp.mediacontroller.view;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.iqb.api.utils.ToastUtils;
import com.iqb.player.R;
import com.iqb.player.adapter.LiveContentMsgAdapter;
import com.iqb.player.mvp.mediacontroller.constitute.impl.ConstituteLiveView;
import com.iqb.player.mvp.mediacontroller.listener.IQBLiveControllerOnClickListener;
import com.iqb.player.mvp.mediacontroller.presenter.IQBLiveControllerPresenter;
import com.iqb.player.mvp.mediagroup.BaseGroup;
import com.iqb.player.mvp.plugin.view.LivePluginView;
import com.iqb.player.tools.TimeMsgTools;
import com.iqb.src.widget.IQBPlayerRecyclerView;
import com.iqb.src.widget.IQBSpaceItemDecoration;

import java.util.ArrayList;

import static com.iqb.player.mvp.mediacontroller.contract.IQBLiveControllerContract.*;

public class IQBLiveControllerView extends IQBLiveControllerRelativeView<IQBLiveControllerPresenter> {
    private LivePluginView livePlugin;
    private LiveContentMsgAdapter liveContentMsgAdapter;
    private IQBPlayerRecyclerView iqbPlayerRecyclerView;

    public IQBLiveControllerView(Context context) {
        super(context);
    }

    @Override
    public void setPlayerVoiceModel(boolean voiceCtl) {
        findViewById(R.id.loudspeaker_img).setBackgroundResource(R.drawable.live_loudspeaker_close_icon);
        findViewById(R.id.loudspeaker_img).setClickable(false);
    }

    @Override
    public void setPlayerRaiseHand() {
        if (TimeMsgTools.getInstance().setView(iqbPlayerRecyclerView, liveContentMsgAdapter)) {
            ToastUtils.showShort("等待教师反馈，请稍后再试！");
            return;
        }
        liveContentMsgAdapter.addString(getContext().getString(R.string.live_player_raise_hand_up_tv));
        iqbPlayerRecyclerView.scrollToPosition(liveContentMsgAdapter.getData().size() - 1);
    }

    @Override
    public void notifyMsg(int lastItemPosition) {
        liveContentMsgAdapter.notifyItemChanged(lastItemPosition);
    }

    @Override
    public void addMsg(String s) {
        if (TimeMsgTools.getInstance().setView(iqbPlayerRecyclerView, liveContentMsgAdapter)) {
            ToastUtils.showShort("等待教师反馈，请稍后再试！");
            return;
        }
        liveContentMsgAdapter.addString(s);
        iqbPlayerRecyclerView.scrollToPosition(liveContentMsgAdapter.getData().size() - 1);
    }


    @Override
    protected IQBLiveControllerPresenter bindPresenter() {
        return new IQBLiveControllerPresenter();
    }

    @Override
    public void initConstituteView(BaseGroup baseGroup) {
        ConstituteLiveView.getInstance().init(this)
                .addLeftColumnView()
                .addRightColumnView()
                .addBelowColumnView();
        initViewConfig();
    }

    @Override
    public void initViewConfig() {
        IQBLiveControllerOnClickListener.getInstance().bindController(getPresenter());
        liveContentMsgAdapter = new LiveContentMsgAdapter(new ArrayList<>());
        iqbPlayerRecyclerView = findViewById(R.id.class_content_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        //设置item间距，30dp
        iqbPlayerRecyclerView.addItemDecoration(new IQBSpaceItemDecoration(getContext().getResources().getDimensionPixelSize(R.dimen.y20)));
        iqbPlayerRecyclerView.setLayoutManager(linearLayoutManager);
        iqbPlayerRecyclerView.setAdapter(liveContentMsgAdapter);
        iqbPlayerRecyclerView.addOnScrollListener(IQBLiveControllerOnClickListener.getInstance());
    }
}
