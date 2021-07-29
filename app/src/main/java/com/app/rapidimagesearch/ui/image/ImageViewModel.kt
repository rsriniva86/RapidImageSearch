package com.app.rapidimagesearch.ui.image

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.rapidimagesearch.network.ImageData

class ImageViewModel : ViewModel() {
    private val imageLiveData: MutableLiveData<ImageData> = MutableLiveData<ImageData>()

    companion object {
        private const val IMAGE_ARG = "image"
        fun createArguments(imageData: ImageData): Bundle {
            val bundle = Bundle()
            bundle.putSerializable(IMAGE_ARG, imageData)
            return bundle
        }
    }

    fun loadArguments(arguments: Bundle?) {
        if (arguments == null) {
            return
        }

        val image: ImageData? = arguments.get(IMAGE_ARG) as ImageData?
        image?.let {
            imageLiveData.value = it
            Log.e("Image", imageLiveData.value.toString())
        }
    }

    fun getImageDetailData(): LiveData<ImageData> {
        return imageLiveData
    }
}