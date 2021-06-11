package com.shafiq.saruul.imagepicker.ui.picker

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.picker_fragment, container, false)
    }
}
