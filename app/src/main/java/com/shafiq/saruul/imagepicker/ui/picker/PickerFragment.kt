package com.shafiq.saruul.imagepicker.ui.picker

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
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
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            // TODO: Handle file access for api >= 30 (https://developer.android.com/training/data-storage/manage-all-files)
        } else {
            if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                // TODO: Load images
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
