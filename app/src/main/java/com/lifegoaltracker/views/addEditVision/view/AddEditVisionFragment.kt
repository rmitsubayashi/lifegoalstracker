package com.lifegoaltracker.views.addEditVision.view

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.lifegoaltracker.R
import com.lifegoaltracker.databinding.FragmentAddEditVisionBinding
import com.lifegoaltracker.di.Injectable
import com.lifegoaltracker.model.vision.Vision
import com.lifegoaltracker.views.addEditVision.viewmodel.AddEditVisionViewModel
import kotlinx.android.synthetic.main.fragment_add_edit_vision.view.*
import javax.inject.Inject

class AddEditVisionFragment: Fragment(), Injectable {
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var viewModel: AddEditVisionViewModel
    lateinit var binding: FragmentAddEditVisionBinding

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory)
                .get(AddEditVisionViewModel::class.java)
        arguments?.getSerializable("vision").let {
            try {
                viewModel.setCurrentVision(it as Vision)
            } catch (exception: ClassCastException){
                //do nothing
            }
        }
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_edit_vision, container, false)
        val view = binding.root
        view.button_add_edit_vision_submit.setOnClickListener{
            viewModel.addVision()
            it.findNavController().navigateUp()
        }
        return view
    }

    companion object {
        fun createBundle(vision: Vision) = Bundle().apply { putSerializable("vision", vision) }
    }
}
