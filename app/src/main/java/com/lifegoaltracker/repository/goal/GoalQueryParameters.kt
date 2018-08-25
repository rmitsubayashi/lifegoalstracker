package com.lifegoaltracker.repository.goal

import com.lifegoaltracker.model.goal.dueDate.dateObjects.Date
import com.lifegoaltracker.model.goal.dueDate.span.GoalSpan

data class GoalQueryParameters (
        val startDate: Date,
        val endDate: Date,
        val span: GoalSpan
)