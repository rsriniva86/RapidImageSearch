package com.app.rapidimagesearch.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.*
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.app.rapidimagesearch.db.DatabaseService
import com.app.rapidimagesearch.mockers.GenerateMockObjects
import com.app.rapidimagesearch.mockers.MockNetworkService
import com.app.rapidimagesearch.network.ImageData
import com.app.rapidimagesearch.network.RapidImageSearchService
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalPagingApi
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class SearchRemoteMediatorTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var database: DatabaseService
    private lateinit var networkService: RapidImageSearchService

    @Before
    fun setup() {
        networkService = MockNetworkService()
        database=GenerateMockObjects.generateMockDB(ApplicationProvider.getApplicationContext())
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() = runBlocking {
        // Add mock results for the API to return.

        val remoteMediator = SearchRemoteMediator(
            databaseService = database,
            networkService = networkService,
            input = "test"
        )
        val pagingState = PagingState<Int, ImageData>(
            listOf(),
            null,
            PagingConfig(40),
            10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue (result is RemoteMediator.MediatorResult.Success )
        assertFalse ((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun refreshLoadSuccessAndEndOfPaginationWhenNoMoreData() = runBlocking {
        // To test endOfPaginationReached, don't set up the mockApi to return post
        // data here.
        val remoteMediator = SearchRemoteMediator(
            databaseService = database,
            networkService = networkService,
            input = "NO_DATA"
        )
        val pagingState = PagingState<Int, ImageData>(
            listOf(),
            null,
            PagingConfig(40),
            10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue (result is RemoteMediator.MediatorResult.Success )
        assertTrue ( (result as RemoteMediator.MediatorResult.Success).endOfPaginationReached )
    }
}