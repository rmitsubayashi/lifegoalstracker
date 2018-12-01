package com.lifegoaltracker.utils.bindings

import android.R
import android.databinding.BindingAdapter
import android.databinding.InverseBindingListener
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.lifegoaltracker.utils.uiDisplay.SpinnerItems

class SpinnerBindings {
    @BindingAdapter("entries")
    fun Spinner.setEntries(entries: List<String>?) {
        if (entries != null) {
            adapter = ArrayAdapter(context, R.layout.simple_spinner_item, entries)
        }
    }

    @BindingAdapter("onItemSelected")
    fun Spinner.setItemSelectedListener(listener: ItemSelectedListener?) {
        if (listener == null) {
            onItemSelectedListener = null
            return
        }

        onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (tag != position) {
                    listener.onItemSelected(parent.getItemAtPosition(position))
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    fun Spinner.setSpinnerInverseBindingListener(listener: InverseBindingListener?) {
        onItemSelectedListener = if (listener == null) {
            null
        } else {
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if (tag != position) {
                        listener.onChange()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }
    }



    interface ItemSelectedListener {
        fun onItemSelected(item: Any)
    }
}