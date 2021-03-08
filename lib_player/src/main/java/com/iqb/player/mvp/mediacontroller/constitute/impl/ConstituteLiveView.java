package com.iqb.player.mvp.mediacontroller.constitute.impl;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.iqb.player.R;
import com.iqb.player.mvp.mediacontroller.constitute.IConstituteView;
import com.iqb.player.mvp.mediacontroller.listener.IQBLiveControllerOnClickListener;
import com.iqb.player.tools.TimeTools;
import com.iqb.src.widget.IQBPlayerImageView;
import com.iqb.src.widget.IQBPlayerLinearLayout;
import com.iqb.src.widget.IQBPlayerRecyclerView;
import com.iqb.src.widget.IQBPlayerTextView;


/**
 * ----------Dragon be here!----------/
 * Created by HBWang on 2019/2/13-17:51
 * 控制层组件整合
 */
public class ConstituteLiveView implements IConstituteView {
    @SuppressLint("StaticFieldLeak")
    private static ConstituteLiveView mConstituteLiveView;

    private ConstituteLiveView() {
    }

    public static synchronized ConstituteLiveView getInstance() {
        if (mConstituteLiveView == null) {
            mConstituteLiveView = new ConstituteLiveView();
        }
        return mConstituteLiveView;
    }

    private ViewGroup viewGroup;

    @Override
    public IConstituteView addBelowColumnView() {
        return this;
    }

    @Override
    public IConstituteView addTopColumnView() {
        return this;
    }

    @SuppressLint("RtlHardcoded")
    @Override
    public IConstituteView addLeftColumnView() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.setMargins(viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.x32), 0, 0, viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.y80));

        IQBPlayerLinearLayout mIQBPlayerLinearLayout = new IQBPlayerLinearLayout(viewGroup.getContext());
        mIQBPlayerLinearLayout.setOrientation(LinearLayout.VERTICAL);
        mIQBPlayerLinearLayout.setGravity(Gravity.CENTER);

        viewGroup.addView(mIQBPlayerLinearLayout, layoutParams);

        {
            IQBPlayerRecyclerView iqbPlayerRecyclerView = new IQBPlayerRecyclerView(viewGroup.getContext());
            iqbPlayerRecyclerView.setId(R.id.class_content_list);
            IQBPlayerLinearLayout.LayoutParams iqbPlayerRecyclerParams = new IQBPlayerLinearLayout.LayoutParams(viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.x500), viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.y140));
            mIQBPlayerLinearLayout.addView(iqbPlayerRecyclerView, iqbPlayerRecyclerParams);
        }
        {
            IQBPlayerTextView imageView = new IQBPlayerTextView(viewGroup.getContext());
            imageView.setId(R.id.class_time_img);
            imageView.setTag(false);
            imageView.setOnClickListener(IQBLiveControllerOnClickListener.getInstance());
            IQBPlayerLinearLayout.LayoutParams imageViewLayoutParams = new IQBPlayerLinearLayout.LayoutParams(viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.x88), viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.x88));
            imageViewLayoutParams.topMargin = viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.y40);
            mIQBPlayerLinearLayout.addView(imageView, imageViewLayoutParams);
            imageView.setBackgroundResource(R.drawable.live_btn_time_normal);
            imageView.setTextColor(Color.WHITE);
            imageView.setGravity(Gravity.CENTER);
            TimeTools.getInstance().setTimeTv(imageView);
        }
        {
            IQBPlayerImageView imageView = new IQBPlayerImageView(viewGroup.getContext());
            imageView.setId(R.id.staff_icon_img);
            imageView.setOnClickListener(IQBLiveControllerOnClickListener.getInstance());
            IQBPlayerLinearLayout.LayoutParams imageViewLayoutParams = new IQBPlayerLinearLayout.LayoutParams(viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.x88), viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.x88));
            imageViewLayoutParams.topMargin = viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.y40);
            mIQBPlayerLinearLayout.setGravity(Gravity.LEFT);
            mIQBPlayerLinearLayout.addView(imageView, imageViewLayoutParams);
            imageView.setBackgroundResource(R.drawable.live_staff_icon);
        }
        return this;
    }

    @Override
    public IConstituteView addRightColumnView() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.setMargins(0, 0, viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.x32), viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.y80));

        IQBPlayerLinearLayout mIQBPlayerLinearLayout = new IQBPlayerLinearLayout(viewGroup.getContext());
        mIQBPlayerLinearLayout.setOrientation(LinearLayout.VERTICAL);
        mIQBPlayerLinearLayout.setGravity(Gravity.CENTER);

        viewGroup.addView(mIQBPlayerLinearLayout, layoutParams);


        {
            IQBPlayerImageView imageView = new IQBPlayerImageView(viewGroup.getContext());
            imageView.setId(R.id.stand_up_img);
            imageView.setOnClickListener(IQBLiveControllerOnClickListener.getInstance());
            IQBPlayerLinearLayout.LayoutParams imageViewLayoutParams = new IQBPlayerLinearLayout.LayoutParams(viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.x88), viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.x88));
            imageViewLayoutParams.topMargin = viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.y40);
            mIQBPlayerLinearLayout.addView(imageView, imageViewLayoutParams);
            imageView.setBackgroundResource(R.drawable.live_btn_hands_up_selected);
        }
        {
            IQBPlayerImageView imageView = new IQBPlayerImageView(viewGroup.getContext());
            imageView.setId(R.id.loudspeaker_img);
            imageView.setTag(true);
            imageView.setOnClickListener(IQBLiveControllerOnClickListener.getInstance());
            IQBPlayerLinearLayout.LayoutParams imageViewLayoutParams = new IQBPlayerLinearLayout.LayoutParams(viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.x88), viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.x88));
            imageViewLayoutParams.topMargin = viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.y40);
            mIQBPlayerLinearLayout.addView(imageView, imageViewLayoutParams);
            imageView.setBackgroundResource(R.drawable.live_loudspeaker_open_icon);
        }
        {
            IQBPlayerImageView imageView = new IQBPlayerImageView(viewGroup.getContext());
            imageView.setId(R.id.microphone_img);
            imageView.setTag(true);
            imageView.setOnClickListener(IQBLiveControllerOnClickListener.getInstance());
            IQBPlayerLinearLayout.LayoutParams imageViewLayoutParams = new IQBPlayerLinearLayout.LayoutParams(viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.x88), viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.x88));
            imageViewLayoutParams.topMargin = viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.y40);
            mIQBPlayerLinearLayout.addView(imageView, imageViewLayoutParams);
            imageView.setBackgroundResource(R.drawable.live_microphone_open_icon);
        }
        {
            IQBPlayerImageView imageView = new IQBPlayerImageView(viewGroup.getContext());
            imageView.setId(R.id.leave_channel_img);
            imageView.setOnClickListener(IQBLiveControllerOnClickListener.getInstance());
            IQBPlayerLinearLayout.LayoutParams imageViewLayoutParams = new IQBPlayerLinearLayout.LayoutParams(viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.x88), viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.x88));
            imageViewLayoutParams.topMargin = viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.y40);
            mIQBPlayerLinearLayout.addView(imageView, imageViewLayoutParams);
            imageView.setBackgroundResource(R.drawable.live_channel_out_icon);
        }

        return this;
    }

    @Override
    public IConstituteView addLoadingView() {
        return this;
    }

    @Override
    public IConstituteView addBarrageView() {
        return this;
    }

    @Override
    public IConstituteView addAdvertisingView() {
        return this;
    }

    @Override
    public IConstituteView addNavigationView() {
        return this;
    }

    @Override
    public IConstituteView init(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
        return this;
    }
}