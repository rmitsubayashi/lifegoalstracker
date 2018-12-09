package com.lifegoaltracker.views.visionGoals.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lifegoaltracker.R
import com.lifegoaltracker.di.Injectable
import com.lifegoaltracker.repository.ID
import com.lifegoaltracker.views.visionGoals.viewmodel.VisionGoalsViewModel
import kotlinx.android.synthetic.main.fragment_vision_goals.view.*
import javax.inject.Inject

class VisionGoalsFragment: Fragment(), Injectable {
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var viewModel: VisionGoalsViewModel
    private val adapter = VisionGoalsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_vision_goals, container, false)
        view.list_vision_goals.adapter = adapter
        view.list_vision_goals.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory)
                .get(VisionGoalsViewModel::class.java)
        val visionID = arguments?.getSerializable("visionID") as ID
        viewModel.fetchGoals(visionID).observe(this, Observer {
            it?.let {
                list ->
                adapter.setGoals(list)
            }
        })

    }

    companion object {
        fun newInstance(visionID: ID): VisionGoalsFragment {
            val bundle = Bundle().apply { putSerializable("visionID", visionID) }
            return VisionGoalsFragment().apply { arguments = bundle }
        }
    }
}