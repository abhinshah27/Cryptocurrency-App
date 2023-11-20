package my.demo.myapplication.data.common

import retrofit2.HttpException
import java.io.IOException
import my.demo.myapplication.R


// RequestErrorHandler is a singleton object responsible for handling errors related to HTTP requests.
object RequestErrorHandler {

    // HTTP status code ranges for client and server errors.
    private const val HTTP_CODE_CLIENT_START = 400
    private const val HTTP_CODE_CLIENT_END = 499
    private const val HTTP_CODE_SERVER_START = 500
    private const val HTTP_CODE_SERVER_END = 599

    /**
     *  Function to determine the appropriate DataSourceException based on the provided throwable.
     *  @param: throwable: The Throwable representing the error.
     *  @return DataSourceException, an exception type specific to the data source layer.
     */
    fun getRequestError(throwable: Throwable): DataSourceException {
        return when (throwable) {
            is HttpException -> {
                // If the throwable is an HttpException, delegate to handleHttpException function.
                handleHttpException(throwable)
            }
            is IOException -> {
                // If the throwable is an IOException, create a DataSourceException for connection issues.
                DataSourceException.Connection(R.string.error_server_unexpected_message)
            }
            else -> {
                // For other types of throwable, create a generic DataSourceException for unexpected errors.
                DataSourceException.Unexpected(R.string.error_unexpected_message)
            }
        }
    }

    /**
     *  Function to handle HttpException and determine the appropriate DataSourceException.
     *  @param: throwable: The HttpException representing the HTTP error.
     *  @return DataSourceException, an exception type specific to the data source layer.
     */
    private fun handleHttpException(httpException: HttpException): DataSourceException {
        return when (httpException.code()) {
            // If the HTTP status code falls within the client error range, create a Client DataSourceException.
            in HTTP_CODE_CLIENT_START..HTTP_CODE_CLIENT_END -> {
                DataSourceException.Client(R.string.error_client_unexpected_message)
            }
            // If the HTTP status code falls within the server error range, create a Server DataSourceException.
            in HTTP_CODE_SERVER_START..HTTP_CODE_SERVER_END -> {
                DataSourceException.Server(R.string.error_server_unexpected_message)
            }
            // For any other HTTP status code, create a generic DataSourceException for unexpected errors.
            else -> {
                DataSourceException.Unexpected(R.string.error_unexpected_message)
            }
        }
    }
}
