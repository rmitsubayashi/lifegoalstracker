package com.lifegoaltracker.views.visionGoals.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.lifegoaltracker.model.goal.Goal
import com.lifegoaltracker.model.goal.dueDate.span.GoalSpan
import com.lifegoaltracker.repository.ID
import com.lifegoaltracker.repository.goal.GoalRepository
import com.lifegoaltracker.utils.date.DateGenerator
import com.lifegoaltracker.views.goalList.GoalListLiveDataHelper
import javax.inject.Inject

class VisionGoalsViewModel
    @Inject constructor(private val goalRepository: GoalRepository,
                        private val liveDataHelper: GoalListLiveDataHelper,
                        private val dateGenerator: DateGenerator)
    : ViewModel() {
    private var goalList: MutableLiveData<List<Goal>>? = null

    fun fetchGoals(visionID: ID): LiveData<List<Goal>> {
        //TODO check for different ID
        goalList?.let { return it }

        return liveDataHelper.getVisionGoalsLiveData(
                goalRepository, visionID, dateGenerator.getCurrentDate(),
                listOf(GoalSpan.ONE_MONTH, GoalSpan.THREE_MONTHS, GoalSpan.ONE_YEAR)
        )
    }
}