package com.sotwtm.databinding.adapters

import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.databinding.adapters.ListenerUtil
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

/**
 * BindingAdapter methods and BindingMethods created for easier implementation for Android DataBinding.
 * Implementation for [ViewPager]
 *
 * @author sheungon
 */
@BindingMethods(
    BindingMethod(
        type = ViewPager::class,
        attribute = "onTouchListener",
        method = "setOnTouchListener"
    ),
    BindingMethod(
        type = ViewPager::class,
        attribute = "offscreenPageLimit",
        method = "setOffscreenPageLimit"
    )
)
object ViewPagerBindingAdapter {

    /*
     * Put them together as either one of the values changed the view pager should be updated too
     */
    @JvmStatic
    @BindingAdapter(
        value = [
            "adapter",
            "currentItem",
            "tabLayout"],
        requireAll = false
    )
    fun setAdapter(
        view: ViewPager,
        adapter: PagerAdapter?,
        currentItem: Int?,
        tabLayout: TabLayout?
    ) {
        if (view.adapter != adapter) {
            view.adapter = adapter
        }

        if (currentItem != null) {
            if (view.currentItem == currentItem) {
                // To trigger the adapter to refresh the selected indicator
                view.currentItem = currentItem + 1
            }
            view.currentItem = currentItem
        }

        tabLayout?.setupWithViewPager(if (view.adapter == null) null else view)
    }

    @JvmStatic
    @BindingAdapter("onPageChange")
    fun ViewPager.onPageChangeListener(
        listener: ViewPager.OnPageChangeListener?
    ) {
        val oldListener = ListenerUtil.trackListener(this, listener, id)
        if (oldListener != null) {
            removeOnPageChangeListener(oldListener)
        }
        if (listener != null) {
            addOnPageChangeListener(listener)
        }
    }
}