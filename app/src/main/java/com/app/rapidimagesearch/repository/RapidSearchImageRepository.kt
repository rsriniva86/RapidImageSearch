package com.app.rapidimagesearch.repository

import androidx.paging.PagingData
import com.app.rapidimagesearch.network.ImageData
import kotlinx.coroutines.flow.Flow

interface RapidSearchImageRepository {
    suspend fun getData(input: String): Flow<PagingData<ImageData>>
}