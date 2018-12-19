package com.lifegoaltracker.repository.goal

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
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
import org.jetbrains.anko.doAsync
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GoalRepository @Inject constructor(private val goalDao: GoalDao) {
    fun getGoals(queryParameters: GoalQueryParameters): LiveData<List<Goal>>{
        val goal1 = Goal(ID(0),
                GoalUserFields("go for a run three times this week",
                        "I want to be fit enough to REALLY enjoy playing soccer",
                        DueDate(Date(Year(2018), Month.DECEMBER, WeekOfMonth.WEEK_FOUR), GoalSpan.ONE_MONTH)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal2 = Goal(ID(1),
                GoalUserFields("Big 6 workout six times a week",
                        "I DON'T want to be a scrawny engineer,",
                        DueDate(Date(Year(2018), Month.DECEMBER, WeekOfMonth.WEEK_FOUR), GoalSpan.THREE_MONTHS)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal3 = Goal(ID(1),
                GoalUserFields("Big 6 workout six times a week",
                        "I DON'T want to be a scrawny engineer,",
                        DueDate(Date(Year(2018), Month.DECEMBER, WeekOfMonth.WEEK_FOUR), GoalSpan.THREE_MONTHS)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal4 = Goal(ID(1),
                GoalUserFields("Big 6 workout six times a week",
                        "I DON'T want to be a scrawny engineer,",
                        DueDate(Date(Year(2018), Month.DECEMBER, WeekOfMonth.WEEK_FOUR), GoalSpan.THREE_MONTHS)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal5 = Goal(ID(1),
                GoalUserFields("Big 6 workout six times a week",
                        "I DON'T want to be a scrawny engineer,",
                        DueDate(Date(Year(2018), Month.DECEMBER, WeekOfMonth.WEEK_FOUR), GoalSpan.THREE_MONTHS)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal6 = Goal(ID(1),
                GoalUserFields("Big 6 workout six times a week",
                        "I DON'T want to be a scrawny engineer,",
                        DueDate(Date(Year(2018), Month.DECEMBER, WeekOfMonth.WEEK_FOUR), GoalSpan.THREE_MONTHS)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal7 = Goal(ID(1),
                GoalUserFields("Big 6 workout six times a week",
                        "I DON'T want to be a scrawny engineer,",
                        DueDate(Date(Year(2018), Month.DECEMBER, WeekOfMonth.WEEK_FOUR), GoalSpan.THREE_MONTHS)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal8 = Goal(ID(1),
                GoalUserFields("Big 6 workout six times a week",
                        "I DON'T want to be a scrawny engineer,",
                        DueDate(Date(Year(2018), Month.DECEMBER, WeekOfMonth.WEEK_FOUR), GoalSpan.THREE_MONTHS)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal9 = Goal(ID(1),
                GoalUserFields("Big 6 workout six times a week",
                        "I DON'T want to be a scrawny engineer,",
                        DueDate(Date(Year(2018), Month.DECEMBER, WeekOfMonth.WEEK_FOUR), GoalSpan.THREE_MONTHS)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal10 = Goal(ID(1),
                GoalUserFields("Big 6 workout six times a week",
                        "I DON'T want to be a scrawny engineer,",
                        DueDate(Date(Year(2018), Month.DECEMBER, WeekOfMonth.WEEK_FOUR), GoalSpan.THREE_MONTHS)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goalList = listOf(goal1, goal2, goal3, goal4, goal5, goal6, goal7, goal8, goal9, goal10)
        return MutableLiveData<List<Goal>>().apply{value=goalList}

        /* return goalDao.queryGoals(queryParameters.startDate,
                queryParameters.endDate, queryParameters.span) */
    }

    fun getVisionGoals(queryParameters: GoalQueryParameters, visionID: ID): LiveData<List<Goal>>{
        val goal1 = Goal(ID(0),
                GoalUserFields("go for a run three times this week",
                        "I want to be fit enough to REALLY enjoy playing soccer",
                        DueDate(Date(Year(2018), Month.DECEMBER, WeekOfMonth.WEEK_FOUR), GoalSpan.ONE_MONTH)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal2 = Goal(ID(1),
                GoalUserFields("Big 6 workout six times a week",
                        "I DON'T want to be a scrawny engineer,",
                        DueDate(Date(Year(2018), Month.DECEMBER, WeekOfMonth.WEEK_FOUR), GoalSpan.THREE_MONTHS)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal3 = Goal(ID(1),
                GoalUserFields("Big 6 workout six times a week",
                        "I DON'T want to be a scrawny engineer,",
                        DueDate(Date(Year(2018), Month.DECEMBER, WeekOfMonth.WEEK_FOUR), GoalSpan.THREE_MONTHS)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal4 = Goal(ID(1),
                GoalUserFields("Big 6 workout six times a week",
                        "I DON'T want to be a scrawny engineer,",
                        DueDate(Date(Year(2018), Month.DECEMBER, WeekOfMonth.WEEK_FOUR), GoalSpan.THREE_MONTHS)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal5 = Goal(ID(1),
                GoalUserFields("Big 6 workout six times a week",
                        "I DON'T want to be a scrawny engineer,",
                        DueDate(Date(Year(2018), Month.DECEMBER, WeekOfMonth.WEEK_FOUR), GoalSpan.THREE_MONTHS)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal6 = Goal(ID(1),
                GoalUserFields("Big 6 workout six times a week",
                        "I DON'T want to be a scrawny engineer,",
                        DueDate(Date(Year(2018), Month.DECEMBER, WeekOfMonth.WEEK_FOUR), GoalSpan.THREE_MONTHS)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal7 = Goal(ID(1),
                GoalUserFields("Big 6 workout six times a week",
                        "I DON'T want to be a scrawny engineer,",
                        DueDate(Date(Year(2018), Month.DECEMBER, WeekOfMonth.WEEK_FOUR), GoalSpan.THREE_MONTHS)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal8 = Goal(ID(1),
                GoalUserFields("Big 6 workout six times a week",
                        "I DON'T want to be a scrawny engineer,",
                        DueDate(Date(Year(2018), Month.DECEMBER, WeekOfMonth.WEEK_FOUR), GoalSpan.THREE_MONTHS)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal9 = Goal(ID(1),
                GoalUserFields("Big 6 workout six times a week",
                        "I DON'T want to be a scrawny engineer,",
                        DueDate(Date(Year(2018), Month.DECEMBER, WeekOfMonth.WEEK_FOUR), GoalSpan.THREE_MONTHS)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal10 = Goal(ID(1),
                GoalUserFields("Big 6 workout six times a week",
                        "I DON'T want to be a scrawny engineer,",
                        DueDate(Date(Year(2018), Month.DECEMBER, WeekOfMonth.WEEK_FOUR), GoalSpan.THREE_MONTHS)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal11 = Goal(ID(1),
                GoalUserFields("Big 6 workout six times a week",
                        "I DON'T want to be a scrawny engineer,",
                        DueDate(Date(Year(2018), Month.DECEMBER, WeekOfMonth.WEEK_FOUR), GoalSpan.THREE_MONTHS)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal12 = Goal(ID(1),
                GoalUserFields("Big 6 workout six times a week",
                        "I DON'T want to be a scrawny engineer,",
                        DueDate(Date(Year(2018), Month.DECEMBER, WeekOfMonth.WEEK_FOUR), GoalSpan.THREE_MONTHS)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goal13 = Goal(ID(1),
                GoalUserFields("Big 6 workout six times a week",
                        "I DON'T want to be a scrawny engineer,",
                        DueDate(Date(Year(2018), Month.DECEMBER, WeekOfMonth.WEEK_FOUR), GoalSpan.THREE_MONTHS)
                ),
                GoalProperties(0, ID(0)),
                GoalStatus()
        )
        val goalList = listOf(goal1, goal2, goal3, goal4, goal5, goal6, goal7, goal8, goal9, goal10, goal11, goal12, goal13)
        return MutableLiveData<List<Goal>>().apply{value=goalList}

        /* return goalDao.queryVisionGoals(queryParameters.startDate,
                queryParameters.endDate, queryParameters.span, visionID) */
    }

    fun getGoalHistory() : LiveData<List<Goal>> {
        return goalDao.queryCompletedGoals()
    }

    fun addGoal(goal: Goal) {
        doAsync {
            goalDao.postGoal(goal)
        }
    }

    fun updateGoal(goal: Goal){
        doAsync {
            goalDao.updateGoal(goal)
        }
    }
}