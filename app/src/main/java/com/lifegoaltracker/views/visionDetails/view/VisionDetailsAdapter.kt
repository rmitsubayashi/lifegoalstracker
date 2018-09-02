package com.lifegoaltracker.views.visionDetails.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.lifegoaltracker.R
import com.lifegoaltracker.views.visionDetails.viewmodel.VisionDetailsRecyclerViewItem
import com.lifegoaltracker.views.visionDetails.viewmodel.VisionDetailsRecyclerViewItemType
import kotlinx.android.synthetic.main.row_vision_details_description.view.*
import kotlinx.android.synthetic.main.row_vision_details_goal_subheader.view.*
import kotlinx.android.synthetic.main.row_vision_details_reason.view.*

class VisionDetailsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<VisionDetailsRecyclerViewItem>()
    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = items[position].type.value

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (item.type){
            VisionDetailsRecyclerViewItemType.GOAL ->
                item.goal?.let {
                    (holder as VisionDetailsGoalViewHolder).setGoal(it)
                }
            VisionDetailsRecyclerViewItemType.VISION_DESCRIPTION ->
                holder.itemView.textview_row_details_description.text =
                        item.displayString
            VisionDetailsRecyclerViewItemType.VISION_REASON ->
                holder.itemView.textview_row_vision_details_reason.text =
                        item.displayString
            VisionDetailsRecyclerViewItemType.HEADER ->
                holder.itemView.textview_row_vision_details_subheader.text =
                        item.displayString
            else ->
                return
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VisionDetailsRecyclerViewItemType.GOAL.value){
            val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_vision_details_goal, parent, false)
            return VisionDetailsGoalViewHolder(itemView)
        }

        val resourceID = when (viewType){
            VisionDetailsRecyclerViewItemType.HEADER.value -> R.layout.row_vision_details_goal_subheader
            VisionDetailsRecyclerViewItemType.VISION_DESCRIPTION.value -> R.layout.row_vision_details_description
            VisionDetailsRecyclerViewItemType.VISION_REASON.value -> R.layout.row_vision_details_reason
            else -> R.layout.row_error
        }
        val itemView = LayoutInflater.from(parent.context)
                .inflate(resourceID, parent, false)
        return object: RecyclerView.ViewHolder(itemView){}
    }

    fun setItems(items: List<VisionDetailsRecyclerViewItem>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}