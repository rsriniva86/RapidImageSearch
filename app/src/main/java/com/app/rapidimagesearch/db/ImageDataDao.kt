package com.app.rapidimagesearch.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.rapidimagesearch.network.ImageData

@Dao
interface ImageDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(imageDataList: List<ImageData>)

    @Query("SELECT * FROM image_data WHERE `query` LIKE :input")
    fun select(input: String?): PagingSource<Int, ImageData>

    @Query("DELETE FROM image_data WHERE `query` = :q")
    suspend fun delete(q: String)


}