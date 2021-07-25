package com.app.rapidimagesearch.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.rapidimagesearch.network.ImageData

@Dao
interface ImageDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllImageData(imageDataList: List<ImageData>)

    @Query("SELECT * FROM image_data WHERE `query` LIKE :q")
    fun selectAll(q: String?): List<ImageData>

    @Query("DELETE FROM image_data WHERE `query` = :q")
    suspend fun deleteImageDataByQuery(q: String)

    @Query("SELECT * FROM image_data")
    fun selectAllData(): List<ImageData>

}