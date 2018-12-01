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
    private val date = Date(Year(2018), Month.JANUARY, null)
    
    @Before
    fun setup(){
        goalDateRange = GoalDateRange()
        goalListQueryHelper = GoalListQueryHelper(goalDateRange)
    }

    @Test
    fun week(){
        val query = goalListQueryHelper.getWeekGoalsQuery(date)
        assertEquals(GoalQueryParameters(goalDateRange.getWeekFrom(date),
                goalDateRange.getWeekTo(date),GoalSpan.ONE_WEEK),
                query)
    }
    
    @Test
    fun month(){
        val query = goalListQueryHelper.getMonthGoalsQuery(date)
        assertEquals(GoalQueryParameters(goalDateRange.getMonthFrom(date),
                goalDateRange.getMonthTo(date), GoalSpan.ONE_MONTH),
                query)
    }

    @Test
    fun threeMonths(){
        val query = goalListQueryHelper.getThreeMonthGoalsQuery(date)
        assertEquals(GoalQueryParameters(goalDateRange.getThreeMonthsFrom(date),
                goalDateRange.getThreeMonthsTo(date), GoalSpan.THREE_MONTHS),
                query)
    }

    @Test
    fun year(){
        val query = goalListQueryHelper.getYearGoalsQuery(date)
        assertEquals(GoalQueryParameters(goalDateRange.getYearFrom(date),
                goalDateRange.getYearTo(date), GoalSpan.ONE_YEAR),
                query)
    }
}