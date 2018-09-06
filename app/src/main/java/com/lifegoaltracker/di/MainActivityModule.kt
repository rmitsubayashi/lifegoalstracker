package com.lifegoaltracker.di

import com.lifegoaltracker.views.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules=[FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}