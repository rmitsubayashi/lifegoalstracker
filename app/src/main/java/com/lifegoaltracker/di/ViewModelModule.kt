package com.lifegoaltracker.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.lifegoaltracker.views.addEditVision.viewmodel.AddEditVisionViewModel
import com.lifegoaltracker.views.visions.viewmodel.VisionsViewModel
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
    @ViewModelKey(VisionsViewModel::class)
    abstract fun bindVisionsViewModel(vm: VisionsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}