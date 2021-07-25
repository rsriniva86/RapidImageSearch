package com.app.rapidimagesearch.ui.imagesearch

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.app.rapidimagesearch.network.RapidImageSearchService

class RapidImageSearchViewModel
@ViewModelInject
constructor(
    private val networkService: RapidImageSearchService
) : ViewModel() {

    suspend fun search(query: String, autoCorrect: Boolean) {

    }
}
