package com.lifegoaltracker.views.visionGoals.view

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
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
import com.lifegoaltracker.utils.sort.goal.GoalSortBySpan
import com.lifegoaltracker.views.visionDetails.view.VisionDetailsGoalViewHolder
import kotlinx.android.synthetic.main.row_goal_list_goal.view.*
import kotlinx.android.synthetic.main.row_goal_list_subheader.view.*
import kotlinx.android.synthetic.main.row_vision_details_goal_subheader.view.*

class VisionGoalsAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Goal> = listOf()

    fun setGoals(goals: List<Goal>, visionID: ID) {
        items = goals.convertToRecyclerViewItems(visionID)
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position].id.value){
            -1L -> HEADER
            -2L -> EMPTY
            else -> ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val resourceID = when (viewType){
            HEADER -> R.layout.row_goal_list_subheader
            EMPTY -> R.layout.row_goal_list_empty
            ITEM -> R.layout.row_goal_list_goal
            else -> R.layout.row_error
        }

        val itemView = LayoutInflater.from(parent.context)
                .inflate(resourceID, parent, false)
        return when (viewType) {
            HEADER -> VisionGoalsHeaderViewHolder(itemView)
            else -> object: RecyclerView.ViewHolder(itemView){}
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemType = getItemViewType(position)
        val item = items[position]
        when (itemType) {
            HEADER -> (holder as VisionGoalsHeaderViewHolder).setHeader(item)
            ITEM -> holder.itemView.textview_row_goal_list_goal.text =
                    item.userFields.description
            else -> return
        }
    }

    companion object {
        private const val HEADER = 0
        private const val EMPTY = 1
        private const val ITEM = 2
    }
}