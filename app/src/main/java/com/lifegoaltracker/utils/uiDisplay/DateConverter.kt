package com.lifegoaltracker.utils.uiDisplay

import com.lifegoaltracker.model.goal.dueDate.dateObjects.Month
import com.lifegoaltracker.model.goal.dueDate.dateObjects.WeekOfMonth

//for displaying dates to the user
class DateConverter {
    //we don't have the cheat day here because
    // we don't want the user to select it
    private val weeks = Mapper(listOf(
            Pair(WeekOfMonth.WEEK_ONE, "1週目"),
            Pair(WeekOfMonth.WEEK_TWO, "2週目"),
            Pair(WeekOfMonth.WEEK_THREE, "3週目"),
            Pair(WeekOfMonth.WEEK_FOUR, "4週目"),
            Pair(WeekOfMonth.ANY, "いつでも"))
    )

    private val months = Mapper(listOf(
            Pair(Month.JANUARY, "1月"),
            Pair(Month.FEBRUARY, "2月"),
            Pair(Month.MARCH, "3月"),
            Pair(Month.APRIL, "4月"),
            Pair(Month.MAY, "5月"),
            Pair(Month.JUNE, "6月"),
            Pair(Month.JULY, "7月"),
            Pair(Month.AUGUST, "8月"),
            Pair(Month.SEPTEMBER, "9月"),
            Pair(Month.OCTOBER, "10月"),
            Pair(Month.NOVEMBER, "11月"),
            Pair(Month.DECEMBER, "12月")
    ))

    private val threeMonths = Mapper(listOf(
            Pair(Month.MARCH, "3月"),
            Pair(Month.JUNE, "6月"),
            Pair(Month.SEPTEMBER, "9月"),
            Pair(Month.DECEMBER, "12月")
    ))

    fun getWeek(text: String?): WeekOfMonth? {
        return weeks.getModel(text)
    }

    fun getMonth(text: String?): Month? {
        return months.getModel(text)
    }

    fun getThreeMonth(text: String?): Month? {
        return threeMonths.getModel(text)
    }

    fun getWeekString(week: WeekOfMonth?): String?{
        return weeks.getDisplay(week)
    }

    fun getMonthString(month: Month?): String?{
        return months.getDisplay(month)
    }

    fun getThreeMonthString(month: Month?): String?{
        return threeMonths.getDisplay(month)
    }

}