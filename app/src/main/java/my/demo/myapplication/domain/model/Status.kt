package my.demo.myapplication.domain.model


import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("credit_count")
    val creditCount: Int? = null,
    @SerializedName("elapsed")
    val elapsed: Int? = null,
    @SerializedName("error_code")
    val errorCode: Int? = null,
    @SerializedName("error_message")
    val errorMessage: String? = null,
    @SerializedName("timestamp")
    val timestamp: String? = null
)