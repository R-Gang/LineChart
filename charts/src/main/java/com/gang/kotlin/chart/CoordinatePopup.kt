package com.gang.kotlin.chart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PointF
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.gang.kotlin.R
import com.gang.kotlin.databinding.PopupEverywhereBinding
import com.gang.kotlin.popup.ARROWDIRECTION
import com.gang.kotlin.popup.EverywherePopup
import com.gang.kotlin.popup.TriangleDrawable
import com.gang.kotlin.views.setBackground
import com.gang.library.common.utils.gone
import com.gang.library.common.utils.show

/**
 *
 * @ProjectName:    Chart-Popup
 * @Package:        com.gang.kotlin.chart
 * @ClassName:      CoordinatePopup
 * @Description:    坐标弹框
 * @Author:         haoruigang
 * @CreateDate:     2022/4/15 17:15
 * @UpdateUser:     更新者：
 * @UpdateDate:     2022/4/15 17:15
 * @UpdateRemark:   更新说明：
 * @Version:
 */
open class CoordinatePopup(context: Context, attrs: AttributeSet) :
    CoordinateTouch(context, attrs) {

    var mEverywherePopup: EverywherePopup? = null
    var everywhereBinding: PopupEverywhereBinding? = null

    init {
        everywhereBinding = PopupEverywhereBinding.inflate(LayoutInflater.from(mContext))
        everywhereBinding?.lineData = this
        everywherePopup()
        drawFocusedInfoText(mCanvas, focusedDataIndex)
    }

    private fun everywherePopup() {
        if (mEverywherePopup == null) {
            mEverywherePopup = EverywherePopup.create(context)
                .setContentView(everywhereBinding?.root)
                ?.setOnViewListener(object : EverywherePopup.OnViewListener {
                    override fun initViews(view: View?, popup: EverywherePopup?) {
                        everywhereBinding?.apply {
                            vLArrow.background =
                                TriangleDrawable(ARROWDIRECTION.LEFT,
                                    Color.parseColor("#20A0DA"))
                            vRArrow.background =
                                TriangleDrawable(ARROWDIRECTION.RIGHT,
                                    Color.parseColor("#20A0DA"))
                            rlBox.setBackground(colorRes = R.color.color_20a0da, cornerRadius = 6f)
                        }
                    }
                })
                ?.setNeedReMeasureWH(true)
                ?.setFocusAndOutsideEnable(true)
                ?.apply()
        }
    }

    /**
     * 绘制说明区域
     */
    final override fun drawFocusedInfoText(canvas: Canvas?, focusedDataIndex: Int) {
        super.drawFocusedInfoText(canvas, focusedDataIndex)
        everywhereBinding?.let {
            // 说明弹框
            mEverywherePopup?.apply {
                listener = fun(yData: Int?, xData: String?, point: PointF?) {
                    dismiss()
                    if (yData != null) {
                        this@CoordinatePopup.yData = yData
                        it.tvYData.text = "播放量：$yData"
                    }
                    if (xData != null) {
                        this@CoordinatePopup.xData = xData
                        it.tvXData.text = xData
                    }

                    // 判断弹框出现的位置方向，计算偏移量 start  ========
                    val screenWidth: Int = getScreenWidthHeight()[0]
                    val offsetX: Int
                    if (point != null) {
                        // , sumWidth.toInt(), sumHeight.toInt()
                        // 获得当前view在屏幕中的坐标
                        val location = IntArray(2)
                        this@CoordinatePopup.getLocationOnScreen(location)
                        // x坐标距离屏幕左边的宽度 > 总控件右边距离
                        if (point.x + location[0] + width > screenWidth - location[0]) {
                            // 图表控件左边距 + 控件宽度 = 控件最右边x坐标
                            val lMarginSumWidth = +sumWidth
                            // 出现在右边👉🏻  (控件最右x坐标 - (圆点到控件右边的距离) -  圆点宽度 - 边距
                            offsetX = (lMarginSumWidth - (lMarginSumWidth - point.x.toInt()
                                    - location[0]) - dotCircleSize.toInt() - dp2px(5f)).toInt()
                            /*// 出现在右边👈🏻  (圆点在控件中的x + y轴值左边距 - 圆点宽度 - 边距)
                            offsetX = point.x.toInt() + valueSpaceYL.toInt() -
                                    dotCircleSize.toInt() - dp2px(5f).toInt()*/
                            it.vRArrow.show()
                            it.vLArrow.gone()
                        } else {
                            // 出现在左边👈🏻  (圆点在控件中的x + y轴值左边距 + 圆点宽度 + 边距)
                            offsetX = point.x.toInt() + valueSpaceYL.toInt() +
                                    dotCircleSize.toInt() + dp2px(5f).toInt()
                            it.vRArrow.gone()
                            it.vLArrow.show()
                        }
                        // 判断弹框出现的位置方向，计算偏移量 end  ========

                        showEverywhere(this@CoordinatePopup,
                            offsetX,
                            // 圆点在控件中的y + 控件距离屏幕顶部的高度 - 弹框高度/2
                            point.y.toInt() + location[1] - height / 2,
                            point, location)
                    }
                }

            }
        }

    }

}