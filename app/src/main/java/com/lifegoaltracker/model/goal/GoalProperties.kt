package com.lifegoaltracker.model.goal

import com.lifegoaltracker.repository.ID

data class GoalProperties (
        //required
        val createdTimestamp: Long,
        //required
        val visionID: ID
)