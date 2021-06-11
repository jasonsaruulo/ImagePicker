package com.shafiq.saruul.imagepicker.ui.picker

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract

class PickerResult : ActivityResultContract<Void?, List<Uri>>() {

    companion object {
        const val IMAGES_URIS_PICKED = "imageUrisPicked"
    }

    override fun createIntent(context: Context, input: Void?) =
        Intent(context, PickerActivity::class.java)

    override fun parseResult(resultCode: Int, intent: Intent?): List<Uri> {
        return when (resultCode) {
            Activity.RESULT_OK -> {
                intent?.let {
                    it.getParcelableArrayListExtra<Uri>(IMAGES_URIS_PICKED)?.let { uris ->
                        return uris
                    }
                }
                return emptyList()
            }
            else -> emptyList()
        }
    }
}