package com.shafiq.saruul.imagepicker.ui.main.di

import com.shafiq.saruul.imagepicker.ui.main.MainFragment
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(fragment: MainFragment)
}
