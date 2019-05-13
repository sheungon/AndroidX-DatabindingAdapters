package com.sotwtm.databinding.adapters

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.Build
import android.widget.ProgressBar
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

/**
 * DataBinding methods and BindingMethods created for easier implementation for Android DataBinding.
 * Implementation for [ProgressBar]
 *
 * @author sheungon
 */
object ProgressBarBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["progressColorRes"])
    fun ProgressBar.setProgressBarColorRes(@ColorRes colorRes: Int) {
        setProgressBarColor(ContextCompat.getColor(context, colorRes))
    }

    @JvmStatic
    @BindingAdapter("progressColor")
    fun ProgressBar.setProgressBarColor(@ColorInt color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            progressTintList = ColorStateList.valueOf(color)
        } else {
            progressDrawable.setColorFilter(color, PorterDuff.Mode.SRC_IN)
        }
    }

    @JvmStatic
    @BindingAdapter(
        value = ["android:progress",
            "animateProgress"],
        requireAll = false
    )
    fun ProgressBar.setProgress(
        progress: Int,
        animateProgress: Boolean?
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.setProgress(progress, animateProgress ?: false)
        } else {
            this.progress = progress
        }
    }

    @JvmStatic
    @BindingAdapter("android:max")
    fun ProgressBar.setProgressMax(progressMax: Int) {
        max = progressMax
    }
}