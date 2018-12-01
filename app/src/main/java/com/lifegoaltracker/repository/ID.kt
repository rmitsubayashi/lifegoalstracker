package com.lifegoaltracker.repository

import java.io.Serializable

//null value = not set in DB -> auto-generates ID
data class ID (val value: Long?): Serializable