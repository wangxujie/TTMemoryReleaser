package com.tt.releasememory.ui.activitys;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.tt.releasememory.R;
import com.tt.releasememory.configs.AdConfig;
import com.tt.releasememory.helpers.ConfigHelper;
import com.tt.releasememory.helpers.ConfigHelper.Sensitivity;
import com.tt.releasememory.helpers.ConfigHelper.V;
import com.tt.releasememory.services.ReleaseService;
import com.tt.releasememory.storage.PreferenceHelper;
import com.tt.releasememory.utils.IntentUtil;

/**
 * 悬浮窗的配置页面 <功能简述> <Br>
 * 
 * @author Kyson
 */
public class ConfigActivity extends Activity {
    // tanx
    // private BannerProperties properties;
    // private BannerController<?> mController;

    private SeekBar mBalloonCountBar, mBalloonVBar, mLineLenBar, mPullSenBar;

    private TextView mBalloonCountTxt, mBalloonVTxt, mLineLenTxt, mPullSenTxt;

    private CheckBox mIsOnlyDestop;

    private int mMaxLineLen;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        // tanx
        // ViewGroup nat = (ViewGroup)
        // this.findViewById(R.id.activity_config_rl);
        // String slotId = "63916";
        // setupAlimama(nat, slotId);
        boolean isDebug = false;

        AdConfig.setupAd(ConfigActivity.this);

        PreferenceHelper.get(ConfigActivity.this);

        mMaxLineLen = getWindowManager().getDefaultDisplay().getHeight() / 3;
        mBalloonCountBar = (SeekBar) this
                .findViewById(R.id.activity_config_balloon_count_seek);
        mBalloonVBar = (SeekBar) this
                .findViewById(R.id.activity_config_balloon_v_seek);
        mLineLenBar = (SeekBar) this
                .findViewById(R.id.activity_config_line_len_seek);
        mPullSenBar = (SeekBar) this
                .findViewById(R.id.activity_config_pull_sen_seek);
        mBalloonCountTxt = (TextView) this
                .findViewById(R.id.activity_config_balloon_count_txt);
        mBalloonVTxt = (TextView) this
                .findViewById(R.id.activity_config_balloon_v_txt);
        mLineLenTxt = (TextView) this
                .findViewById(R.id.activity_config_line_len_txt);
        mPullSenTxt = (TextView) this
                .findViewById(R.id.activity_config_pull_sen_txt);
        mIsOnlyDestop = (CheckBox) this
                .findViewById(R.id.activity_config_only_destop_check);

        mIsOnlyDestop.setChecked(PreferenceHelper.getOnlyDestop(this));
        ((TextView) this.findViewById(R.id.activity_config_feedback))
                .getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        ((TextView) this.findViewById(R.id.activity_config_share)).getPaint()
                .setFlags(Paint.UNDERLINE_TEXT_FLAG);

        mBalloonCountBar
                .setProgress((int) (((ConfigHelper.getBalloonCount() - 3) * 16.7f) + 8.3f));
        mBalloonCountTxt.setText(ConfigHelper.getBalloonCount() + "个");

        int vProgress = 0;
        switch (ConfigHelper.getBalloonV()) {
        case FASTER:
            vProgress = 90;
            mBalloonVTxt.setText("很快");
            break;
        case FAST:
            vProgress = 70;
            mBalloonVTxt.setText("快");
            break;
        case MIDDLE:
        default:
            vProgress = 50;
            mBalloonVTxt.setText("中等");
            break;
        case SLOW:
            vProgress = 30;
            mBalloonVTxt.setText("慢");
            break;
        case SLOWER:
            vProgress = 10;
            mBalloonVTxt.setText("很慢");
            break;
        }
        mBalloonVBar.setProgress(vProgress);

        mLineLenBar.setProgress(ConfigHelper.getLineOriLen() * 100
                / mMaxLineLen);
        mLineLenTxt.setText(String.valueOf(ConfigHelper.getLineOriLen()));

        int sProgress = 0;
        switch (ConfigHelper.getPullSensitivityEnum()) {
        case SENSITIVE_MORE:
            sProgress = 90;
            mPullSenTxt.setText("很灵敏");
            break;
        case SENSITIVE:
            sProgress = 70;
            mPullSenTxt.setText("灵敏");
            break;
        case MIDDLE:
        default:
            sProgress = 50;
            mPullSenTxt.setText("中等");
            break;
        case INSENSITIVE:
            sProgress = 30;
            mPullSenTxt.setText("迟钝");
            break;
        case INSENSITIVE_MORE:
            sProgress = 10;
            mPullSenTxt.setText("很迟钝");
            break;
        }
        mPullSenBar.setProgress(sProgress);

        mBalloonCountBar
                .setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onProgressChanged(SeekBar seekBar,
                            int progress, boolean fromUser) {
                        int count = 3;
                        if (progress < 16) {
                            count = 3;
                        } else if (progress < 33) {
                            count = 4;
                        } else if (progress < 50) {
                            count = 5;
                        } else if (progress < 67) {
                            count = 6;
                        } else if (progress < 84) {
                            count = 7;
                        } else if (progress <= 100) {
                            count = 8;
                        }
                        mBalloonCountTxt.setText(count + "个");
                        ConfigHelper.setBalloonCount(count);
                    }
                });
        mBalloonVBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                    boolean fromUser) {
                V v = V.MIDDLE;
                if (progress < 20) {
                    mBalloonVTxt.setText("很慢");
                    v = V.SLOWER;
                } else if (progress < 40) {
                    mBalloonVTxt.setText("慢");
                    v = V.SLOW;
                } else if (progress < 60) {
                    mBalloonVTxt.setText("中等");
                    v = V.MIDDLE;
                } else if (progress < 80) {
                    mBalloonVTxt.setText("快");
                    v = V.FAST;
                } else if (progress <= 100) {
                    mBalloonVTxt.setText("很快");
                    v = V.FASTER;
                }
                ConfigHelper.setBalloonV(v);
            }
        });
        mLineLenBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                    boolean fromUser) {
                int len = mMaxLineLen * progress / 100;
                mLineLenTxt.setText(String.valueOf(len));
                ConfigHelper.setLineOriLen(len);
            }
        });
        mPullSenBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                    boolean fromUser) {
                Sensitivity sen = Sensitivity.MIDDLE;
                if (progress < 20) {
                    mPullSenTxt.setText("很迟钝");
                    sen = Sensitivity.INSENSITIVE_MORE;
                } else if (progress < 40) {
                    mPullSenTxt.setText("迟钝");
                    sen = Sensitivity.INSENSITIVE;
                } else if (progress < 60) {
                    mPullSenTxt.setText("中等");
                    sen = Sensitivity.MIDDLE;
                } else if (progress < 80) {
                    mPullSenTxt.setText("灵敏");
                    sen = Sensitivity.SENSITIVE;
                } else if (progress <= 100) {
                    mPullSenTxt.setText("很灵敏");
                    sen = Sensitivity.SENSITIVE_MORE;
                }
                ConfigHelper.setPullSensitivity(sen);
            }
        });

        mIsOnlyDestop.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                    boolean isChecked) {
                PreferenceHelper.setOnlyDestop(ConfigActivity.this, isChecked);
                if (PreferenceHelper.isRunning(ConfigActivity.this)) {
                    ReleaseService.stopService(ConfigActivity.this);
                    ReleaseService.startService(ConfigActivity.this);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceHelper.set(ConfigActivity.this);
    }

    public void start(View v) {
        ReleaseService.startService(ConfigActivity.this);
    }

    public void stop(View v) {
        ReleaseService.stopService(ConfigActivity.this);
    }

    public void share(View v) {
        final String appName = getString(R.string.app_name);
        final String shareFormat = getString(R.string.share);
        final String shareUrl = getString(R.string.share_url);
        //        if (TextUtils.isEmpty(sShareContent)) {
        //            //之前如果没获取到过分享内容，就请求服务器获取一下
        //            getShareContent(new OnlineConfigCallBack() {
        //                @Override
        //                public void onGetOnlineConfigSuccessful(String key, String value) {
        //                    // 获取在线参数成功
        //                    sShareContent = String.format(shareFormat, value);
        //                    shareByContent(appName);
        //                }
        //
        //                @Override
        //                public void onGetOnlineConfigFailed(String key) {
        //                    // 获取在线参数失败，可能原因有：键值未设置或为空、网络异常、服务器异常
        //                    sShareContent = String.format(shareFormat, shareUrl);
        //                    shareByContent(appName);
        //                }
        //            });
        //        } else {
        //            //之前获取过服务器内容了，就不获取了
        //            shareByContent(appName);
        //        }
    }

    private void shareByContent(String appName) {
        IntentUtil.startIntent(ConfigActivity.this, IntentUtil.getShareIntent(
                ConfigActivity.this, appName, sShareContent, "分享"));
    }

    public static String sShareContent;

    public static final String KEY_SHARE = "share";

    // // tanx
    // private void setupAlimama(ViewGroup nat, String slotId) {
    // MmuSDK mmuSDK = MmuSDKFactory.getMmuSDK();
    // // 初始化SDK,该方法必须保证在集成代码前调用，可移到程序入口处调用
    // mmuSDK.init(getApplication());
    // properties = new BannerProperties(slotId, nat);
    // mController = (BannerController<?>) properties.getMmuController();
    // mmuSDK.attach(properties);
    // }

    // // tanx
    // @Override
    // public void onBackPressed() {
    // boolean interrupt = false;
    // if (mController != null) {// 通知Banner推广返回键按下，如果Banner进行了一些UI切换将返回true
    // // 否则返回false(如从 expand状态切换会normal状态将返回true)
    // interrupt = mController.onBackPressed();
    // }
    // if (!interrupt)
    // super.onBackPressed();
    // }

}
