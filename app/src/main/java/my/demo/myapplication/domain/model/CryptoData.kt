package my.demo.myapplication.domain.model


import com.google.gson.annotations.SerializedName

data class CryptoData(
    @SerializedName("circulating_supply")
    val circulatingSupply: Double? = null,
    @SerializedName("cmc_rank")
    val cmcRank: Int? = null,
    @SerializedName("date_added")
    val dateAdded: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("infinite_supply")
    val infiniteSupply: Boolean? = null,
    @SerializedName("last_updated")
    val lastUpdated: String? = null,
    @SerializedName("max_supply")
    val maxSupply: Long? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("num_market_pairs")
    val numMarketPairs: Int? = null,
    @SerializedName("platform")
    val platform: Platform? = null,
    @SerializedName("quote")
    val quote: Quote? = null,
    @SerializedName("self_reported_circulating_supply")
    val selfReportedCirculatingSupply: Any? = null,
    @SerializedName("self_reported_market_cap")
    val selfReportedMarketCap: Any? = null,
    @SerializedName("slug")
    val slug: String? = null,
    @SerializedName("symbol")
    val symbol: String? = null,
    @SerializedName("tags")
    val tags: List<String>? = null,
    @SerializedName("total_supply")
    val totalSupply: Double? = null
)