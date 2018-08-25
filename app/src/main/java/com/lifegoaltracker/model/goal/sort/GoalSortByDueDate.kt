package com.lifegoaltracker.model.goal.sort

import com.lifegoaltracker.model.goal.Goal

class GoalSortByDueDate: Comparator<Goal> {
    override fun compare(p0: Goal, p1: Goal): Int {
        return p0.userFields.dueDate.date.compareTo(p1.userFields.dueDate.date)
    }
}