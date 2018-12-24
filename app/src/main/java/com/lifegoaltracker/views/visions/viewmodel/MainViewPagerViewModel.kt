package com.lifegoaltracker.views.visions.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.lifegoaltracker.model.vision.Vision
import com.lifegoaltracker.repository.vision.VisionRepository
import javax.inject.Inject

class MainViewPagerViewModel @Inject constructor(private val repository: VisionRepository)
    : ViewModel(){
    val visionsList : LiveData<List<Vision>> = repository.getVisions()

    fun removeVision(vision: Vision) {
        repository.removeVision(vision)
    }
}