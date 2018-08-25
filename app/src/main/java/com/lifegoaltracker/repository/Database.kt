package com.lifegoaltracker.repository

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.lifegoaltracker.model.goal.Goal
import com.lifegoaltracker.model.vision.Vision
import com.lifegoaltracker.repository.goal.GoalDao
import com.lifegoaltracker.repository.goal.typeConverters.DateTypeConverter
import com.lifegoaltracker.repository.goal.typeConverters.GoalSpanTypeConverter
import com.lifegoaltracker.repository.vision.VisionDao
import com.lifegoaltracker.repository.vision.typeConverters.PriorityTypeConverter

@Database(entities = [Goal::class, Vision::class], version=1)
@TypeConverters(    DateTypeConverter::class,
                    GoalSpanTypeConverter::class,
                    PriorityTypeConverter::class,
                    IDTypeConverter::class
                )
abstract class Database : RoomDatabase() {
    abstract fun goalDao() : GoalDao
    abstract fun visionDao() : VisionDao
}