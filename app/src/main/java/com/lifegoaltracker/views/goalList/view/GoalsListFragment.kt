package com.lifegoaltracker.views.goalList.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.lifegoaltracker.R
import com.lifegoaltracker.model.goal.Goal
import com.lifegoaltracker.model.goal.GoalProperties
import com.lifegoaltracker.model.goal.GoalStatus
import com.lifegoaltracker.model.goal.GoalUserFields
import com.lifegoaltracker.model.goal.dueDate.DueDate
import com.lifegoaltracker.model.goal.dueDate.dateObjects.Date
import com.lifegoaltracker.model.goal.dueDate.dateObjects.Month
import com.lifegoaltracker.model.goal.dueDate.dateObjects.WeekOfMonth
import com.lifegoaltracker.model.goal.dueDate.dateObjects.Year
import com.lifegoaltracker.model.goal.dueDate.span.GoalSpan
import com.lifegoaltracker.repository.ID
import com.lifegoaltracker.views.goalList.viewmodel.GoalListRecyclerViewItem
import com.lifegoaltracker.views.goalList.viewmodel.GoalListRecyclerViewItemType
import kotlinx.android.synthetic.main.fragment_goal_list.view.*

class GoalListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_goal_list, container, false)
        view.fab_goal_list.setOnClickListener {
            it.findNavController().navigate(R.id.action_goalListFragment_to_addEditGoalFragment)
        }
        val adapter = GoalListAdapter()
        adapter.setItems(listOf(
                GoalListRecyclerViewItem(GoalListRecyclerViewItemType.HEADER,
                        null, "Monthly"),
                GoalListRecyclerViewItem(GoalListRecyclerViewItemType.GOAL,
                        Goal(ID(0),
                                GoalUserFields("desc2",
                                        "reason",
                                        DueDate(Date(Year(2018), Month.JULY, WeekOfMonth.WEEK_TWO), GoalSpan.ONE_YEAR)
                                ),
                                GoalProperties(0, ID(0)),
                                GoalStatus()
                        ), null)

        ))
        view.recyclerview_goal_list.adapter = adapter
        view.recyclerview_goal_list.layoutManager = LinearLayoutManager(context)
        return view
    }
}