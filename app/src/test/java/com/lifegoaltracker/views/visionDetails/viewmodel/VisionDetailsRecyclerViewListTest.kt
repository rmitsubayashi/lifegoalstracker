package com.lifegoaltracker.views.visionDetails.viewmodel

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
import com.lifegoaltracker.model.vision.*
import com.lifegoaltracker.repository.ID
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class VisionDetailsRecyclerViewListTest {
    private lateinit var visionDetailsRecyclerViewList: VisionDetailsRecyclerViewList

    @Before
    fun setup(){
        visionDetailsRecyclerViewList = VisionDetailsRecyclerViewList()
    }

    @Test
    fun empty_showsEmptyState(){
        val emptyGoals = emptyList<Goal>()
        val emptyVision = Vision(ID(1), VisionUserFields("title",null,null, Priority.HIGH),
                VisionProperties(0), VisionStatus())
        visionDetailsRecyclerViewList.set(emptyVision, emptyGoals)
        val correctList = listOf(
                VisionDetailsRecyclerViewItem(
                        VisionDetailsRecyclerViewItemType.VISION_DESCRIPTION_EMPTY, null, null
                ),
                VisionDetailsRecyclerViewItem(
                        VisionDetailsRecyclerViewItemType.VISION_REASON_EMPTY, null, null
                ),
                VisionDetailsRecyclerViewItem(
                        VisionDetailsRecyclerViewItemType.GOALS_EMPTY, null, null
                )
        )
        assertEquals(correctList, visionDetailsRecyclerViewList.items)
    }

    @Test
    fun loading_showsLoadingState(){
        visionDetailsRecyclerViewList.set(null, null)
        val correctList = listOf(
                VisionDetailsRecyclerViewItem(
                        VisionDetailsRecyclerViewItemType.VISION_LOADING, null, null
                ),
                VisionDetailsRecyclerViewItem(
                        VisionDetailsRecyclerViewItemType.GOALS_LOADING, null, null
                )
        )
        assertEquals(correctList, visionDetailsRecyclerViewList.items)
    }

    @Test
    fun visions_showsCorrectList(){
        val vision1 = Vision(ID(1), VisionUserFields("title","desc","reason", Priority.HIGH),
                VisionProperties(0), VisionStatus())
        val emptyGoals = emptyList<Goal>()
        visionDetailsRecyclerViewList.set(vision1, emptyGoals)
        val correctList = listOf(
                VisionDetailsRecyclerViewItem(
                        VisionDetailsRecyclerViewItemType.VISION_DESCRIPTION, null, "desc"
                ),
                VisionDetailsRecyclerViewItem(
                        VisionDetailsRecyclerViewItemType.VISION_REASON, null, "reason"
                ),
                VisionDetailsRecyclerViewItem(
                        VisionDetailsRecyclerViewItemType.GOALS_EMPTY, null, null
                )
        )
        assertEquals(correctList, visionDetailsRecyclerViewList.items)
    }

    @Test
    fun goals_showsCorrectList(){
        val goal1 = createGoalWithDueDate(1, 2019, Month.AUGUST,WeekOfMonth.WEEK_ONE, GoalSpan.ONE_WEEK)
        val goal2 = createGoalWithDueDate(2, 2019, Month.AUGUST, WeekOfMonth.WEEK_TWO, GoalSpan.ONE_WEEK)
        val goal3 = createGoalWithDueDate(3, 2019, Month.AUGUST, null, GoalSpan.ONE_MONTH)
        val goal4 = createGoalWithDueDate(4, 2019, Month.JULY, null, GoalSpan.THREE_MONTHS)
        val goal5 = createGoalWithDueDate(5, 2019, null, null, GoalSpan.ONE_YEAR)
        val randomGoals = listOf(goal4, goal1, goal3, goal5, goal2)
        val emptyVision = Vision(ID(1), VisionUserFields("title",null,null, Priority.HIGH),
                VisionProperties(0), VisionStatus())
        visionDetailsRecyclerViewList.set(emptyVision, randomGoals)
        val correctList = listOf(
                VisionDetailsRecyclerViewItem(
                        VisionDetailsRecyclerViewItemType.VISION_DESCRIPTION_EMPTY, null, null
                ),
                VisionDetailsRecyclerViewItem(
                        VisionDetailsRecyclerViewItemType.VISION_REASON_EMPTY, null, null
                ),
                VisionDetailsRecyclerViewItem(
                        VisionDetailsRecyclerViewItemType.HEADER, null, "週"
                ),
                VisionDetailsRecyclerViewItem(
                        VisionDetailsRecyclerViewItemType.GOAL, goal1, null
                ),
                VisionDetailsRecyclerViewItem(
                        VisionDetailsRecyclerViewItemType.GOAL, goal2, null
                ),
                VisionDetailsRecyclerViewItem(
                        VisionDetailsRecyclerViewItemType.HEADER, null, "月"
                ),
                VisionDetailsRecyclerViewItem(
                        VisionDetailsRecyclerViewItemType.GOAL, goal3, null
                ),
                VisionDetailsRecyclerViewItem(
                        VisionDetailsRecyclerViewItemType.HEADER, null, "3か月"
                ),
                VisionDetailsRecyclerViewItem(
                        VisionDetailsRecyclerViewItemType.GOAL, goal4, null
                ),
                VisionDetailsRecyclerViewItem(
                        VisionDetailsRecyclerViewItemType.HEADER, null, "年"
                ),
                VisionDetailsRecyclerViewItem(
                        VisionDetailsRecyclerViewItemType.GOAL, goal5, null
                )

                )
        assertEquals(correctList, visionDetailsRecyclerViewList.items)
    }

    @Test
    fun missingSpan_hidesHeader(){
        val goalWeek = createGoalWithDueDate(1, 2019, Month.AUGUST, WeekOfMonth.WEEK_ONE, GoalSpan.ONE_WEEK)
        val goalMonth = createGoalWithDueDate(4, 2019, Month.JULY, null, GoalSpan.THREE_MONTHS)
        val goalYear = createGoalWithDueDate(5, 2019, null, null, GoalSpan.ONE_YEAR)
        val goals = listOf(goalMonth, goalWeek, goalYear)
        val emptyVision = Vision(ID(1), VisionUserFields("title",null,null, Priority.HIGH),
                VisionProperties(0), VisionStatus())
        visionDetailsRecyclerViewList.set(emptyVision, goals)
        val shouldNotHave = VisionDetailsRecyclerViewItem(VisionDetailsRecyclerViewItemType.HEADER, null, "月")
        assertFalse(visionDetailsRecyclerViewList.items.contains(shouldNotHave))
    }
}

private fun createGoalWithDueDate(id: Long, year: Int, month: Month?, week: WeekOfMonth?, span: GoalSpan): Goal{
    return Goal(ID(id),
            GoalUserFields("desc",
                    "reason",
                    DueDate(Date(Year(year), month, week), span)
            ),
            GoalProperties(0, ID(0)),
            GoalStatus()
    )
}