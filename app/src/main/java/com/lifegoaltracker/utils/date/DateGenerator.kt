package com.lifegoaltracker.utils.date

import com.lifegoaltracker.model.goal.dueDate.dateObjects.Date
import com.lifegoaltracker.model.goal.dueDate.dateObjects.Month
import com.lifegoaltracker.model.goal.dueDate.dateObjects.WeekOfMonth
import com.lifegoaltracker.model.goal.dueDate.dateObjects.Year
import java.util.*
import javax.inject.Inject


class DateGenerator @Inject constructor() {
    fun getCurrentDate(): Date {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        //+1 since JANUARY is 0
        val month = calendar.get(Calendar.MONTH)+1
        val week = calendar.get(Calendar.WEEK_OF_MONTH)
        return Date(Year(year), Month.valueOf(month), WeekOfMonth.valueOf(week))
    }

    fun getCurrentTimestamp(): Long {
        return System.currentTimeMillis()
    }
}