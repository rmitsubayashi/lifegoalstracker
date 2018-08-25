package com.lifegoaltracker.model.goal.dueDate.dateObjects

enum class Month(val monthValue: Int) {
    JANUARY(1),
    FEBRUARY(2),
    MARCH(3),
    APRIL(4),
    MAY(5),
    JUNE(6),
    JULY(7),
    AUGUST(8),
    SEPTEMBER(9),
    OCTOBER(10),
    NOVEMBER(11),
    DECEMBER(12);

    //so we can store the enum as Ints
    // and grab them back as Ints
    companion object {
        private val map = Month.values().associateBy(Month::monthValue)
        fun valueOf(type: Int) = map[type] ?: throw IllegalArgumentException()
    }
}