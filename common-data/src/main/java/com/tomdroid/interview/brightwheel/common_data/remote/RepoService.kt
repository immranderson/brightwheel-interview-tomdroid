package com.tomdroid.interview.brightwheel.common_data.remote

import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Sourced from https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28#list-repository-contributors
 */
interface RepoService {

    @GET("repos/{owner}/{repo}/contributors")
    suspend fun getContributorsList(
        @Path("owner") ownerId: String,
        @Path("repo") repoId: String
    ) : List<ContributorListResponseEntityItem>

}