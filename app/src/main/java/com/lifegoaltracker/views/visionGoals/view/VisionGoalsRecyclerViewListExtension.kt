package com.lifegoaltracker.views.visionGoals.view

import com.lifegoaltracker.model.goal.Goal
import com.lifegoaltracker.model.goal.GoalProperties
import com.lifegoaltracker.model.goal.GoalStatus
import com.lifegoaltracker.model.goal.GoalUserFields
import com.lifegoaltracker.model.goal.dueDate.DueDate
import com.lifegoaltracker.model.goal.dueDate.dateObjects.Date
import com.lifegoaltracker.model.goal.dueDate.dateObjects.Month
import com.lifegoaltracker.model.goal.dueDate.dateObjects.WeekOfMonth
import com.lifegoaltracker.model.goal.dueDate.dateObjects.Year
import com.lifegoaltracker.model.goal.dueDate.span.GoalSpan
import com.lifegoaltracker.repository.ID
import com.lifegoaltracker.utils.sort.goal.GoalSortBySpan

fun List<Goal>.convertToRecyclerViewItems(visionID: ID = ID(-1)): List<Goal> {
    //want to make this a pure function
    val copy = toMutableList()
    //ID(-2) = empty
    copy.addEmptyGoalSpans()
    val sortedCopy = copy.sortedWith(GoalSortBySpan()).toMutableList()
    //ID(-1) = header
    sortedCopy.addHeaders(visionID)
    return sortedCopy
}

private fun MutableList<Goal>.addEmptyGoalSpans(){
    insertEmptyGoalSpan(GoalSpan.ONE_MONTH, "no goals for this month")
    insertEmptyGoalSpan(GoalSpan.THREE_MONTHS, "no goals for this quarter")
    insertEmptyGoalSpan(GoalSpan.ONE_YEAR, "no goals for this year")
}

private fun MutableList<Goal>.insertEmptyGoalSpan(span: GoalSpan, text: String){
    for (item in iterator()){
        if (item.userFields.dueDate.span == span){
            return
        }
    }
    //don't care about ordering because we're sorting later
    add(
        Goal(ID(-2),
            GoalUserFields(text,
                "",
                DueDate(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_ONE), span)
            ),
            GoalProperties(0, ID(-1)),
            GoalStatus()
        )
    )
}

private fun MutableList<Goal>.addHeaders(visionID: ID){
    insertHeader(GoalSpan.ONE_MONTH, "This month I will...", visionID)
    insertHeader(GoalSpan.THREE_MONTHS, "In three months I will...", visionID)
    insertHeader(GoalSpan.ONE_YEAR, "This year I will...", visionID)
}

//assumes sorted list
private fun MutableList<Goal>.insertHeader(span: GoalSpan, text: String, visionID: ID){
    for ((index, goalItem) in withIndex()){
        if (goalItem.userFields.dueDate.span == span){
            add(index,
                    Goal(ID(-1),
                            GoalUserFields(text,
                                    "",
                                    //arbitrary placeholders
                                    DueDate(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_ONE), span)
                            ),
                            GoalProperties(0, visionID),
                            GoalStatus()
                    )
            )
            return
        }
    }
}