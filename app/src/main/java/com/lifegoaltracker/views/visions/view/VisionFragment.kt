package com.lifegoaltracker.views.visions.view

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lifegoaltracker.R
import com.lifegoaltracker.di.Injectable
import com.lifegoaltracker.model.vision.Vision
import com.lifegoaltracker.views.visionDetails.viewmodel.VisionDetailsViewModel
import com.lifegoaltracker.views.visions.viewmodel.MainViewPagerViewModel
import kotlinx.android.synthetic.main.fragment_vision.view.*
import javax.inject.Inject

class VisionFragment: Fragment(), Injectable {
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_vision, container, false)
        val viewPager = view.view_pager_vision
        val vision = arguments?.getSerializable("vision") as Vision
        viewPager.adapter = VisionAdapter(childFragmentManager, vision)
        return view
    }

    companion object {
        fun newInstance(vision: Vision): VisionFragment {
            val bundle = Bundle().apply { putSerializable("vision", vision) }
            return VisionFragment().apply { arguments = bundle }
        }
    }
}