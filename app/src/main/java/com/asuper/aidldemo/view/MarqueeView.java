package com.asuper.aidldemo.view;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.asuper.aidldemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author super
 * @date 2017/12/21
 */
public class MarqueeView extends RelativeLayout implements Runnable{

    private static final int TIME_COUNT = 6000;

    private Context context;
    private LinearLayout mainLayout;
    private int scrollSpeed = 1;
    private int scrollDirection = RIGHT_TO_LEFT;
    private int currentX;
    private int viewMargin = dip2px(30);
    private int viewWidth;
    private int screenWidth;
    private List<RelativeLayout> mList;
    private CountDownTimer mTimer;
    private boolean isStop = false;
    private int winnerCount;

    public static final int LEFT_TO_RIGHT = 1;
    public static final int RIGHT_TO_LEFT = 2;

    public MarqueeView(Context context) {
        this(context, null);
    }

    public MarqueeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarqueeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        mList = new ArrayList<>();
        initView();
    }

    void initView() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, dip2px(220));
        mainLayout = new LinearLayout(context);
        mainLayout.setOrientation(LinearLayout.HORIZONTAL);
        mainLayout.setLayoutParams(params);
        this.addView(mainLayout);
    }

    public void addData(List<String> list){
        if (list == null || list.isEmpty()) {
            return;
        }

        if (list.size() > 20) {
            list = list.subList(0, 19);
        }

        winnerCount = list.size();

        if (mList == null) {
            mList = new ArrayList<>();
        }
        mList.clear();

        for (int i = 0; i < list.size(); i++) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dip2px(90),
                    LinearLayout.LayoutParams.MATCH_PARENT);
            RelativeLayout view = new RelativeLayout(context);
            RelativeLayout.LayoutParams lpLayout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            if (isOdd(i)) {
                lpLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            } else {
                lpLayout.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            }
            view.addView(linearLayout, lpLayout);

            LinearLayout.LayoutParams lpIv = new LinearLayout.LayoutParams(dip2px(90), dip2px(90));
            lpIv.gravity = Gravity.CENTER_HORIZONTAL;
            ImageView iv = new ImageView(context);
            iv.setImageResource(R.mipmap.bang_start_tip);
            linearLayout.addView(iv, lpIv);

            LinearLayout.LayoutParams lpTv = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            lpTv.gravity = Gravity.CENTER_HORIZONTAL;
            lpTv.topMargin = dip2px(15);
            TextView textView = new TextView(context);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            textView.setTextColor(context.getResources().getColor(R.color.kk_ffffff));
            // TODO: 2017/12/21 四个字限制
            textView.setText(list.get(i));
            linearLayout.addView(textView, lpTv);

            lp.setMargins(viewMargin, 0, 0, 0);
            view.setLayoutParams(lp);
            mainLayout.addView(view);
            view.measure(0, 0);
            mList.add(view);
            viewWidth = viewWidth + view.getMeasuredWidth() + viewMargin;
        }
    }

    public void startScroll(){
        setVisibility(View.VISIBLE);
        removeCallbacks(this);
        currentX = (scrollDirection == LEFT_TO_RIGHT ? viewWidth : -screenWidth);
        isStop = false;
        mTimer = new CountDownTimer(TIME_COUNT, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                stopScroll();
            }
        };
        mTimer.start();
        post(this);
    }

    public void stopScroll(){
        isStop = true;
        removeCallbacks(this);
        if (mList != null && !mList.isEmpty()) {
            for (RelativeLayout view : mList) {
                mainLayout.removeView(view);
            }
            mList.clear();
        }
        if (mTimer != null) {
            mTimer.cancel();
        }
    }

    public void setViewMargin(int viewMargin){
        this.viewMargin = viewMargin;
    }


    public void setScrollDirection(int scrollDirection){
        this.scrollDirection = scrollDirection;
    }

    @Override
    public void run() {
        Log.i("DMC", "currentX =" + scrollSpeed);
        if (isStop) {
            return;
        }
        switch (scrollDirection){
            case LEFT_TO_RIGHT:
                mainLayout.scrollTo(currentX, 0);
                currentX -= 6;

                if (-currentX >= screenWidth) {
//                    mainLayout.scrollTo(viewWidth, 0);
//                    currentX = viewWidth;
                    stopScroll();
                    setVisibility(View.GONE);
                }
                break;
            case RIGHT_TO_LEFT:
                mainLayout.scrollTo(currentX, 0);
                currentX += 6;

                if (currentX >= viewWidth) {
//                    mainLayout.scrollTo(-screenWidth, 0);
//                    currentX = -screenWidth;
                    stopScroll();
                    setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }

        if (winnerCount <= 5) {
            scrollSpeed = 12;
        } else if (winnerCount <= 10) {
            scrollSpeed = 9;
        } else if (winnerCount <= 15) {
            scrollSpeed = 6;
        } else {
            scrollSpeed = 3;
        }

        postDelayed(this, scrollSpeed);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    public int dip2px(float dip) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dip, getContext().getApplicationContext().getResources().getDisplayMetrics()
        );
    }

    public boolean isOdd(int a) {
        if((a & 1) != 1){
            return true;
        }
        return false;
    }
}