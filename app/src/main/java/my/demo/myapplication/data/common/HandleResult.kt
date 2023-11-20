package my.demo.myapplication.data.common


// Define a sealed class HandleResult with an out type parameter T.
sealed class HandleResult<out T> {

    // Data class representing a successful result with a response of type T.
    data class Success<out T>(val response: T) : HandleResult<T>()

    // Data class representing an error result with an exception of type DataSourceException.
    data class Error(val exception: DataSourceException) : HandleResult<Nothing>()

    // Object representing a loading state.
    data object Loading : HandleResult<Nothing>()
}

// Extension function on HandleResult class to execute an action if the result is a Success.
inline fun <T : Any> HandleResult<T>.onSuccess(action: (T) -> Unit): HandleResult<T> {
    // If the result is a Success, execute the provided action with the response.
    if (this is HandleResult.Success) action(response)

    // Return the modified HandleResult.
    return this
}

// Extension function on HandleResult class to execute an action if the result is an Error.
inline fun <T : Any> HandleResult<T>.onError(action: (DataSourceException) -> Unit): HandleResult<T> {
    // If the result is an Error, execute the provided action with the exception.
    if (this is HandleResult.Error) action(exception)

    // Return the modified HandleResult.
    return this
}

// Extension function on HandleResult class to execute an action if the result is Loading.
inline fun <T : Any> HandleResult<T>.onLoading(action: () -> Unit): HandleResult<T> {
    // If the result is Loading, execute the provided action.
    if (this is HandleResult.Loading) action()

    // Return the modified HandleResult.
    return this
}