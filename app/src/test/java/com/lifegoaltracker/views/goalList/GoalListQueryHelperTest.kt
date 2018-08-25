package com.lifegoaltracker.views.goalList

import com.lifegoaltracker.model.goal.dueDate.dateObjects.Date
import com.lifegoaltracker.model.goal.dueDate.dateObjects.Month
import com.lifegoaltracker.model.goal.dueDate.dateObjects.WeekOfMonth
import com.lifegoaltracker.model.goal.dueDate.dateObjects.Year
import com.lifegoaltracker.model.goal.dueDate.span.GoalSpan
import com.lifegoaltracker.repository.goal.GoalQueryParameters
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GoalListQueryHelperTest {
    private lateinit var goalDateRange: GoalDateRange
    private lateinit var goalListQueryHelper: GoalListQueryHelper
    
    @Before
    fun setup(){
        goalDateRange = GoalDateRange(Date(
                Year(2018),Month.JULY, WeekOfMonth.WEEK_ONE
        ))
        goalListQueryHelper = GoalListQueryHelper(goalDateRange)
    }

    @Test
    fun week(){
        val query = goalListQueryHelper.getWeekGoalsQuery()
        assertEquals(GoalQueryParameters(goalDateRange.getWeekFrom(), goalDateRange.getWeekTo(),GoalSpan.ONE_WEEK),
                query)
    }
    
    @Test
    fun month(){
        val query = goalListQueryHelper.getMonthGoalsQuery()
        assertEquals(GoalQueryParameters(goalDateRange.getMonthFrom(),
                goalDateRange.getMonthTo(), GoalSpan.ONE_MONTH),
                query)
    }

    @Test
    fun threeMonths(){
        val query = goalListQueryHelper.getThreeMonthGoalsQuery()
        assertEquals(GoalQueryParameters(goalDateRange.getThreeMonthsFrom(),
                goalDateRange.getThreeMonthsTo(), GoalSpan.THREE_MONTHS),
                query)
    }

    @Test
    fun year(){
        val query = goalListQueryHelper.getYearGoalsQuery()
        assertEquals(GoalQueryParameters(goalDateRange.getYearFrom(),
                goalDateRange.getYearTo(), GoalSpan.ONE_YEAR),
                query)
    }
}