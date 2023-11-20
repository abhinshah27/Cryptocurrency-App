package my.demo.myapplication.data.repositoryImpl

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import my.demo.myapplication.R
import my.demo.myapplication.data.common.DataSourceException
import my.demo.myapplication.data.common.HandleResult
import my.demo.myapplication.data.common.onFlowStarts
import my.demo.myapplication.data.datasource.remote.RemoteDataSource
import my.demo.myapplication.data.repository.CryptoRepository
import my.demo.myapplication.domain.model.CryptoLogoResponse
import my.demo.myapplication.domain.model.CryptocurrencyResponse
import my.demo.myapplication.utils.isInternetAvailable


/**
 * Implementation of the [CryptoRepository] interface that retrieves cryptocurrency data
 * from a remote data source using the provided [RemoteDataSource]. Requires a [Context]
 * for checking internet availability.
 *
 * @param remoteDataSource The data source for fetching cryptocurrency information remotely.
 * @param context The Android application context for checking internet availability.
 */
class CryptoRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val context: Context,
) : CryptoRepository {

    /**
     * Fetches a list of cryptocurrencies asynchronously as a [Flow] of [HandleResult].
     *
     * @return A [Flow] emitting [HandleResult] containing either a [CryptocurrencyResponse]
     * on success or an [Exception] on error.
     */
    override fun getCrypto(): Flow<HandleResult<CryptocurrencyResponse>> {
        return flow {
            if (isInternetAvailable(context)) {
                remoteDataSource.getCryptoList().run {
                    when (this) {
                        is HandleResult.Success -> {
                            emit(HandleResult.Success(response))
                        }

                        is HandleResult.Error -> {
                            emit(HandleResult.Error(exception))
                        }

                        else -> {
                            // No implementation needed
                        }
                    }
                }
            } else {
                emit(HandleResult.Error(DataSourceException.Server(R.string.no_internet_available)))
            }
        }.flowOn(Dispatchers.IO).onFlowStarts()
    }

    /**
     * Fetches the logo of a specific cryptocurrency asynchronously as a [Flow] of [HandleResult].
     *
     * @param slug The unique identifier of the cryptocurrency.
     * @return A [Flow] emitting [HandleResult] containing either a [CryptoLogoResponse]
     * on success or an [Exception] on error.
     */
    override fun getCryptoLogo(slug: String): Flow<HandleResult<CryptoLogoResponse>> {
        return flow {
            if (isInternetAvailable(context)) {
                remoteDataSource.getCryptoLogo(slug = slug).run {
                    when (this) {
                        is HandleResult.Success -> {
                            emit(HandleResult.Success(response))
                        }

                        is HandleResult.Error -> {
                            emit(HandleResult.Error(exception))
                        }

                        else -> {
                            // No implementation needed
                        }
                    }
                }
            } else {
                emit(HandleResult.Error(DataSourceException.Server(R.string.no_internet_available)))
            }
        }.flowOn(Dispatchers.IO).onFlowStarts()
    }
}

