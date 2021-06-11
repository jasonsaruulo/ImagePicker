package com.shafiq.saruul.imagepicker.ui.picker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shafiq.saruul.imagepicker.R

class PickerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.picker_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PickerFragment.newInstance())
                .commitNow()
        }
    }
}