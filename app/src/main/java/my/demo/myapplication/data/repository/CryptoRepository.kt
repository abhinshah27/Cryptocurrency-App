package my.demo.myapplication.data.repository

import kotlinx.coroutines.flow.Flow
import my.demo.myapplication.data.common.HandleResult
import my.demo.myapplication.domain.model.CryptoLogoResponse
import my.demo.myapplication.domain.model.CryptocurrencyResponse

/**
 * Interface representing a repository for cryptocurrency-related data.
 */
interface CryptoRepository {
    fun getCrypto(): Flow<HandleResult<CryptocurrencyResponse>>
    fun getCryptoLogo(slug: String): Flow<HandleResult<CryptoLogoResponse>>
}
