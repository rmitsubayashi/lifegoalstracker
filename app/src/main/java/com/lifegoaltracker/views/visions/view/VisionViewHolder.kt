package com.lifegoaltracker.views.visions.view

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import androidx.navigation.Navigation
import com.lifegoaltracker.R
import com.lifegoaltracker.model.vision.Priority
import com.lifegoaltracker.model.vision.Vision
import com.lifegoaltracker.views.addEditVision.view.AddEditVisionFragment
import kotlinx.android.synthetic.main.row_visions_vision.view.*

class VisionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun setVision(vision: Vision){
        itemView.textview_vision_title.text = vision.userFields.title
        itemView.textview_vision_description.text = vision.userFields.description
        itemView.textview_vision_title.setTypeface(null,
                if (vision.userFields.priority == Priority.HIGH){
                    Typeface.BOLD
                } else {
                    Typeface.NORMAL
                }
        )

        itemView.layout_row_visions_vision.setOnClickListener{
            view ->
            val bundle = AddEditVisionFragment.createBundle(vision)
            Navigation.findNavController(view).navigate(
                    R.id.action_visionsFragment_to_visionDetailsFragment,
                    bundle
            )
        }

        itemView.layout_row_visions_vision.setOnLongClickListener {
            view ->
            //for debugging only. we should show menu instead?
            val bundle = Bundle()
            bundle.putSerializable("vision", vision)
            Navigation.findNavController(view).navigate(
                    R.id.action_visionsFragment_to_addEditVisionFragment,
                    bundle
            )
            true
        }
    }
}