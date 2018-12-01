package com.lifegoaltracker.views.visionDetails.view

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
import androidx.navigation.findNavController
import com.lifegoaltracker.R
import com.lifegoaltracker.di.Injectable
import com.lifegoaltracker.repository.ID
import com.lifegoaltracker.views.addEditGoal.view.AddEditGoalFragment
import com.lifegoaltracker.views.visionDetails.viewmodel.VisionDetailsViewModel
import kotlinx.android.synthetic.main.fragment_vision_details.view.*
import javax.inject.Inject

class VisionDetailsFragment: Fragment(), Injectable {
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var viewModel: VisionDetailsViewModel
    lateinit var adapter: VisionDetailsAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory)
                .get(VisionDetailsViewModel::class.java)
        arguments?.getSerializable("visionID")?.let {
            fetchRecyclerViewItems(it as ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_vision_details, container, false)
        adapter = VisionDetailsAdapter()
        view.recyclerview_vision_details.adapter = adapter
        view.recyclerview_vision_details.layoutManager = LinearLayoutManager(context)

        view.fab_vision_details.setOnClickListener{
            val visionID = arguments?.getSerializable("visionID") as ID
            val bundle = AddEditGoalFragment.createBundle(visionID)
            it.findNavController().navigate(R.id.action_visionDetailsFragment_to_addEditGoalFragment, bundle)
        }

        return view
    }

    private fun fetchRecyclerViewItems(visionID: ID){
        viewModel.fetchRecyclerViewItems(visionID as ID).observe(
                this, Observer {
            list -> Log.d("list",list.toString())
            list?.let{ adapter.setItems(it) }
        })
    }

    companion object {
        fun createBundle(visionID: ID) = Bundle().apply { putSerializable("visionID", visionID) }
    }
}