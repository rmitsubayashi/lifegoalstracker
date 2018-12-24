package com.lifegoaltracker.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.lifegoaltracker.views.addEditGoal.viewmodel.AddEditGoalViewModel
import com.lifegoaltracker.views.addEditVision.viewmodel.AddEditVisionViewModel
import com.lifegoaltracker.views.visionDetails.viewmodel.VisionDetailsViewModel
import com.lifegoaltracker.views.visionGoalChecklist.viewmodel.VisionGoalChecklistViewModel
import com.lifegoaltracker.views.visionGoals.viewmodel.VisionGoalsViewModel
import com.lifegoaltracker.views.visions.viewmodel.MainViewPagerViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AddEditVisionViewModel::class)
    abstract fun bindAddEditVisionViewModel(vm: AddEditVisionViewModel): ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(MainViewPagerViewModel::class)
    abstract fun bindVisionsViewModel(vm: MainViewPagerViewModel): ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(VisionDetailsViewModel::class)
    abstract fun bindVisionDetailsViewModel(vm: VisionDetailsViewModel): ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(AddEditGoalViewModel::class)
    abstract fun bindAddEditGoalViewModel(vm: AddEditGoalViewModel): ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(VisionGoalsViewModel::class)
    abstract fun bindVisionGoalsViewModel(vm: VisionGoalsViewModel): ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(VisionGoalChecklistViewModel::class)
    abstract fun bindVisionGoalChecklistViewModel(vm: VisionGoalChecklistViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}