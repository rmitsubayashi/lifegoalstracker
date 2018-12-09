package com.lifegoaltracker.views.goalList

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import com.lifegoaltracker.model.goal.Goal
import com.lifegoaltracker.model.goal.dueDate.dateObjects.Date
import com.lifegoaltracker.model.goal.dueDate.span.GoalSpan
import com.lifegoaltracker.repository.ID
import com.lifegoaltracker.repository.goal.GoalRepository
import javax.inject.Inject

class GoalListLiveDataHelper @Inject constructor(private val queryHelper: GoalListQueryHelper) {
    fun getGoalsLiveData(repository: GoalRepository, date: Date,
                         spans: List<GoalSpan>): MediatorLiveData<List<Goal>> {
        val weekQuery = queryHelper.getWeekGoalsQuery(date)
        val weekLiveData = if (spans.contains(GoalSpan.ONE_WEEK))
            repository.getGoals(weekQuery) else null
        val monthQuery = queryHelper.getMonthGoalsQuery(date)
        val monthLiveData = if (spans.contains(GoalSpan.ONE_MONTH))
                repository.getGoals(monthQuery) else null
        val threeMonthQuery = queryHelper.getThreeMonthGoalsQuery(date)
        val threeMonthLiveData = if (spans.contains(GoalSpan.THREE_MONTHS))
            repository.getGoals(threeMonthQuery) else null
        val yearQuery = queryHelper.getYearGoalsQuery(date)
        val yearLiveData = if (spans.contains(GoalSpan.ONE_YEAR))
            repository.getGoals(yearQuery) else null

        val mediatorLiveData = MediatorLiveData<List<Goal>>()
        weekLiveData?.let {
            mediatorLiveData.addSource(weekLiveData) { _ ->
                mediatorLiveData.value = combineLiveData(
                        weekLiveData, monthLiveData, threeMonthLiveData, yearLiveData)
            }
        }
        monthLiveData?.let {
            mediatorLiveData.addSource(monthLiveData) { _ ->
                mediatorLiveData.value = combineLiveData(
                        weekLiveData, monthLiveData, threeMonthLiveData, yearLiveData)
            }
        }
        threeMonthLiveData?.let {
            mediatorLiveData.addSource(threeMonthLiveData) { _ ->
                mediatorLiveData.value = combineLiveData(
                        weekLiveData, monthLiveData, threeMonthLiveData, yearLiveData)
            }
        }

        yearLiveData?.let {
            mediatorLiveData.addSource(yearLiveData) { _ ->
                mediatorLiveData.value = combineLiveData(
                        weekLiveData, monthLiveData, threeMonthLiveData, yearLiveData)
            }
        }

        return mediatorLiveData
    }

    fun getVisionGoalsLiveData(repository: GoalRepository, visionID: ID, date: Date,
                               spans: List<GoalSpan>): MediatorLiveData<List<Goal>> {
        val mediatorLiveData = MediatorLiveData<List<Goal>>()
        if (spans.contains(GoalSpan.ONE_WEEK)) {
            val weekQuery = queryHelper.getWeekGoalsQuery(date)
            val weekLiveData = repository.getVisionGoals(weekQuery, visionID)
            mediatorLiveData.addSource(weekLiveData) { mediatorLiveData.value = it }
        }
        if (spans.contains(GoalSpan.ONE_MONTH)) {
            val monthQuery = queryHelper.getMonthGoalsQuery(date)
            val monthLiveData = repository.getVisionGoals(monthQuery, visionID)
            mediatorLiveData.addSource(monthLiveData) { mediatorLiveData.value = it }
        }
        if (spans.contains(GoalSpan.THREE_MONTHS)) {
            val threeMonthQuery = queryHelper.getThreeMonthGoalsQuery(date)
            val threeMonthLiveData = repository.getVisionGoals(threeMonthQuery, visionID)
            mediatorLiveData.addSource(threeMonthLiveData) { mediatorLiveData.value = it }
        }
        if (spans.contains(GoalSpan.ONE_YEAR)) {
            val yearQuery = queryHelper.getYearGoalsQuery(date)
            val yearLiveData = repository.getVisionGoals(yearQuery, visionID)
            mediatorLiveData.addSource(yearLiveData) { mediatorLiveData.value = it }
        }

        return mediatorLiveData
    }

    private fun combineLiveData(week: LiveData<List<Goal>>?, month: LiveData<List<Goal>>?,
                                threeMonth: LiveData<List<Goal>>?, year: LiveData<List<Goal>>?): List<Goal>{
        val result = mutableListOf<Goal>()
        result.addAll(week?.value ?: emptyList())
        result.addAll(month?.value ?: emptyList())
        result.addAll(threeMonth?.value ?: emptyList())
        result.addAll(year?.value ?: emptyList())
        return result
    }

}