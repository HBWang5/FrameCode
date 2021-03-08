package com.iqb.api.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;

import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import java.io.File;

import static android.app.Activity.RESULT_OK;

public class UCropUtils {
    private static UCropUtils mUCropUtils;

    private UCropUtils() {
    }

    public static synchronized UCropUtils getInstance() {
        if (mUCropUtils == null) {
            mUCropUtils = new UCropUtils();
        }
        return mUCropUtils;
    }

    public void init(Activity context, Uri uri) {
        String[] split = uri.getPath().split("/");
        UCrop.Options options = new UCrop.Options();
        //裁剪后图片保存在文件夹中
        Uri destinationUri = Uri.fromFile(new File(context.getExternalCacheDir(), split[split.length - 1]));
        UCrop uCrop = UCrop.of(uri, destinationUri);//第一个参数是裁剪前的uri,第二个参数是裁剪后的uri
        uCrop.withAspectRatio(1, 1);//设置裁剪框的宽高比例
        //下面参数分别是缩放,旋转,裁剪框的比例
        options.setAllowedGestures(UCropActivity.ALL, UCropActivity.NONE, UCropActivity.ALL);
        options.setToolbarTitle("裁剪");//设置标题栏文字
        options.setCropGridStrokeWidth(2);//设置裁剪网格线的宽度(我这网格设置不显示，所以没效果)
        options.setCropFrameStrokeWidth(10);//设置裁剪框的宽度
        options.setMaxScaleMultiplier(3);//设置最大缩放比例
        options.setHideBottomControls(true);//隐藏下边控制栏
        options.setShowCropGrid(false);  //设置是否显示裁剪网格
//        options.setOvalDimmedLayer(true);//设置是否为圆形裁剪框
        options.setShowCropFrame(true); //设置是否显示裁剪边框(true为方形边框)
        options.setToolbarWidgetColor(Color.parseColor("#ffffff"));//标题字的颜色以及按钮颜色
        options.setDimmedLayerColor(Color.parseColor("#AA000000"));//设置裁剪外颜色
        options.setToolbarColor(Color.parseColor("#000000")); // 设置标题栏颜色
        options.setStatusBarColor(Color.parseColor("#000000"));//设置状态栏颜色
        options.setCropGridColor(Color.parseColor("#ffffff"));//设置裁剪网格的颜色
        options.setCropFrameColor(Color.parseColor("#ffffff"));//设置裁剪框的颜色
        uCrop.withOptions(options);
        uCrop.start(context);
    }

    public void switchUCropCode(int code, int requestCode, Intent data, UCropCallBack uCropCallBack) {
        switch (requestCode) {
            case UCrop.REQUEST_CROP:
                if (data != null) {
                    uCropCallBack.requestCrop(UCrop.getOutput(data));
                }
                break;
            case UCrop.RESULT_ERROR:
                if (code == RESULT_OK) {
                    final Throwable cropError = UCrop.getError(data);
                    handleCropError(cropError);
                    uCropCallBack.resultError(cropError);
                }
                break;
        }
    }

    private void handleCropError(Throwable cropError) {
        deleteTempPhotoFile();
        if (cropError != null) {
            ToastUtils.showShort(cropError.getMessage());
        } else {
            ToastUtils.showShort("无法剪切选择图片");
        }
    }

    /**
     * 删除拍照临时文件
     */
    private void deleteTempPhotoFile() {
        File tempFile = new File(Environment.getExternalStorageDirectory() + File.separator + "output_iamge.jpg");
        if (tempFile.exists() && tempFile.isFile()) {
            tempFile.delete();
        }
    }


    public interface UCropCallBack {
        /**
         * 裁剪后的效果
         *
         * @param output
         */
        void requestCrop(Uri output);

        /**
         * 错误裁剪的结果
         */
        void resultError(Throwable e);
    }
}
