package com.iqb.api.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.iqb.api.net.http.config.EncryptCons;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Provider;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public class SPUtils {

    private SPUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context 上下文
     * @param key     key
     * @param object  value
     */
    public static void put(Context context, String fileName, String key, Object object) {
        if (object == null) {
            return;
        }
        key = EncryptUtils.encryptMD5ToString(key);
        SharedPreferences sp = context.getSharedPreferences(fileName,
                Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sp.edit();
        if (object instanceof Set) {
            editor.putStringSet(key, (Set) object);
        } else if (object instanceof String) {


            String value = (String) object;
            if (TextUtils.isEmpty(value)) {
                editor.putString(key, "");
            } else {
                byte[] bytes = EncryptUtils.encryptAES2Base64(((String) object).getBytes(), generateAesKey(EncryptCons.ENCRYPT_KEY),
                        EncryptCons.AES_CBC_PKCS5_PADDING, EncryptCons.ENCRYPT_IV.getBytes());
                editor.putString(key, new String(bytes, Charset.forName("UTF-8")));
            }
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }

        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     */
    public static Object get(Context context, String fileName, String key, Object defaultObject) {
        key = EncryptUtils.encryptMD5ToString(key);
        SharedPreferences sp = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        if (defaultObject instanceof Set) {
            return sp.getStringSet(key, (Set) defaultObject);
        } else if (defaultObject instanceof String) {


            String value = sp.getString(key, "");
            byte[] bytes = EncryptUtils.decryptBase64AES(value.getBytes(), generateAesKey(EncryptCons.ENCRYPT_KEY),
                    EncryptCons.AES_CBC_PKCS5_PADDING, EncryptCons.ENCRYPT_IV.getBytes());
            if (bytes != null && bytes.length != 0) {
                value = new String(bytes, Charset.forName("UTF-8"));
            } else {
                value = (String) defaultObject;
            }
            return value;
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }

        return null;
    }

    /**
     * 针对复杂类型存储<对象>
     *
     * @param context  上下文
     * @param fileName 文件名
     * @param key      key
     * @param object   value
     */
    public static void putObject(Context context, String fileName, String key, Object object) {
        key = EncryptUtils.encryptMD5ToString(key);
        SharedPreferences sp = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(baos);
            out.writeObject(object);
            String objectVal = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            editor.putString(key, objectVal);
            SharedPreferencesCompat.apply(editor);

        } catch (IOException e) {
        } finally {
            CloseUtils.closeIoQuietly(baos);
        }
        SharedPreferencesCompat.apply(editor);
    }


    @SuppressWarnings("unchecked")
    public static <T> T getObject(Context context, String fileName, String key, Class<T> clazz) {
        key = EncryptUtils.encryptMD5ToString(key);
        SharedPreferences sp = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        if (sp.contains(key)) {
            String objectVal = sp.getString(key, null);
            byte[] buffer = Base64.decode(objectVal, Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(bais);
                return (T) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
            } finally {
                CloseUtils.closeIoQuietly(bais, ois);
            }
        }
        return null;
    }

    /**
     * 存储list
     *
     * @param context  上下文
     * @param fileName 文件名
     * @param key      key
     * @param list     value
     */
    public static void putList(Context context, String fileName, String key, List list) {
        key = EncryptUtils.encryptMD5ToString(key);
        SharedPreferences sp = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        // 实例化一个ByteArrayOutputStream对象，用来装载压缩后的字节文件。
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 然后将得到的字符数据装载到ObjectOutputStream
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            // writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
            objectOutputStream.writeObject(list);
            // 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
            String sceneListString = new String(Base64.encode(
                    byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
            editor.putString(key, sceneListString);
        } catch (IOException e) {
        } finally {
            CloseUtils.closeIoQuietly(objectOutputStream);
        }
        SharedPreferencesCompat.apply(editor);

    }

    public static List getList(Context context, String fileName, String key) {
        key = EncryptUtils.encryptMD5ToString(key);
        List list = null;
        SharedPreferences sp = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        String sceneListString = sp.getString(key, "");
        byte[] mobileBytes = Base64.decode(sceneListString.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                mobileBytes);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(
                    byteArrayInputStream);
            list = (List) objectInputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            list = new ArrayList();
        } finally {
            CloseUtils.closeIoQuietly(objectInputStream);
        }
        return list;

    }


    /**
     * 移除某个key值已经对应的值
     *
     * @param context 上下文
     * @param key     key
     */
    public static void remove(Context context, String fileName, String key) {
        key = EncryptUtils.encryptMD5ToString(key);
        SharedPreferences sp = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     */
    public static void clear(Context context, String fileName) {
        SharedPreferences sp = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context 上下文
     * @param key     key
     * @return 是否存在
     */
    public static boolean contains(Context context, String fileName, String key) {
        key = EncryptUtils.encryptMD5ToString(key);
        SharedPreferences sp = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @param context 上下文
     * @return 返回所有的键值对
     */
    public static Map<String, ?> getAll(Context context, String fileName) {
        SharedPreferences sp = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        return sp.getAll();
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class<SharedPreferences.Editor> clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException ignored) {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         */
        static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException ignored) {
            }
            editor.commit();
        }
    }


    /**
     * 对于外部不可见的过渡方法
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @SuppressWarnings("unchecked")
    private <T> T ge0tValue(String key, Class<T> clazz, SharedPreferences sp) {
        T t;
        try {
            t = clazz.newInstance();
            if (t instanceof Integer) {
                return (T) Integer.valueOf(sp.getInt(key, 0));
            } else if (t instanceof String) {
                return (T) sp.getString(key, "");
            } else if (t instanceof Boolean) {
                return (T) Boolean.valueOf(sp.getBoolean(key, false));
            } else if (t instanceof Long) {
                return (T) Long.valueOf(sp.getLong(key, 0L));
            } else if (t instanceof Float) {
                return (T) Float.valueOf(sp.getFloat(key, 0L));
            }
        } catch (InstantiationException | IllegalAccessException e) {
        }
        Log.e("system", "无法找到" + key + "对应的值");
        return null;
    }


    @SuppressLint("DeletedProvider")
    private static byte[] generateAesKey(String seed) {
        byte[] enCodeFormat = null;
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom;
            if (Build.VERSION.SDK_INT >= 28) {
                byte[] passwordBytes = seed.getBytes(StandardCharsets.US_ASCII);
                enCodeFormat = InsecureSHA1PRNGKeyDerivator.deriveInsecureKey(passwordBytes, 32);
                return enCodeFormat;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                secureRandom = SecureRandom.getInstance("SHA1PRNG", new CryptoProvider());
            } else {
                secureRandom = SecureRandom.getInstance("SHA1PRNG", "Crypto");
            }
            secureRandom.setSeed(seed.getBytes());
            keyGenerator.init(128, secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            enCodeFormat = secretKey.getEncoded();
        } catch (Exception ignored) {
        }
        return enCodeFormat;
    }

    // 增加  CryptoProvider  类
    private static class CryptoProvider extends Provider {
        /**
         * Creates a Provider and puts parameters
         */
        private CryptoProvider() {
            super("Crypto", 1.0, "HARMONY (SHA1 digest; SecureRandom; SHA1withDSA signature)");
            super.put("SecureRandom.SHA1PRNG",
                    "org.apache.harmony.security.provider.crypto.SHA1PRNG_SecureRandomImpl");
            super.put("SecureRandom.SHA1PRNG ImplementedIn", "Software");
        }
    }
}  