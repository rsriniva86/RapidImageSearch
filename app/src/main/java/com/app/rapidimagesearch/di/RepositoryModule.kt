package com.app.rapidimagesearch.di

import com.app.rapidimagesearch.db.DatabaseService
import com.app.rapidimagesearch.network.RapidImageSearchService
import com.app.rapidimagesearch.repository.RapidSearchImageRepository
import com.app.rapidimagesearch.repository.RapidSearchImageRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    fun provideSearchImageRepository(
        service: RapidImageSearchService,
        dbService: DatabaseService
    ): RapidSearchImageRepository {
        return RapidSearchImageRepository_Impl(service, dbService)
    }
}