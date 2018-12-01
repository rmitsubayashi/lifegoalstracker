package com.lifegoaltracker.repository.goal

import android.arch.lifecycle.LiveData
import com.lifegoaltracker.model.goal.Goal
import com.lifegoaltracker.repository.ID
import org.jetbrains.anko.doAsync
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GoalRepository @Inject constructor(private val goalDao: GoalDao) {
    fun getGoals(queryParameters: GoalQueryParameters): LiveData<List<Goal>>{
        return goalDao.queryGoals(queryParameters.startDate,
                queryParameters.endDate, queryParameters.span)
    }

    fun getVisionGoals(queryParameters: GoalQueryParameters, visionID: ID): LiveData<List<Goal>>{
        return goalDao.queryVisionGoals(queryParameters.startDate,
                queryParameters.endDate, queryParameters.span, visionID)
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