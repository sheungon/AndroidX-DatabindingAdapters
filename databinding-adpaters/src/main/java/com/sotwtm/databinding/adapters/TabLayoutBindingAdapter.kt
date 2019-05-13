package com.sotwtm.databinding.adapters

import androidx.databinding.*
import androidx.databinding.adapters.ListenerUtil
import com.google.android.material.tabs.TabLayout


/**
 * DataBinding methods and BindingMethods created for easier implementation for Android DataBinding.
 * Implementation for [TabLayout]
 *
 * @author sheungon
 */
@BindingMethods(
    BindingMethod(
        type = TabLayout::class,
        attribute = "setupWithViewPager",
        method = "setupWithViewPager"
    )
)
object TabLayoutBindingAdapter {

    @JvmStatic
    @BindingAdapter("selectTabAt")
    fun TabLayout.setSelectTabAt(selectedTab: Int) {
        getTabAt(selectedTab)?.select()
    }

    @Synchronized
    @JvmStatic
    @BindingAdapter(
        value = ["onTabSelected", "selectTabChanged"],
        requireAll = false
    )
    fun TabLayout.setTabChangeListener(
        listener: TabLayout.OnTabSelectedListener?,
        inverseBindingListener: InverseBindingListener?
    ) {
        if (inverseBindingListener == null) {
            listener
        } else {
            // Warp inverseBindingListener
            object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {
                    inverseBindingListener.onChange()
                    listener?.onTabReselected(tab)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    inverseBindingListener.onChange()
                    listener?.onTabUnselected(tab)
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    inverseBindingListener.onChange()
                    listener?.onTabSelected(tab)
                }
            }
        }.also {
            ListenerUtil.trackListener(this, it, R.id.onTabSelectedListener)?.let { oldListener ->
                removeOnTabSelectedListener(oldListener)
            }
        }?.also {
            addOnTabSelectedListener(it)
        }

    }

    @JvmStatic
    @InverseBindingAdapter(attribute = "selectTabAt", event = "selectTabChanged")
    fun TabLayout.getSelectTabAt(): Int = selectedTabPosition
}