package my.demo.myapplication.data.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import my.demo.myapplication.R

/**
 * Extension function for a Flow of [HandleResult] instances.
 *
 * This extension function is used to handle the start of the flow by emitting a [HandleResult.Loading]
 * element when the flow starts. It also catches any throwable exceptions that occur during the flow
 * and emits a corresponding [HandleResult.Error] with a predefined error message.
 *
 * @return A Flow with added handling for flow start, emitting Loading, and catching errors.
 */
fun <T> Flow<HandleResult<T>>.onFlowStarts() =
    onStart { emit(HandleResult.Loading) }.catch { e: Throwable ->
        // Print the stack trace of the exception for debugging purposes.
        e.printStackTrace()

        // Emit a HandleResult.Error with an unexpected data source exception containing a
        // predefined error message resource ID.
        emit(
            HandleResult.Error(
                DataSourceException
                    .Unexpected(R.string.error_client_unexpected_message),
            ),
        )
    }
