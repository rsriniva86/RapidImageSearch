package com.app.rapidimagesearch.ui.imagesearch

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.rapidimagesearch.R
import com.app.rapidimagesearch.network.ImageData
import com.app.rapidimagesearch.ui.components.RecyclerViewLoadMoreScroll.Companion.VIEW_TYPE_ITEM
import com.app.rapidimagesearch.ui.components.RecyclerViewLoadMoreScroll.Companion.VIEW_TYPE_LOADING
import kotlinx.android.synthetic.main.item_image.view.*

class RapidImageSearchAdapter(imageDataListParam: MutableList<ImageData?>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var imageDataList = imageDataListParam

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context:Context = parent.context

        return if (viewType == VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
            ImageViewHolder(view)
        } else {
            val view = LayoutInflater.from(context).inflate(R.layout.item_progressbar, parent, false)
            LoadingViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val imageData: ImageData? = imageDataList.get(position)
        
    }

    override fun getItemCount(): Int {
        return imageDataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (imageDataList[position] == null) {
            VIEW_TYPE_LOADING
        } else {
            VIEW_TYPE_ITEM
        }
    }

    fun addLoadingView() {
        //Add loading item
        Handler(Looper.getMainLooper()).post {
            imageDataList.add(null)
            notifyItemInserted(imageDataList.size - 1)
        }
    }

    fun removeLoadingView() {
        //Remove loading item
        if (imageDataList.size != 0) {
            imageDataList.removeAt(imageDataList.size - 1)
            notifyItemRemoved(imageDataList.size)
        }
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivImageCover: ImageView = itemView.ivCover
        private val tvTitle: TextView = itemView.tvTitle
        private val tvWebPageUrl: TextView = itemView.tvWebPageUrl
    }

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}