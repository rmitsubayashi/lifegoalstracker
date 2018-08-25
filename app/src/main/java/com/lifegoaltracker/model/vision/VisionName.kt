package com.lifegoaltracker.model.vision

import android.arch.persistence.room.ColumnInfo
import com.lifegoaltracker.repository.ID

//when we only want the vision names,
// not details
//(saves resources :P)
data class VisionName (
    @ColumnInfo(name="id")
    val id : ID,
    @ColumnInfo(name = "title")
    val title : String
)