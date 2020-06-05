package com.baiyu.mytestdemo.ext

import android.view.View

/**
 * @author Baiyu
 * @date :2020/6/4 8:02 PM June
 * @version: 1.0
 */


var lastClickTime = 0L
fun View.noDoubleClick(interval: Long = 500, action: (view: View) -> Unit) {
    setOnClickListener {
        val currentTime = System.currentTimeMillis()
        if (lastClickTime != 0L && (currentTime - lastClickTime < interval)) {
            return@setOnClickListener
        }
        lastClickTime = currentTime
        action(it)
    }
}

fun setOnClickNoDouble(views: List<View?>, onClick: (View) -> Unit) {
    views.forEach {
        it?.noDoubleClick { view ->
            onClick.invoke(view)
        }

    }
}

/**
 * 设置view显示
 */
fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * 设置view占位隐藏
 */
fun View.invisible() {
    visibility = View.INVISIBLE
}

/**
 * 设置view隐藏
 */
fun View.gone() {
    visibility = View.GONE
}