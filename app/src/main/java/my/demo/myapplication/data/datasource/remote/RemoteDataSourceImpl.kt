package my.demo.myapplication.data.datasource.remote

import my.demo.myapplication.R
import my.demo.myapplication.data.common.DataSourceException
import my.demo.myapplication.data.common.HandleResult
import my.demo.myapplication.data.common.RequestErrorHandler
import my.demo.myapplication.domain.model.CryptoLogoResponse
import my.demo.myapplication.domain.model.CryptocurrencyResponse

/**
 * RemoteDataSourceImpl is a class that implements the RemoteDataSource interface.
 * It is responsible for interacting with a remote data source, in this case, an API.
 */
class RemoteDataSourceImpl(private val apiServices: ApiServices) : RemoteDataSource {

    /**
     * getCryptoList is a function that fetches a list of cryptocurrencies from the remote data source.
     * @return HandleResult containing either the successful response or an error.
     */
    override suspend fun getCryptoList(): HandleResult<CryptocurrencyResponse> {
        return try {
            // Call the API service to get the list of cryptocurrencies.
            val result = apiServices.getCryptoList()
            // Check if the API call was successful (HTTP status code 2xx).
            if (result.isSuccessful) {
                result.body()?.let {
                    // If the response body is not null, return a success result with the data.
                    HandleResult.Success(it)
                } ?: run {
                    // If the response body is null, return an error with an unexpected message.
                    HandleResult.Error(
                        DataSourceException.Unexpected(R.string.error_unexpected_message),
                    )
                }
            } else {
                // If the API call was not successful, return an error with a server-related message.
                HandleResult.Error(DataSourceException.Server(R.string.error_server_unexpected_message))
            }
        } catch (e: Exception) {
            // If an exception occurs during the API call, handle and return an error.
            HandleResult.Error(RequestErrorHandler.getRequestError(e))
        }
    }


    /**
     * getCryptoLogo is a function that fetches the logo of a specific cryptocurrency using its slug.
     * @return HandleResult containing either the successful response or an error.
     */
    override suspend fun getCryptoLogo(slug: String): HandleResult<CryptoLogoResponse> {
        return try {
            // Call the API service to get the logo of the specified cryptocurrency.
            val result = apiServices.getCryptoLogo(slug)
            // Check if the API call was successful (HTTP status code 2xx).
            if (result.isSuccessful) {
                result.body()?.let {
                    // If the response body is not null, return a success result with the data.
                    HandleResult.Success(it)
                } ?: run {
                    // If the response body is null, return an error with an unexpected message.
                    HandleResult.Error(
                        DataSourceException.Unexpected(R.string.error_unexpected_message),
                    )
                }
            } else {
                // If the API call was not successful, return an error with a server-related message.
                HandleResult.Error(DataSourceException.Server(R.string.error_server_unexpected_message))
            }
        } catch (e: Exception) {
            // If an exception occurs during the API call, handle and return an error.
            HandleResult.Error(RequestErrorHandler.getRequestError(e))
        }
    }
}
