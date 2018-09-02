package com.lifegoaltracker.views.visions.view

import android.support.v7.widget.RecyclerView
import android.view.View
import androidx.navigation.Navigation
import com.lifegoaltracker.R
import kotlinx.android.synthetic.main.row_visions_add.view.*

class VisionAddViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    init {
        itemView.button_row_visions_add.setOnClickListener{
            view ->
                Navigation.findNavController(view).navigate(
                        R.id.action_visionsFragment_to_addEditVisionFragment
                )
        }
    }
}