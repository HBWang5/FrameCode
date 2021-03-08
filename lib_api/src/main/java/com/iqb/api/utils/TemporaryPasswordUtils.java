package com.iqb.api.utils;

import java.util.Random;

public class TemporaryPasswordUtils {

    private static TemporaryPasswordUtils mTemporaryPasswordUtils;

    private TemporaryPasswordUtils(){}

    public static synchronized TemporaryPasswordUtils getInstance(){
        if (mTemporaryPasswordUtils == null) {
            mTemporaryPasswordUtils = new TemporaryPasswordUtils();
        }
        return mTemporaryPasswordUtils;
    }

    public String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            stringBuilder.append(str.charAt(number));
        }

        return stringBuilder.toString();
    }

}
