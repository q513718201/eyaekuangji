package com.hazz.whb.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class NoScrollViewPager(context: Context, attributeSet: AttributeSet?) : ViewPager(context, attributeSet) {
    constructor(context: Context) : this(context, null)

    var noScroll = true
    fun setCanScroll(can: Boolean) {
        this.noScroll = can
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (noScroll) {
            return false
        }
        return super.onInterceptTouchEvent(ev)
    }

    override fun setCurrentItem(item: Int) {
        super.setCurrentItem(item,false)
    }

    override fun setCurrentItem(item: Int, smoothScroll: Boolean) {
        super.setCurrentItem(item, smoothScroll)
    }
}