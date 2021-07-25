package com.app.rapidimagesearch.db

import androidx.room.TypeConverter
import com.app.rapidimagesearch.network.Provider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object DataConverter {

    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun convertJsonStringToProvider(data: String?): Provider? {
        val itemType = object : TypeToken<Provider>() {}.type
        return gson.fromJson(data, itemType)
    }

    @TypeConverter
    @JvmStatic
    fun convertProviderToJsonString(provider: Provider): String? {
        val jsonString = gson.toJson(provider)
        return jsonString
    }

}