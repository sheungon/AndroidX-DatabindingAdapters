package com.sotwtm.databinding.adapters

import android.content.Context
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.ArrayRes
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingMethod
import androidx.databinding.InverseBindingMethods
import androidx.databinding.adapters.ListenerUtil

/**
 * DataBinding methods and BindingMethods created for easier implementation for Android DataBinding.
 * Implementation for [AdapterView]
 *
 * @author sheungon
 */
@InverseBindingMethods(
    InverseBindingMethod(
        type = AdapterView::class,
        attribute = "selectedItemString",
        method = "getSelectedItem",
        event = "android:selectedItemPositionAttrChanged"
    )
)
object AdapterViewBindingAdapter {

    @JvmStatic
    @BindingAdapter(
        value = ["itemsStringArrayRes",
            "spinnerLayout",
            "dropdownLayout"],
        requireAll = false
    )
    fun AdapterView<*>.setItemsStringArrayRes(
        stringArrayRes: Int?,
        @LayoutRes spinnerLayout: Int?,
        @LayoutRes dropDownLayout: Int?
    ) {
        val arrayAdapter = stringArrayRes?.let { arrayRes ->
            ListenerUtil.getListener<ArrayAdapter<String>>(this, arrayRes)
                ?: arrayRes.toResStringArray(context)
                    .toArrayAdapter(
                        context = context,
                        spinnerLayout = spinnerLayout ?: android.R.layout.simple_spinner_item,
                        dropDownLayout = dropDownLayout
                            ?: android.R.layout.simple_spinner_dropdown_item
                    )
                    .also {
                        ListenerUtil.trackListener(this, it, arrayRes)
                    }
        }
        if (adapter != arrayAdapter) {
            adapter = arrayAdapter
        }
    }

    @JvmStatic
    @BindingAdapter("selectedItemString")
    fun AdapterView<*>.selectedItemString(selectedString: String?) {
        val selectedPos = (adapter as? ArrayAdapter<String>)
            ?.run {
                for (i in 0 until count) {
                    if (getItem(i) == selectedString) {
                        return@run i
                    }
                }
                -1
            } ?: -1
        if (selectedItemPosition != selectedPos) {
            setSelection(selectedPos)
        }
    }

    @JvmStatic
    @BindingAdapter(
        "itemsStringArrayRes",
        "spinnerLayout",
        "dropdownLayout",
        "selectedItemString"
    )
    fun AdapterView<*>.selectedItemString(
        @ArrayRes stringArrayRes: Int?,
        @LayoutRes spinnerLayout: Int?,
        @LayoutRes dropDownLayout: Int?,
        selectedString: String?
    ) {
        setItemsStringArrayRes(stringArrayRes, spinnerLayout, dropDownLayout)
        selectedItemString(selectedString)
    }

    private fun Int.toResStringArray(context: Context) =
        context.resources.getStringArray(this).asList()

    private fun List<String>.toArrayAdapter(
        context: Context,
        @LayoutRes spinnerLayout: Int = android.R.layout.simple_spinner_item,
        @LayoutRes dropDownLayout: Int = android.R.layout.simple_spinner_dropdown_item
    ) = ArrayAdapter(context, spinnerLayout, this).apply {
        setDropDownViewResource(dropDownLayout)
    }
}