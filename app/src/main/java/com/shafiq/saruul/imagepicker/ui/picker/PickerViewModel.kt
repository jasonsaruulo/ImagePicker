package com.shafiq.saruul.imagepicker.ui.picker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class PickerViewModel @Inject constructor() : ViewModel() {
    enum class State {
        Content,
        ReadExternalStoragePermissionNeeded
    }

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    fun onReadExternalStoragePermissionGranted(granted: Boolean) {
        if (granted) {
            _state.value = State.Content
        } else {
            _state.value = State.ReadExternalStoragePermissionNeeded
        }
    }
}
