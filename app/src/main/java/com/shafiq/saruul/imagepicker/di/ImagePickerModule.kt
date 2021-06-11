package com.shafiq.saruul.imagepicker.di

import com.shafiq.saruul.imagepicker.MainActivity
import com.shafiq.saruul.imagepicker.ui.main.di.MainModule
import com.shafiq.saruul.imagepicker.ui.picker.PickerActivity
import com.shafiq.saruul.imagepicker.ui.picker.di.PickerModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ImagePickerModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeMainActivityInjector(): MainActivity

    @ContributesAndroidInjector(modules = [PickerModule::class])
    abstract fun contributePickerActivityInjector(): PickerActivity
}
