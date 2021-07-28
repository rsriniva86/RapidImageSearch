package com.app.rapidimagesearch.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.rapidimagesearch.network.ImageData
import javax.inject.Singleton

@Singleton
@Database(
    entities = [ImageData::class, ImageDataIndex::class],
    exportSchema = false,
    version = 1
)
@TypeConverters(DataConverter::class)
abstract class DatabaseService : RoomDatabase() {
    abstract fun imageDao(): ImageDataDao
    abstract fun imageIndexDao():ImageDataIndexDao
}