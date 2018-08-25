package com.lifegoaltracker.utils.uiDisplay

import com.lifegoaltracker.model.goal.dueDate.span.GoalSpan

class GoalSpanConverter {
    private val spans = Mapper(listOf(
            Pair(GoalSpan.ONE_WEEK, "一週間"),
            Pair(GoalSpan.ONE_MONTH, "一か月"),
            Pair(GoalSpan.THREE_MONTHS, "三か月"),
            Pair(GoalSpan.ONE_YEAR, "一年")
    ))

    fun getSpan(text: String): GoalSpan?{
        return spans.getModel(text)
    }

    fun getString(span: GoalSpan): String? {
        return spans.getDisplay(span)
    }
}