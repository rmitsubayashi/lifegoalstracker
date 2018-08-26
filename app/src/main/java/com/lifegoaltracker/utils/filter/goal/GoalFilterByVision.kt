package com.lifegoaltracker.utils.filter.goal

import com.lifegoaltracker.model.goal.Goal
import com.lifegoaltracker.repository.ID
import com.lifegoaltracker.utils.filter.IFilter

class GoalFilterByVision constructor(private val selectVisionID: ID): IFilter<Goal> {
    override fun selectFrom(originalList: List<Goal>): List<Goal> {
        val filteredList = ArrayList<Goal>(originalList.size)
        for (goal in originalList){
            val goalVisionID = goal.properties.visionID
            if (goalVisionID == selectVisionID){
                filteredList.add(goal.copy())
            }
        }

        return filteredList
    }
}