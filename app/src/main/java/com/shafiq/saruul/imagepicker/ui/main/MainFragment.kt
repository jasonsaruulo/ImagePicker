package com.shafiq.saruul.imagepicker.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shafiq.saruul.imagepicker.R
import com.shafiq.saruul.imagepicker.di.ImagePickerApplication
import com.shafiq.saruul.imagepicker.di.ImagePickerViewModelFactory
import com.shafiq.saruul.imagepicker.ui.picker.PickerResult
import javax.inject.Inject

class MainFragment : Fragment() {

    private var pickerLauncher: ActivityResultLauncher<Void?>? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModelFactory: ImagePickerViewModelFactory
    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pickerLauncher = registerForActivityResult(PickerResult()) {
            // TODO: Handle result
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        view.findViewById<View>(R.id.main_open_image_picker).setOnClickListener {
            viewModel.onOpenImagePickerClicked()
        }

        viewModel.openImagePicker.observe(viewLifecycleOwner, {
            pickerLauncher?.launch(null /* input */)
        })

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as ImagePickerApplication).appComponent.mainComponent()
            .create().inject(fragment = this)
    }

}
