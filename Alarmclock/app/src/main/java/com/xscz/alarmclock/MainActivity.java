package com.xscz.alarmclock;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qq.e.ads.banner.ADSize;
import com.qq.e.ads.banner.AbstractBannerADListener;
import com.qq.e.ads.banner.BannerView;
import com.xscz.alarmclock.ads.Constants;

public class MainActivity extends AppCompatActivity {

    private static TextView times;
    private TextView stop;
    private ImageButton fab;
    private static RelativeLayout layoutBg;
    private static Toolbar toolbar;
    private static int white, blue;

    private static Vibrator vib;
    private static MediaPlayer mediaPlayer3,mediaPlayer2;
    private ViewGroup bannerContainer;
    private BannerView bv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        initViewId();
        initDate();

        /***
         *
         * 默认隐藏
         *
         * stop 和times,并且 layoutBg 为白色，只显示 fab
         */
        initState();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 当fab 点击后即刻隐藏，stop，times 显示，并且 layoutBg 为蓝色
                 */
                clickFabState();
            }
        });

        stop.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                /**
                 * 当stop 长按后即刻隐藏，times 隐藏，fab 显示, loyoutBg 为白色
                 */
                clickStopState();
                return true;
            }
        });
        MyService.setView(times);
    }

    private void initDate() {
        initBanner();
    }

    private void doRefreshBanner() {
        if (bv == null) {
            initBanner();
        }
        bv.loadAD();
    }

    private void initBanner() {
        this.bv = new BannerView(this, ADSize.BANNER, Constants.APPID, Constants.BannerPosID);
        bv.setRefresh(30);
        bv.setADListener(new AbstractBannerADListener() {
            @Override
            public void onNoAD(int arg0) {
                Log.i("AD_DEMO", "BannerNoAD，eCode=" + arg0);
            }

            @Override
            public void onADReceiv() {
                Log.i("AD_DEMO", "ONBannerReceive");
            }
        });
        bannerContainer.addView(bv);
        bv.loadAD();
    }
    private void initViewId() {
        vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);

        mediaPlayer3 = MediaPlayer.create(MainActivity.this, R.raw.p3);
        mediaPlayer2 = MediaPlayer.create(MainActivity.this, R.raw.p2);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                doRefreshBanner();
            }
        });

        setSupportActionBar(toolbar);

        bannerContainer = (ViewGroup) findViewById(R.id.bannerContainer);

        fab = (ImageButton) findViewById(R.id.fab);
        times = (TextView) findViewById(R.id.times);
        stop = (TextView) findViewById(R.id.stop);
        layoutBg = (RelativeLayout) findViewById(R.id.content_main_layout);
        white = getResources().getColor(R.color.sogou_game_sdk_main_background_color);
        blue = getResources().getColor(R.color.blue);
    }


    public static void sleep() {
        layoutBg.setBackgroundColor(blue);
        times.setTextColor(white);
        toolbar.setTitle("休息");

//        if (vib != null)
//            vib.vibrate(500);//只震动50毫秒，一次


        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer2!=null){
                    mediaPlayer2.start();
                }
            }
        });
    }

    public static void work() {
        layoutBg.setBackgroundColor(white);
        times.setTextColor(blue);
        toolbar.setTitle("工作");
//        if (vib != null)
//            vib.vibrate(2500);//只震动50毫秒，一次


        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer3!=null){
                    mediaPlayer3.start();
                }
            }
        });

    }


    /**
     * 当点击stop 之后
     */
    private void clickStopState() {
        /**震动服务*/
        if (vib != null)
            vib.vibrate(50);//只震动50毫秒，一次

        stop.setVisibility(View.GONE);
        times.setVisibility(View.GONE);
        fab.setVisibility(View.VISIBLE);
        layoutBg.setBackgroundColor(white);
        toolbar.setTitle("");

        stopService(new Intent(MainActivity.this, MyService.class));
    }


    /**
     * fab 点击状态
     */
    private void clickFabState() {
        toolbar.setTitle("工作");
        fab.setVisibility(View.GONE);
        stop.setVisibility(View.VISIBLE);
        times.setVisibility(View.VISIBLE);

        times.setTextColor(blue);
        layoutBg.setBackgroundColor(white);

        startService(new Intent(MainActivity.this, MyService.class));

    }

    /**
     * 初始化状态
     */
    private void initState() {
        stop.setVisibility(View.GONE);
        times.setVisibility(View.GONE);
        layoutBg.setBackgroundColor(white);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.info) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
            return true;
        } else if (id == R.id.about) {
            doRefreshBanner();
            Toast.makeText(getApplicationContext(), "番茄树团队出品1.0版本", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.feedback) {
            Intent data = new Intent(Intent.ACTION_SENDTO);
            data.setData(Uri.parse("mailto:feedback@gqyer.com"));
            data.putExtra(Intent.EXTRA_SUBJECT, "这是标题");
            data.putExtra(Intent.EXTRA_TEXT, "这是内容");
            startActivity(data);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (vib != null) {
            mediaPlayer2.release();
            mediaPlayer3.release();
            vib.cancel();
        }

        bannerContainer.removeAllViews();
        bv.destroy();
        bv = null;

        stopService(new Intent(MainActivity.this, MyService.class));
    }
}
