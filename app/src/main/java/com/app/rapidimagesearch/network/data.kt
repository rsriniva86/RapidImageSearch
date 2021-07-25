package com.app.rapidimagesearch.network

import com.google.gson.annotations.SerializedName

data class RapidImageSearchResponse(
    @SerializedName("_type")
    var type: String,
    @SerializedName("totalCount")
    var count: Int,
    @SerializedName("value")
    var imageList: List<ImageData>
)

data class ImageData(
    @SerializedName("url")
    val url: String = "",
    @SerializedName("height")
    val height: Int? = 0,
    @SerializedName("width")
    val width: Int? = 0,
    @SerializedName("thumbnail")
    val thumbnail: String? = null,
    @SerializedName("thumbnailHeight")
    val thumbnailHeight: Int? = 0,
    @SerializedName("thumbnailWidth")
    val thumbnailWidth: Int? = 0,
    @SerializedName("base64Encoding")
    val base64Encoding: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("provider")
    val provider: Provider? = null,
    @SerializedName("imageWebSearchUrl")
    val imageWebSearchUrl: String? = null,
    @SerializedName("webpageUrl")
    val webPageUrl: String? = null
)

data class Provider(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("favIcon")
    val favIcon: String? = null,
    @SerializedName("favIconBase64Encoding")
    val favIconBase64Encoding: String? = null
)


