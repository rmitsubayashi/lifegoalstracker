package com.lifegoaltracker.views.goalList.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.lifegoaltracker.model.goal.Goal
import com.lifegoaltracker.utils.filter.goal.GoalFilterByVision
import com.lifegoaltracker.model.vision.VisionName
import com.lifegoaltracker.repository.ID
import com.lifegoaltracker.repository.goal.GoalRepository
import com.lifegoaltracker.repository.vision.VisionRepository
import com.lifegoaltracker.utils.filter.IFilter
import com.lifegoaltracker.views.goalList.GoalListLiveDataHelper
import javax.inject.Inject

class GoalListViewModel
    @Inject constructor(
                        private val visionRepository: VisionRepository,
                        private val goalRepository: GoalRepository,
                        private val liveDataHelper: GoalListLiveDataHelper,
                        private val goalListRecyclerViewList: GoalListRecyclerViewList
                        )
    : ViewModel() {
    private val goalList: MutableLiveData<List<Goal>> = MutableLiveData()
    private val visionNames: MutableLiveData<List<VisionName>> = MutableLiveData()
    private val currentFilter: MutableLiveData<IFilter<Goal>> = MutableLiveData()
    private val recyclerViewItems: MediatorLiveData<List<GoalListRecyclerViewItem>> = MediatorLiveData()

    fun fetchGoalList() : LiveData<List<GoalListRecyclerViewItem>> {
        goalList.postValue(
                liveDataHelper.getAllGoalsLiveData(goalRepository).value
        )
        recyclerViewItems.addSource(goalList) {
            val filteredList = applyFilter()
            goalListRecyclerViewList.set(filteredList)
            recyclerViewItems.postValue(goalListRecyclerViewList.items)
        }
        recyclerViewItems.addSource(currentFilter) {
            val filteredList = applyFilter()
            goalListRecyclerViewList.set(filteredList)
            recyclerViewItems.postValue(goalListRecyclerViewList.items)
        }
        return recyclerViewItems
    }

    fun fetchVisionNames() : LiveData<List<VisionName>> {
        visionNames.postValue(
            visionRepository.getVisionNames().value
        )
        return visionNames
    }

    fun selectByVisionID(id: ID){
        currentFilter.value = GoalFilterByVision(id)
    }

    private fun applyFilter() : List<Goal>?{
        val toFilter : List<Goal>? = goalList.value
        val filter : IFilter<Goal>? = currentFilter.value

        if (toFilter == null){
            return null
        }

        if (filter == null){
            return toFilter
        }

        return filter.selectFrom(toFilter)
    }

    fun completeGoal(goal: Goal) {
        goal.status.isCompleted = true
        goalRepository.updateGoal(goal)
    }
}