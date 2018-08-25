package com.lifegoaltracker.views.goalList.viewmodel

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
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

class GoalListRecyclerViewListTest {
    private lateinit var goalListRecyclerViewList: GoalListRecyclerViewList
    
    @Before
    fun setup(){
        goalListRecyclerViewList = GoalListRecyclerViewList()
    }
    
    @Test
    fun empty_showsEmptyState(){
        val emptyGoals = emptyList<Goal>()
        goalListRecyclerViewList.set(emptyGoals)
        val correctList = listOf(
                GoalListRecyclerViewItem(
                        GoalListRecyclerViewItemType.EMPTY, null, null
                )
        )
        assertEquals(correctList, goalListRecyclerViewList.items)
    }
    
    @Test
    fun loading_showsLoadingState(){
        goalListRecyclerViewList.set(null)
        val correctList = listOf(
                GoalListRecyclerViewItem(
                        GoalListRecyclerViewItemType.LOADING, null, null
                )
        )
        assertEquals(correctList, goalListRecyclerViewList.items)
    }
    
    @Test
    fun goals_showsCorrectList(){
        val goal1 = Goal(ID(1),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_ONE), GoalSpan.ONE_WEEK)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal2 = Goal(ID(2),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_TWO), GoalSpan.ONE_WEEK)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal3 = Goal(ID(3),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), Month.AUGUST, null), GoalSpan.ONE_MONTH)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal4 = Goal(ID(4),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), Month.JULY, null), GoalSpan.THREE_MONTHS)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal5 = Goal(ID(5),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), null, null), GoalSpan.ONE_YEAR)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val randomGoals = listOf(goal4, goal1, goal3, goal5, goal2)
        goalListRecyclerViewList.set(randomGoals)
        val correctList = listOf(
                GoalListRecyclerViewItem(
                        GoalListRecyclerViewItemType.HEADER, null, "週"
                ),
                GoalListRecyclerViewItem(
                        GoalListRecyclerViewItemType.GOAL, goal1, null
                ),
                GoalListRecyclerViewItem(
                        GoalListRecyclerViewItemType.GOAL, goal2, null
                ),
                GoalListRecyclerViewItem(
                        GoalListRecyclerViewItemType.HEADER, null, "月"
                ),
                GoalListRecyclerViewItem(
                        GoalListRecyclerViewItemType.GOAL, goal3, null
                ),
                GoalListRecyclerViewItem(
                        GoalListRecyclerViewItemType.HEADER, null, "3か月"
                ),
                GoalListRecyclerViewItem(
                        GoalListRecyclerViewItemType.GOAL, goal4, null
                ),
                GoalListRecyclerViewItem(
                        GoalListRecyclerViewItemType.HEADER, null, "年"
                ),
                GoalListRecyclerViewItem(
                        GoalListRecyclerViewItemType.GOAL, goal5, null
                )

        )
        assertEquals(correctList, goalListRecyclerViewList.items)
    }

    @Test
    fun missingSpan_hidesHeader(){
        val goalWeek = Goal(ID(1),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), Month.AUGUST, WeekOfMonth.WEEK_ONE), GoalSpan.ONE_WEEK)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )

        val goalMonth = Goal(ID(3),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), Month.AUGUST, null), GoalSpan.ONE_MONTH)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goalYear = Goal(ID(5),
                GoalUserFields("desc",
                        "reason",
                        DueDate(Date(Year(2019), null, null), GoalSpan.ONE_YEAR)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val randomGoals = listOf(goalMonth, goalWeek, goalYear)
        goalListRecyclerViewList.set(randomGoals)
        val shouldNotHave = GoalListRecyclerViewItem(
                GoalListRecyclerViewItemType.HEADER,
                null, "3か月"
        )
        assertFalse(goalListRecyclerViewList.items.contains(shouldNotHave))
    }
}