package com.shafiq.saruul.imagepicker.ui.main.di

import androidx.lifecycle.ViewModel
import com.shafiq.saruul.imagepicker.di.ViewModelKey
import com.shafiq.saruul.imagepicker.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindViewModel(viewModel: MainViewModel): ViewModel
}
