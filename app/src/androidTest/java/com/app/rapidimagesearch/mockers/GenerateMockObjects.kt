package com.app.rapidimagesearch.mockers

import android.content.Context
import androidx.room.Room
import com.app.rapidimagesearch.db.DatabaseService
import com.app.rapidimagesearch.network.ImageData
import com.app.rapidimagesearch.network.Provider
import com.app.rapidimagesearch.network.RapidImageSearchResponse
import java.util.concurrent.Executors

object GenerateMockObjects {
    fun generateServerResponse(input:String): RapidImageSearchResponse =
            RapidImageSearchResponse(
                    type = "imageDataType",
                    count = 100,
                    imageList = generateImageDataList(
                        numberOfObjects = 100,
                        input = input)

            )

    fun generateImageDataList(numberOfObjects: Int,input: String): List<ImageData> {
        if(input==null || input == "NO_DATA"){
            return emptyList()
        }
        val list: MutableList<ImageData> = mutableListOf()
        for (index in 0..numberOfObjects) {
            list.add(generateImageData(index))
        }
        return list
    }

    private fun generateImageData(objectID: Int): ImageData =
            ImageData(
                    url = "http://url${objectID}",
                    height = 200,
                    width = 200,
                    base64Encoding = "",
                    thumbnail = "",
                    thumbnailHeight = 50,
                    thumbnailWidth = 50,
                    name = "ImageDataName$objectID",
                    title = "ImageDataTitle$objectID",
                    imageWebSearchUrl = "",
                    webPageUrl = "",
                    provider = generateProvider()
            )


    private fun generateProvider(): Provider = Provider(
            name = "",
            favIcon = "",
            favIconBase64Encoding = ""
    )

     fun generateMockDB(context: Context)=
        Room.inMemoryDatabaseBuilder(
            context, DatabaseService::class.java
        ).allowMainThreadQueries()
            .setTransactionExecutor(Executors.newSingleThreadExecutor())
            .build()


}