package com.app.rapidimagesearch.ui.imagesearch

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.rapidimagesearch.R
import com.app.rapidimagesearch.network.ImageData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.synthetic.main.item_image.view.*

class RapidImageSearchAdapter :
        PagingDataAdapter<ImageData, RapidImageSearchAdapter.ImageDataViewHolder>(DiffUtilCallBack()) {
    private var onSearchImageSearchImageItemClickListener: OnSearchImageItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageDataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageDataViewHolder, position: Int) {
        getItem(position)?.let { holder.bindImageData(it) }
        val item = getItem(position)
        holder.itemView.ivImage.setOnClickListener {
            item?.let { it1 -> onSearchImageSearchImageItemClickListener?.onItemClick(it1) }
        }
        holder.itemView.tvWebPageUrl.setOnClickListener {
            item?.let { it1 -> onSearchImageSearchImageItemClickListener?.onUrlClick(it1) }
        }


    }

    fun setOnItemClickListener(listenerSearchImage: OnSearchImageItemClickListener) {
        onSearchImageSearchImageItemClickListener = listenerSearchImage
    }

    class ImageDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivImageCover: ImageView = itemView.ivImage
        private val tvData: TextView = itemView.tvData
        private val tvWebPageUrl: TextView = itemView.tvWebPageUrl

        fun bindImageData(imageData: ImageData) {
            with(imageData) {
                Glide.with(itemView.context).load(thumbnail)
                        .transform(CenterCrop(), RoundedCorners(25))
                        .into(ivImageCover)
                tvData.text = "${title}|${name}"
                tvWebPageUrl.text = webPageUrl
                tvWebPageUrl.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            }
        }
    }

    interface OnSearchImageItemClickListener {
        fun onItemClick(imageData: ImageData)
        fun onUrlClick(imageData: ImageData)
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<ImageData>() {
        override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
            return oldItem.url == newItem.url
                    && oldItem.title == newItem.title
                    && oldItem.webPageUrl == newItem.webPageUrl
        }
    }
}