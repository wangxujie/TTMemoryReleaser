/*
 * FileName:	BootReceiver.java
 * Copyright:	kyson
 * Author: 		kysonX
 * Description:	<文件描述>
 * History:		2014-11-24 1.00 初始版本
 */
package com.tt.releasememory.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tt.releasememory.services.ReleaseService;
import com.tt.releasememory.storage.PreferenceHelper;
import com.tt.releasememory.ui.widgets.ReleaseToast;
import com.tt.releasememory.utils.ServiceUtil;

/**
 * 开机启动接收器<功能简述> <Br>
 * <功能详细描述> <Br>
 * 
 * @author kysonX
 */
public class BootBroadcastReceiver extends BroadcastReceiver {
    // 重写onReceive方法
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean b = ServiceUtil.isWorked(context,
                ReleaseService.class.getName());
        if (PreferenceHelper.isRunning(context) && !b) {
            ReleaseService.startService(context);
        }
    }

}
