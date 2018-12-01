package com.lifegoaltracker.views.visionDetails.viewmodel

import com.lifegoaltracker.model.goal.Goal
import com.lifegoaltracker.model.goal.dueDate.span.GoalSpan
import com.lifegoaltracker.utils.sort.goal.GoalSortByDueDate
import com.lifegoaltracker.utils.sort.goal.GoalSortBySpan
import com.lifegoaltracker.model.vision.Vision
import javax.inject.Inject

class VisionDetailsRecyclerViewList @Inject constructor() {
    private val _items: MutableList<VisionDetailsRecyclerViewItem> = mutableListOf()
    //the role of this class is to sort the list.
    //no outside class should be able to edit the sorted list
    val items: List<VisionDetailsRecyclerViewItem>
        get() = _items

    fun set(vision: Vision?, list: List<Goal>?){
        _items.clear()
        setVisionSection(vision)
        setGoalListSection(list)
    }

    private fun setVisionSection(vision: Vision?){
        if (vision == null){
            _items.add(VisionDetailsRecyclerViewItem(
                    VisionDetailsRecyclerViewItemType.VISION_LOADING,
                    null, null
            ))
            return
        }

        if (vision.userFields.description.isNullOrBlank()) {
            _items.add(VisionDetailsRecyclerViewItem(
                    VisionDetailsRecyclerViewItemType.VISION_DESCRIPTION_EMPTY,
                    null, null
            ))
        } else {
            val visionDescriptionItem = VisionDetailsRecyclerViewItem(
                    VisionDetailsRecyclerViewItemType.VISION_DESCRIPTION,
                    null,
                    vision.userFields.description)
            _items.add(visionDescriptionItem)
        }

        if (vision.userFields.reason.isNullOrBlank()) {
            _items.add(VisionDetailsRecyclerViewItem(
                    VisionDetailsRecyclerViewItemType.VISION_REASON_EMPTY,
                    null, null
            ))
        } else {
            val visionReasonItem = VisionDetailsRecyclerViewItem(
                    VisionDetailsRecyclerViewItemType.VISION_REASON,
                    null,
                    vision.userFields.reason)
            _items.add(visionReasonItem)
        }
    }

    private fun setGoalListSection(list: List<Goal>?){
        if (list == null){
            _items.add(VisionDetailsRecyclerViewItem(
                    VisionDetailsRecyclerViewItemType.GOALS_LOADING,
                    null, null
            ))
            return
        }

        if (list.isEmpty()) {
            _items.add(VisionDetailsRecyclerViewItem(
                    VisionDetailsRecyclerViewItemType.GOALS_EMPTY,
                    null, null
            ))
            return
        }

        val sortedList = orderGoals(list)
        val goalItems = convertGoalsToItems(sortedList).toMutableList()
        insertHeader(goalItems, GoalSpan.ONE_WEEK, "週")
        insertHeader(goalItems, GoalSpan.ONE_MONTH, "月")
        insertHeader(goalItems, GoalSpan.THREE_MONTHS, "3か月")
        insertHeader(goalItems, GoalSpan.ONE_YEAR, "年")
        _items.addAll(goalItems)
    }

    private fun orderGoals(goals: List<Goal>): List<Goal>{
        val dueDateSort = goals.sortedWith(GoalSortByDueDate())
        return dueDateSort.sortedWith(GoalSortBySpan())
    }

    private fun convertGoalsToItems(goals: List<Goal>): List<VisionDetailsRecyclerViewItem>{
        val result = mutableListOf<VisionDetailsRecyclerViewItem>()
        for (goal in goals){
            result.add(VisionDetailsRecyclerViewItem(
                    VisionDetailsRecyclerViewItemType.GOAL,
                    goal, null
            ))
        }
        return result
    }

    //assumes sorted list
    private fun insertHeader(goalItemList: MutableList<VisionDetailsRecyclerViewItem>, span: GoalSpan, text: String){
        for ((index, goalItem) in goalItemList.withIndex()){
            if (goalItem.goal == null){
                continue
            }
            if (goalItem.goal.userFields.dueDate.span == span){
                goalItemList.add(index,
                        VisionDetailsRecyclerViewItem(
                                VisionDetailsRecyclerViewItemType.HEADER,
                                null, text
                        )
                )
                return
            }
        }
    }
}