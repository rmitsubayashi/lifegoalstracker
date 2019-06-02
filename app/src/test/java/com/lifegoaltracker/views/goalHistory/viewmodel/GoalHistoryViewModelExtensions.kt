package com.lifegoaltracker.views.goalHistory.viewmodel

import android.arch.lifecycle.LiveData
import com.lifegoaltracker.model.vision.VisionName
import org.junit.Assert

fun GoalHistoryViewModel.assertVisionNamesFetched(visionNames: List<VisionName>){
    val liveData : LiveData<List<VisionName>> = this.fetchVisionNames()
    Assert.assertNotNull(liveData.value)
    Assert.assertEquals(visionNames.size, liveData.value?.size)
}