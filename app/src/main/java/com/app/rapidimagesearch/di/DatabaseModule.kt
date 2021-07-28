package com.app.rapidimagesearch.di

import android.content.Context
import androidx.room.Room
import com.app.rapidimagesearch.db.DatabaseService
import com.app.rapidimagesearch.db.ImageDataDao
import com.app.rapidimagesearch.db.ImageDataIndexDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideImageDataIndexDao(databaseService: DatabaseService): ImageDataIndexDao {
        return databaseService.imageIndexDao()
    }

    @Singleton
    @Provides
    fun provideImageDao(databaseService: DatabaseService): ImageDataDao {
        return databaseService.imageDao()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): DatabaseService {
        return Room.databaseBuilder(
            appContext,
            DatabaseService::class.java,
            "RapidImageSearch"
        ).build()
    }
}