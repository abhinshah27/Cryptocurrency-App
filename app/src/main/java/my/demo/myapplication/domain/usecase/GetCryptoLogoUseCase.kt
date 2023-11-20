package my.demo.myapplication.domain.usecase

import javax.inject.Inject
import my.demo.myapplication.data.repository.CryptoRepository

/**
 * This class represents a use case for getting cryptocurrency logos.
 */
class GetCryptoLogoUseCase @Inject constructor(private val cryptoRepository: CryptoRepository) {
    fun getCryptoLogo(slug: String) = cryptoRepository.getCryptoLogo(slug)
}