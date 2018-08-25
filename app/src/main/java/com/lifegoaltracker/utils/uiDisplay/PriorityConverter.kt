package com.lifegoaltracker.utils.uiDisplay

import com.lifegoaltracker.model.vision.Priority

class PriorityConverter {
    private val priorities = Mapper(listOf(
            Pair(Priority.HIGH, true),
            Pair(Priority.LOW, false)
    ))

    fun getBoolean(priority: Priority?): Boolean? {
        return priorities.getDisplay(priority)
    }

    fun getPriority(boolean: Boolean?): Priority? {
        return priorities.getModel(boolean)
    }
}