package com.app.rapidimagesearch

import androidx.paging.ExperimentalPagingApi
import com.app.rapidimagesearch.db.ImageDataDaoTest
import com.app.rapidimagesearch.db.ImageDataIndexDaoTest
import com.app.rapidimagesearch.repository.SearchRemoteMediatorTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalPagingApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    SearchRemoteMediatorTest::class,
    ImageDataDaoTest::class,
    ImageDataIndexDaoTest::class
)

class TestSuite