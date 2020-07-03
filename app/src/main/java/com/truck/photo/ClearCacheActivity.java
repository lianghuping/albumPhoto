package com.truck.photo;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ClearCacheActivity extends AppCompatActivity {

    private ImageView imageview_clear;
    private ImageView imageview_clear2;
    private RecyclerView recyclerview_clear;
    private TextView tv_clear;
    private ArrayList<ClearCacheBean> clearCacheBeans;
    Handler handler = new Handler();
    private ImageView imageview_clear_back;
    private TextView tv_cleat_sizenum;
    private TextView tv_cleat_huancun;
    private TextView tv_cleat_sizedanwei;
    private TextView tv_cleat_wenben;
    private int num = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_cache);
        initView();
    }

    private void initView() {
        imageview_clear = (ImageView) findViewById(R.id.imageview_clear);
        imageview_clear2 = (ImageView) findViewById(R.id.imageview_clear2);
        recyclerview_clear = (RecyclerView) findViewById(R.id.recyclerview_clear);
        tv_clear = (TextView) findViewById(R.id.tv_clear);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ClearCacheActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerview_clear.setLayoutManager(linearLayoutManager);
        clearCacheBeans = new ArrayList<>();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Drawable drawable = getDrawable(R.drawable.ic_launcher_background);
            clearCacheBeans.add(new ClearCacheBean("QQ", "", drawable, 0));
            clearCacheBeans.add(new ClearCacheBean("微信", "", drawable, 0));
            clearCacheBeans.add(new ClearCacheBean("支付宝", "", drawable, 0));
            clearCacheBeans.add(new ClearCacheBean("手机淘宝", "", drawable, 0));
            clearCacheBeans.add(new ClearCacheBean("抖音短视频", "", drawable, 0));
            clearCacheBeans.add(new ClearCacheBean("系统缓存", "", drawable, 0));
            clearCacheBeans.add(new ClearCacheBean("", "", drawable, 0));
            clearCacheBeans.add(new ClearCacheBean("QQ", "", drawable, 0));
            clearCacheBeans.add(new ClearCacheBean("QQ", "", drawable, 0));
            clearCacheBeans.add(new ClearCacheBean("QQ", "", drawable, 0));
        }
        final ClearCacheAdapter clearCacheAdapter = new ClearCacheAdapter(R.layout.item_clearcache, clearCacheBeans, this);
        recyclerview_clear.setAdapter(clearCacheAdapter);

        ClearCacheUtil.getInstance().getCleatData(this, new ClearCacheUtil.CallBackData() {
            @Override
            public void getdata(final List<ClearCacheBean> list) {
                clearCacheBeans.clear();
                clearCacheBeans.addAll(list);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        clearCacheAdapter.notifyDataSetChanged();

                        tv_clear.setVisibility(View.VISIBLE);
                        ObjectAnimator translations = new ObjectAnimator().ofFloat(tv_clear, "translationY", dip2px(ClearCacheActivity.this, 100), 10, 0);
                        translations.setDuration(500);
                        translations.start();
                        ObjectAnimator translations2 = new ObjectAnimator().ofFloat(recyclerview_clear, "translationY", 0, dip2px(ClearCacheActivity.this, -100), dip2px(ClearCacheActivity.this, -90));
                        translations2.setDuration(500);
                        translations2.start();

                        ObjectAnimator anim = ObjectAnimator.ofFloat(imageview_clear, "alpha", 1f, 0f);
                        anim.setDuration(500);// 动画持续时间
                        anim.start();

                        ObjectAnimator anim2 = ObjectAnimator.ofFloat(imageview_clear2, "alpha", 0f, 1f);
                        anim2.setDuration(500);// 动画持续时间
                        anim2.start();

                        ValueAnimator valueAnimator = ValueAnimator.ofInt(dip2px(ClearCacheActivity.this, 360), dip2px(ClearCacheActivity.this, 240)).setDuration(300);
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                imageview_clear.getLayoutParams().height = (int) animation.getAnimatedValue();
                                imageview_clear.requestLayout();
                            }
                        });
                        valueAnimator.start();

                        ValueAnimator valueAnimator1 = ValueAnimator.ofInt(dip2px(ClearCacheActivity.this, 360), dip2px(ClearCacheActivity.this, 240)).setDuration(300);
                        valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                imageview_clear2.getLayoutParams().height = (int) animation.getAnimatedValue();
                                imageview_clear2.requestLayout();
                            }
                        });
                        valueAnimator1.start();
                        num = 0;
                        for (int i = 0; i < list.size(); i++) {
                            num += list.get(i).getSize();
                        }
                        float i = (float) formatFileSizeint(num);
                        DecimalFormat df = new DecimalFormat("#.0");
                        String format = df.format(i);
                        float v = Float.parseFloat(format);
                        Log.e("tag", "currentValue=" + num);
                        //在2000毫秒内，将值从0过渡到1的动画
                        ValueAnimator anims = ValueAnimator.ofFloat(0, v);
                        anims.setDuration(2000);
                        anims.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                float animatedValue = (float) valueAnimator.getAnimatedValue();
                                DecimalFormat df = new DecimalFormat("#.0");
                                String format = df.format(animatedValue);
                                float v = Float.parseFloat(format);
                                tv_cleat_sizenum.setText(v + "");
                            }
                        });
                        anims.start();
                    }
                });
            }
        });


        imageview_clear_back = (ImageView) findViewById(R.id.imageview_clear_back);
        tv_cleat_sizenum = (TextView) findViewById(R.id.tv_cleat_sizenum);
        tv_cleat_huancun = (TextView) findViewById(R.id.tv_cleat_huancun);
        tv_cleat_sizedanwei = (TextView) findViewById(R.id.tv_cleat_sizedanwei);
        tv_cleat_wenben = (TextView) findViewById(R.id.tv_cleat_wenben);
        imageview_clear_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        tv_cleat_sizenum.setOnClickListener(this);
//        tv_cleat_huancun.setOnClickListener(this);
//        tv_cleat_sizedanwei.setOnClickListener(this);
//        tv_cleat_wenben.setOnClickListener(this);
//        lin_clear.setOnClickListener(this);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    public double formatFileSizeint(long fileS) {
        double fileSizeString;
        double wrongSize = 0;
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = (double) fileS;//b
            tv_cleat_sizedanwei.setText("b");
        } else if (fileS < 1048576) {
            fileSizeString = (double) fileS / 1024;//+ "KB"
            tv_cleat_sizedanwei.setText("KB");
        } else if (fileS < 1073741824) {
            fileSizeString = (double) fileS / 1048576;//+ "MB"
            tv_cleat_sizedanwei.setText("MB");
        } else {
            fileSizeString = (double) fileS / 1073741824;//+ "GB"
            tv_cleat_sizedanwei.setText("GB");
        }
        return fileSizeString;
    }

}
