package com.lifegoaltracker.views.goalList

import com.lifegoaltracker.model.goal.dueDate.span.GoalSpan
import com.lifegoaltracker.repository.goal.GoalQueryParameters

class GoalListQueryHelper(private val goalDateRange: GoalDateRange) {
    fun getWeekGoalsQuery(): GoalQueryParameters {
        val startOfWeek = goalDateRange.getWeekFrom()
        val endOfWeek = goalDateRange.getWeekTo()
        return GoalQueryParameters(startOfWeek, endOfWeek, GoalSpan.ONE_WEEK)
    }

    fun getMonthGoalsQuery(): GoalQueryParameters {
        val startOfMonth = goalDateRange.getMonthFrom()
        val endOfMonth = goalDateRange.getMonthTo()
        return GoalQueryParameters(startOfMonth, endOfMonth, GoalSpan.ONE_MONTH)
    }

    fun getThreeMonthGoalsQuery(): GoalQueryParameters {
        val startOfThreeMonths = goalDateRange.getThreeMonthsFrom()
        val endOfThreeMonths = goalDateRange.getThreeMonthsTo()
        return GoalQueryParameters(startOfThreeMonths, endOfThreeMonths, GoalSpan.THREE_MONTHS)
    }

    fun getYearGoalsQuery(): GoalQueryParameters {
        val startOfYear = goalDateRange.getYearFrom()
        val endOfYear = goalDateRange.getYearTo()
        return GoalQueryParameters(startOfYear, endOfYear, GoalSpan.ONE_YEAR)
    }
}