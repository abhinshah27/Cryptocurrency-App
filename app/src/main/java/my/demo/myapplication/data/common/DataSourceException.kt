package my.demo.myapplication.data.common

/**
 * Custom exception class representing errors related to data sources.
 *
 * @param messageResource Resource identifier for the exception message.
 */
sealed class DataSourceException(val messageResource: Int?) : RuntimeException() {
    class Connection(messageResource: Int) : DataSourceException(messageResource)
    class Unexpected(messageResource: Int) : DataSourceException(messageResource)
    class Client(messageResource: Int) : DataSourceException(messageResource)
    class Server(messageResource: Int?) : DataSourceException(messageResource)
}
