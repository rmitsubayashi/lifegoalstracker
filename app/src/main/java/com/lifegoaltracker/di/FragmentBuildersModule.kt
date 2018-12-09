package com.lifegoaltracker.di

import com.lifegoaltracker.views.addEditGoal.view.AddEditGoalFragment
import com.lifegoaltracker.views.addEditVision.view.AddEditVisionFragment
import com.lifegoaltracker.views.visionDetails.view.VisionDetailsFragment
import com.lifegoaltracker.views.visionGoals.view.VisionGoalsFragment
import com.lifegoaltracker.views.visions.view.MainViewPagerFragment
import com.lifegoaltracker.views.visions.view.VisionFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeAddEditVisionFragment(): AddEditVisionFragment
    @ContributesAndroidInjector
    abstract fun contributeVisionsFragment(): VisionFragment
    @ContributesAndroidInjector
    abstract fun contributeVisionDetailsFragment(): VisionDetailsFragment
    @ContributesAndroidInjector
    abstract fun contributeAddEditGoalFragment(): AddEditGoalFragment
    @ContributesAndroidInjector
    abstract fun contributeMainViewPagerFragment(): MainViewPagerFragment
    @ContributesAndroidInjector
    abstract fun contributeVisionGoalsFragment(): VisionGoalsFragment
}