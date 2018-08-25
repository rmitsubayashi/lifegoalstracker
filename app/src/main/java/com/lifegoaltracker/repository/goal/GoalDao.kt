package com.lifegoaltracker.repository.goal

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.lifegoaltracker.model.goal.Goal
import com.lifegoaltracker.model.goal.dueDate.dateObjects.Date
import com.lifegoaltracker.model.goal.dueDate.span.GoalSpan
import com.lifegoaltracker.repository.ID


@Dao
interface GoalDao {
    @Query("SELECT * FROM goal WHERE id = :id")
    fun queryByID(id: ID) : LiveData<Goal>

    @Query("SELECT * FROM goal WHERE NOT isDeleted AND isCompleted ")
    fun queryCompletedGoals() : LiveData<List<Goal>>

    @Query("SELECT * FROM goal WHERE :startDate <= date AND date <= :endDate AND span = :goalSpan AND NOT isDeleted")
    fun queryGoals(startDate: Date, endDate: Date, goalSpan: GoalSpan) : LiveData<List<Goal>>

    @Query("SELECT * FROM goal WHERE :startDate <= date AND date <= :endDate AND span = :goalSpan AND visionID = :visionID AND NOT isDeleted")
    fun queryVisionGoals(startDate: Date, endDate: Date, goalSpan: GoalSpan, visionID: ID) : LiveData<List<Goal>>

    @Update
    fun updateGoal(goal: Goal)

    @Insert(onConflict = REPLACE)
    fun postGoal(goal: Goal)
}