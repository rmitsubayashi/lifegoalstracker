package com.lifegoaltracker.repository.vision

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.lifegoaltracker.model.vision.Vision
import com.lifegoaltracker.model.vision.VisionName
import com.lifegoaltracker.repository.ID

@Dao
interface VisionDao {
    @Query("SELECT * FROM vision WHERE NOT isDeleted")
    fun queryVisions(): LiveData<List<Vision>>

    @Query("SELECT id, title FROM vision WHERE NOT isDeleted")
    fun queryVisionNames(): LiveData<List<VisionName>>

    @Query("SELECT * FROM vision WHERE id = :id AND NOT isDeleted")
    fun queryVision(id: ID) : LiveData<Vision>

    @Insert(onConflict=REPLACE)
    fun postVision(vision: Vision)

    @Update
    fun updateVision(vision: Vision)

    @Transaction
    fun remove(vision: Vision){
        removeVision(vision.id)
        /*
        * Is this the best practice?
        * To call an update on another table in this DAO.
        * Should I reference GoalDAO instead?
        * */
        removeGoal(vision.id)
    }

    @Query("UPDATE vision SET isDeleted = 1 WHERE id = :visionID")
    fun removeVision(visionID: ID)

    @Query("UPDATE goal SET isDeleted = 1 WHERE visionID = :visionID")
    fun removeGoal(visionID: ID)

}