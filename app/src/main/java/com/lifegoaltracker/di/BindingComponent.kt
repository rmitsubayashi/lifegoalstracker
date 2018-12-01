package com.lifegoaltracker.di

import android.databinding.DataBindingComponent
import com.lifegoaltracker.utils.bindings.InverseSpinnerBindings
import com.lifegoaltracker.utils.bindings.SpinnerBindings
import dagger.Component

@Component(modules = [BindingModule::class])
interface BindingComponent: DataBindingComponent {
    override fun getSpinnerBindings(): SpinnerBindings {
        return SpinnerBindings()
    }

    override fun getInverseSpinnerBindings(): InverseSpinnerBindings {
        return InverseSpinnerBindings()
    }
}