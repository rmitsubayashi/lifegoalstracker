package com.lifegoaltracker.model.vision

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.lifegoaltracker.repository.ID

@Entity(tableName = "vision")
data class Vision (
        @PrimaryKey(autoGenerate = true)
        val id: ID,
        @Embedded
        val userFields: VisionUserFields,
        @Embedded
        val properties: VisionProperties,
        @Embedded
        val status: VisionStatus
)