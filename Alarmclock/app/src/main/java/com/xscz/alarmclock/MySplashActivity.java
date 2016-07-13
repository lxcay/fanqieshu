package com.xscz.alarmclock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.tencent.stat.StatService;
import com.xscz.alarmclock.ads.SplashActivity;
import com.tencent.stat.StatConfig;
/**
 * Created by lixiang on 2016/7/3.
 */
public class MySplashActivity extends Activity {
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //MTA统计
//        StatConfig.setDebugEnable(true);
        StatService.trackCustomEvent(this, "onCreate", "");

        setContentView(R.layout.splash_launcher);
        handler.postDelayed(runnable, 800);

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(MySplashActivity.this, SplashActivity.class));
            finish();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
