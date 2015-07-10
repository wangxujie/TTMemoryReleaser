/*
 * FileName:	AdConfig.java
 * Copyright:	kyson
 * Author: 		kysonX
 * Description:	<文件描述>
 * History:		2014-11-29 1.00 初始版本
 */
package com.tt.releasememory.configs;

import android.content.Context;

/**
 * 广告的设置 <功能简述> </Br> <功能详细描述> </Br>
 * 
 * @author kysonX
 */
public class AdConfig {
    // 广告的key
    public static final String KEY_AD = "ad";
    // 广告关闭
    public static final String VALUE_AD_CLOSE = "close";
    // 广告打开
    public static final String VALUE_AD_OPEN = "open";

    private static boolean sCanAd;

    /**
     * @return 返回 sCanAd
     */
    public static boolean isCanAd() {
        return sCanAd;
    }

    /**
     * @param 对sCanAd进行赋值
     */
    public static void setCanAd(boolean canAd) {
        AdConfig.sCanAd = canAd;
    }

    /**
     * 设置广告的打开关闭<功能简述>
     */
    public static void setupAd(Context context) {
        //        AdManager.getInstance(context).asyncGetOnlineConfig(KEY_AD,
        //                new OnlineConfigCallBack() {
        //                    @Override
        //                    public void onGetOnlineConfigSuccessful(String key,
        //                            String value) {
        //                        // 获取在线参数成功
        //                        setCanAd(value.equals(VALUE_AD_OPEN));
        //                    }
        //
        //                    @Override
        //                    public void onGetOnlineConfigFailed(String key) {
        //                        // 获取在线参数失败，可能原因有：键值未设置或为空、网络异常、服务器异常
        //                        setCanAd(false);
        //                    }
        //                });
    }

}
