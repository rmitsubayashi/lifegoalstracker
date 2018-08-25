package com.lifegoaltracker.model.goalComment

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.lifegoaltracker.model.goal.Goal
import com.lifegoaltracker.repository.ID

@Entity (tableName = "goalComment",
        foreignKeys = [(ForeignKey( entity = Goal::class,
                                    parentColumns = arrayOf("id"),
                                    childColumns = arrayOf("goalID"),
                                    //we never delete, but just in case
                                    onDelete = ForeignKey.CASCADE
                ))]
)
data class GoalComment (
        @PrimaryKey
        val id: ID,
        val goalID: Long,
        val comment: String,
        val timeStamp: Long
)