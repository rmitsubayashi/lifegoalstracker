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
        goalDateRange = GoalDateRange()
        assertEquals(Date(Year(2018), Month.FEBRUARY, WeekOfMonth.WEEK_THREE), goalDateRange.getWeekFrom(middleOfFebruary))
        assertEquals(Date(Year(2018), Month.FEBRUARY, WeekOfMonth.ANY), goalDateRange.getWeekTo(middleOfFebruary))
        assertEquals(Date(Year(2018), Month.FEBRUARY, null), goalDateRange.getMonthFrom(middleOfFebruary))
        assertEquals(Date(Year(2018), Month.FEBRUARY, null), goalDateRange.getMonthTo(middleOfFebruary))
        assertEquals(Date(Year(2018), Month.JANUARY, null), goalDateRange.getThreeMonthsFrom(middleOfFebruary))
        assertEquals(Date(Year(2018), Month.MARCH, null), goalDateRange.getThreeMonthsTo(middleOfFebruary))

    }

    @Test
    fun endOfFebruary(){
        val endOfFebruary = Date(Year(2018), Month.FEBRUARY, WeekOfMonth.WEEK_FOUR)
        goalDateRange = GoalDateRange()
        assertEquals(Date(Year(2018), Month.FEBRUARY, WeekOfMonth.WEEK_FOUR), goalDateRange.getWeekFrom(endOfFebruary))
        assertEquals(Date(Year(2018), Month.FEBRUARY, WeekOfMonth.ANY), goalDateRange.getWeekTo(endOfFebruary))
        assertEquals(Date(Year(2018), Month.FEBRUARY, null), goalDateRange.getMonthFrom(endOfFebruary))
        assertEquals(Date(Year(2018), Month.FEBRUARY, null), goalDateRange.getMonthTo(endOfFebruary))
        assertEquals(Date(Year(2018), Month.JANUARY, null), goalDateRange.getThreeMonthsFrom(endOfFebruary))
        assertEquals(Date(Year(2018), Month.MARCH, null), goalDateRange.getThreeMonthsTo(endOfFebruary))

    }

    @Test
    fun beginningOfFebruary(){
        val beginningOfFebruary = Date(Year(2018), Month.FEBRUARY, WeekOfMonth.WEEK_ONE)
        goalDateRange = GoalDateRange()
        assertEquals(Date(Year(2018), Month.FEBRUARY, WeekOfMonth.WEEK_ONE), goalDateRange.getWeekFrom(beginningOfFebruary))
        assertEquals(Date(Year(2018), Month.FEBRUARY, WeekOfMonth.ANY), goalDateRange.getWeekTo(beginningOfFebruary))
        assertEquals(Date(Year(2018), Month.FEBRUARY, null), goalDateRange.getMonthFrom(beginningOfFebruary))
        assertEquals(Date(Year(2018), Month.FEBRUARY, null), goalDateRange.getMonthTo(beginningOfFebruary))
        assertEquals(Date(Year(2018), Month.JANUARY, null), goalDateRange.getThreeMonthsFrom(beginningOfFebruary))
        assertEquals(Date(Year(2018), Month.MARCH, null), goalDateRange.getThreeMonthsTo(beginningOfFebruary))

    }

    @Test
    fun month(){
        val monthOfFebruary = Date(Year(2018), Month.FEBRUARY, null)
        goalDateRange = GoalDateRange()
        assertEquals(Date(Year(2018), Month.FEBRUARY, WeekOfMonth.WEEK_ONE), goalDateRange.getWeekFrom(monthOfFebruary))
        assertEquals(Date(Year(2018), Month.FEBRUARY, WeekOfMonth.ANY), goalDateRange.getWeekTo(monthOfFebruary))
        assertEquals(Date(Year(2018), Month.FEBRUARY, null), goalDateRange.getMonthFrom(monthOfFebruary))
        assertEquals(Date(Year(2018), Month.FEBRUARY, null), goalDateRange.getMonthTo(monthOfFebruary))
        assertEquals(Date(Year(2018), Month.JANUARY, null), goalDateRange.getThreeMonthsFrom(monthOfFebruary))
        assertEquals(Date(Year(2018), Month.MARCH, null), goalDateRange.getThreeMonthsTo(monthOfFebruary))
    }

    @Test
    fun year(){
        val year = Date(Year(2018), null, null)
        goalDateRange = GoalDateRange()
        assertEquals(Date(Year(2018), Month.JANUARY, WeekOfMonth.WEEK_ONE), goalDateRange.getWeekFrom(year))
        assertEquals(Date(Year(2018), Month.DECEMBER, WeekOfMonth.ANY), goalDateRange.getWeekTo(year))
        assertEquals(Date(Year(2018), Month.JANUARY, null), goalDateRange.getMonthFrom(year))
        assertEquals(Date(Year(2018), Month.DECEMBER, null), goalDateRange.getMonthTo(year))
        assertEquals(Date(Year(2018), Month.JANUARY, null), goalDateRange.getThreeMonthsFrom(year))
        assertEquals(Date(Year(2018), Month.DECEMBER, null), goalDateRange.getThreeMonthsTo(year))
    }

    @Test
    fun threeMonths(){
        val january = Date(Year(2018), Month.JANUARY, null)
        goalDateRange = GoalDateRange()
        assertEquals(Date(Year(2018), Month.JANUARY, null), goalDateRange.getThreeMonthsFrom(january))
        assertEquals(Date(Year(2018), Month.MARCH, null), goalDateRange.getThreeMonthsTo(january))
        val february = Date(Year(2018), Month.FEBRUARY, null)
        goalDateRange = GoalDateRange()
        assertEquals(Date(Year(2018), Month.JANUARY, null), goalDateRange.getThreeMonthsFrom(february))
        assertEquals(Date(Year(2018), Month.MARCH, null), goalDateRange.getThreeMonthsTo(february))
        val march = Date(Year(2018), Month.MARCH, null)
        goalDateRange = GoalDateRange()
        assertEquals(Date(Year(2018), Month.JANUARY, null), goalDateRange.getThreeMonthsFrom(march))
        assertEquals(Date(Year(2018), Month.MARCH, null), goalDateRange.getThreeMonthsTo(march))
        val april = Date(Year(2018), Month.APRIL, null)
        goalDateRange = GoalDateRange()
        assertEquals(Date(Year(2018), Month.APRIL, null), goalDateRange.getThreeMonthsFrom(april))
        assertEquals(Date(Year(2018), Month.JUNE, null), goalDateRange.getThreeMonthsTo(april))
        val october = Date(Year(2018), Month.OCTOBER, null)
        goalDateRange = GoalDateRange()
        assertEquals(Date(Year(2018), Month.OCTOBER, null), goalDateRange.getThreeMonthsFrom(october))
        assertEquals(Date(Year(2018), Month.DECEMBER, null), goalDateRange.getThreeMonthsTo(october))
        val december = Date(Year(2018), Month.DECEMBER, null)
        goalDateRange = GoalDateRange()
        assertEquals(Date(Year(2018), Month.OCTOBER, null), goalDateRange.getThreeMonthsFrom(december))
        assertEquals(Date(Year(2018), Month.DECEMBER, null), goalDateRange.getThreeMonthsTo(december))
    }


}