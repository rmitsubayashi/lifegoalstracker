package com.lifegoaltracker.views.visionDetails.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
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
import com.lifegoaltracker.views.visionDetails.viewmodel.VisionDetailsRecyclerViewItem
import com.lifegoaltracker.views.visionDetails.viewmodel.VisionDetailsRecyclerViewItemType
import kotlinx.android.synthetic.main.fragment_vision_details.view.*

class VisionDetailsFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_vision_details, container, false)
        val adapter = VisionDetailsAdapter()
        adapter.setItems(
                listOf(
                        VisionDetailsRecyclerViewItem(
                                VisionDetailsRecyclerViewItemType.VISION_DESCRIPTION, null, "description"
                        ),
                        VisionDetailsRecyclerViewItem(
                                VisionDetailsRecyclerViewItemType.VISION_REASON, null, "reason"
                        ),
                        VisionDetailsRecyclerViewItem(
                                VisionDetailsRecyclerViewItemType.HEADER, null, "header"
                        ),
                        VisionDetailsRecyclerViewItem(
                                VisionDetailsRecyclerViewItemType.GOAL,
                                Goal(ID(0),
                                        GoalUserFields("desc",
                                                "reason",
                                                DueDate(Date(Year(2018), Month.JULY, WeekOfMonth.WEEK_TWO), GoalSpan.ONE_YEAR)
                                        ),
                                        GoalProperties(0, ID(0)),
                                        GoalStatus()
                                ), null
                        ),
                        VisionDetailsRecyclerViewItem(
                                VisionDetailsRecyclerViewItemType.GOAL,
                                Goal(ID(0),
                                        GoalUserFields("desc2",
                                                "reason",
                                                DueDate(Date(Year(2018), Month.JULY, WeekOfMonth.WEEK_TWO), GoalSpan.ONE_YEAR)
                                        ),
                                        GoalProperties(0, ID(0)),
                                        GoalStatus()
                                ), null
                        ),
                        VisionDetailsRecyclerViewItem(
                                VisionDetailsRecyclerViewItemType.HEADER, null, "header2"
                        ),
                        VisionDetailsRecyclerViewItem(
                                VisionDetailsRecyclerViewItemType.GOAL,
                                Goal(ID(0),
                                        GoalUserFields("desc3",
                                                "reason",
                                                DueDate(Date(Year(2018), Month.JULY, WeekOfMonth.WEEK_TWO), GoalSpan.ONE_YEAR)
                                        ),
                                        GoalProperties(0, ID(0)),
                                        GoalStatus()
                                ), null
                        )
                )
        )
        view.recyclerview_vision_details.adapter = adapter
        view.recyclerview_vision_details.layoutManager = LinearLayoutManager(context)

        view.fab_vision_details.setOnClickListener{
            it.findNavController().navigate(R.id.action_visionDetailsFragment_to_addEditGoalFragment)
        }

        return view
    }
}