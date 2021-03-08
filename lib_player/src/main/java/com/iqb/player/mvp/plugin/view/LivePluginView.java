package com.iqb.player.mvp.plugin.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;

import com.alibaba.android.arouter.utils.TextUtils;
import com.bumptech.glide.Glide;
import com.iqb.api.BuildConfig;
import com.iqb.api.base.app.ApiApplication;
import com.iqb.player.R;
import com.iqb.player.mvp.plugin.contract.IQBLivePluginContract;
import com.iqb.player.mvp.plugin.listener.IQBLivePluginOnClickListener;
import com.iqb.player.mvp.plugin.presenter.LivePluginPresenter;
import com.iqb.src.widget.IQBPlayerImageView;
import com.iqb.src.widget.IQBPlayerLinearLayout;
import com.iqb.src.widget.IQBPlayerPictureDrawView;
import com.iqb.src.widget.IQBRoundImageView;
import com.iqb.src.widget.IQBTextView;
import com.iqb.src.widget.IQBWebView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;

import static com.iqb.src.widget.IQBPlayerPictureDrawView.ERASER;
import static com.iqb.src.widget.IQBPlayerPictureDrawView.PEN;

@SuppressLint("ViewConstructor")
public class LivePluginView extends IQBLivePluginContract.IQBLivePluginRelativeView<LivePluginPresenter> {
    private IQBPlayerPictureDrawView iqbLivePictureDrawView;
    private IQBWebView iqbWebView;
    private IQBPlayerImageView imageViewBackLive;
    private IQBPlayerImageView imageViewDownUp;
    private IQBPlayerImageView iqbPlayerImageView;
    private IQBPlayerLinearLayout iqbPlayerLinearLayout;
    private String preImg;
    private Map<String, IQBPlayerPictureDrawView> drawViewMap = new HashMap<>();
    private LinkedHashSet<String> hashSet;
    private HashSet<String> cacheSet = new HashSet<>();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public LivePluginView(Context context) {
        super(context);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void initView() {
        IQBLivePluginOnClickListener.getInstance().bindController(getPresenter());
        this.setBackgroundColor(getResources().getColor(R.color.white_color));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        RelativeLayout.LayoutParams layoutParamsSc = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, getContext().getResources().getDimensionPixelSize(R.dimen.y256));
        layoutParamsSc.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        //画布图层
        iqbLivePictureDrawView = new IQBPlayerPictureDrawView(getContext());
        //PPT加载WebView
        iqbWebView = new IQBWebView(getContext());
        //webView配置
        webViewConfig();
        //课前准备图片大图
        iqbPlayerImageView = new IQBPlayerImageView(getContext());
        iqbPlayerImageView.setAdjustViewBounds(true);
        iqbPlayerImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //课前准备图片列表（横向列表）
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(getContext());
        //水平方向的水平滚动条是否显示
        horizontalScrollView.setHorizontalScrollBarEnabled(false);
        //课前准备图片列表容器
        iqbPlayerLinearLayout = new IQBPlayerLinearLayout(getContext());
        //设置排列方向
        iqbPlayerLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        //添加课前准备容器（线性布局）
        horizontalScrollView.addView(iqbPlayerLinearLayout);
        //添加加载PPT的WebView
        addView(iqbWebView, layoutParams);
        iqbWebView.setWebViewClient(new MyWebViewClient());
        //添加课前准备大图图片View
        addView(iqbPlayerImageView, layoutParams);
        //添加课前准备图片列表容器
        addView(horizontalScrollView, layoutParamsSc);
        //添加批注图层View
        addView(iqbLivePictureDrawView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        {
            RelativeLayout.LayoutParams layoutParamsRl = new RelativeLayout.LayoutParams(getContext().getResources().getDimensionPixelSize(R.dimen.x88), getContext().getResources().getDimensionPixelSize(R.dimen.x88));
            layoutParamsRl.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            layoutParamsRl.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            layoutParamsRl.setMargins(getContext().getResources().getDimensionPixelSize(R.dimen.x32), 0, 0, getContext().getResources().getDimensionPixelSize(R.dimen.y280));
            imageViewDownUp = new IQBPlayerImageView(getContext());
            imageViewDownUp.setId(R.id.down_up_img);
            imageViewDownUp.setTag(true);
            imageViewDownUp.setOnClickListener(IQBLivePluginOnClickListener.getInstance());
            imageViewDownUp.setBackgroundResource(R.drawable.live_class_down_icon);
            addView(imageViewDownUp, layoutParamsRl);
        }
        //返回视频icon添加
        {
            RelativeLayout.LayoutParams layoutParamsRl = new RelativeLayout.LayoutParams(getContext().getResources().getDimensionPixelSize(R.dimen.x88), getContext().getResources().getDimensionPixelSize(R.dimen.x88));
            layoutParamsRl.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            layoutParamsRl.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            layoutParamsRl.setMargins(getContext().getResources().getDimensionPixelSize(R.dimen.x32), 0, 0, getContext().getResources().getDimensionPixelSize(R.dimen.y400));
            imageViewBackLive = new IQBPlayerImageView(getContext());
            imageViewBackLive.setId(R.id.back_live_img);
            imageViewBackLive.setOnClickListener(IQBLivePluginOnClickListener.getInstance());
            imageViewBackLive.setBackgroundResource(R.drawable.live_back_live_icon);
            addView(imageViewBackLive, layoutParamsRl);
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void webViewConfig() {
        WebSettings webSettings = iqbWebView.getSettings();
        // 让WebView能够执行javaScript
        webSettings.setJavaScriptEnabled(true);
        // 让JavaScript可以自动打开windows
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置缓存
        webSettings.setAppCacheEnabled(true);
        // 设置缓存模式,一共有四种模式
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 设置缓存路径
        webSettings.setAppCachePath(ApiApplication.getApplication().getFilesDir().getAbsolutePath() + "IQB_WEB");
        // 支持缩放(适配到当前屏幕)
        webSettings.setSupportZoom(true);
        // 将图片调整到合适的大小
        webSettings.setUseWideViewPort(true);
        // 支持内容重新布局,一共有四种方式
        // 默认的是NARROW_COLUMNS
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        // 设置可以被显示的屏幕控制
        webSettings.setDisplayZoomControls(true);
        // 设置默认字体大小
        webSettings.setDefaultFontSize(12);
    }

    @Override
    protected LivePluginPresenter bindPresenter() {
        return new LivePluginPresenter();
    }


    @Override
    public void pptDrawCtlTypeBegin(double x, double y) {
        iqbLivePictureDrawView.drawCtlTypeBegin((float) x, (float) y);
    }

    @Override
    public void pptDrawCtlTypeMove(double x, double y) {
        iqbLivePictureDrawView.drawCtlTypeMove((float) x, (float) y);
    }

    @Override
    public void pptDrawCtlTypeEnd(double x, double y) {
        iqbLivePictureDrawView.pptDrawCtlTypeEnd();
    }

    @Override
    public void showPPT(String url) {
        this.setVisibility(VISIBLE);
        iqbPlayerImageView.setVisibility(GONE);
        imageViewBackLive.setVisibility(GONE);
        iqbPlayerLinearLayout.setVisibility(GONE);
        imageViewDownUp.setVisibility(GONE);
        iqbWebView.setVisibility(VISIBLE);
        if (TextUtils.isEmpty(url)) {
            return;
        }
        iqbWebView.loadUrl(url);
        if (!drawViewMap.containsKey(url)) {
            drawViewMap.put(url, new IQBPlayerPictureDrawView(getContext()));
        }
        removeView(iqbLivePictureDrawView);
        iqbLivePictureDrawView = drawViewMap.get(url);
        addView(iqbLivePictureDrawView);
    }

    @Override
    public void isPen(boolean pen) {
        if (iqbLivePictureDrawView == null) return;
        if (pen) {
            iqbLivePictureDrawView.setStyle(PEN);
        } else {
            iqbLivePictureDrawView.setStyle(ERASER);
        }
    }

    @Override
    public WebView getWebView() {
        return null;
    }

    @Override
    public void drawCtlTypeClear() {
        iqbLivePictureDrawView.drawCtlTypeClear();
    }

    @Override
    public void drawCtlImageOpen(boolean isShowList) {

        iqbPlayerImageView.setVisibility(VISIBLE);
        imageViewBackLive.setVisibility(VISIBLE);
        if (isShowList) {
            this.setVisibility(VISIBLE);
            iqbPlayerLinearLayout.setVisibility(VISIBLE);
            imageViewDownUp.setVisibility(VISIBLE);
            iqbWebView.setVisibility(GONE);
            imageViewDownUp.setTag(true);
            imageViewDownUp.setBackgroundResource(R.drawable.live_class_down_icon);
        }
        //加载大图
        Glide.with(getContext()).load(preImg).into(iqbPlayerImageView);
        if (!drawViewMap.containsKey(preImg)) {
            drawViewMap.put(preImg, new IQBPlayerPictureDrawView(getContext()));
        }
        removeView(iqbLivePictureDrawView);
        iqbLivePictureDrawView = drawViewMap.get(preImg);
        addView(iqbLivePictureDrawView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

    }

    @Override
    public void drawCtlImageChange(String scoreImageURL) {
        loadImg(scoreImageURL);
    }

    @Override
    public void removeImgListAllView() {
        iqbPlayerLinearLayout.removeAllViews();
        IQBPlayerLinearLayout.LayoutParams imageViewLayoutParams = new IQBPlayerLinearLayout.LayoutParams(getContext().getResources().getDimensionPixelSize(R.dimen.x144), getContext().getResources().getDimensionPixelSize(R.dimen.y192));
        imageViewLayoutParams.setMargins(getContext().getResources().getDimensionPixelSize(R.dimen.x10), getContext().getResources().getDimensionPixelSize(R.dimen.y32), getContext().getResources().getDimensionPixelSize(R.dimen.x10), getContext().getResources().getDimensionPixelSize(R.dimen.y32));
        loadImgListHead(imageViewLayoutParams);
    }

    @Override
    public void bindImgList(String[] split, String picUrl) {
        iqbPlayerLinearLayout.removeAllViews();
        IQBPlayerLinearLayout.LayoutParams imageViewLayoutParams = new IQBPlayerLinearLayout.LayoutParams(getContext().getResources().getDimensionPixelSize(R.dimen.x144), getContext().getResources().getDimensionPixelSize(R.dimen.y192));
        imageViewLayoutParams.setMargins(getContext().getResources().getDimensionPixelSize(R.dimen.x10), getContext().getResources().getDimensionPixelSize(R.dimen.y32), getContext().getResources().getDimensionPixelSize(R.dimen.x10), getContext().getResources().getDimensionPixelSize(R.dimen.y32));
        hashSet = new LinkedHashSet<>(Arrays.asList(split));
        loadImgListHead(imageViewLayoutParams);

        //加载图片列表
        for (String s : hashSet) {
            @SuppressLint("InflateParams")
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.class_live_img_item, null);
            iqbPlayerLinearLayout.addView(inflate, imageViewLayoutParams);
            IQBRoundImageView iqbRoundImageView = inflate.findViewById(R.id.class_prepare_item_img);
            IQBTextView iqbTextView = inflate.findViewById(R.id.img_select_tv);
            iqbTextView.setVisibility(VISIBLE);
            iqbTextView.setOnClickListener(IQBLivePluginOnClickListener.getInstance());
            iqbTextView.setTag(picUrl.replace(s, ""));
            iqbRoundImageView.setTag(s);
            iqbRoundImageView.setmBorderRadius(20);
            iqbRoundImageView.setOnClickListener(v -> loadImg(BuildConfig.BASE_URL + v.getTag()));
            Glide.with(getContext()).load(BuildConfig.BASE_URL + s).into(iqbRoundImageView);
        }


    }

    private void loadImg(String url) {
        preImg = url;
        //加载大图
        Glide.with(getContext()).load(url).into(iqbPlayerImageView);
        if (!drawViewMap.containsKey(url)) {
            drawViewMap.put(url, new IQBPlayerPictureDrawView(getContext()));
        }
        removeView(iqbLivePictureDrawView);
        iqbLivePictureDrawView = drawViewMap.get(url);
        addView(iqbLivePictureDrawView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        drawCtlImageOpen(false);
    }

    private void loadImgListHead(LinearLayout.LayoutParams imageViewLayoutParams) {
        @SuppressLint("InflateParams")
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.class_live_img_item, null);
        iqbPlayerLinearLayout.addView(inflate, imageViewLayoutParams);
        IQBRoundImageView iqbRoundImageView = inflate.findViewById(R.id.class_prepare_item_img);
        inflate.findViewById(R.id.img_select_tv).setVisibility(GONE);
        iqbRoundImageView.setOnClickListener(IQBLivePluginOnClickListener.getInstance());
        iqbRoundImageView.setmBorderRadius(20);
        iqbRoundImageView.setBackgroundResource(R.drawable.live_class_add_picture_icon);
    }

    @Override
    public void imgDrawCtlTypeBegin(float drawCtlPointX, float drawCtlPointY) {
        iqbLivePictureDrawView.drawCtlTypeBegin(drawCtlPointX, drawCtlPointY);
        getIqbLivePictureDrawView();
    }

    @Override
    public void imgDrawCtlTypeMove(float drawCtlPointX, float drawCtlPointY) {
        iqbLivePictureDrawView.drawCtlTypeMove(drawCtlPointX, drawCtlPointY);
        getIqbLivePictureDrawView();
    }

    @Override
    public void imgDrawCtlTypeEnd(float drawCtlPointX, float drawCtlPointY) {
        iqbLivePictureDrawView.pptDrawCtlTypeEnd();
        getIqbLivePictureDrawView();
    }

    @Override
    public void setImgListVisible(boolean b) {
        iqbPlayerLinearLayout.setVisibility(b ? VISIBLE : GONE);
    }

    public void getIqbLivePictureDrawView() {
        if (!TextUtils.isEmpty(preImg) && iqbWebView.getVisibility() != VISIBLE) {
            cacheSet.add(preImg.replace(BuildConfig.BASE_URL, ""));
        }
    }

    @Override
    public boolean isDraw(String split) {
        for (String s : hashSet) {
            if (!split.contains(s)) {
                return cacheSet.contains(s);
            }
        }
        return false;
    }

    @Override
    public void hidePPT(String url) {
        this.setVisibility(GONE);
        iqbPlayerImageView.setVisibility(GONE);
        imageViewBackLive.setVisibility(GONE);
        iqbPlayerLinearLayout.setVisibility(GONE);
        imageViewDownUp.setVisibility(GONE);
        iqbWebView.setVisibility(GONE);
//        if (TextUtils.isEmpty(url)) {
//            return;
//        }
//        iqbWebView.loadUrl(url);
//        if (!drawViewMap.containsKey(url)) {
//            drawViewMap.put(url, new IQBPlayerPictureDrawView(getContext()));
//        }
//        removeView(iqbLivePictureDrawView);
//        iqbLivePictureDrawView = drawViewMap.get(url);
//        addView(iqbLivePictureDrawView);
    }

    @Override
    public void openImg() {
        iqbPlayerImageView.setVisibility(VISIBLE);
        imageViewBackLive.setVisibility(VISIBLE);
        iqbPlayerLinearLayout.setVisibility(VISIBLE);
        imageViewDownUp.setVisibility(VISIBLE);
        iqbWebView.setVisibility(GONE);
    }

    private class MyWebViewClient extends WebViewClient {

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @SuppressLint("NewApi")
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            view.evaluateJavascript("javascript:var msgbutt = document.getElementsByClassName(\"btn_no\");" +
                    "    if(msgbutt.length > 0) {" +
                    "        var event = document.createEvent(\"Event\");" +
                    "        event.initEvent(\"touchstart\", false, true);" +
                    "        msgbutt[0].dispatchEvent(event);" +
                    "        event.initEvent(\"touchend\", false, true);" +
                    "        msgbutt[0].dispatchEvent(event);" +
                    "    }" +
                    "    var tops = document.getElementsByClassName(\"top_panel\");" +
                    "    tops[0].style.visibility = \"hidden\";" +
                    "    var bott = document.getElementsByClassName(\"bottom_panel\");" +
                    "    bott[0].style.visibility = \"hidden\";", value -> {
            });
        }
    }

}
