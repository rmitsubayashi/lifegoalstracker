package com.lifegoaltracker.views.visions.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.lifegoaltracker.R
import com.lifegoaltracker.model.vision.*
import com.lifegoaltracker.repository.ID
import kotlinx.android.synthetic.main.fragment_visions.view.*

class VisionsFragment: Fragment() {
    private lateinit var adapter: VisionsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_visions, container, false)
        adapter = VisionsAdapter()
        view.recyclerview_visions.adapter = adapter
        view.recyclerview_visions.layoutManager = LinearLayoutManager(context)
        adapter.setVisions(listOf(
                Vision(ID(0), VisionUserFields("title","desc","reason", Priority.HIGH),
                        VisionProperties(0), VisionStatus())
        ))
        return view
    }
}