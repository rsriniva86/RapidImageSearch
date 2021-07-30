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
class ImageDataIndexDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var database: DatabaseService
    private lateinit var networkService: RapidImageSearchService
    private lateinit var imageDataIndexDao: ImageDataIndexDao


    @Before
    fun setup() {
        networkService = MockNetworkService()
        database= GenerateMockObjects.generateMockDB(ApplicationProvider.getApplicationContext())
        imageDataIndexDao = database.imageIndexDao()
    }

    @After
    fun tearDown() {
        database.clearAllTables()
        database.close()
    }

    @Test
    fun test_selectData_Empty() = runBlocking {
        Assert.assertEquals(0, imageDataIndexDao.get("Test").size)
    }

    @Test
    fun test_selectData_Success() = runBlocking {
        val list = GenerateMockObjects.generateImageDataIndex("Test")
        imageDataIndexDao.insertOrReplace(list)
        Assert.assertNotNull(imageDataIndexDao.selectAll())
        Assert.assertEquals(1, imageDataIndexDao.selectAll().size)
    }
    @Test
    fun test_delete() = runBlocking {
        val list = GenerateMockObjects.generateImageDataIndex("Test")
        imageDataIndexDao.insertOrReplace(list)
        Assert.assertNotNull(imageDataIndexDao.selectAll())
        Assert.assertEquals(1, imageDataIndexDao.selectAll().size)
        imageDataIndexDao.delete("Test")
        Assert.assertEquals(0, imageDataIndexDao.selectAll().size)
    }
}