package com.shafiq.saruul.imagepicker.ui.picker.di

import androidx.lifecycle.ViewModel
import com.shafiq.saruul.imagepicker.di.ViewModelKey
import com.shafiq.saruul.imagepicker.ui.picker.GlideImageHandler
import com.shafiq.saruul.imagepicker.ui.picker.ImageHandler
import com.shafiq.saruul.imagepicker.ui.picker.PickerViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PickerModule {

    @Binds
    @IntoMap
    @ViewModelKey(PickerViewModel::class)
    abstract fun bindViewModel(viewModel: PickerViewModel): ViewModel

    @Binds
    abstract fun bindImageHandler(glideImageHandler: GlideImageHandler): ImageHandler
}
