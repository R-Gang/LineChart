package com.gang.kotlin.views

import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat.getColor

/**
 *
 * @ProjectName:    JetPack_Simple
 * @Package:        com.simple.kotlin.views
 * @ClassName:      ViewExt
 * @Description:     java类作用描述
 * @Author:         haoruigang
 * @CreateDate:     2022/4/8 10:57
 */
/**
 * 给 [View] 设置背景：纯色、可渐变、边框、虚线边框、选择器 [GradientDrawable]
 *
 * 1，设置颜色
 *  （1）单独指定 [colorRes]/[color] 则为纯色
 *  （2）指定 [colorRes]/[color]（开始色）和 [endColorRes]/[endColor]（结束色）颜色不同时，则为渐变色
 *  （3）单独指定 [colorStateList] 则为颜色选择器
 *
 * 2，设置边框：当 [strokeWidth] (px) 大于0时，在设置颜色的情况下都可以指定边框
 * （1）[strokeColorRes] 则为纯色边框
 * （2）[strokeColorStateList] 则为颜色选择器边框
 *
 * 3，设置虚线边框：在2中指定边框时，可以通过 [dashWidth] (px) 和 [dashGap] (px) 设定
 *
 * 4，设置圆角，[cornerRadius] (px) 圆角半径，并指定圆角方位 [direction] 默认全方位四角
 *
 * 5，指定渐变色时可以指定渐变方向 [orientation] 默认从上到下 [GradientDrawable.Orientation.TOP_BOTTOM]
 */
fun View.setBackground(
    @ColorRes colorRes: Int = android.R.color.transparent,
    @ColorRes endColorRes: Int? = null,
    @ColorRes strokeColorRes: Int = android.R.color.transparent,
    @ColorInt color: Int? = null,
    @ColorInt endColor: Int? = null,
    @ColorInt strokeColor: Int? = null,
    colorStateList: ColorStateList? = null,
    strokeColorStateList: ColorStateList? = null,
    strokeWidth: Int = 0,
    dashWidth: Float = 0F,
    dashGap: Float = 0F,
    cornerRadius: Float = 0F,
    direction: Int = Direction.ALL,
    orientation: GradientDrawable.Orientation = GradientDrawable.Orientation.TOP_BOTTOM,
): View {
    background = getShapeDrawable(
        colorRes = colorRes,
        endColorRes = endColorRes,
        strokeColorRes = strokeColorRes,
        color = color,
        endColor = endColor,
        strokeColor = strokeColor,
        colorStateList = colorStateList,
        strokeColorStateList = strokeColorStateList,
        strokeWidth = strokeWidth,
        dashWidth = dashWidth,
        dashGap = dashGap,
        cornerRadius = cornerRadius,
        direction = direction,
        orientation = orientation
    )
    return this
}

/**
 * 获取一个纯色、可渐变、边框、虚线边框、选择器 [GradientDrawable]
 *
 * 1，设置颜色
 * （1）单独指定 [colorRes]/[color] 则为纯色
 * （2）指定 [colorRes]/[color]（开始色）和 [endColorRes]/[endColor]（结束色）颜色不同时，则为渐变色
 * （3）单独指定 [colorStateList] 则为颜色选择器
 *
 * 2，设置边框：当 [strokeWidth] (px) 大于0时，在设置颜色的情况下都可以指定边框
 * （1）[strokeColorRes] 则为纯色边框
 * （2）[strokeColorStateList] 则为颜色选择器边框
 *
 * 3，设置虚线边框：在2中指定边框时，可以通过 [dashWidth] (px) 和 [dashGap] (px) 设定
 *
 * 4，设置圆角，[cornerRadius] (px) 圆角半径，并指定圆角方位 [direction] 默认全方位四角
 *
 * 5，指定渐变色时可以指定渐变方向 [orientation] 默认从上到下 [GradientDrawable.Orientation.TOP_BOTTOM]
 */
fun View.getShapeDrawable(
    @ColorRes colorRes: Int = android.R.color.transparent,
    @ColorRes endColorRes: Int? = null,
    @ColorRes strokeColorRes: Int = android.R.color.transparent,
    @ColorInt color: Int? = null,
    @ColorInt endColor: Int? = null,
    @ColorInt strokeColor: Int? = null,
    colorStateList: ColorStateList? = null,
    strokeColorStateList: ColorStateList? = null,
    strokeWidth: Int = 0,
    dashWidth: Float = 0F,
    dashGap: Float = 0F,
    cornerRadius: Float = 0F,
    direction: Int = Direction.ALL,
    orientation: GradientDrawable.Orientation = GradientDrawable.Orientation.TOP_BOTTOM,
): GradientDrawable {
    return getGradientDrawable(
        color = color ?: getColor(context, colorRes),
        endColor = endColor ?: endColorRes?.let { getColor(context, it) },
        strokeColor = strokeColor ?: getColor(context, strokeColorRes),
        colorStateList = colorStateList,
        strokeColorStateList = strokeColorStateList,
        strokeWidth = strokeWidth,
        dashWidth = dashWidth,
        dashGap = dashGap,
        cornerRadius = cornerRadius,
        direction = direction,
        orientation = orientation
    )
}