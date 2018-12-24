package com.lifegoaltracker.views.visionGoals.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lifegoaltracker.R
import com.lifegoaltracker.model.goal.Goal
import com.lifegoaltracker.repository.ID

class VisionGoalsAdapter(private val contextMenuListener: View.OnCreateContextMenuListener):
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Goal> = listOf()
    private var longClickedItem: Goal? = null

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

    fun getLongClickedItem(): Goal? = longClickedItem

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
            ITEM -> VisionGoalsGoalViewHolder(itemView)
            else -> object: RecyclerView.ViewHolder(itemView){}
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemType = getItemViewType(position)
        val item = items[position]
        when (itemType) {
            HEADER -> (holder as VisionGoalsHeaderViewHolder).setHeader(item)
            ITEM -> {
                (holder as VisionGoalsGoalViewHolder)
                        .setItem(item, contextMenuListener, getOnLongClickListener(item))
            }
            else -> return
        }
    }

    private fun getOnLongClickListener(goal: Goal): View.OnLongClickListener =
        View.OnLongClickListener {
            longClickedItem = goal
            //we don't want to block the context menu from showing so return false
            false
        }


    companion object {
        private const val HEADER = 0
        private const val EMPTY = 1
        private const val ITEM = 2
    }
}