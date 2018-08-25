package com.lifegoaltracker.views.goalList.viewmodel

import com.lifegoaltracker.model.goal.Goal

data class GoalListRecyclerViewItem (
        val itemType: GoalListRecyclerViewItemType,
        val goal: Goal?,
        val text: String?
)