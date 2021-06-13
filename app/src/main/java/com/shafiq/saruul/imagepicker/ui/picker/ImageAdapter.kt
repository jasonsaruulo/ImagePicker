package com.shafiq.saruul.imagepicker.ui.picker

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shafiq.saruul.imagepicker.R

class ImageAdapter(
    private val images: List<Image>,
    private val imageHandler: ImageHandler,
    private val listener: Listener
) :
    RecyclerView.Adapter<ImageViewHolder>() {

    data class Image constructor(val uri: Uri, var selected: Boolean)

    interface Listener {
        fun onImageClicked(position: Int, image: Image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_cell, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[itemCount - position - 1]
        imageHandler.loadImage(
            holder.image,
            image.uri
        )
        if (image.selected) {
            holder.selectedOverlay.visibility = View.VISIBLE
        } else {
            holder.selectedOverlay.visibility = View.GONE
        }
        holder.itemView.setOnClickListener {
            listener.onImageClicked(position, image)
        }
    }
}
