package com.tomdroid.interview.brightwheel.common_data.remote

import SearchRepositoriesResponseEntity
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Sourced from https://docs.github.com/en/rest/search/search?apiVersion=2022-11-28#search-repositories
 */
interface SearchService {

    @GET ("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("order") order: String = "desc",
        @Query("per_page") perPage: Int = 100,
        @Query("page") page: Int = 0
    ): SearchRepositoriesResponseEntity

}