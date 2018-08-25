package com.lifegoaltracker.views.visionDetails.viewmodel

import com.lifegoaltracker.model.goal.Goal

data class VisionDetailsRecyclerViewItem
(
        val type: VisionDetailsRecyclerViewItemType,
        val goal: Goal?,
        val displayString: String?
)