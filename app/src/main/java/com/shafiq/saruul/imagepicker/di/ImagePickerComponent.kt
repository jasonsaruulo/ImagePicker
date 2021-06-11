package com.shafiq.saruul.imagepicker.di

import android.content.Context
import com.shafiq.saruul.imagepicker.ui.main.di.MainComponent
import com.shafiq.saruul.imagepicker.ui.picker.di.PickerComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ImagePickerModule::class,
        AndroidSupportInjectionModule::class,
        ViewModelFactoryModule::class,
        SubcomponentsModule::class]
)
interface ImagePickerComponent : AndroidInjector<ImagePickerApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ImagePickerComponent
    }

    fun mainComponent(): MainComponent.Factory
    fun pickerComponent(): PickerComponent.Factory
}

@Module(subcomponents = [MainComponent::class, PickerComponent::class])
object SubcomponentsModule
