package com.lifegoaltracker.model.goal.filter

import com.lifegoaltracker.model.goal.Goal

interface GoalFilter {
    fun selectFrom(goalList: List<Goal>): List<Goal>
}