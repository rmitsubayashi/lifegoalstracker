package com.lifegoaltracker.views.visions.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.lifegoaltracker.model.vision.Vision
import com.lifegoaltracker.views.visionDetails.view.VisionDetailsFragment
import com.lifegoaltracker.views.visionGoalChecklist.view.VisionGoalChecklistFragment
import com.lifegoaltracker.views.visionGoals.view.VisionGoalsFragment

class VisionAdapter(fm: FragmentManager, private val vision: Vision):
    FragmentStatePagerAdapter(fm) {
    override fun getCount() = 3

    override fun getItem(position: Int): Fragment =
    when (position){
        0 -> VisionDetailsFragment.newInstance(vision.id)
        1 -> VisionGoalsFragment.newInstance(vision.id)
        2 -> VisionGoalChecklistFragment.newInstance(vision.id)
        else -> Fragment()
    }
}