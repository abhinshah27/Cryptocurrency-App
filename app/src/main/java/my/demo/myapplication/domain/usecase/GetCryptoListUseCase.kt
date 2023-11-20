package my.demo.myapplication.domain.usecase

import javax.inject.Inject
import my.demo.myapplication.data.repository.CryptoRepository

/**
 * This class represents a use case for retrieving a list of cryptocurrencies.
 */
class GetCryptoListUseCase @Inject // Dagger annotation indicating that dependencies should be provided through dependency injection.
constructor(private val cryptoRepository: CryptoRepository) {
    // This function is responsible for fetching the list of cryptocurrencies.
    fun getCrypto() = cryptoRepository.getCrypto()
}