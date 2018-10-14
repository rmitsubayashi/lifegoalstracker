package com.lifegoaltracker.repository.vision

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.lifegoaltracker.model.vision.Vision
import com.lifegoaltracker.model.vision.VisionName
import com.lifegoaltracker.repository.ID
import org.jetbrains.anko.doAsync
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class VisionRepository @Inject constructor(private val visionDao: VisionDao) {
    fun getVisions() : LiveData<List<Vision>>{
        return visionDao.queryVisions()
    }

    fun getVisionNames() : LiveData<List<VisionName>>{
        return visionDao.queryVisionNames()
    }

    fun getVision(id : ID) : LiveData<Vision>{
        return visionDao.queryVision(id)
    }

    fun addVision(vision : Vision){
        doAsync {
            visionDao.postVision(vision)
        }
    }

    fun editVision(vision: Vision){
        doAsync {
            visionDao.updateVision(vision)
        }
    }

    fun removeVision(vision: Vision){
        visionDao.remove(vision)
    }

}