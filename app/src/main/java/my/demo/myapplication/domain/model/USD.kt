package my.demo.myapplication.domain.model


import com.google.gson.annotations.SerializedName

data class USD(
    @SerializedName("fully_diluted_market_cap")
    val fullyDilutedMarketCap: Double? = null,
    @SerializedName("last_updated")
    val lastUpdated: String? = null,
    @SerializedName("market_cap")
    val marketCap: Double? = null,
    @SerializedName("market_cap_dominance")
    val marketCapDominance: Double? = null,
    @SerializedName("percent_change_1h")
    val percentChange1h: Double? = null,
    @SerializedName("percent_change_24h")
    val percentChange24h: Double? = null,
    @SerializedName("percent_change_7d")
    val percentChange7d: Double? = null,
    @SerializedName("price")
    val price: Double? = null,
    @SerializedName("volume_24h")
    val volume24h: Double? = null,
    @SerializedName("volume_change_24h")
    val volumeChange24h: Double? = null
)