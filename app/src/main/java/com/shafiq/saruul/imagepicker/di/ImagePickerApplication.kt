package com.shafiq.saruul.imagepicker.di

import android.content.Context
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ImagePickerApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerImagePickerComponent.factory().create(applicationContext)
    }

    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: ImagePickerComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): ImagePickerComponent {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerImagePickerComponent.factory().create(applicationContext)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
//        MultiDex.install(this)
    }
}
