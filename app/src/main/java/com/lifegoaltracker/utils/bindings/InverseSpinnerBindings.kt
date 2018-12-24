package com.lifegoaltracker.utils.bindings

import android.databinding.BindingAdapter
import android.databinding.InverseBindingAdapter
import android.databinding.InverseBindingListener
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class InverseSpinnerBindings {
    @BindingAdapter("selectedValue")
    fun Spinner.setSelectedValue(entry: String) {
        if (adapter != null ) {
            val position = (adapter as ArrayAdapter<String>).getPosition(entry)
            setSelection(position, false)
            tag = position
        }
    }

    @BindingAdapter("selectedValueAttrChanged")
    fun Spinner.setInverseBindingListener(inverseBindingListener: InverseBindingListener?) {
        onItemSelectedListener = if (inverseBindingListener == null) {
            null
        } else {
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if (tag != position) {
                        inverseBindingListener.onChange()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }
    }

    companion object InverseSpinnerBindings {

        @JvmStatic
        @InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
        fun Spinner.getSelectedValue(): Any? {
            return selectedItem
        }
    }
}