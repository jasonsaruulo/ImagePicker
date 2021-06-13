package com.shafiq.saruul.imagepicker.ui.picker

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.shafiq.saruul.imagepicker.R
import javax.inject.Inject

class GlideImageHandler @Inject constructor() : ImageHandler {

    override fun loadImage(imageView: ImageView, uri: Uri) {
        Glide.with(imageView).load(uri).placeholder(R.color.white).into(imageView)
    }
}
