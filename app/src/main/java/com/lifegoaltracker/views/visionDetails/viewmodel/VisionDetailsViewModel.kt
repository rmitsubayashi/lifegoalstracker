package com.lifegoaltracker.views.visionDetails.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.lifegoaltracker.model.goal.Goal
import com.lifegoaltracker.model.vision.Vision
import com.lifegoaltracker.repository.ID
import com.lifegoaltracker.repository.goal.GoalRepository
import com.lifegoaltracker.repository.vision.VisionRepository
import com.lifegoaltracker.views.goalList.GoalListLiveDataHelper
import javax.inject.Inject

class VisionDetailsViewModel
    @Inject constructor(
                        private val visionRepository: VisionRepository,
                        private val goalRepository: GoalRepository,
                        private val liveDataHelper: GoalListLiveDataHelper,
                        private val recyclerViewList: VisionDetailsRecyclerViewList
                        )
    : ViewModel(){
    private var visionID : ID = ID(-1)
    private val vision : MutableLiveData<Vision> = MutableLiveData()
    private val goalList : MutableLiveData<List<Goal>> = MutableLiveData()
    private val recyclerViewItems: MediatorLiveData<List<VisionDetailsRecyclerViewItem>>
            = MediatorLiveData()

    fun fetchRecyclerViewItems(): LiveData<List<VisionDetailsRecyclerViewItem>>{
        recyclerViewItems.addSource(fetchVision()){
            _ -> refreshItems()
        }
        recyclerViewItems.addSource(fetchGoalList()){
            _ -> refreshItems()
        }
        return recyclerViewItems
    }

    private fun fetchVision() : LiveData<Vision>{
        vision.postValue(visionRepository.getVision(visionID).value)
        return vision
    }

    private fun fetchGoalList() : LiveData<List<Goal>>{
        goalList.postValue(
                liveDataHelper.getAllVisionGoalsLiveData(
                        goalRepository, visionID
                ).value
        )
        return goalList
    }

    private fun refreshItems(){
        recyclerViewList.set(
                vision.value, goalList.value
        )
        recyclerViewItems.postValue(recyclerViewList.items)
    }

    fun setVisionID(id: ID){
        visionID = id
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