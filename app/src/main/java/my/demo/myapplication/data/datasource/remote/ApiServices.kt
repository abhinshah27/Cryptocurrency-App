package my.demo.myapplication.data.datasource.remote

import my.demo.myapplication.domain.model.CryptoLogoResponse
import my.demo.myapplication.domain.model.CryptocurrencyResponse
import my.demo.myapplication.utils.EndPoints.CRYPTO_CURRENCY_LIST
import my.demo.myapplication.utils.EndPoints.CRYPTO_CURRENCY_LOGO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Define an interface for API services
 */
interface ApiServices {
    @GET(CRYPTO_CURRENCY_LIST)
    suspend fun getCryptoList(): Response<CryptocurrencyResponse>

    @GET(CRYPTO_CURRENCY_LOGO)
    suspend fun getCryptoLogo(
        @Query("slug") slug: String
    ): Response<CryptoLogoResponse>
}
