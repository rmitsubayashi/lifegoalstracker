package com.lifegoaltracker.views.visions.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.lifegoaltracker.R
import com.lifegoaltracker.model.vision.Vision

class VisionsAdapter:
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val visionsList = mutableListOf<Vision>()
    private val add = 1
    private val vision = 2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutID = when (viewType){
            add -> R.layout.row_visions_add
            vision -> R.layout.row_visions_vision
            else -> R.layout.row_error
        }
        val itemView = LayoutInflater.from(parent.context)
                .inflate(layoutID, parent, false)
        return when (viewType){
            add -> VisionAddViewHolder(itemView)
            vision -> VisionViewHolder(itemView)
            else -> object: RecyclerView.ViewHolder(itemView){}
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)){
            add -> return//do nothing
            vision -> (holder as VisionViewHolder).setVision(visionsList[position])
        }
    }

    //account for button at the bottom
    override fun getItemCount() = visionsList.size + 1

    override fun getItemViewType(position: Int): Int {
        if (position == itemCount-1){
            return add
        }
        return vision
    }

    fun setVisions(visions: List<Vision>){
        visionsList.clear()
        visionsList.addAll(visions)
        notifyDataSetChanged()
    }
}