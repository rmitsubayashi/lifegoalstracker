package com.lifegoaltracker.views.goalList

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import com.lifegoaltracker.model.goal.Goal
import com.lifegoaltracker.repository.ID
import com.lifegoaltracker.repository.goal.GoalRepository

class GoalListLiveDataHelper(private val queryHelper: GoalListQueryHelper) {
    fun getAllGoalsLiveData(repository: GoalRepository): MediatorLiveData<List<Goal>> {
        val mediatorLiveData = MediatorLiveData<List<Goal>>()
        val weekQuery = queryHelper.getWeekGoalsQuery()
        val weekLiveData = repository.getGoals(weekQuery)
        val monthQuery = queryHelper.getMonthGoalsQuery()
        val monthLiveData = repository.getGoals(monthQuery)
        val threeMonthQuery = queryHelper.getThreeMonthGoalsQuery()
        val threeMonthLiveData = repository.getGoals(threeMonthQuery)
        val yearQuery = queryHelper.getYearGoalsQuery()
        val yearLiveData = repository.getGoals(yearQuery)
        mediatorLiveData.addSource(weekLiveData){
            _ -> mediatorLiveData.value=combineLiveData(
                weekLiveData, monthLiveData, threeMonthLiveData, yearLiveData)
        }
        mediatorLiveData.addSource(monthLiveData){
            _ -> mediatorLiveData.value=combineLiveData(
                weekLiveData, monthLiveData, threeMonthLiveData, yearLiveData)
        }
        mediatorLiveData.addSource(threeMonthLiveData){
            _ -> mediatorLiveData.value=combineLiveData(
                weekLiveData, monthLiveData, threeMonthLiveData, yearLiveData)
        }

        mediatorLiveData.addSource(yearLiveData){
            _ -> mediatorLiveData.value=combineLiveData(
                weekLiveData, monthLiveData, threeMonthLiveData, yearLiveData)
        }

        return mediatorLiveData
    }

    fun getAllVisionGoalsLiveData(repository: GoalRepository, visionID: ID): MediatorLiveData<List<Goal>> {
        val mediatorLiveData = MediatorLiveData<List<Goal>>()
        val weekQuery = queryHelper.getWeekGoalsQuery()
        val weekLiveData = repository.getVisionGoals(weekQuery, visionID)
        mediatorLiveData.addSource(weekLiveData){mediatorLiveData.value=it}
        val monthQuery = queryHelper.getMonthGoalsQuery()
        val monthLiveData = repository.getVisionGoals(monthQuery, visionID)
        mediatorLiveData.addSource(monthLiveData){mediatorLiveData.value=it}
        val threeMonthQuery = queryHelper.getThreeMonthGoalsQuery()
        val threeMonthLiveData = repository.getVisionGoals(threeMonthQuery, visionID)
        mediatorLiveData.addSource(threeMonthLiveData){mediatorLiveData.value=it}
        val yearQuery = queryHelper.getYearGoalsQuery()
        val yearLiveData = repository.getVisionGoals(yearQuery, visionID)
        mediatorLiveData.addSource(yearLiveData){mediatorLiveData.value=it}

        return mediatorLiveData
    }

    private fun combineLiveData(week: LiveData<List<Goal>>, month: LiveData<List<Goal>>,
                                threeMonth: LiveData<List<Goal>>, year: LiveData<List<Goal>>): List<Goal>{
        val result = mutableListOf<Goal>()
        result.addAll(week.value ?: emptyList())
        result.addAll(month.value ?: emptyList())
        result.addAll(threeMonth.value ?: emptyList())
        result.addAll(year.value ?: emptyList())
        return result
    }

}