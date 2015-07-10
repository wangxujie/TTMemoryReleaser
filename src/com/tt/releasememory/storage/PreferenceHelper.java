/*
 * FileName:	PreferenceHelper.java
 * Copyright:	kyson
 * Author: 		kysonX
 * Description:	<文件描述>
 * History:		2014-11-23 1.00 初始版本
 */
package com.tt.releasememory.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.tt.releasememory.helpers.ConfigHelper;

/**
 * 存储变量 <功能简述> <Br>
 * <功能详细描述> <Br>
 * 
 * @author kysonX
 */
public class PreferenceHelper {
    public static final String CONFIG = "config";

    public static final String KEY_BC = "balloon_count";
    public static final String KEY_BD = "balloon_duration";
    public static final String KEY_LL = "line_len";
    public static final String KEY_PS = "pull_sen";
    public static final String KEY_IS_RUNNING = "is_running";
    public static final String KEY_ONLY_DESTOP = "only_destop";
    
    /**
     * 退出应用调用 <功能简述>
     * 
     * @param context
     */
    public static void set(Context context) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG,
                Context.MODE_PRIVATE);
        sp.edit().putInt(KEY_BC, ConfigHelper.getBalloonCount())
                .putLong(KEY_BD, ConfigHelper.getBalloonDuration())
                .putInt(KEY_LL, ConfigHelper.getLineOriLen())
                .putFloat(KEY_PS, ConfigHelper.getPullSensitivity()).commit();
    }

    /**
     * 进入应用时调用 <功能简述>
     */
    public static void get(Context context) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG,
                Context.MODE_PRIVATE);
        ConfigHelper.setBalloonCount(sp.getInt(KEY_BC, 5));
        ConfigHelper.setBalloonDuration(sp.getLong(KEY_BD, 2500));
        ConfigHelper.setLineOriLen(sp.getInt(KEY_LL, 72));
        ConfigHelper.setPullSensitivity(sp.getFloat(KEY_PS, 1.8f));
    }

    /**
     * 服务是否正在运行 <功能简述>
     * 
     * @param context
     * @param isRunning
     */
    public static void setIsRunning(Context context, boolean isRunning) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG,
                Context.MODE_PRIVATE);
        sp.edit().putBoolean(KEY_IS_RUNNING, isRunning).commit();
    }

    /**
     * 服务是否正在运行 <功能简述>
     * 
     * @param context
     * @param isRunning
     */
    public static boolean isRunning(Context context) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG,
                Context.MODE_PRIVATE);
        return sp.getBoolean(KEY_IS_RUNNING, false);
    }

    /**
     * 设置是否仅在桌面显示 <功能简述>
     * 
     * @param context
     * @param onlyDestop
     */
    public static void setOnlyDestop(Context context, boolean onlyDestop) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG,
                Context.MODE_PRIVATE);
        sp.edit().putBoolean(KEY_ONLY_DESTOP, onlyDestop).commit();
    }

    /**
     * 获取是否在桌面显示 <功能简述>
     * 
     * @param context
     * @return
     */
    public static boolean getOnlyDestop(Context context) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG,
                Context.MODE_PRIVATE);
        return sp.getBoolean(KEY_ONLY_DESTOP, false);
    }
}
