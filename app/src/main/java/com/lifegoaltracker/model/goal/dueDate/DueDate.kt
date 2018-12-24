package com.lifegoaltracker.model.goal.dueDate

import com.lifegoaltracker.model.goal.dueDate.dateObjects.Date
import com.lifegoaltracker.model.goal.dueDate.span.GoalSpan

data class DueDate (
        var date: Date,
        var span: GoalSpan
)