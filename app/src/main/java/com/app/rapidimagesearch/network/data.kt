package com.app.rapidimagesearch.network

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class RapidImageSearchResponse(
    @SerializedName("_type")
    var type: String,
    @SerializedName("totalCount")
    var count: Int,
    @SerializedName("value")
    var imageList: List<ImageData>
)

@Entity(tableName = "image_data")
data class ImageData(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "url")
    @SerializedName("url")
    val url: String = "",
    @ColumnInfo(name = "height")
    @SerializedName("height")
    val height: Int? = 0,
    @ColumnInfo(name = "width")
    @SerializedName("width")
    val width: Int? = 0,
    @ColumnInfo(name = "thumbnail")
    @SerializedName("thumbnail")
    val thumbnail: String? = null,
    @ColumnInfo(name = "thumbnail_height")
    @SerializedName("thumbnailHeight")
    val thumbnailHeight: Int? = 0,
    @ColumnInfo(name = "thumbnail_width")
    @SerializedName("thumbnailWidth")
    val thumbnailWidth: Int? = 0,
    @ColumnInfo(name = "base64_encoding")
    @SerializedName("base64Encoding")
    val base64Encoding: String? = null,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String? = null,
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String? = null,
    @ColumnInfo(name = "provider")
    @SerializedName("provider")
    val provider: Provider? = null,
    @ColumnInfo(name = "image_websearch_url")
    @SerializedName("imageWebSearchUrl")
    val imageWebSearchUrl: String? = null,
    @ColumnInfo(name = "webpage_url")
    @SerializedName("webpageUrl")
    val webPageUrl: String? = null,
    @ColumnInfo(name = "query")
    val query: String? = null
)

data class Provider(
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String? = null,
    @ColumnInfo(name = "favIcon")
    @SerializedName("favIcon")
    val favIcon: String? = null,
    @ColumnInfo(name = "favIconBase64Encoding")
    @SerializedName("favIconBase64Encoding")
    val favIconBase64Encoding: String? = null
)


