package com.lifegoaltracker.model.goal.dueDate.dateObjects

enum class WeekOfMonth(val weekValue: Int) {
    WEEK_ONE(1),
    WEEK_TWO(2),
    WEEK_THREE(3),
    WEEK_FOUR(4),
    //cheat days are any days past Week 4
    CHEAT_DAY(5),
    //when sorting this should be last
    ANY(6);

    //so we can store the enum as Ints
    // and grab them back as Ints
    companion object {
        private val map = WeekOfMonth.values().associateBy(WeekOfMonth::weekValue)
        fun valueOf(type: Int) = map[type] ?: throw IllegalArgumentException()
    }
}