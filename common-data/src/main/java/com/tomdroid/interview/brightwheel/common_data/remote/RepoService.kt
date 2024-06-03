package com.tomdroid.interview.brightwheel.common_data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface RepoService {

    @GET("repos/{owner}/{repo}/contributors")
    suspend fun getContributorsList(@Path("owner") ownerId: String, @Path("repo") repoId: String)

}