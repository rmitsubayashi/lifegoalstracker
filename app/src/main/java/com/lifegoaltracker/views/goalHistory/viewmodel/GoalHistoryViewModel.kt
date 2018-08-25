package com.lifegoaltracker.views.goalHistory.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.lifegoaltracker.model.goal.Goal
import com.lifegoaltracker.model.goal.filter.GoalFilter
import com.lifegoaltracker.model.goal.filter.GoalFilterByVision
import com.lifegoaltracker.model.vision.VisionName
import com.lifegoaltracker.repository.ID
import com.lifegoaltracker.repository.goal.GoalRepository
import com.lifegoaltracker.repository.vision.VisionRepository
import javax.inject.Inject

class GoalHistoryViewModel
    @Inject constructor(private val goalRepository: GoalRepository,
                        private val visionRepository: VisionRepository)
    : ViewModel(){
    private val goalList : MutableLiveData<List<Goal>> = MutableLiveData()
    private val visionNameList : MutableLiveData<List<VisionName>> = MutableLiveData()
    private val currentFilter : MutableLiveData<GoalFilter> = MutableLiveData()
    private val goalFilteredList : MediatorLiveData<List<Goal>> = MediatorLiveData()

    fun fetchGoalList() : LiveData<List<Goal>>{
        goalList.postValue(goalRepository.getGoalHistory().value)
        goalFilteredList.addSource(goalList) {goalFilteredList.value = applyFilter()}
        goalFilteredList.addSource(currentFilter) {goalFilteredList.value = applyFilter()}
        return goalFilteredList
    }

    fun fetchVisionNames() : LiveData<List<VisionName>>{
        visionNameList.postValue(visionRepository.getVisionNames().value)
        return visionNameList
    }

    fun selectByVisionID(id: ID){
        currentFilter.value = GoalFilterByVision(id)
    }

    private fun applyFilter() : List<Goal>{
        val toFilter : List<Goal>? = goalList.value
        val filter : GoalFilter? = currentFilter.value

        if (toFilter == null){
            return ArrayList()
        }

        if (filter == null){
            return toFilter
        }

        return filter.selectFrom(toFilter)
    }
}