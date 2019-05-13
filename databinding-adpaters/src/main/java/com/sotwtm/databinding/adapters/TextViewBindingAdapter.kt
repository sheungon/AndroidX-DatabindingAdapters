package com.sotwtm.databinding.adapters

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods

/**
 * DataBinding methods and BindingMethods created for easier implementation for Android DataBinding.
 * Implementation for [TextView]
 *
 * @author sheungon
 */
@BindingMethods(
    BindingMethod(type = TextView::class, attribute = "selected", method = "setSelected")
)
object TextViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("showError")
    fun TextView.showError(error: String?) {
        this.error = error
        if (error != null) {
            requestFocus()
        }
    }

    @JvmStatic
    @BindingAdapter("showErrorRes")
    fun TextView.showError(@StringRes errorRes: Int?) {
        showError(if (errorRes == null) null else context.getString(errorRes))
    }

    @JvmStatic
    @BindingAdapter("textColorRes")
    fun TextView.setTextColorRes(@ColorRes textColorRes: Int) {
        setTextColor(ContextCompat.getColor(context, textColorRes))
    }

    @JvmStatic
    @BindingAdapter(
        value = ["drawableResLeft",
            "drawableResRight",
            "drawableResTop",
            "drawableResBottom",
            "drawableResStart",
            "drawableResEnd"],
        requireAll = false
    )
    fun TextView.setDrawablesRes(
        @DrawableRes left: Int?,
        @DrawableRes right: Int?,
        @DrawableRes top: Int?,
        @DrawableRes bottom: Int?,
        @DrawableRes start: Int?,
        @DrawableRes end: Int?
    ) {
        val topDrawable = top?.safeGetDrawable(context)
        val bottomDrawable = bottom?.safeGetDrawable(context)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            val startDrawable = start?.safeGetDrawable(context)
            val endDrawable = end?.safeGetDrawable(context)
            setCompoundDrawablesRelative(
                startDrawable,
                topDrawable,
                endDrawable,
                bottomDrawable
            )
            if ((start == null && left != null) ||
                (end == null && right != null)
            ) {
                val leftDrawable =
                    if (start == null) left?.safeGetDrawable(context) else startDrawable
                val rightDrawable =
                    if (end == null) right?.safeGetDrawable(context) else endDrawable
                setCompoundDrawables(
                    leftDrawable,
                    topDrawable,
                    rightDrawable,
                    bottomDrawable
                )
            }
        } else {
            val leftDrawable = left?.safeGetDrawable(context)
            val rightDrawable = right?.safeGetDrawable(context)
            setCompoundDrawables(
                leftDrawable,
                topDrawable,
                rightDrawable,
                bottomDrawable
            )
        }
    }

    @JvmStatic
    @BindingAdapter("underlineText")
    fun TextView.underLineText(underline: Boolean) {
        if (underline) {
            paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
        } else {
            paintFlags = paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
        }
    }

    @JvmStatic
    @BindingAdapter(
        "typeface", "textStyle",
        requireAll = false
    )
    fun TextView.decorateText(
        typeface: Typeface?,
        textStyle: Int?
    ) {
        setTypeface(
            typeface ?: typeface,
            textStyle ?: Typeface.NORMAL
        )
    }

    @JvmStatic
    private fun Int.safeGetDrawable(context: Context): Drawable? =
        if (this == 0) null else ContextCompat.getDrawable(context, this)
}
