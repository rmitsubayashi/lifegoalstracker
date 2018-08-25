package com.lifegoaltracker.model.vision

data class VisionUserFields (
    //required
    var title: String,
    //optional
    var description: String?,
    //optional
    var reason: String?,
    //required
    var priority: Priority
    )