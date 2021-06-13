package com.shafiq.saruul.imagepicker.ui.picker

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shafiq.saruul.imagepicker.R

class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val image: ImageView = view.findViewById(R.id.image_cell_image)
    val selectedOverlay: View = view.findViewById(R.id.image_cell_selected_overlay)
}
