package com.xscz.alarmclock;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.TextView;

/**
 * Created by lixiangos0170 on 2016/7/1.
 */
public class MyService extends Service {

    private static int minute = -1;
    private static int second = -1;
    private static TextView times;
    private static boolean STATE_SLEEP_25 = false, STATE_SLEEP_5 = false;
    ///计数器
    private int count = 0;
    private Handler mHandler = new Handler();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mHandler.postDelayed(runnable, 1000);
            if (minute == 0) {
                if (second == 0) {
                    //删除计时任务
                    mHandler.removeCallbacks(runnable);

                    count++;

                    if (STATE_SLEEP_25) {
                        MainActivity.work();
                        timerWork(25, 1);
                        //变量还原
                        STATE_SLEEP_25 = false;
                        return;
                    } else if (STATE_SLEEP_5) {
                        MainActivity.work();
                        timerWork(25, 1);
                        //变量还原
                        STATE_SLEEP_5 = false;
                        return;
                    } else if (count % 4 == 0) {
                        MainActivity.sleep();
                        timerWork(25, 1);
                        //记录一个变量，休息25分钟
                        STATE_SLEEP_25 = true;
                    } else {
                        MainActivity.sleep();
                        timerWork(5, 1);
                        STATE_SLEEP_5 = true;
                    }
                } else {
                    second--;
                    if (second >= 10) {
                        times.setText("0" + minute + ":" + second);
                    } else {
                        times.setText("0" + minute + ":0" + second);
                    }
                }
            } else {
                if (second == 0) {
                    second = 59;
                    minute--;
                    if (minute >= 10) {
                        times.setText(minute + ":" + second);
                    } else {
                        times.setText("0" + minute + ":" + second);
                    }
                } else {
                    second--;
                    if (second >= 10) {
                        if (minute >= 10) {
                            times.setText(minute + ":" + second);
                        } else {
                            times.setText("0" + minute + ":" + second);
                        }
                    } else {
                        if (minute >= 10) {
                            times.setText(minute + ":0" + second);
                        } else {
                            times.setText("0" + minute + ":0" + second);
                        }
                    }
                }
            }
        }
    };

    public static void setView(TextView times) {
        MyService.times = times;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //开始工作
        timerWork(25, 1);
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 倒计时开始
     */
    private void timerWork(int m, int s) {
        this.minute = m;
        this.second = s;
        mHandler.post(runnable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(runnable);
    }
}
