package com.shafiq.saruul.imagepicker.ui.picker

import android.net.Uri
import android.widget.ImageView

interface ImageHandler {

    fun loadImage(imageView: ImageView, uri: Uri)
}
