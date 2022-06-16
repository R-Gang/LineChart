package com.gang.kotlin.views

import java.text.DecimalFormat

private val sCountFormat: DecimalFormat = DecimalFormat(",###.#")

fun formatNumber(count: Long): String {
    // todo 千分、万、亿 通用规则显示
    return if (count <= 9999) {
        sCountFormat.format(count.toDouble())
    } else {
        formatCount(count)
    }
}

/**
 * 通用的 格式化count值
 *
 * @param cnt
 * @param zero
 * @return
 */
fun formatCount(cnt: Long, zero: Boolean = true): String {
    if (cnt >= 100000000) {
        val f = cnt / 100000000f
        return sCountFormat.format(f.toDouble()) + "亿"
    }
    if (cnt >= 10000) {
        val f = cnt / 10000f
        return sCountFormat.format(f.toDouble()) + "万"
    }
    return if (cnt <= 0) {
        if (zero) "0" else ""
    } else cnt.toString()
}