package com.iqb.api.net.tools;


import com.iqb.api.BuildConfig;
import com.iqb.api.utils.DeviceUtils;
import com.iqb.api.utils.EncryptUtils;
import com.iqb.api.utils.SPHelper;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RequestUtils {

    /**
     * Header参数
     */
    public static Map<String, String> getHeadParams(Map<String, String> headers) {
        Map<String, String> headParams = new HashMap<>();
        headParams.put("Did", getDid());
        if (headers != null && !headers.isEmpty()) {
            headParams.putAll(headers);
        }
        return headParams;
    }

    /**
     * 获取query参数
     *
     * @param path       请求路径
     * @param signParams 需要签名的参数
     * @return query参数
     */
    public static Map<String, Object> getQueryParams(String path, Map<String, Object> signParams) {
        HashMap<String, Object> queryParams = new HashMap<>();
//        queryParams.put("appkey", BuildConfig.APP_KEY);
//        queryParams.put("signt", String.valueOf(System.currentTimeMillis()));
//        queryParams.put("nonce", String.valueOf(UUID.randomUUID()));
//        queryParams.putAll(signParams);
//        queryParams.put("sign", sign(path, queryParams, BuildConfig.SECRET_KEY).toLowerCase());
        return queryParams;
    }

    public static Map<String, Object> getCdpQueryParams(String path, Map<String, Object> signParams) {
        if (path.startsWith("test/")) {
            path = path.substring(5);
        }
        HashMap<String, Object> queryParams = new HashMap<>();
//        queryParams.put("appkey", BuildConfig.APP_KEY);
//        queryParams.put("signTimestamp", String.valueOf(System.currentTimeMillis()));
//        queryParams.put("nonce", String.valueOf(UUID.randomUUID()));
//        queryParams.putAll(signParams);
//        queryParams.put("digitalSign", sign(path, queryParams, BuildConfig.SECRET_KEY).toLowerCase());
        return queryParams;
    }


    public static void showParams(Map<String, Object> request) {
        if (request == null || request.isEmpty()) {
//            LogUtils.d("no requestParams");
        }
//        LogUtils.d("[http request]:" + new Gson().toJson(request));
    }

    public static void logParams(Map<String, Object> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
//        LogUtils.d("[http request]:" + sb.toString());
    }

    /**
     * 生成签名参数
     *
     * @param path      请求地址（不要前面的“/”）
     * @param params    query参数
     * @param secretKey 配置的secretKey
     * @return 签名参数
     */
    private static String sign(String path, Map<String, Object> params, String secretKey) {
        List<Map.Entry<String, Object>> parameters = new ArrayList<>(params.entrySet());
        Collections.sort(parameters, (o1, o2) -> o1.getKey().compareTo(o2.getKey()));
        StringBuilder sb = new StringBuilder();
        sb.append(path).append("_");

        for (Map.Entry<String, Object> param : parameters) {
            sb.append(param.getKey()).append("=").append(String.valueOf(param.getValue())).append("_");
        }

        sb.append(secretKey);
        try {
            String baseString1 = URLEncoder.encode(sb.toString(), "UTF-8");
            return EncryptUtils.encryptMD5ToString(baseString1);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取did
     *
     * @return //格式：<tenantid>_<client_type>_<device_model>_<device_id>_<os_version>_<app_version>
     */
    private static String getDid() {
        return "APP_" +
                DeviceUtils.getBrand() + "-" + DeviceUtils.getModel() + "_" +
                SPHelper.getIMEI() + "_" +
                "Android" + DeviceUtils.getDeviceOS() + "_" +
                "v" + BuildConfig.VERSION_NAME;
    }

    public static MultipartBody.Part getPart(String paramName, String mediaType, String filePath) {
        File file = new File(filePath);
        RequestBody requestAvatar = RequestBody.create(MediaType.parse(mediaType), file);
        return MultipartBody.Part.createFormData(paramName, file.getName(), requestAvatar);
    }


}
