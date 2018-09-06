package com.lifegoaltracker.views.addEditVision.view

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.lifegoaltracker.R
import com.lifegoaltracker.di.Injectable
import com.lifegoaltracker.views.addEditVision.viewmodel.AddEditVisionViewModel
import kotlinx.android.synthetic.main.fragment_add_edit_vision.view.*
import javax.inject.Inject

class AddEditVisionFragment: Fragment(), Injectable {
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var viewModel: AddEditVisionViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory)
                .get(AddEditVisionViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_edit_vision, container, false)
        view.button_add_edit_vision_submit.setOnClickListener{
            it.findNavController().navigateUp()
        }

        return view
    }
}
