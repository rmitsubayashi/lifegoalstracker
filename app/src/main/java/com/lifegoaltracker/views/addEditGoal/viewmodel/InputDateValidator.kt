package com.lifegoaltracker.views.addEditGoal.viewmodel

import com.lifegoaltracker.model.goal.dueDate.DueDate
import com.lifegoaltracker.utils.date.DateGenerator

class InputDateValidator {
    fun isValidInput(dueDate: DueDate): Boolean {
        return dueDate.date >= DateGenerator().getCurrentDate()
    }
}