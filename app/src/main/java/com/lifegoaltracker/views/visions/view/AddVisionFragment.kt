package com.lifegoaltracker.views.visions.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.lifegoaltracker.R
import kotlinx.android.synthetic.main.fragment_add_vision.view.*

class AddVisionFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_vision, container, false)
        view.button_add_vision.setOnClickListener {
            Navigation.findNavController(parentFragment?.view!!).navigate(R.id.action_mainViewPagerFragment_to_addEditVisionFragment)
        }
        return view
    }
}