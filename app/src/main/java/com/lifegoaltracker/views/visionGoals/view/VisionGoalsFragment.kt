package com.lifegoaltracker.views.visionGoals.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import androidx.navigation.fragment.findNavController
import com.lifegoaltracker.R
import com.lifegoaltracker.di.Injectable
import com.lifegoaltracker.repository.ID
import com.lifegoaltracker.views.addEditGoal.view.AddEditGoalFragment
import com.lifegoaltracker.views.visionGoals.viewmodel.VisionGoalsViewModel
import kotlinx.android.synthetic.main.fragment_vision_goals.view.*
import javax.inject.Inject

class VisionGoalsFragment: Fragment(), Injectable {
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var viewModel: VisionGoalsViewModel
    private val adapter = VisionGoalsAdapter(getContextMenuListener())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_vision_goals, container, false)
        view.list_vision_goals.adapter = adapter
        view.list_vision_goals.layoutManager = LinearLayoutManager(context)
        registerForContextMenu(view.list_vision_goals)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory)
                .get(VisionGoalsViewModel::class.java)
        val visionID = arguments?.getSerializable("visionID") as ID
        viewModel.fetchGoals(visionID).observe(this, Observer {
            it?.let {
                list ->
                adapter.setGoals(list, visionID)
            }
        })

    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        if (item == null){
            return true
        }
        when (item.itemId){
            0 -> {
                val longClickedGoal = adapter.getLongClickedItem()
                longClickedGoal?.let {
                    val bundle = AddEditGoalFragment.createBundle(it)
                    findNavController().navigate(R.id.action_mainViewPagerFragment_to_addEditGoalFragment, bundle)
                }
            }
            1 -> return true //TODO remove
        }
        return super.onContextItemSelected(item)
    }

    private fun getContextMenuListener(): View.OnCreateContextMenuListener =
        View.OnCreateContextMenuListener {
            contextMenu, view, contextMenuInfo ->
            contextMenu?.add(Menu.NONE, 0, Menu.NONE, "Edit")
            contextMenu?.add(Menu.NONE, 1, Menu.NONE, "Remove")
        }

    companion object {
        fun newInstance(visionID: ID): VisionGoalsFragment {
            val bundle = Bundle().apply { putSerializable("visionID", visionID) }
            return VisionGoalsFragment().apply { arguments = bundle }
        }
    }
}