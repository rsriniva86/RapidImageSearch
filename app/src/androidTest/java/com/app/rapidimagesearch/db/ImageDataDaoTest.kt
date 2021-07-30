package com.app.rapidimagesearch.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.app.rapidimagesearch.mockers.GenerateMockObjects
import com.app.rapidimagesearch.mockers.MockNetworkService
import com.app.rapidimagesearch.network.RapidImageSearchService
import kotlinx.coroutines.runBlocking
import org.junit.*

@SmallTest
class ImageDataDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var database: DatabaseService
    private lateinit var networkService: RapidImageSearchService
    private lateinit var imageDataDao: ImageDataDao


    @Before
    fun setup() {
        networkService = MockNetworkService()
        database= GenerateMockObjects.generateMockDB(ApplicationProvider.getApplicationContext())
        imageDataDao = database.imageDao()
    }

    @After
    fun tearDown() {
        database.clearAllTables()
        database.close()
    }

    @Test
    fun test_selectData_Empty() = runBlocking {
        Assert.assertEquals(0, imageDataDao.selectData("Test").size)
    }

    @Test
    fun test_selectData_Success() = runBlocking {
        val list = GenerateMockObjects.generateImageDataList(10,"Test")
        imageDataDao.save(list)
        Assert.assertNotNull(imageDataDao.selectData("Test"))
        Assert.assertEquals(list.size, imageDataDao.selectData("Test").size)
    }
    @Test
    fun test_delete() = runBlocking {
        val list = GenerateMockObjects.generateImageDataList(10,"Test")
        imageDataDao.save(list)
        Assert.assertNotNull(imageDataDao.selectData("Test"))
        Assert.assertEquals(list.size, imageDataDao.selectData("Test").size)
        imageDataDao.delete("Test")
        Assert.assertEquals(0, imageDataDao.selectData("Test").size)
    }
}