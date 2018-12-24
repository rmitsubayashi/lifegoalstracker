package com.lifegoaltracker.views.visionGoalChecklist.view

import com.lifegoaltracker.model.goal.Goal

interface OnGoalCheckedListener {
    fun onGoalChecked(goal: Goal)
    fun onGoalUnchecked(goal: Goal)
}