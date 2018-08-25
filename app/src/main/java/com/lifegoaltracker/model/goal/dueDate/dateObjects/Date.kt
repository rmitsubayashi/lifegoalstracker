package com.lifegoaltracker.model.goal.dueDate.dateObjects

data class Date (
    val year: Year,
    //can be null if recording yearly goals
    val month: Month?,
    //can be null if recording monthly/yearly goals
    val week: WeekOfMonth?
    ): Comparable<Date>{
    override operator fun compareTo(other: Date): Int{
        val yearComparisonResult = compareYear(this, other)
        if (canStopComparing(yearComparisonResult)){
            return yearComparisonResult
        }
        val monthComparisonResult = compareMonth(this, other)
        if (canStopComparing(monthComparisonResult)){
            return monthComparisonResult
        }
        return compareWeek(this, other)
    }

    private fun canStopComparing(result: Int): Boolean{
        return result != 0
    }

    private fun compareYear(p0: Date, p1: Date): Int {
        val p0YearVal = p0.year.yearValue
        val p1YearVal = p1.year.yearValue
        return p0YearVal.compareTo(p1YearVal)
    }

    private fun compareMonth(p0: Date, p1: Date): Int {
        val p0Month = p0.month
        val p1Month = p1.month
        if (p0Month == null && p1Month == null){
            return 0
        }
        if (p0Month == null){
            return 1
        }
        if (p1Month == null){
            return -1
        }
        return p0Month.monthValue.compareTo(p1Month.monthValue)
    }

    private fun compareWeek(p0: Date, p1: Date): Int {
        val p0Week = p0.week
        val p1Week = p1.week
        if (p0Week == null && p1Week == null){
            return 0
        }
        if (p0Week == null){
            return 1
        }
        if (p1Week == null){
            return -1
        }
        return p0Week.weekValue.compareTo(p1Week.weekValue)
    }
}