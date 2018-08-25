package com.lifegoaltracker.repository

import android.arch.persistence.room.TypeConverter

class IDTypeConverter {
    @TypeConverter
    fun toLong(id: ID): Long?{
        return id.value
    }

    @TypeConverter
    fun toID(value: Long?): ID{
        return ID(value)
    }
}