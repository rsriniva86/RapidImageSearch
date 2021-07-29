package com.app.rapidimagesearch.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ImageDataIndexDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(imageDataIndex: ImageDataIndex)

    @Query("SELECT * FROM image_data_index WHERE `input` = :input ORDER BY id DESC")
    suspend fun get(input: String): List<ImageDataIndex>

    @Query("SELECT * FROM image_data_index")
    suspend fun selectAll(): List<ImageDataIndex>

    @Query("DELETE FROM image_data_index WHERE `input` = :input")
    suspend fun delete(input: String)

}
