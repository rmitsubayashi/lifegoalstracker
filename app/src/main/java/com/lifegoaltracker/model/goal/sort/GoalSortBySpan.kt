package com.lifegoaltracker.model.goal.sort

import com.lifegoaltracker.model.goal.Goal
import com.lifegoaltracker.model.goal.dueDate.span.GoalSpan

class GoalSortBySpan: Comparator<Goal> {
    override fun compare(p0: Goal, p1: Goal): Int {
        val priority = listOf(GoalSpan.ONE_WEEK, GoalSpan.ONE_MONTH, GoalSpan.THREE_MONTHS, GoalSpan.ONE_YEAR)
        return  priority.indexOf(p0.userFields.dueDate.span)
                .compareTo(
                priority.indexOf(p1.userFields.dueDate.span)
                )
    }
}