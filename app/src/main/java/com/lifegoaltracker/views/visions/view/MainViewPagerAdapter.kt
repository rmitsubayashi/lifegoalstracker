package com.lifegoaltracker.views.visions.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.lifegoaltracker.model.vision.Vision

class MainViewPagerAdapter(fm: FragmentManager):
        FragmentStatePagerAdapter(fm) {
    private val visions: MutableList<Vision> = mutableListOf()

    override fun getItem(position: Int): Fragment {
        if (position == visions.size){
            return AddVisionFragment()
        }
        val vision = visions[position]
        return VisionFragment.newInstance(vision)
    }

    override fun getCount() = if (visions.size == 0){ 0 } else { visions.size + 1 }

    fun setVisions(visions: List<Vision>) {
        this.visions.clear()
        this.visions.addAll(visions)
        notifyDataSetChanged()
    }
}