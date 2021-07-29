package com.app.rapidimagesearch.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.rapidimagesearch.db.DatabaseService
import com.app.rapidimagesearch.network.Constants
import com.app.rapidimagesearch.network.ImageData
import com.app.rapidimagesearch.network.RapidImageSearchService

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RapidSearchImageRepository_Impl
@Inject
constructor(
    private val networkService: RapidImageSearchService,
    private val databaseService: DatabaseService,
) : RapidSearchImageRepository {

    override suspend fun getData(
        input: String
    ): Flow<PagingData<ImageData>> {
        return Pager(
            PagingConfig(pageSize = Constants.DEFAULT_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = SearchRemoteMediator(
                networkService = networkService,
                databaseService = databaseService,
                input = input
            ),
            pagingSourceFactory = {
                databaseService.imageDao().select(input)
            }
        ).flow
    }
}