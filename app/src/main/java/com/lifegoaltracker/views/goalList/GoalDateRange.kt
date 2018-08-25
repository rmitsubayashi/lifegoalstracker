package com.lifegoaltracker.views.goalList

import com.lifegoaltracker.model.goal.dueDate.dateObjects.Date
import com.lifegoaltracker.model.goal.dueDate.dateObjects.Month
import com.lifegoaltracker.model.goal.dueDate.dateObjects.WeekOfMonth

class GoalDateRange(private val date: Date) {
    fun getWeekFrom(): Date {
        return Date(
                date.year,
                date.month ?: Month.JANUARY,
                date.week ?: WeekOfMonth.WEEK_ONE
        )
    }

    fun getWeekTo(): Date{
        return Date(
                date.year,
                date.month ?: Month.DECEMBER,
                WeekOfMonth.ANY)
    }

    fun getMonthFrom(): Date {
        return Date(
                date.year,
                date.month ?: Month.JANUARY,
                null
        )
    }

    fun getMonthTo(): Date {
        return Date(
                date.year,
                date.month ?: Month.DECEMBER,
                null
        )
    }

    fun getThreeMonthsFrom(): Date {
        val month = if (date.month == null){
            Month.JANUARY
        }else {
            val monthsInQuarter = 3
            when ((date.month.monthValue-1) / monthsInQuarter){
                0 -> Month.JANUARY
                1 -> Month.APRIL
                2 -> Month.JULY
                else -> Month.OCTOBER
            }
        }

        return Date(
                date.year,
                month,
                null
        )
    }

    fun getThreeMonthsTo(): Date {
        val month = if (date.month == null){
            Month.DECEMBER
        }else {
            val monthsInQuarter = 3
            when ((date.month.monthValue-1) / monthsInQuarter){
                0 -> Month.MARCH
                1 -> Month.JUNE
                2 -> Month.SEPTEMBER
                else -> Month.DECEMBER
            }
        }

        return Date(
                date.year,
                month,
                null
        )
    }

    fun getYearFrom(): Date{
        return Date(
                date.year,
                null,
                null)
    }

    fun getYearTo(): Date{
        return Date(
                date.year,
                null,
                null)
    }
}