package com.iqb.api.net.http.config;


import com.iqb.api.utils.SPHelper;

public class EncryptCons {
    public static final String ENCRYPT_KEY = SPHelper.getIMEI();
    public static final String ENCRYPT_IV = "fawvw@ebo!#3174@";
    public static final String AES_CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";
    public static final String DESEDE_CBC_PKCS5_PADDING = "DESede/CBC/PKCS5Padding";
    public static final String RSA_ECB_PKCS5_PADDING = "RSA/ECB/PKCS1Padding";

}
