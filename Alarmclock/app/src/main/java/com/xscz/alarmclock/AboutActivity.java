package com.xscz.alarmclock;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

/**
 * Created by lixiang on 2016/7/2.
 */
public class AboutActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mToolbar.setTitleTextColor(getResources().getColor(R.color.wilte));
        TextView viewById = (TextView) findViewById(R.id.infos);
        viewById.setText("\n" +
                "title1 ：番茄工作法\n" +
                "内容：\n" +
                "番茄工作法是简单易行的时间管理方法，是由弗朗西斯科·西里洛于1992年创立的一种相对于GTD更微观的时间管理方法。\n" +
                "使用番茄工作法，选择一个待完成的任务，将番茄时间设为25分钟，专注工作，中途不允许做任何与该任务无关的事，直到番茄时钟响起，然后在纸上画一个X短暂休息一下（5分钟就行），每4个番茄时段多休息一会儿。\n" +
                "番茄工作法极大地提高了工作的效率，还会有意想不到的成就感。\n" +
                "\n" +
                "title2：为什么用番茄工作法\n" +
                "内容：\n" +
                "番茄工作法是一套简单的工具和流程，其优点如下：\n" +
                "1、减轻时间焦虑\n" +
                "2、提升集中力和注意力，减少中断\n" +
                "3、增强决策意识\n" +
                "4、唤醒激励和持久激励\n" +
                "5、巩固达成目标的决心\n" +
                "6、完善预估流程，精确地保质保量\n" +
                "7、改进工作学习流程\n" +
                "8、强化决断力，快刀斩乱麻\n" +
                "\n" +
                "title3 ：原则\n" +
                "内容：\n" +
                "1）一个番茄时间（25分钟）不可分割，不存在半个或一个半番茄时间。\n" +
                "2） 一个番茄时间内如果做与任务无关的事情，则该番茄时间作废。\n" +
                "3）永远不要在非工作时间内使用《番茄工作法》。（例如：用3个番茄时间陪儿子下的棋、用5个番茄时间钓鱼，等等。）\n" +
                "4）不要拿自己的番茄数据与他人的番茄数据比较。\n" +
                "5）番茄的数量不可能决定任务最终的成败。\n" +
                "6）必须有一份适合自己的作息时间表。\n" +
                "\n" +
                "title4：做法：\n" +
                "内容：\n" +
                "1、每天开始的时候规划今天要完成的几项任务，将任务逐项写在列表里（或记在软件的清单里）\n" +
                "2、设定你的番茄钟（定时器、软件、闹钟等），时间是25分钟。\n" +
                "3、开始完成第一项任务，直到番茄钟响铃或提醒（25分钟到）。\n" +
                "4、停止工作，并在列表里该项任务后画个X。\n" +
                "5、休息3~5分钟，活动、喝水、方便等等。\n" +
                "6、开始下一个番茄钟，继续该任务。一直循环下去，直到完成该任务，并在列表里将该任务划掉。\n" +
                "7、每四个番茄钟后，休息25分钟。\n" +
                "在某个番茄钟的过程里，如果突然想起要做什么事情——\n" +
                "a.非得马上做不可的话，停止这个番茄钟并宣告它作废（哪怕还剩5分钟就结束了），去完成这件事情，之后再重新开始同一个番茄钟；\n" +
                "b.不是必须马上去做的话，在列表里该项任务后面标记一个逗号（表示打扰），并将这件事记在另一个列表里（比如叫“计划外事件”），然后接着完成这个番茄钟。\n");
    }
}
