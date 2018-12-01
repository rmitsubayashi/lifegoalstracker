package com.lifegoaltracker.views.goalList

import com.lifegoaltracker.model.goal.dueDate.dateObjects.Date
import com.lifegoaltracker.model.goal.dueDate.span.GoalSpan
import com.lifegoaltracker.repository.goal.GoalQueryParameters
import javax.inject.Inject

class GoalListQueryHelper @Inject constructor(private val goalDateRange: GoalDateRange) {
    fun getWeekGoalsQuery(date: Date): GoalQueryParameters {
        val startOfWeek = goalDateRange.getWeekFrom(date)
        val endOfWeek = goalDateRange.getWeekTo(date)
        return GoalQueryParameters(startOfWeek, endOfWeek, GoalSpan.ONE_WEEK)
    }

    fun getMonthGoalsQuery(date: Date): GoalQueryParameters {
        val startOfMonth = goalDateRange.getMonthFrom(date)
        val endOfMonth = goalDateRange.getMonthTo(date)
        return GoalQueryParameters(startOfMonth, endOfMonth, GoalSpan.ONE_MONTH)
    }

    fun getThreeMonthGoalsQuery(date: Date): GoalQueryParameters {
        val startOfThreeMonths = goalDateRange.getThreeMonthsFrom(date)
        val endOfThreeMonths = goalDateRange.getThreeMonthsTo(date)
        return GoalQueryParameters(startOfThreeMonths, endOfThreeMonths, GoalSpan.THREE_MONTHS)
    }

    fun getYearGoalsQuery(date: Date): GoalQueryParameters {
        val startOfYear = goalDateRange.getYearFrom(date)
        val endOfYear = goalDateRange.getYearTo(date)
        return GoalQueryParameters(startOfYear, endOfYear, GoalSpan.ONE_YEAR)
    }
}