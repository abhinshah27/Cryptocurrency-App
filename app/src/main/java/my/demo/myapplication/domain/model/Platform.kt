package my.demo.myapplication.domain.model


import com.google.gson.annotations.SerializedName

data class Platform(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("slug")
    val slug: String? = null,
    @SerializedName("symbol")
    val symbol: String? = null,
    @SerializedName("token_address")
    val tokenAddress: String? = null
)