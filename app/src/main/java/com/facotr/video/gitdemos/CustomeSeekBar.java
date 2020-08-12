package com.facotr.video.gitdemos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.FileUtils;
import android.provider.CalendarContract;
import android.telecom.Call;
import android.util.AttributeSet;
import android.widget.SeekBar;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author: lixin
 * @time: 2020/7/15
 */
public class CustomeSeekBar extends androidx.appcompat.widget.AppCompatSeekBar {


    private Paint mPaint;
    int mBgWidth = 40;
    int mBgHeight = 40;
    private String mText;
    private float mTextWidth;
    private int mTextBaseLineY;

    public CustomeSeekBar(Context context) {
        super(context);
    }

    public CustomeSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /** * 计算SeekBar数值文字的显示位置 */
    private void getTextLocation() {
        // Paint.FontMetrics fm = mPaint.getFontMetrics();
        mText = ""+getProgress() + "%";
        //测量文字宽度
        mTextWidth = mPaint.measureText(mText);
        //计算文字基线Y坐标
        mTextBaseLineY = mBgHeight ;/// 2 - fm.descent + (fm.descent - fm.ascent) / 2
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        getTextLocation();
        Rect bgRect = getProgressDrawable().getBounds();
        //计算数值背景X坐标
        float bgX = bgRect.width() * getProgress() / getMax();
        //计算数值文字X坐标
        float textX = bgX + (mBgWidth - mTextWidth) / 2 + 10;
        canvas.drawText(mText, textX, mTextBaseLineY, mPaint);
    }



    private void init() {
        //设置画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        mPaint.setTextSize(getResources().getColor(R.color.colorAccent));

        //设置SeekBar顶部数值文字预留空间，左右为数值背景图片的一半，顶部为数值背景图片高度加五的间隔
        setPadding((int) Math.ceil(mBgWidth) / 2, (int) Math.ceil(mBgHeight) + 5 + 20, (int) Math.ceil(mBgWidth) / 2, 0);

    }




}
