package com.shafiq.saruul.imagepicker.ui.picker

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shafiq.saruul.imagepicker.R

class ImageAdapter(private val uris: List<Uri>, private val imageHandler: ImageHandler) :
    RecyclerView.Adapter<ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_cell, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return uris.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        imageHandler.loadImage(
            holder.image,
            uris[itemCount - position - 1]
        )
    }
}