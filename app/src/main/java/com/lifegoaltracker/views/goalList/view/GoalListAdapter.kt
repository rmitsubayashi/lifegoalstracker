package com.lifegoaltracker.views.goalList.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.lifegoaltracker.R
import com.lifegoaltracker.views.goalList.viewmodel.GoalListRecyclerViewItem
import com.lifegoaltracker.views.goalList.viewmodel.GoalListRecyclerViewItemType
import kotlinx.android.synthetic.main.row_goal_list_subheader.view.*

class GoalListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items: MutableList<GoalListRecyclerViewItem> = mutableListOf()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val resourceID = when (viewType){
            GoalListRecyclerViewItemType.GOAL.value ->
                R.layout.row_goal_list_goal
            GoalListRecyclerViewItemType.HEADER.value ->
                R.layout.row_goal_list_subheader
            GoalListRecyclerViewItemType.LOADING.value ->
                R.layout.row_loading_full_screen
            GoalListRecyclerViewItemType.EMPTY.value ->
                R.layout.row_goal_list_empty
            else ->
                R.layout.row_error
        }

        val view =  LayoutInflater.from(parent.context).inflate(resourceID, parent, false)

        return when (viewType){
            GoalListRecyclerViewItemType.GOAL.value ->
                GoalListGoalViewHolder(view)
            else ->
                object: RecyclerView.ViewHolder(view){}
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (item.itemType){
            GoalListRecyclerViewItemType.HEADER ->
                holder.itemView.textview_row_goal_list_subheader.text = item.text
            GoalListRecyclerViewItemType.GOAL ->
                (holder as GoalListGoalViewHolder).setGoal(item.goal)
            else ->
                return
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].itemType.value
    }

    fun setItems(newItems: List<GoalListRecyclerViewItem>){
        items.clear()
        items.addAll(newItems)
    }
}