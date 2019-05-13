package com.sotwtm.databinding.adapters

import android.app.ActionBar
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods

/**
 * DataBinding methods and BindingMethods created for easier implementation for Android DataBinding.
 * Implementation for [ActionBar]
 *
 * @author sheungon
 */
@BindingMethods(
    BindingMethod(
        type = ActionBar::class,
        attribute = "displayShowHomeEnabled",
        method = "setDisplayShowHomeEnabled"
    ),
    BindingMethod(
        type = ActionBar::class,
        attribute = "displayHomeAsUpEnabled",
        method = "setDisplayHomeAsUpEnabled"
    )
)
object ActionBarBindingAdapter