package com.lifegoaltracker.model.goal.filter

import com.lifegoaltracker.model.goal.Goal
import com.lifegoaltracker.repository.ID

class GoalFilterByVision constructor(private val selectVisionID: ID): GoalFilter {
    override fun selectFrom(goalList: List<Goal>): List<Goal> {
        val filteredList = ArrayList<Goal>(goalList.size)
        for (goal in goalList){
            val goalVisionID = goal.properties.visionID
            if (goalVisionID == selectVisionID){
                filteredList.add(goal.copy())
            }
        }

        return filteredList
    }
}