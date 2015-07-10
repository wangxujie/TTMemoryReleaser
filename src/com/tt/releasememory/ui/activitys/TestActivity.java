package com.tt.releasememory.ui.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tt.releasememory.R;
import com.tt.releasememory.R.layout;
import com.tt.releasememory.services.ReleaseService;

public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

    }

    public void start(View v) {
        Intent intent = new Intent(TestActivity.this, ReleaseService.class);
        startService(intent);
        // Intent intent = new Intent("release");
        // localBroadcastManager.sendBroadcast(intent);
    }

    public void stop(View v) {
        Intent intent = new Intent(TestActivity.this, ReleaseService.class);
        stopService(intent);
    }

}
