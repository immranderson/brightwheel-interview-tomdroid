package com.tomdroid.interview.brightwheel.common_data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContributorListResponseEntityItem(
    @SerialName("contributions")
    val contributions: Int,
    @SerialName("login")
    val login: String?,
)