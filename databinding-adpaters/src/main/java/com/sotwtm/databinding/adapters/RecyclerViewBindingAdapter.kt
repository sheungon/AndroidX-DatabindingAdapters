package com.sotwtm.databinding.adapters

import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.databinding.adapters.ListenerUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * DataBinding methods and BindingMethods created for easier implementation for Android DataBinding.
 * Implementation for [RecyclerView]
 *
 * @author sheungon
 */
@BindingMethods(
    BindingMethod(
        type = RecyclerView::class,
        attribute = "adapter",
        method = "setAdapter"
    )
)
object RecyclerViewBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["layoutManagerOrientation"])
    fun setLayoutManager(
        recyclerView: RecyclerView,
        orientation: Int
    ) {
        val reverseLayout =
            ListenerUtil.getListener<Boolean?>(recyclerView, R.id.layoutManagerReverseLayout)
        val autoMeasureEnabled =
            ListenerUtil.getListener<Boolean?>(recyclerView, R.id.autoMeasureEnabled)
        val layoutManager = LinearLayoutManager(
            recyclerView.context,
            orientation,
            reverseLayout == true
        )
        if (autoMeasureEnabled != null) {
            layoutManager.isAutoMeasureEnabled = autoMeasureEnabled
        }
        recyclerView.layoutManager = layoutManager
    }

    @JvmStatic
    @BindingAdapter(value = ["layoutManagerReverseLayout"])
    fun setLayoutManagerReverseLayout(
        recyclerView: RecyclerView,
        reverseLayout: Boolean?
    ) {
        ListenerUtil.trackListener(recyclerView, reverseLayout, R.id.layoutManagerReverseLayout)
        (recyclerView.layoutManager as? LinearLayoutManager)
            ?.reverseLayout = reverseLayout == true
    }

    @JvmStatic
    @BindingAdapter(value = ["autoMeasureEnabled"])
    fun setAutoMeasureEnabled(
        recyclerView: RecyclerView,
        autoMeasureEnabled: Boolean?
    ) {
        ListenerUtil.trackListener(recyclerView, autoMeasureEnabled, R.id.autoMeasureEnabled)
        if (autoMeasureEnabled != null) {
            recyclerView.layoutManager?.isAutoMeasureEnabled = autoMeasureEnabled
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["nestedScrollingEnabled"])
    fun setNestedScrollingEnabled(
        recyclerView: RecyclerView,
        nestedScrollingEnabled: Boolean?
    ) {
        if (nestedScrollingEnabled != null) {
            recyclerView.isNestedScrollingEnabled = nestedScrollingEnabled
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["gridLayoutNumberOfColumn"])
    fun setGridNumberOfColumn(
        recyclerView: RecyclerView,
        numOfCol: Int?
    ) {
        if (numOfCol == null) return
        recyclerView.layoutManager = GridLayoutManager(recyclerView.context, numOfCol)
    }
}