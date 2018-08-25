package com.lifegoaltracker.model.goal

data class GoalStatus (
        //default false
        var isCompleted: Boolean = false,
        //default false
        var isDeleted: Boolean = false
)