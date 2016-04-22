package com.life.lifeconnect;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Administrator on 2016/4/20.
 */
public class LifeConfig {

    private static Context context;
    private static String userID;

    public static void init(Context context) {
        LifeConfig.context = context;
    }

    public static String getApiHost() {
        return "".equals(getString("nomarl")) ? "http://app.tunnel.qydev.com" : "";
    }

    public static String getString(String key) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("config",
                    0);
            return sharedPreferences.getString(key, "");
        } catch (Exception e) {
            Log.e("getStringInfo", e + "");
            return "";
        }
    }

    public static void putString(String key, String value) {
        try {
            //AppContext 这里是Demo里面的Application子类，开发时候需要替换成自己相关的类
            SharedPreferences sharedPreferences = context.getSharedPreferences("config",
                    0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.commit();
        } catch (Exception e) {
            Log.e("putStringInfo", e + "");
        }
    }

    /**
     * 设置用户相关信息
     *
     * @param userID    用户id
     * @param loginTime 登录时间
     * @param checkCode
     */
    public static void setCurrentUserInfo(String userID, String loginTime, String checkCode) {
        LifeConfig.userID = userID;
        putString("userID", userID);
    }

}