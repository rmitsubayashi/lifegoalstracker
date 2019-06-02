package com.lifegoaltracker.repository.vision

import android.arch.lifecycle.MutableLiveData
import com.lifegoaltracker.model.vision.VisionName
import org.mockito.Mockito

fun VisionRepository.mockGetVisionNames(visionNames: List<VisionName>){
    val visionNamesLiveData: MutableLiveData<List<VisionName>> = MutableLiveData()
    visionNamesLiveData.value = visionNames

    Mockito.`when`(this.getVisionNames()).thenReturn(visionNamesLiveData)
}