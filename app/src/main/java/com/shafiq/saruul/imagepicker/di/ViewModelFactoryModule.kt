package com.shafiq.saruul.imagepicker.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    internal abstract fun bindViewModelFactory(
        factory: ImagePickerViewModelFactory
    ): ViewModelProvider.Factory
}
