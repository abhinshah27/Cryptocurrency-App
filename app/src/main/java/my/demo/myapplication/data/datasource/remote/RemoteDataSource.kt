package my.demo.myapplication.data.datasource.remote

import my.demo.myapplication.data.common.HandleResult
import my.demo.myapplication.domain.model.CryptoLogoResponse
import my.demo.myapplication.domain.model.CryptocurrencyResponse

/**
 * Interface for accessing remote data related to cryptocurrencies.
 */
interface RemoteDataSource {
    suspend fun getCryptoList(): HandleResult<CryptocurrencyResponse>
    suspend fun getCryptoLogo(slug: String): HandleResult<CryptoLogoResponse>
}
