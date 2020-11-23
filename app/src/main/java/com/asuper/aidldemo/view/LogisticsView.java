package com.asuper.aidldemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.asuper.aidldemo.R;
import com.asuper.aidldemo.bean.OrderLogisticsBean;
import com.asuper.aidldemo.bean.OrderLogisticsBean.TracesBean;
import com.asuper.aidldemo.util.Util;

/**
 * @author super
 * @date 11/17/20
 */
public class LogisticsView extends RelativeLayout {
    private static final String TAG = "LogisticsViw";

    private Context mContext;
    private Paint mLinePaint;
    private Path mLinePath;
    private TextPaint mPaint;

    private OrderLogisticsBean logisticsAdapter;

    private float width;
    /**
     * 节点间隔
     */
    int nodeDistance;

    /**
     * 边距
     */
    private int left = 40;
    private int top = 30;
    private int dWidth;
    private int dHeight;


    public LogisticsView(Context context) {
        this(context, null);
    }

    public LogisticsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LogisticsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        width = Util.dip2px(context, 5);
        init();
        setWillNotDraw(false);
    }

    private void init() {
        DashPathEffect pathEffect = new DashPathEffect(new float[]{20f, 10f}, 0);
        mLinePaint = new Paint();
        mLinePaint.reset();
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(Util.dip2px(mContext, 2));
        mLinePaint.setColor(getResources().getColor(R.color.kk_dedede));
        mLinePaint.setAntiAlias(true);
        mLinePaint.setPathEffect(pathEffect);
        mLinePath = new Path();

        mPaint = new TextPaint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(Util.dip2px(mContext, 14));

        nodeDistance = Util.dip2px(mContext, 90);

        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        dWidth = wm.getDefaultDisplay().getWidth();
        dHeight = wm.getDefaultDisplay().getHeight();
    }

    /**
     * 设置适配数据
     */
    public void setLogisticsAdapter(OrderLogisticsBean logisticsAdapter) {
        this.logisticsAdapter = logisticsAdapter;
        TextView tv = new TextView(mContext);
        tv.setText("111111111");
        tv.setLayoutParams(new RelativeLayout.LayoutParams((int) (dWidth * 0.8), nodeDistance));
        tv.setX(width / 2 + left + Util.dip2px(mContext, 12));
        tv.setY(nodeDistance);
        addView(tv);
        requestLayout();
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG, "onDraw");
        super.onDraw(canvas);
        if (logisticsAdapter == null || logisticsAdapter.traces.size() == 0)
            return;

        for (int i = 0; i < logisticsAdapter.traces.size(); i++) {
            TracesBean trace = logisticsAdapter.traces.get(i);

            //画间断线
            if (i != (logisticsAdapter.traces.size() - 1)) {
                mLinePath.moveTo(left + width / 2, i * nodeDistance + top + Util.dip2px(mContext, 10f));
                mLinePath.lineTo(left + width / 2, (i + 1) * nodeDistance + top - Util.dip2px(mContext, 10f));
                canvas.drawPath(mLinePath, mLinePaint);
            }

//            TextView mTvContent = newTvView();
//            mTvContent.setX(width / 2 + left + Util.dip2px(mContext, 12));
//            mTvContent.setY(i * nodeDistance);
//            mTvContent.setText(trace.acceptStation + "\n" + trace.acceptTime);
//            addView(mTvContent);

            if (i == 0) {
                //文字换行
                mPaint.setColor(getResources().getColor(R.color.kk_999999));
                StaticLayout layout = new StaticLayout(trace.acceptStation,
                        mPaint, (int) (dWidth * 0.8), Layout.Alignment.ALIGN_NORMAL,
                        1.5F, 0.0F, true);

                canvas.save();
                canvas.translate(width / 2 + left + Util.dip2px(mContext, 12),
                        i * nodeDistance);
                layout.draw(canvas);
                canvas.restore();//重置

                mPaint.setColor(getResources().getColor(R.color.kk_333333));
                canvas.drawText(trace.acceptTime, width / 2 + left + Util.dip2px(mContext, 12),
                        layout.getHeight() + Util.dip2px(mContext, 20), mPaint);

                //画圆
                mPaint.setColor(getResources().getColor(R.color.colorAccent));
                canvas.drawCircle(width / 2 + left, i * nodeDistance + top, width, mPaint);

            } else {
                mPaint.setColor(getResources().getColor(R.color.kk_d8d8d8));
                canvas.drawCircle(width / 2 + left, i * nodeDistance + top, width, mPaint);

                //画文字
                mPaint.setColor(getResources().getColor(R.color.kk_999999));
                StaticLayout layout = new StaticLayout(trace.acceptStation + "\n" + trace.acceptTime,
                        mPaint, (int) (dWidth * 0.8), Layout.Alignment.ALIGN_NORMAL, 1.5F,
                        0.0F, true);
                canvas.save();
                canvas.translate(width / 2 + left + Util.dip2px(mContext, 12),
                        i * nodeDistance);
                layout.draw(canvas);
                canvas.restore();
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG, "onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (logisticsAdapter == null || logisticsAdapter.traces.size() == 0)
            return;
        setMeasuredDimension(widthMeasureSpec, logisticsAdapter.traces.size() * nodeDistance + top);
    }

//    private TextView newTvView() {
//        TextView tv = new TextView(getContext());
//        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
////        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
//        tv.setLayoutParams(lp);
//        tv.setGravity(Gravity.CENTER);
//        tv.setTextColor(Color.parseColor("#333333"));
//        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
//        return tv;
//    }
}
