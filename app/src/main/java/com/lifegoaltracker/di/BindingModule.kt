package com.lifegoaltracker.di

import com.lifegoaltracker.utils.bindings.InverseSpinnerBindings
import com.lifegoaltracker.utils.bindings.SpinnerBindings
import dagger.Module
import dagger.Provides

@Suppress("unused")
@Module
class BindingModule {
    @Provides
    fun getSpinnerBindings(): SpinnerBindings {
        return SpinnerBindings()
    }

    @Provides
    fun getInverseSpinnerBindings(): InverseSpinnerBindings{
        return InverseSpinnerBindings()
    }
}