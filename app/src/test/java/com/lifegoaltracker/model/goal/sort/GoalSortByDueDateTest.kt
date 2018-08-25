package com.lifegoaltracker.model.goal.sort

import com.lifegoaltracker.model.goal.Goal
import com.lifegoaltracker.model.goal.GoalProperties
import com.lifegoaltracker.model.goal.GoalStatus
import com.lifegoaltracker.model.goal.GoalUserFields
import com.lifegoaltracker.model.goal.dueDate.DueDate
import com.lifegoaltracker.model.goal.dueDate.dateObjects.Date
import com.lifegoaltracker.model.goal.dueDate.dateObjects.Month
import com.lifegoaltracker.model.goal.dueDate.dateObjects.WeekOfMonth
import com.lifegoaltracker.model.goal.dueDate.dateObjects.Year
import com.lifegoaltracker.model.goal.dueDate.span.GoalSpan
import com.lifegoaltracker.repository.ID
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GoalSortByDueDateTest {
    private lateinit var beforeList: List<Goal>
    private lateinit var afterList: List<Goal>
    private lateinit var correctList: List<Goal>

    private lateinit var goal1: Goal
    private lateinit var goal2: Goal

    @Test
    fun equals(){
        goal1 = createGoalWithDueDate(2019, Month.AUGUST,WeekOfMonth.WEEK_ONE)
        goal2 = createGoalWithDueDate(2019, Month.AUGUST,WeekOfMonth.WEEK_ONE)
        beforeList = listOf(goal1, goal2)
        afterList = beforeList.sortedWith(GoalSortByDueDate())
        correctList = listOf(goal1, goal2)
        assertEquals(correctList, afterList)
    }

    @Test
    fun differentYear(){
        goal1 = createGoalWithDueDate(2019, Month.AUGUST,WeekOfMonth.WEEK_ONE)
        goal2 = createGoalWithDueDate(2018, Month.AUGUST,WeekOfMonth.WEEK_ONE)
        beforeList = listOf(goal1, goal2)
        afterList = beforeList.sortedWith(GoalSortByDueDate())
        correctList = listOf(goal2, goal1)
        assertEquals(correctList, afterList)
    }

    @Test
    fun differentMonth(){
        goal1 = createGoalWithDueDate(2019, Month.AUGUST,WeekOfMonth.WEEK_ONE)
        goal2 = createGoalWithDueDate(2019, Month.JULY,WeekOfMonth.WEEK_ONE)
        beforeList = listOf(goal1, goal2)
        afterList = beforeList.sortedWith(GoalSortByDueDate())
        correctList = listOf(goal2, goal1)
        assertEquals(correctList, afterList)
    }

    @Test
    fun differentWeek(){
        goal1 = createGoalWithDueDate(2019, Month.AUGUST,WeekOfMonth.WEEK_TWO)
        goal2 = createGoalWithDueDate(2019, Month.AUGUST,WeekOfMonth.WEEK_ONE)
        beforeList = listOf(goal1, goal2)
        afterList = beforeList.sortedWith(GoalSortByDueDate())
        correctList = listOf(goal2, goal1)
        assertEquals(correctList, afterList)
    }
    
    private fun createGoalWithDueDate(year: Int, month: Month, week: WeekOfMonth): Goal{
        return Goal(ID(0),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(year), month, week), GoalSpan.ONE_WEEK)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
    }
}