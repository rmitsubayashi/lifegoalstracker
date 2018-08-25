package com.lifegoaltracker.views.visionDetails.view

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.lifegoaltracker.views.visionDetails.viewmodel.VisionDetailsRecyclerViewItem

class VisionDetailsRecyclerViewAdapter: RecyclerView.Adapter<VisionDetailsViewHolder>() {
    private val items: List<VisionDetailsRecyclerViewItem> = emptyList()
    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type.value
    }

    override fun onBindViewHolder(holder: VisionDetailsViewHolder, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisionDetailsViewHolder {
        return VisionDetailsViewHolder(parent)
    }
}