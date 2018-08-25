package com.lifegoaltracker.views.goalList

import com.lifegoaltracker.model.goal.dueDate.dateObjects.Date
import com.lifegoaltracker.model.goal.dueDate.dateObjects.Month
import com.lifegoaltracker.model.goal.dueDate.dateObjects.WeekOfMonth
import com.lifegoaltracker.model.goal.dueDate.dateObjects.Year
import org.junit.Assert.assertEquals
import org.junit.Test

class GoalDateRangeTest {
    lateinit var goalDateRange: GoalDateRange

    @Test
    fun middleOfFebruary(){
        val middleOfFebruary = Date(Year(2018), Month.FEBRUARY, WeekOfMonth.WEEK_THREE)
        goalDateRange = GoalDateRange(middleOfFebruary)
        assertEquals(Date(Year(2018), Month.FEBRUARY, WeekOfMonth.WEEK_THREE), goalDateRange.getWeekFrom())
        assertEquals(Date(Year(2018), Month.FEBRUARY, WeekOfMonth.ANY), goalDateRange.getWeekTo())
        assertEquals(Date(Year(2018), Month.FEBRUARY, null), goalDateRange.getMonthFrom())
        assertEquals(Date(Year(2018), Month.FEBRUARY, null), goalDateRange.getMonthTo())
        assertEquals(Date(Year(2018), Month.JANUARY, null), goalDateRange.getThreeMonthsFrom())
        assertEquals(Date(Year(2018), Month.MARCH, null), goalDateRange.getThreeMonthsTo())

    }

    @Test
    fun endOfFebruary(){
        val endOfFebruary = Date(Year(2018), Month.FEBRUARY, WeekOfMonth.WEEK_FOUR)
        goalDateRange = GoalDateRange(endOfFebruary)
        assertEquals(Date(Year(2018), Month.FEBRUARY, WeekOfMonth.WEEK_FOUR), goalDateRange.getWeekFrom())
        assertEquals(Date(Year(2018), Month.FEBRUARY, WeekOfMonth.ANY), goalDateRange.getWeekTo())
        assertEquals(Date(Year(2018), Month.FEBRUARY, null), goalDateRange.getMonthFrom())
        assertEquals(Date(Year(2018), Month.FEBRUARY, null), goalDateRange.getMonthTo())
        assertEquals(Date(Year(2018), Month.JANUARY, null), goalDateRange.getThreeMonthsFrom())
        assertEquals(Date(Year(2018), Month.MARCH, null), goalDateRange.getThreeMonthsTo())

    }

    @Test
    fun beginningOfFebruary(){
        val beginningOfFebruary = Date(Year(2018), Month.FEBRUARY, WeekOfMonth.WEEK_ONE)
        goalDateRange = GoalDateRange(beginningOfFebruary)
        assertEquals(Date(Year(2018), Month.FEBRUARY, WeekOfMonth.WEEK_ONE), goalDateRange.getWeekFrom())
        assertEquals(Date(Year(2018), Month.FEBRUARY, WeekOfMonth.ANY), goalDateRange.getWeekTo())
        assertEquals(Date(Year(2018), Month.FEBRUARY, null), goalDateRange.getMonthFrom())
        assertEquals(Date(Year(2018), Month.FEBRUARY, null), goalDateRange.getMonthTo())
        assertEquals(Date(Year(2018), Month.JANUARY, null), goalDateRange.getThreeMonthsFrom())
        assertEquals(Date(Year(2018), Month.MARCH, null), goalDateRange.getThreeMonthsTo())

    }

    @Test
    fun month(){
        val monthOfFebruary = Date(Year(2018), Month.FEBRUARY, null)
        goalDateRange = GoalDateRange(monthOfFebruary)
        assertEquals(Date(Year(2018), Month.FEBRUARY, WeekOfMonth.WEEK_ONE), goalDateRange.getWeekFrom())
        assertEquals(Date(Year(2018), Month.FEBRUARY, WeekOfMonth.ANY), goalDateRange.getWeekTo())
        assertEquals(Date(Year(2018), Month.FEBRUARY, null), goalDateRange.getMonthFrom())
        assertEquals(Date(Year(2018), Month.FEBRUARY, null), goalDateRange.getMonthTo())
        assertEquals(Date(Year(2018), Month.JANUARY, null), goalDateRange.getThreeMonthsFrom())
        assertEquals(Date(Year(2018), Month.MARCH, null), goalDateRange.getThreeMonthsTo())
    }

    @Test
    fun year(){
        val year = Date(Year(2018), null, null)
        goalDateRange = GoalDateRange(year)
        assertEquals(Date(Year(2018), Month.JANUARY, WeekOfMonth.WEEK_ONE), goalDateRange.getWeekFrom())
        assertEquals(Date(Year(2018), Month.DECEMBER, WeekOfMonth.ANY), goalDateRange.getWeekTo())
        assertEquals(Date(Year(2018), Month.JANUARY, null), goalDateRange.getMonthFrom())
        assertEquals(Date(Year(2018), Month.DECEMBER, null), goalDateRange.getMonthTo())
        assertEquals(Date(Year(2018), Month.JANUARY, null), goalDateRange.getThreeMonthsFrom())
        assertEquals(Date(Year(2018), Month.DECEMBER, null), goalDateRange.getThreeMonthsTo())
    }

    @Test
    fun threeMonths(){
        val january = Date(Year(2018), Month.JANUARY, null)
        goalDateRange = GoalDateRange(january)
        assertEquals(Date(Year(2018), Month.JANUARY, null), goalDateRange.getThreeMonthsFrom())
        assertEquals(Date(Year(2018), Month.MARCH, null), goalDateRange.getThreeMonthsTo())
        val february = Date(Year(2018), Month.FEBRUARY, null)
        goalDateRange = GoalDateRange(february)
        assertEquals(Date(Year(2018), Month.JANUARY, null), goalDateRange.getThreeMonthsFrom())
        assertEquals(Date(Year(2018), Month.MARCH, null), goalDateRange.getThreeMonthsTo())
        val march = Date(Year(2018), Month.MARCH, null)
        goalDateRange = GoalDateRange(march)
        assertEquals(Date(Year(2018), Month.JANUARY, null), goalDateRange.getThreeMonthsFrom())
        assertEquals(Date(Year(2018), Month.MARCH, null), goalDateRange.getThreeMonthsTo())
        val april = Date(Year(2018), Month.APRIL, null)
        goalDateRange = GoalDateRange(april)
        assertEquals(Date(Year(2018), Month.APRIL, null), goalDateRange.getThreeMonthsFrom())
        assertEquals(Date(Year(2018), Month.JUNE, null), goalDateRange.getThreeMonthsTo())
        val may = Date(Year(2018), Month.MAY, null)
        goalDateRange = GoalDateRange(may)
        assertEquals(Date(Year(2018), Month.APRIL, null), goalDateRange.getThreeMonthsFrom())
        assertEquals(Date(Year(2018), Month.JUNE, null), goalDateRange.getThreeMonthsTo())
        val june = Date(Year(2018), Month.JUNE, null)
        goalDateRange = GoalDateRange(june)
        assertEquals(Date(Year(2018), Month.APRIL, null), goalDateRange.getThreeMonthsFrom())
        assertEquals(Date(Year(2018), Month.JUNE, null), goalDateRange.getThreeMonthsTo())
        val july = Date(Year(2018), Month.JULY, null)
        goalDateRange = GoalDateRange(july)
        assertEquals(Date(Year(2018), Month.JULY, null), goalDateRange.getThreeMonthsFrom())
        assertEquals(Date(Year(2018), Month.SEPTEMBER, null), goalDateRange.getThreeMonthsTo())
        val august = Date(Year(2018), Month.AUGUST, null)
        goalDateRange = GoalDateRange(august)
        assertEquals(Date(Year(2018), Month.JULY, null), goalDateRange.getThreeMonthsFrom())
        assertEquals(Date(Year(2018), Month.SEPTEMBER, null), goalDateRange.getThreeMonthsTo())
        val september = Date(Year(2018), Month.SEPTEMBER, null)
        goalDateRange = GoalDateRange(september)
        assertEquals(Date(Year(2018), Month.JULY, null), goalDateRange.getThreeMonthsFrom())
        assertEquals(Date(Year(2018), Month.SEPTEMBER, null), goalDateRange.getThreeMonthsTo())
        val october = Date(Year(2018), Month.OCTOBER, null)
        goalDateRange = GoalDateRange(october)
        assertEquals(Date(Year(2018), Month.OCTOBER, null), goalDateRange.getThreeMonthsFrom())
        assertEquals(Date(Year(2018), Month.DECEMBER, null), goalDateRange.getThreeMonthsTo())
        val november = Date(Year(2018), Month.NOVEMBER, null)
        goalDateRange = GoalDateRange(november)
        assertEquals(Date(Year(2018), Month.OCTOBER, null), goalDateRange.getThreeMonthsFrom())
        assertEquals(Date(Year(2018), Month.DECEMBER, null), goalDateRange.getThreeMonthsTo())
        val december = Date(Year(2018), Month.DECEMBER, null)
        goalDateRange = GoalDateRange(december)
        assertEquals(Date(Year(2018), Month.OCTOBER, null), goalDateRange.getThreeMonthsFrom())
        assertEquals(Date(Year(2018), Month.DECEMBER, null), goalDateRange.getThreeMonthsTo())
    }


}