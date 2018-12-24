package com.lifegoaltracker.views.addEditGoal.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.lifegoaltracker.R
import com.lifegoaltracker.databinding.FragmentAddEditGoalBinding
import com.lifegoaltracker.di.Injectable
import com.lifegoaltracker.model.goal.Goal
import com.lifegoaltracker.model.goal.dueDate.span.GoalSpan
import com.lifegoaltracker.repository.ID
import com.lifegoaltracker.views.addEditGoal.viewmodel.AddEditGoalViewModel
import kotlinx.android.synthetic.main.fragment_add_edit_goal.view.*
import javax.inject.Inject

class AddEditGoalFragment: Fragment(), Injectable {
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var viewModel: AddEditGoalViewModel
    lateinit var binding: FragmentAddEditGoalBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_edit_goal, container, false)
        val view = binding.root
        view.button_add_edit_goal_submit.setOnClickListener{
            viewModel.addGoal()
            it.findNavController().navigateUp()
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory)
                .get(AddEditGoalViewModel::class.java)
        viewModel.inputErrorMessage.observe(
                this, Observer { Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show() }
        )

        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        arguments?.getSerializable("goal").let {
            try {
                viewModel.setCurrentGoal(it as Goal)
                return
            } catch (exception: ClassCastException) {
                //do nothing
            }
        }
        arguments?.getSerializable("visionID").let {
            try {
                viewModel.setCurrentVision(it as ID)
            } catch (exception: ClassCastException) {
                //do nothing
            }
        }
        arguments?.getSerializable("goalSpan").let {
            try {
                viewModel.setCurrentVisionGoalSpan(it as GoalSpan)
            } catch (exception: ClassCastException) {
                //do nothing
            }
        }
    }

    companion object {
        fun createBundle(goal: Goal) = Bundle().apply { putSerializable("goal", goal) }
        fun createBundle(visionID: ID) = Bundle().apply { putSerializable("visionID", visionID)}
        fun createBundle(visionID: ID, span: GoalSpan) =
                Bundle().apply { putSerializable("goalSpan", span)}
                        .apply { putSerializable("visionID",visionID) }
    }
}