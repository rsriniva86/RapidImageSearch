package com.app.rapidimagesearch.mockers

import com.app.rapidimagesearch.network.RapidImageSearchResponse
import com.app.rapidimagesearch.network.RapidImageSearchService

class MockNetworkService : RapidImageSearchService{

    override suspend fun search(apiKey: String, host: String, input: String, pageNumber: Int, pageSize: Int, autoCorrect: Boolean): RapidImageSearchResponse {
        return GenerateMockObjects.generateServerResponse(input)
    }

}