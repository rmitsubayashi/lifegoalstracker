package com.lifegoaltracker.views.visions.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.lifegoaltracker.model.vision.Vision
import com.lifegoaltracker.repository.vision.VisionRepository
import javax.inject.Inject

class VisionsViewModel @Inject constructor(private val repository: VisionRepository)
    : ViewModel(){
    private val visionsList : MutableLiveData<List<Vision>> = MutableLiveData()

    fun fetchVisionsList() : LiveData<List<Vision>> {
        visionsList.postValue(
                repository.getVisions().value
        )

        return visionsList
    }

    fun removeVision(vision: Vision) {
        repository.removeVision(vision)
    }
}