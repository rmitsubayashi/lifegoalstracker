package com.lifegoaltracker.utils.filter.goal

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
import com.lifegoaltracker.utils.filter.IFilter
import com.lifegoaltracker.utils.filter.goal.GoalFilterByVision
import org.junit.Assert.assertEquals
import org.junit.Test

class GoalFilterByVisionTest {
    private lateinit var filter: IFilter<Goal>

    @Test
    fun filter_filterable_shouldFilter(){
        val goal1 = createGoalWithVisionID(0)
        val goal2 = createGoalWithVisionID(1)
        val goalList = arrayListOf(goal1, goal2)
        filter = GoalFilterByVision(ID(1))
        val result = filter.selectFrom(goalList)
        assertEquals(1, result.size)
        assertEquals(ID(1), result[0].id)
    }

    @Test
    fun filter_allFilterable_shouldFilter(){
        val goal1 = createGoalWithVisionID(0)
        val goal2 = createGoalWithVisionID(1)
        val goalList = arrayListOf(goal1, goal2)
        filter = GoalFilterByVision(ID(2))
        val result = filter.selectFrom(goalList)
        assertEquals(0, result.size)
    }

    @Test
    fun filter_noFilterable_shouldNotFilter(){
        val goal1 = createGoalWithVisionID(0)
        val goal2 = createGoalWithVisionID(0)
        val goalList = arrayListOf(goal1, goal2)
        filter = GoalFilterByVision(ID(0))
        val result = filter.selectFrom(goalList)
        assertEquals(2, result.size)
    }

    @Test
    fun filter_emptyList_shouldNotCrash(){
        val goalList = ArrayList<Goal>()
        filter = GoalFilterByVision(ID(0))
        val result = filter.selectFrom(goalList)
        assertEquals(0, result.size)
    }

    private fun createGoalWithVisionID(id: Long): Goal{
        return Goal(ID(1),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2018), Month.AUGUST,WeekOfMonth.WEEK_ONE),GoalSpan.ONE_WEEK)
                ),
                GoalProperties(0, ID(id)),
                GoalStatus()
        )
    }
}