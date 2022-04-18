package com.gang.kotlin.chart

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.animation.AccelerateDecelerateInterpolator
import com.gang.kotlin.R

class LineChart(context: Context, attrs: AttributeSet) : CoordinatePopup(context, attrs) {

    var inited = true // 数据变化后是否已绘制动画
    var progress = 0f // 当前动画绘制进度，取值 (0 - data.size)

    init {
        val obtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.Chart)
        animation = obtainStyledAttributes.getBoolean(R.styleable.Chart_animation, false)
        animationDuration = obtainStyledAttributes.getInteger(R.styleable.Chart_animationDuration,
            500).toLong()
        obtainStyledAttributes.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawSeries(canvas)
    }

    /**
     * 绘制坐标系内的bar或line
     */
    fun drawSeries(canvas: Canvas) {
        if (animation) {
            if (!inited) {
                inited = true
                startAnimation()
            }
            drawAnimateLines(canvas, progress)
        }
    }

    private fun drawAnimateLines(canvas: Canvas, progress: Float) {

    }

    private fun startAnimation() {
        ValueAnimator.ofFloat(0f, (XValueList?.size as Int - 1).toFloat()).apply {
            addUpdateListener {
                progress = animatedValue as Float
                invalidate()
            }
            interpolator = AccelerateDecelerateInterpolator()
            duration = animationDuration
        }.start()
    }

    override fun onDataUpdate(yData: ArrayList<Int>?, xData: ArrayList<String>?) {
        super.onDataUpdate(yData, xData)
        if (animation) {
            inited = false
            progress = 0f
        }
    }

}