/*
 * FileName:	Config.java
 * Copyright:	kyson
 * Author: 		kysonX
 * Description:	<文件描述>
 * History:		2014-11-23 1.00 初始版本
 */
package com.tt.releasememory.helpers;

/**
 * 可配置项<功能简述> <Br>
 * * 可配置参数包括<br>
 * 1.气球个数<br>
 * 2.气球飞行时间<br>
 * 3.拉线长度<br>
 * 4.下拉灵敏度<br>
 * <功能详细描述> <Br>
 * 
 * @author kysonX
 */
public class ConfigHelper {
    /**
     * 气球飞行速度 <功能简述> </Br> <功能详细描述> </Br>
     * 
     * @author kysonX
     */
    public enum V {
        FASTER, FAST, MIDDLE, SLOW, SLOWER;
    }

    /**
     * 下拉线的敏感度 <功能简述> </Br> <功能详细描述> </Br>
     * 
     * @author kysonX
     */
    public enum Sensitivity {
        SENSITIVE_MORE, SENSITIVE, MIDDLE, INSENSITIVE, INSENSITIVE_MORE;
    }

    // 气球个数
    private static int BALLON_COUNT = 5;
    // 气球飞行速度(时间)
    private static long DURATION = 2500;
    // 拉线长度
    private static int LINE_ORI_Y_OFFSET = 72;
    // 下拉灵敏度
    private static float PULL_SENSITIVITY = 1.8f;

    public static void setBalloonCount(int count) {
        BALLON_COUNT = count;
    }

    public static int getBalloonCount() {
        return BALLON_COUNT;
    }

    public static void setBalloonV(V v) {
        switch (v) {
        case FASTER:
            DURATION = 1500;
            break;
        case FAST:
            DURATION = 2000;
            break;
        case MIDDLE:
        default:
            DURATION = 2500;
            break;
        case SLOW:
            DURATION = 3000;
            break;
        case SLOWER:
            DURATION = 3500;
            break;
        }
    }

    public static void setBalloonDuration(long duration) {
        DURATION = duration;

    }

    public static V getBalloonV() {
        if (DURATION == 1500) {
            return V.FASTER;
        } else if (DURATION == 2000) {
            return V.FAST;
        } else if (DURATION == 2500) {
            return V.MIDDLE;
        } else if (DURATION == 3000) {
            return V.SLOW;
        } else if (DURATION == 3500) {
            return V.SLOWER;
        }
        return V.MIDDLE;
    }

    public static long getBalloonDuration() {
        return DURATION;
    }

    /**
     * 拉线长度不超过屏幕的1/3 <功能简述>
     * 
     * @param len
     */
    public static void setLineOriLen(int len) {
        LINE_ORI_Y_OFFSET = len;
    }

    public static int getLineOriLen() {
        return LINE_ORI_Y_OFFSET;
    }

    public static void setPullSensitivity(Sensitivity sen) {
        switch (sen) {
        case SENSITIVE_MORE:
            PULL_SENSITIVITY = 1.2f;
            break;
        case SENSITIVE:
            PULL_SENSITIVITY = 1.5f;
            break;
        case MIDDLE:
        default:
            PULL_SENSITIVITY = 1.8f;
            break;
        case INSENSITIVE:
            PULL_SENSITIVITY = 2.1f;
            break;
        case INSENSITIVE_MORE:
            PULL_SENSITIVITY = 2.4f;
            break;
        }
    }

    public static void setPullSensitivity(float sen) {
        PULL_SENSITIVITY = sen;
    }

    public static Sensitivity getPullSensitivityEnum() {
        if (PULL_SENSITIVITY == 1.2f) {
            return Sensitivity.SENSITIVE_MORE;
        } else if (PULL_SENSITIVITY == 1.5f) {
            return Sensitivity.SENSITIVE;
        } else if (PULL_SENSITIVITY == 1.8f) {
            return Sensitivity.MIDDLE;
        } else if (PULL_SENSITIVITY == 2.1f) {
            return Sensitivity.INSENSITIVE;
        } else if (PULL_SENSITIVITY == 2.4f) {
            return Sensitivity.INSENSITIVE_MORE;
        }
        return Sensitivity.MIDDLE;
    }

    public static float getPullSensitivity() {
        return PULL_SENSITIVITY;
    }

    private static boolean sIsOnlyDestop;

    /**
     * @return 返回 sIsOnlyDestop
     */
    public static boolean isOnlyDestop() {
        return sIsOnlyDestop;
    }

    /**
     * @param 对sIsOnlyDestop进行赋值
     */
    public static void setIsOnlyDestop(boolean sIsOnlyDestop) {
        ConfigHelper.sIsOnlyDestop = sIsOnlyDestop;
    }

}
