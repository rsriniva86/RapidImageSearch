package com.app.rapidimagesearch.di

import android.content.Context
import com.app.rapidimagesearch.RapidImageSearchApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): RapidImageSearchApp {
        return app as RapidImageSearchApp
    }

}