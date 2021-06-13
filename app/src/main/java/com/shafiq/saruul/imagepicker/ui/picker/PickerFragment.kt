package com.shafiq.saruul.imagepicker.ui.picker

import android.Manifest
import android.content.ContentUris
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shafiq.saruul.imagepicker.R
import com.shafiq.saruul.imagepicker.di.ImagePickerApplication
import com.shafiq.saruul.imagepicker.di.ImagePickerViewModelFactory
import javax.inject.Inject

class PickerFragment @Inject constructor() : Fragment() {

    companion object {
        fun newInstance() = PickerFragment()
    }

    private var requestReadExternalStoragePermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            viewModel.onReadExternalStoragePermissionGranted(it)
        }

    @Inject
    lateinit var viewModelFactory: ImagePickerViewModelFactory
    private val viewModel by viewModels<PickerViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as ImagePickerApplication).appComponent.pickerComponent()
            .create().inject(this /* fragment */)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.state.observe(viewLifecycleOwner, {
            when (it) {
                PickerViewModel.State.Content -> {
                    // TODO: Show images
                }
                PickerViewModel.State.ReadExternalStoragePermissionNeeded -> {
                    showReadExternalStoragePermissionNeeded()
                }
                else -> {
                    // Do nothing
                }
            }
        })
        viewModel.loadImageUris.observe(viewLifecycleOwner, {
            context?.let {
                val imageUris = mutableListOf<Uri>()

                val collection =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        MediaStore.Images.Media.getContentUri(
                            MediaStore.VOLUME_EXTERNAL
                        )
                    } else {
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    }

                val projection =
                    arrayOf(MediaStore.Images.Media._ID, MediaStore.Images.Media.DATE_ADDED)
                val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} ASC"

                val query = it.contentResolver?.query(
                    collection,
                    projection,
                    null /* selection */,
                    null /* selectionArgs */,
                    sortOrder
                )
                query?.use { cursor ->
                    val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)

                    while (cursor.moveToNext()) {
                        val id = cursor.getLong(idColumn)

                        val contentUri: Uri = ContentUris.withAppendedId(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            id
                        )
                        imageUris += contentUri
                    }
                }
                viewModel.onImageUrisLoaded(imageUris)
            }
        })
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // TODO: Handle file access for api >= 30 (https://developer.android.com/training/data-storage/manage-all-files)
        } else {
            if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                viewModel.onReadExternalStoragePermissionGranted(granted = true)
            } else {
                requestReadExternalStoragePermissionLauncher.launch(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            }
        }
        return inflater.inflate(R.layout.picker_fragment, container, false)
    }

    private fun showReadExternalStoragePermissionNeeded() {
        view?.findViewById<View>(R.id.picker_permission_needed)?.visibility = View.VISIBLE
    }
}
