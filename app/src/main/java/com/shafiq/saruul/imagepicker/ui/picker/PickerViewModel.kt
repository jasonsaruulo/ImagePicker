package com.shafiq.saruul.imagepicker.ui.picker

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shafiq.saruul.imagepicker.SingleLiveEvent
import javax.inject.Inject

class PickerViewModel @Inject constructor() : ViewModel() {
    enum class State {
        Content,
        ReadExternalStoragePermissionNeeded
    }

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state
    private val _loadImageUris = SingleLiveEvent<Void?>()
    val loadImageUris: LiveData<Void?> = _loadImageUris

    fun onReadExternalStoragePermissionGranted(granted: Boolean) {
        if (granted) {
            _loadImageUris.call()
        } else {
            _state.value = State.ReadExternalStoragePermissionNeeded
        }
    }

    fun onImageUrisLoaded(imageUris: List<Uri>) {
        // TODO: Show images
    }
}
