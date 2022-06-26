package com.echomu.github.utils

import android.content.Context

// 根据手机的分辨率将dp的单位转成px(像素)
fun dip2px(context: Context, dpValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}