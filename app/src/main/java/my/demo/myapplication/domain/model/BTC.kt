package my.demo.myapplication.domain.model


import com.google.gson.annotations.SerializedName

data class BTC(
    @SerializedName("fully_diluted_market_cap")
    val fullyDilutedMarketCap: Double? = null,
    @SerializedName("last_updated")
    val lastUpdated: String? = null,
    @SerializedName("market_cap")
    val marketCap: Int? = null,
    @SerializedName("market_cap_dominance")
    val marketCapDominance: Double? = null,
    @SerializedName("percent_change_1h")
    val percentChange1h: Int? = null,
    @SerializedName("percent_change_24h")
    val percentChange24h: Int? = null,
    @SerializedName("percent_change_7d")
    val percentChange7d: Int? = null,
    @SerializedName("price")
    val price: Int? = null,
    @SerializedName("volume_24h")
    val volume24h: Double? = null,
    @SerializedName("volume_change_24h")
    val volumeChange24h: Int? = null
)