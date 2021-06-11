package com.shafiq.saruul.imagepicker.di

import com.shafiq.saruul.imagepicker.MainActivity
import com.shafiq.saruul.imagepicker.ui.main.di.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ImagePickerModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeMainActivityInjector(): MainActivity
}
