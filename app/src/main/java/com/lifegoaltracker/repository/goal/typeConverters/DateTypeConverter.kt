package com.lifegoaltracker.repository.goal.typeConverters

import android.arch.persistence.room.TypeConverter
import com.lifegoaltracker.model.goal.dueDate.dateObjects.Date
import com.lifegoaltracker.model.goal.dueDate.dateObjects.Month
import com.lifegoaltracker.model.goal.dueDate.dateObjects.WeekOfMonth
import com.lifegoaltracker.model.goal.dueDate.dateObjects.Year

class DateTypeConverter {
    @TypeConverter
    fun toString(date: Date): String {
        var dateString = ""
        val year = date.year.yearValue.toString()
        dateString = dateString.plus(year)
        if (date.month == null){
            return dateString
        }
        val month = date.month.monthValue.toString()
        dateString = dateString.plus("-")
        dateString = dateString.plus(month)
        if (date.week == null){
            return dateString
        }
        val week = date.week.weekValue.toString()
        dateString = dateString.plus("-")
        dateString = dateString.plus(week)

        return dateString
    }

    @TypeConverter
    fun toDate(dateString: String): Date {
        val dateStringArray = dateString.split("-")
        val yearVal = dateStringArray[0].toInt()
        val monthVal = if (dateString.length > 1) {dateStringArray[1].toInt()} else {null}
        val weekVal = if (dateString.length > 2) {dateStringArray[2].toInt()} else {null}

        val year = Year(yearVal)
        val month: Month? = if (monthVal == null){null} else {Month.valueOf(monthVal)}
        val week: WeekOfMonth? = if (weekVal == null){null} else {
            WeekOfMonth.valueOf(weekVal)
        }

        return Date(year, month, week)
    }
}