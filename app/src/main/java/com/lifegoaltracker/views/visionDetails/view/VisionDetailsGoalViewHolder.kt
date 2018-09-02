package com.lifegoaltracker.views.visionDetails.view

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import androidx.navigation.Navigation
import com.lifegoaltracker.R
import com.lifegoaltracker.model.goal.Goal
import kotlinx.android.synthetic.main.row_vision_details_goal.view.*

class VisionDetailsGoalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun setGoal(goal: Goal){
        itemView.checkBox_row_vision_details_goal.text = goal.userFields.description
        itemView.layout_row_vision_details_goal.setOnClickListener{
            view ->
                val bundle = Bundle()
                bundle.putSerializable("goal", goal)
                Navigation.findNavController(view).navigate(
                        R.id.action_visionDetailsFragment_to_addEditGoalFragment,
                        bundle
                )
        }
    }
}