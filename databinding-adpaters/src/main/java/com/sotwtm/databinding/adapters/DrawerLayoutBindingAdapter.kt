package com.sotwtm.databinding.adapters

import androidx.databinding.BindingAdapter
import androidx.databinding.adapters.ListenerUtil
import androidx.drawerlayout.widget.DrawerLayout

/**
 * DataBinding methods and BindingMethods created for easier implementation for Android DataBinding.
 * Implementation for [DrawerLayout]
 *
 * @author sheungon
 */
object DrawerLayoutBindingAdapter {

    @JvmStatic
    @BindingAdapter("drawerListener")
    fun DrawerLayout.drawerListener(listener: DrawerLayout.DrawerListener?) {

        ListenerUtil.trackListener(this, listener, R.id.drawerListener)
            ?.let {
                // Remove the previous listener
                removeDrawerListener(it)
            }

        listener?.let {
            addDrawerListener(it)
        }
    }
}