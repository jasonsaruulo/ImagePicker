package com.shafiq.saruul.imagepicker.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shafiq.saruul.imagepicker.SingleLiveEvent
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    private val _openImagePicker = SingleLiveEvent<Void?>()
    val openImagePicker: LiveData<Void?> = _openImagePicker

    fun onOpenImagePickerClicked() {
        _openImagePicker.call()
    }
}
