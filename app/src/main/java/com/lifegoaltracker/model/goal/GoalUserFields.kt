package com.lifegoaltracker.model.goal

import android.arch.persistence.room.Embedded
import com.lifegoaltracker.model.goal.dueDate.DueDate

data class GoalUserFields (
        var description: String,
        //optional
        var reason: String?,
        @Embedded
        var dueDate: DueDate
)