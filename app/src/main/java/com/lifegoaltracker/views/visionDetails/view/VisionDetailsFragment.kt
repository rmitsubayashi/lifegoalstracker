package com.lifegoaltracker.views.visionDetails.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.lifegoaltracker.R
import com.lifegoaltracker.di.Injectable
import com.lifegoaltracker.model.vision.Vision
import com.lifegoaltracker.repository.ID
import com.lifegoaltracker.views.addEditVision.view.AddEditVisionFragment
import com.lifegoaltracker.views.visionDetails.viewmodel.VisionDetailsViewModel
import kotlinx.android.synthetic.main.fragment_vision_details.*
import javax.inject.Inject

class VisionDetailsFragment: Fragment(), Injectable {
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var viewModel: VisionDetailsViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_vision_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory)
                .get(VisionDetailsViewModel::class.java)
        val visionID = arguments?.getSerializable("visionID") as ID
        viewModel.fetchVision(visionID)
        viewModel.vision?.observe(this, Observer {
            it?.let {
                vision ->
                textview_vision_details_title.text = vision.userFields.title
                textview_vision_details_description.text = vision.userFields.description
                textview_vision_details_reason.text = vision.userFields.reason
                setEditVisionListener(vision)
            }
        })
    }

    private fun setEditVisionListener(vision: Vision) {
        button_vision_details_edit.setOnClickListener {
            val bundle = AddEditVisionFragment.createBundle(vision)
            it.findNavController().navigate(R.id.action_mainViewPagerFragment_to_addEditVisionFragment, bundle)
        }
    }

    companion object {
        fun newInstance(visionID: ID): VisionDetailsFragment {
            val bundle = Bundle().apply { putSerializable("visionID", visionID) }
            return VisionDetailsFragment().apply { arguments = bundle }
        }
    }
}