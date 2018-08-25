package com.lifegoaltracker.views.goalList.viewmodel

import com.lifegoaltracker.model.goal.Goal
import com.lifegoaltracker.model.goal.dueDate.span.GoalSpan
import com.lifegoaltracker.model.goal.sort.GoalSortByDueDate
import com.lifegoaltracker.model.goal.sort.GoalSortBySpan

//TODO somehow create an abstraction with VisionDetailsRecyclerViewList...
class GoalListRecyclerViewList {
    private val _items: MutableList<GoalListRecyclerViewItem> = mutableListOf()
    val items: List<GoalListRecyclerViewItem>
        get() = _items

    fun set(list: List<Goal>?){
        _items.clear()
        if (list == null){
            _items.add(GoalListRecyclerViewItem(
                    GoalListRecyclerViewItemType.LOADING,
                    null, null
            ))
            return
        }

        if (list.isEmpty()){
            _items.add(GoalListRecyclerViewItem(
                    GoalListRecyclerViewItemType.EMPTY,
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

    private fun convertGoalsToItems(goals: List<Goal>): List<GoalListRecyclerViewItem>{
        val result = mutableListOf<GoalListRecyclerViewItem>()
        for (goal in goals){
            result.add(GoalListRecyclerViewItem(
                    GoalListRecyclerViewItemType.GOAL,
                    goal, null
            ))
        }
        return result
    }

    //assumes sorted list
    private fun insertHeader(goalItemList: MutableList<GoalListRecyclerViewItem>, span: GoalSpan, text: String){
        for ((index, goalItem) in goalItemList.withIndex()){
            if (goalItem.goal == null){
                continue
            }
            if (goalItem.goal.userFields.dueDate.span == span){
                goalItemList.add(index,
                        GoalListRecyclerViewItem(
                                GoalListRecyclerViewItemType.HEADER,
                                null, text
                        )
                )
                return
            }
        }
    }
}