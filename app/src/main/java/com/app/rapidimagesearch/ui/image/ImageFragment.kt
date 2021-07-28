package com.app.rapidimagesearch.ui.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.app.rapidimagesearch.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_image.view.*


@AndroidEntryPoint
class ImageFragment : DialogFragment() {

    private val viewModel: ImageViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_image, container, false)
        viewModel.loadArguments(arguments)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getImageDetailData().observe(this, { imageData ->
            imageData.width?.let {
                imageData.height?.let {
                    Glide
                        .with(view.context)
                        .load(imageData.url)
                        .transform(CenterCrop())
                        .override(imageData.width, imageData.height)
                        .error(R.drawable.image_load_failed_icon)
                        .into(view.image)
                }
            }
        })
    }
}