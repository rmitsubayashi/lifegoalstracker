package com.lifegoaltracker.views.visionGoalChecklist.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CompoundButton
import com.lifegoaltracker.model.goal.Goal
import kotlinx.android.synthetic.main.row_vision_goal_checklist_goal.view.*

class VisionGoalChecklistGoalViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun setGoal(goal: Goal, contextMenuListener: View.OnCreateContextMenuListener,
                longClickListener: View.OnLongClickListener,
                goalCheckedListener: OnGoalCheckedListener){
        itemView.textview_vision_goal_checklist_goal.text =
                goal.userFields.description
        itemView.setOnCreateContextMenuListener(contextMenuListener)
        itemView.setOnLongClickListener(longClickListener)
        itemView.checkbox_vision_goal_checklist_goal
                .setOnCheckedChangeListener(getOnCheckChangedListener(goal, goalCheckedListener))
    }

    private fun getOnCheckChangedListener(goal: Goal, goalCheckedListener: OnGoalCheckedListener)
    : CompoundButton.OnCheckedChangeListener =
        CompoundButton.OnCheckedChangeListener {
            _, isChecked ->
            if (isChecked) {
                goalCheckedListener.onGoalChecked(goal)
            } else {
                goalCheckedListener.onGoalUnchecked(goal)
            }
        }
}