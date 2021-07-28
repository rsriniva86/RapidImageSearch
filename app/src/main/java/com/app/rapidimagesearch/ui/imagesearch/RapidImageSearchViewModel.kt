package com.app.rapidimagesearch.ui.imagesearch

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.rapidimagesearch.network.ImageData
import com.app.rapidimagesearch.repository.RapidSearchImageRepository
import kotlinx.coroutines.flow.Flow

class RapidImageSearchViewModel
@ViewModelInject
constructor(
    private val repositoryRapid: RapidSearchImageRepository
) : ViewModel() {

    suspend fun getData(input: String): Flow<PagingData<ImageData>> {
        return repositoryRapid.getData(input = input).cachedIn(viewModelScope)
    }
}
