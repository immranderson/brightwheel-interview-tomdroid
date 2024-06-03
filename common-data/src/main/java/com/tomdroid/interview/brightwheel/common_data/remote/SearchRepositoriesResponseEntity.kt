

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchRepositoriesResponseEntity(
    @SerialName("items")
    val items: List<ItemEntity>,
    @SerialName("total_count")
    val totalCount: Int?
)

@Serializable
data class ItemEntity(
    @SerialName("full_name")
    val fullName: String,
    @SerialName("name")
    val name: String,
    @SerialName("owner")
    val owner: Owner,
)

@Serializable
data class Owner(
    @SerialName("id")
    val id: Int?,
    @SerialName("login")
    val login: String,
)