package com.lifegoaltracker.views.visions.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lifegoaltracker.R
import com.lifegoaltracker.di.Injectable
import com.lifegoaltracker.views.addEditVision.viewmodel.AddEditVisionViewModel
import com.lifegoaltracker.views.visions.viewmodel.VisionsViewModel
import kotlinx.android.synthetic.main.fragment_visions.view.*
import javax.inject.Inject

class VisionsFragment: Fragment(), Injectable {
    private lateinit var adapter: VisionsAdapter
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var viewModel: VisionsViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory)
                .get(VisionsViewModel::class.java)
        viewModel.visionsList.observe(this, Observer {
            list -> list?.let{adapter.setVisions(it)}
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_visions, container, false)
        adapter = VisionsAdapter()
        view.recyclerview_visions.adapter = adapter
        view.recyclerview_visions.layoutManager = LinearLayoutManager(context)

        return view
    }
}