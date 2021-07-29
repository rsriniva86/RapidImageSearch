package com.app.rapidimagesearch.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_data_index")
data class ImageDataIndex(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var pageNumber: Int,
    val pageSize: Int,
    val input: String,
    val count: Int
)