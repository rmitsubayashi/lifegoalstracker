package com.lifegoaltracker.repository.vision

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.lifegoaltracker.model.vision.*
import com.lifegoaltracker.repository.ID
import org.jetbrains.anko.doAsync
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class VisionRepository @Inject constructor(private val visionDao: VisionDao) {
    fun getVisions() : LiveData<List<Vision>>{
        val vision1 = Vision(ID(0), VisionUserFields("Fitness",
                "I will have many friends who I play soccer with regularly.","I enjoy playing soccer!!!", Priority.HIGH),
                VisionProperties(0), VisionStatus())
        val visionList = listOf(vision1)
        return MutableLiveData<List<Vision>>().apply{value=visionList}
        // return visionDao.queryVisions()
    }

    fun getVisionNames() : LiveData<List<VisionName>>{
        val visionName1 = VisionName(ID(0), "Fitness")
        val visionNameList = listOf(visionName1)
        return MutableLiveData<List<VisionName>>().apply { value=visionNameList }
        //return visionDao.queryVisionNames()
    }

    fun getVision(id : ID) : LiveData<Vision>{
        val vision1 = Vision(ID(0), VisionUserFields("Fitness",
                "I will have many friends who I play soccer with regularly.","I enjoy playing soccer!!!", Priority.HIGH),
                VisionProperties(0), VisionStatus())
        return MutableLiveData<Vision>().apply{value=vision1}
        // return visionDao.queryVision(id)
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