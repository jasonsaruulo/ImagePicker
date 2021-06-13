package com.shafiq.saruul.imagepicker.ui.picker

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shafiq.saruul.imagepicker.SingleLiveEvent
import javax.inject.Inject

class PickerViewModel @Inject constructor(val imageHandler: ImageHandler) : ViewModel() {

    enum class State {
        Images,
        ReadExternalStoragePermissionNeeded
    }

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state
    private val _loadImageUris = SingleLiveEvent<Void?>()
    val loadImageUris: LiveData<Void?> = _loadImageUris
    private val _showImages = MutableLiveData<List<Uri>>()
    val showImages: LiveData<List<Uri>> = _showImages

    fun onReadExternalStoragePermissionGranted(granted: Boolean) {
        if (granted) {
            _loadImageUris.call()
        } else {
            _state.value = State.ReadExternalStoragePermissionNeeded
        }
    }

    fun onImageUrisLoaded(imageUris: List<Uri>) {
        _showImages.value = imageUris
        _state.value = State.Images
    }
}
