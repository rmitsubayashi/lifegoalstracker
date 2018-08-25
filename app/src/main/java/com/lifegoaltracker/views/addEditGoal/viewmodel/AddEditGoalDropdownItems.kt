package com.lifegoaltracker.views.addEditGoal.viewmodel

import com.lifegoaltracker.model.goal.dueDate.dateObjects.Month
import com.lifegoaltracker.model.goal.dueDate.dateObjects.WeekOfMonth
import com.lifegoaltracker.model.goal.dueDate.span.GoalSpan
import com.lifegoaltracker.utils.uiDisplay.DateConverter
import com.lifegoaltracker.utils.date.DateGenerator
import com.lifegoaltracker.utils.uiDisplay.GoalSpanConverter

class AddEditGoalDropdownItems {
    private val dateConverter = DateConverter()
    private val dateUtil = DateGenerator()

    private val goalSpanConverter = GoalSpanConverter()
    
    fun getWeeks(): List<String>{
        return listOf(
                dateConverter.getWeekString(WeekOfMonth.WEEK_ONE)!!,
                dateConverter.getWeekString(WeekOfMonth.WEEK_TWO)!!,
                dateConverter.getWeekString(WeekOfMonth.WEEK_THREE)!!,
                dateConverter.getWeekString(WeekOfMonth.WEEK_FOUR)!!,
                dateConverter.getWeekString(WeekOfMonth.ANY)!!
        )
    }

    fun getMonths(): List<String>{
        return listOf(
                dateConverter.getMonthString(Month.JANUARY)!!,
                dateConverter.getMonthString(Month.FEBRUARY)!!,
                dateConverter.getMonthString(Month.MARCH)!!,
                dateConverter.getMonthString(Month.APRIL)!!,
                dateConverter.getMonthString(Month.MAY)!!,
                dateConverter.getMonthString(Month.JUNE)!!,
                dateConverter.getMonthString(Month.JULY)!!,
                dateConverter.getMonthString(Month.AUGUST)!!,
                dateConverter.getMonthString(Month.SEPTEMBER)!!,
                dateConverter.getMonthString(Month.OCTOBER)!!,
                dateConverter.getMonthString(Month.NOVEMBER)!!,
                dateConverter.getMonthString(Month.DECEMBER)!!
        )
    }

    fun getThreeMonths(): List<String>{
        return listOf(
                dateConverter.getMonthString(Month.MARCH)!!,
                dateConverter.getMonthString(Month.JUNE)!!,
                dateConverter.getMonthString(Month.SEPTEMBER)!!,
                dateConverter.getMonthString(Month.DECEMBER)!!
        )
    }

    fun getYears(): List<String>{
        val currDate = dateUtil.getCurrentDate()
        val currYear = currDate.year.yearValue
        val tenYears = mutableListOf<String>()
        for (i in 0..10){
            tenYears.add((currYear+i).toString())
        }
        return tenYears.toList()
    }

    fun getGoalSpans(): List<String>{
        return listOf(
                goalSpanConverter.getString(GoalSpan.ONE_WEEK)!!,
                goalSpanConverter.getString(GoalSpan.ONE_MONTH)!!,
                goalSpanConverter.getString(GoalSpan.THREE_MONTHS)!!,
                goalSpanConverter.getString(GoalSpan.ONE_YEAR)!!
        )
    }
}