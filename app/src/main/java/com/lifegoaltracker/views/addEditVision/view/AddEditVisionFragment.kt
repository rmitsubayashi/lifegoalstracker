package com.lifegoaltracker.views.addEditVision.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.lifegoaltracker.R
import kotlinx.android.synthetic.main.fragment_add_edit_vision.view.*

class AddEditVisionFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_edit_vision, container, false)
        view.button_add_edit_vision_submit.setOnClickListener{
            it.findNavController().navigateUp()
        }

        return view
    }
}
