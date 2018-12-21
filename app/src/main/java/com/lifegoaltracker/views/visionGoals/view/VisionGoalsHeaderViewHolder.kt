package com.lifegoaltracker.views.visionGoals.view

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import androidx.navigation.findNavController
import com.lifegoaltracker.R
import com.lifegoaltracker.model.goal.Goal
import kotlinx.android.synthetic.main.row_goal_list_subheader.view.*

class VisionGoalsHeaderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun setHeader(item: Goal) {
        itemView.textview_row_goal_list_subheader.text =
                item.userFields.description
        itemView.button_row_goal_list_subheader.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("visionID", item.properties.visionID)
            }.apply {
                putSerializable("goalSpan", item.userFields.dueDate.span)
            }
            it.findNavController().navigate(R.id.action_mainViewPagerFragment_to_addEditGoalFragment, bundle)
        }
    }
}