package com.lifegoaltracker.views.goalList.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import com.lifegoaltracker.views.goalList.viewmodel.GoalListViewModel

class GoalListFragment : Fragment() {
    lateinit var viewModel : GoalListViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GoalListViewModel::class.java)
    }
}