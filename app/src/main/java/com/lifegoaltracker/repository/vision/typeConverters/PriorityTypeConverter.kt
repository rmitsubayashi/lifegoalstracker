package com.lifegoaltracker.repository.vision.typeConverters

import android.arch.persistence.room.TypeConverter
import com.lifegoaltracker.model.vision.Priority

class PriorityTypeConverter {
    @TypeConverter
    fun toString(priority: Priority): String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(priorityString: String): Priority {
        return Priority.valueOf(priorityString)
    }
}