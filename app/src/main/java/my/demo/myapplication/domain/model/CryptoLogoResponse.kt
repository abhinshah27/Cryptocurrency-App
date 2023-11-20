package my.demo.myapplication.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Shah on 17-11-2023.
 */

data class StatusLogo(
    @SerializedName("timestamp")
    val timestamp: String,
    @SerializedName("error_code")
    val errorCode: Int,
    @SerializedName("error_message")
    val errorMessage: String?,
    @SerializedName("elapsed")
    val elapsed: Int,
    @SerializedName("credit_count")
    val creditCount: Int,
    @SerializedName("notice")
    val notice: String?
)

data class Urls(
    @SerializedName("website")
    val website: List<String>,
    @SerializedName("twitter")
    val twitter: List<String>,
    @SerializedName("message_board")
    val messageBoard: List<String>,
    @SerializedName("chat")
    val chat: List<String>,
    @SerializedName("facebook")
    val facebook: List<String>,
    @SerializedName("explorer")
    val explorer: List<String>,
    @SerializedName("reddit")
    val reddit: List<String>,
    @SerializedName("technical_doc")
    val technicalDoc: List<String>,
    @SerializedName("source_code")
    val sourceCode: List<String>,
    @SerializedName("announcement")
    val announcement: List<String>
)

data class ContractAddress(
    @SerializedName("contract_address")
    val contractAddress: String,
    @SerializedName("platform")
    val platform: PlatformLogo
)

data class PlatformLogo(
    @SerializedName("name")
    val name: String,
    @SerializedName("coin")
    val coin: Coin
)

data class Coin(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("slug")
    val slug: String
)

data class DataItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("subreddit")
    val subreddit: String,
    @SerializedName("notice")
    val notice: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("tag-names")
    val tagNames: List<String>,
    @SerializedName("tag-groups")
    val tagGroups: List<String>,
    @SerializedName("urls")
    val urls: Urls,
    @SerializedName("platform")
    val platform: Any?,
    @SerializedName("date_added")
    val dateAdded: String,
    @SerializedName("twitter_username")
    val twitterUsername: String,
    @SerializedName("is_hidden")
    val isHidden: Int,
    @SerializedName("date_launched")
    val dateLaunched: String?,
    @SerializedName("contract_address")
    val contractAddress: List<ContractAddress>,
    @SerializedName("self_reported_circulating_supply")
    val selfReportedCirculatingSupply: Any?,
    @SerializedName("self_reported_tags")
    val selfReportedTags: Any?,
    @SerializedName("self_reported_market_cap")
    val selfReportedMarketCap: Any?,
    @SerializedName("infinite_supply")
    val infiniteSupply: Boolean
)

data class CryptoLogoResponse(
    @SerializedName("status")
    val status: StatusLogo,
    @SerializedName("data")
    val data: Map<String, DataItem>
)