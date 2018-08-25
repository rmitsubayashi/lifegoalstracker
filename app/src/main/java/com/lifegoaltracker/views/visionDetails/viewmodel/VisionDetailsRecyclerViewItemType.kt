package com.lifegoaltracker.views.visionDetails.viewmodel

enum class VisionDetailsRecyclerViewItemType(val value: Int) {
    GOAL(0),
    HEADER(1),
    VISION_DESCRIPTION(2),
    VISION_REASON(3),
    GOALS_EMPTY(4),
    VISION_DESCRIPTION_EMPTY(5),
    VISION_REASON_EMPTY(6),
    GOALS_LOADING(7),
    VISION_LOADING(8)
}