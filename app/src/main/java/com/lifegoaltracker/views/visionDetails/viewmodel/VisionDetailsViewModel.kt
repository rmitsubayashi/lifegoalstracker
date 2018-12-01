package com.lifegoaltracker.views.visionDetails.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.lifegoaltracker.model.goal.Goal
import com.lifegoaltracker.model.vision.Vision
import com.lifegoaltracker.repository.ID
import com.lifegoaltracker.repository.goal.GoalRepository
import com.lifegoaltracker.repository.vision.VisionRepository
import com.lifegoaltracker.utils.date.DateGenerator
import com.lifegoaltracker.views.goalList.GoalListLiveDataHelper
import javax.inject.Inject

class VisionDetailsViewModel
    @Inject constructor(
                        private val visionRepository: VisionRepository,
                        private val goalRepository: GoalRepository,
                        private val liveDataHelper: GoalListLiveDataHelper,
                        private val dateGenerator: DateGenerator,
                        private val recyclerViewList: VisionDetailsRecyclerViewList
                        )
    : ViewModel(){
    private lateinit var vision: LiveData<Vision>
    private lateinit var goalList: LiveData<List<Goal>>
    private val recyclerViewItems: MediatorLiveData<List<VisionDetailsRecyclerViewItem>>
            = MediatorLiveData()

    fun fetchRecyclerViewItems(visionID: ID): LiveData<List<VisionDetailsRecyclerViewItem>>{
        recyclerViewItems.addSource(fetchVision(visionID)){
            _ -> refreshItems()
        }
        recyclerViewItems.addSource(fetchGoalList(visionID)){
            _ -> refreshItems()
        }
        return recyclerViewItems
    }

    private fun fetchVision(visionID: ID) : LiveData<Vision>{
        vision = visionRepository.getVision(visionID)
        return vision
    }

    private fun fetchGoalList(visionID: ID) : LiveData<List<Goal>>{
        goalList = liveDataHelper.getAllVisionGoalsLiveData(
                        goalRepository, visionID, dateGenerator.getCurrentDate()
                )
        return goalList
    }

    private fun refreshItems(){
        recyclerViewList.set(
                vision.value, goalList.value
        )
        recyclerViewItems.postValue(recyclerViewList.items)
    }

    fun removeGoal(goal: Goal) {
        goal.status.isDeleted = true
        goalRepository.updateGoal(goal)
    }

    fun completeGoal(goal: Goal) {
        goal.status.isCompleted = true
        goalRepository.updateGoal(goal)
    }
}