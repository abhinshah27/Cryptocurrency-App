package my.demo.myapplication.domain.model


import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("BTC")
    val bTC: BTC? = null,
    @SerializedName("ETH")
    val eTH: ETH? = null,
    @SerializedName("USD")
    val uSD: USD? = null
)