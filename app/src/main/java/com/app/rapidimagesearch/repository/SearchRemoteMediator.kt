package com.app.rapidimagesearch.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.app.rapidimagesearch.db.DatabaseService
import com.app.rapidimagesearch.db.ImageDataIndex
import com.app.rapidimagesearch.network.Constants
import com.app.rapidimagesearch.network.ImageData
import com.app.rapidimagesearch.network.RapidImageSearchService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class SearchRemoteMediator @Inject constructor(
    private val networkService: RapidImageSearchService,
    private val databaseService: DatabaseService,
    private val input: String,

    ) : RemoteMediator<Int, ImageData>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ImageData>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    getCurrentKey(input)
                }
            }
            val pageNumber = loadKey?.pageNumber?.plus(1)
                ?: 1 //page number is 1 0r latest page//page number is 1 0r latest page

            val networkResponse = networkService.search(
                Constants.API_KEY_VALUE,
                Constants.API_HOST_VALUE,
                input,
                pageNumber,
                Constants.DEFAULT_PAGE_SIZE,
                true
            )
            val isEndOfList: Boolean = networkResponse.imageList.isEmpty()
            val imageDataList = networkResponse.imageList

            databaseService.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    databaseService.imageIndexDao().delete(input)
                    databaseService.imageDao().delete(input)
                }
            }

            if (imageDataList.isNotEmpty()) {
                databaseService.withTransaction {
                    databaseService.imageIndexDao()
                        .insertOrReplace(
                            ImageDataIndex(
                                0,
                                pageNumber,
                                Constants.DEFAULT_PAGE_SIZE,
                                input,
                                networkResponse.count
                            )
                        )

                    databaseService.imageDao().save(imageDataList)
                }
            }

            return MediatorResult.Success(endOfPaginationReached = isEndOfList)

        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }

    }

    private suspend fun getCurrentKey(input: String): ImageDataIndex? {
        return databaseService.imageIndexDao().get(input).firstOrNull()
    }


}