package com.lifegoaltracker.di

import com.lifegoaltracker.views.addEditGoal.view.AddEditGoalFragment
import com.lifegoaltracker.views.addEditVision.view.AddEditVisionFragment
import com.lifegoaltracker.views.visionDetails.view.VisionDetailsFragment
import com.lifegoaltracker.views.visions.view.VisionsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeAddEditVisionFragment(): AddEditVisionFragment
    @ContributesAndroidInjector
    abstract fun contributeVisionsFragment(): VisionsFragment
    @ContributesAndroidInjector
    abstract fun contributeVisionDetailsFragment(): VisionDetailsFragment
    @ContributesAndroidInjector
    abstract fun contributeAddEditGoalFragment(): AddEditGoalFragment
}