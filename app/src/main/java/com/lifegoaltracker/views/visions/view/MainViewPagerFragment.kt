package com.lifegoaltracker.views.visions.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lifegoaltracker.R
import com.lifegoaltracker.di.Injectable
import com.lifegoaltracker.views.visions.viewmodel.MainViewPagerViewModel
import kotlinx.android.synthetic.main.fragment_main_view_pager.view.*
import javax.inject.Inject

class MainViewPagerFragment: Fragment(), Injectable {
    private lateinit var adapter: MainViewPagerAdapter
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var viewModel: MainViewPagerViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory)
                .get(MainViewPagerViewModel::class.java)
        viewModel.visionsList.observe(this, Observer {
            it?.let{ list ->
                adapter.setVisions(list) }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main_view_pager, container, false)
        val viewPager = view.fragment_main_view_pager
        adapter = MainViewPagerAdapter(childFragmentManager)
        viewPager.adapter = adapter
        return view
    }
}