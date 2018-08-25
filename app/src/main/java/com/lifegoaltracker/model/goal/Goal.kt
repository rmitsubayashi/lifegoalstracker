package com.lifegoaltracker.model.goal

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey
import com.lifegoaltracker.model.vision.Vision
import com.lifegoaltracker.repository.ID

@Entity(tableName = "goal",
        foreignKeys = [ForeignKey(  entity = Vision::class,
                                    parentColumns = arrayOf("id"),
                                    childColumns = arrayOf("visionID"),
                                    //we never delete, but just in case
                                    onDelete = CASCADE
                )]
)
data class Goal (
        @PrimaryKey(autoGenerate = true)
        val id: ID,
        @Embedded
        val userFields: GoalUserFields,
        @Embedded
        val properties: GoalProperties,
        @Embedded
        val status: GoalStatus
)