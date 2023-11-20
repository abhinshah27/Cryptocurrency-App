package my.demo.myapplication.utils

/**
 * This object defines various constants used in the application.
 */
object Constants {
    const val CALL_TIMEOUT = 30L
    const val CONNECT_TIMEOUT = 30L
    const val READ_TIMEOUT = 30L
    const val WRITE_TIMEOUT = 30L
    const val BITCOIN = "bitcoin"
}
/**
 * This object defines various API endpoints used in the application.
 */
object EndPoints {
    const val CRYPTO_CURRENCY_LIST = "v1/cryptocurrency/listings/latest?limit=20"
    const val CRYPTO_CURRENCY_LOGO = "v2/cryptocurrency/info"
}
