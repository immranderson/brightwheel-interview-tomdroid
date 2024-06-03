package com.tomdroid.interview.brightwheel.common_data.remote

import SearchRepositoriesResponseEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET ("search/repositories")
    suspend fun searchRepositories(@Query("q") query: String): SearchRepositoriesResponseEntity

}