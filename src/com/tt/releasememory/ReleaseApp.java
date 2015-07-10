/*
 * FileName:	ReleaseApp.java
 * Copyright:	kyson
 * Author: 		kysonX
 * Description:	<文件描述>
 * History:		2014-11-23 1.00 初始版本
 */
package com.tt.releasememory;

import android.app.Application;
import android.content.res.Configuration;

import com.tt.releasememory.services.ReleaseService;
import com.tt.releasememory.storage.PackageInfoStorage;
import com.tt.releasememory.ui.widgets.ReleaseViewManager;
import com.tt.releasememory.utils.ServiceUtil;

/**
 * 全局应用<功能简述> </Br> <功能详细描述> </Br>
 * 
 * @author kysonX
 */
public class ReleaseApp extends Application {

    @Override
    public void onCreate() {
        PackageInfoStorage.updateHomeList(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        boolean b = ServiceUtil.isWorked(this, ReleaseService.class.getName());
        if (b) {
            // 如果服务已经启动了，悬浮窗重新运行
            ReleaseViewManager.getInstance(this).end();
            ReleaseViewManager.getInstance(this).start();
        }
    }
}
