package com.lifegoaltracker.repository.goal.typeConverters

import android.arch.persistence.room.TypeConverter
import com.lifegoaltracker.model.goal.dueDate.span.GoalSpan

class GoalSpanTypeConverter {
    @TypeConverter
    fun toString(span: GoalSpan): String{
        return span.name
    }

    @TypeConverter
    fun toGoalSpan(span: String): GoalSpan{
        return GoalSpan.valueOf(span)
    }
}