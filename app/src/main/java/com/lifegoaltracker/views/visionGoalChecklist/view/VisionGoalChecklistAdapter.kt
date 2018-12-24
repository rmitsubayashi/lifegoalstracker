package com.lifegoaltracker.views.visionGoalChecklist.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CompoundButton
import androidx.navigation.findNavController
import com.lifegoaltracker.R
import com.lifegoaltracker.model.goal.Goal
import com.lifegoaltracker.model.goal.dueDate.span.GoalSpan
import com.lifegoaltracker.repository.ID
import com.lifegoaltracker.views.addEditGoal.view.AddEditGoalFragment
import kotlinx.android.synthetic.main.row_vision_goal_checklist_footer.view.*

class VisionGoalChecklistAdapter(private val contextMenuListener: View.OnCreateContextMenuListener,
                                 private val goalCheckedListener: OnGoalCheckedListener
                                 ):
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Goal> = listOf()
    private var visionID: ID = ID(-1)
    private var longClickedItem: Goal? = null

    fun setGoals(goals: List<Goal>, visionID: ID) {
        items = goals.toList()
        this.visionID = visionID
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size + 2 // header + footer

    override fun getItemViewType(position: Int): Int {
        if (items.isEmpty()) {
            return EMPTY
        }
        return when (position){
            0 -> HEADER
            items.size+1 -> FOOTER
            else -> ITEM
        }
    }

    fun getLongClickedItem(): Goal? = longClickedItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val resourceID = when (viewType){
            HEADER -> R.layout.row_vision_goal_checklist_header
            EMPTY -> R.layout.row_vision_goal_checklist_empty
            ITEM -> R.layout.row_vision_goal_checklist_goal
            FOOTER -> R.layout.row_vision_goal_checklist_footer
            else -> R.layout.row_error
        }

        val itemView = LayoutInflater.from(parent.context)
                .inflate(resourceID, parent, false)
        return when (viewType) {
            ITEM -> VisionGoalChecklistGoalViewHolder(itemView)
            else -> object: RecyclerView.ViewHolder(itemView){}
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemType = getItemViewType(position)
        when (itemType) {
            ITEM -> {
                val item = items[position-1] //header=0
                (holder as VisionGoalChecklistGoalViewHolder)
                        .setGoal(item, contextMenuListener, getOnLongClickListener(item),
                                goalCheckedListener)
            }
            FOOTER -> {
                setFooterListener(holder.itemView.button_vision_goal_checklist_footer)

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

    private fun setFooterListener(button: Button){
        visionID.value?.let {
            idVal ->
            if (idVal < 0){
                return
            }

            button.setOnClickListener {
                val bundle = AddEditGoalFragment.createBundle(visionID, GoalSpan.ONE_WEEK)
                it.findNavController().navigate(R.id.action_mainViewPagerFragment_to_addEditGoalFragment, bundle)
            }
        }

    }


    companion object {
        private const val HEADER = 0
        private const val EMPTY = 1
        private const val ITEM = 2
        private const val FOOTER = 3
    }
}