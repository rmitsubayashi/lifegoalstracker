package com.lifegoaltracker.views.goalList.view

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import androidx.navigation.findNavController
import com.lifegoaltracker.R
import com.lifegoaltracker.model.goal.Goal
import kotlinx.android.synthetic.main.row_goal_list_goal.view.*

class GoalListGoalViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun setGoal(goal: Goal?){
        if (goal == null){
            itemView.textview_row_goal_list_goal.text = ""
            itemView.layout_row_goal_list.setOnClickListener {  }
        } else {
            itemView.textview_row_goal_list_goal.text = goal.userFields.description
            itemView.layout_row_goal_list.setOnClickListener {
                val bundle = Bundle().apply {
                    this.putSerializable("goal", goal)
                }
                it.findNavController().navigate(R.id.action_goalListFragment_to_addEditGoalFragment, bundle)
            }
        }

    }
}