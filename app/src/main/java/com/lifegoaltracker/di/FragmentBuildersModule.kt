package com.lifegoaltracker.di

import com.lifegoaltracker.views.addEditVision.view.AddEditVisionFragment
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
}