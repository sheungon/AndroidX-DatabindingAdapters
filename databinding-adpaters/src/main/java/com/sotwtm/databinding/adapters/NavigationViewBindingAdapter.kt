package com.sotwtm.databinding.adapters

import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.google.android.material.navigation.NavigationView

/**
 * DataBinding methods and BindingMethods created for easier implementation for Android DataBinding.
 * Implementation for [NavigationView]
 *
 * @author sheungon
 */
@BindingMethods(
    BindingMethod(
        type = NavigationView::class,
        attribute = "onNavigationItemSelected",
        method = "setNavigationItemSelectedListener"
    )
)
object NavigationViewBindingAdapter