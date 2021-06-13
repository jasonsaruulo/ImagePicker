package com.shafiq.saruul.imagepicker.ui.picker

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shafiq.saruul.imagepicker.SingleLiveEvent
import javax.inject.Inject

class PickerViewModel @Inject constructor(val imageHandler: ImageHandler) : ViewModel(),
    ImageAdapter.Listener {

    enum class State {
        Images,
        ReadExternalStoragePermissionNeeded
    }

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state
    private val _loadImageUris = SingleLiveEvent<Void?>()
    val loadImageUris: LiveData<Void?> = _loadImageUris
    private val _showImages = MutableLiveData<List<ImageAdapter.Image>>()
    val showImages: LiveData<List<ImageAdapter.Image>> = _showImages
    private val _refreshCell = MutableLiveData<Int>()
    val refreshCell: LiveData<Int> = _refreshCell

    private val selected = mutableMapOf<Int, Uri>()

    fun onReadExternalStoragePermissionGranted(granted: Boolean) {
        if (granted) {
            _loadImageUris.call()
        } else {
            _state.value = State.ReadExternalStoragePermissionNeeded
        }
    }

    fun onImageUrisLoaded(imageUris: List<Uri>) {
        _showImages.value = imageUris.map { ImageAdapter.Image(it, selected = false) }
        _state.value = State.Images
    }

    override fun onImageClicked(position: Int, image: ImageAdapter.Image) {
        if (image.selected) {
            selected.remove(position)
            image.selected = false
        } else {
            selected[position] = image.uri
            image.selected = true
        }
        _refreshCell.value = position
    }
}
