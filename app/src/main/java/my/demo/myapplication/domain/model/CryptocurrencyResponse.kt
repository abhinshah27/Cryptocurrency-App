package my.demo.myapplication.domain.model


import com.google.gson.annotations.SerializedName

data class CryptocurrencyResponse(
    @SerializedName("data")
    val cryptoData: List<CryptoData>? = null,
    @SerializedName("status")
    val status: Status? = null
)