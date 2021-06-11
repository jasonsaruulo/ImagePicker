package com.shafiq.saruul.imagepicker.ui.picker.di

import com.shafiq.saruul.imagepicker.ui.picker.PickerFragment
import dagger.Subcomponent

@Subcomponent(modules = [PickerModule::class])
interface PickerComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): PickerComponent
    }

    fun inject(fragment: PickerFragment)
}
