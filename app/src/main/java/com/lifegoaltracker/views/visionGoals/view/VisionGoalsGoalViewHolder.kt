package com.lifegoaltracker.views.visionGoals.view

import android.support.v7.widget.RecyclerView
import android.view.View
import com.lifegoaltracker.model.goal.Goal
import kotlinx.android.synthetic.main.row_goal_list_goal.view.*

class VisionGoalsGoalViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun setItem(goal: Goal, contextMenuListener: View.OnCreateContextMenuListener,
                longClickListener: View.OnLongClickListener){
        itemView.textview_row_goal_list_goal.text =
                goal.userFields.description
        itemView.setOnCreateContextMenuListener(contextMenuListener)
        itemView.setOnLongClickListener(longClickListener)
    }
}