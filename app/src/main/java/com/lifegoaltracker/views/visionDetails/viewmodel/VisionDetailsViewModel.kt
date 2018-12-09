package com.lifegoaltracker.views.visionDetails.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.lifegoaltracker.model.vision.Vision
import com.lifegoaltracker.repository.ID
import com.lifegoaltracker.repository.vision.VisionRepository
import javax.inject.Inject

class VisionDetailsViewModel
    @Inject constructor(
                        private val visionRepository: VisionRepository
                        )
    : ViewModel(){
    var vision: LiveData<Vision>? = null

    fun fetchVision(visionID: ID){
        vision?.let { return }
        vision = visionRepository.getVision(visionID)
    }
}