package com.lifegoaltracker.views.addEditGoal.viewmodel

import com.lifegoaltracker.model.goal.dueDate.span.GoalSpan

class DatePickerEnabledState(var week: Boolean, var month: Boolean,
                             var threeMonths: Boolean /*year always enabled*/) {
    fun toggleState(span: GoalSpan){
        when (span) {
            GoalSpan.ONE_WEEK ->
                enableWeek()
            GoalSpan.ONE_MONTH ->
                enableMonth()
            GoalSpan.THREE_MONTHS ->
                enableThreeMonths()
            GoalSpan.ONE_YEAR ->
                enableYear()
        }
    }

    private fun enableWeek(){
        week = true
        month = true
        threeMonths = false
    }

    private fun enableMonth(){
        week = false
        month = true
        threeMonths = false
    }

    private fun enableThreeMonths(){
        week = false
        month = false
        threeMonths = true
    }

    private fun enableYear(){
        week = false
        month = false
        threeMonths = false
    }
}