package my.demo.myapplication.presentation.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import my.demo.myapplication.domain.model.CryptoModel
import my.demo.myapplication.data.common.HandleResult
import my.demo.myapplication.domain.model.CryptoData
import my.demo.myapplication.domain.model.CryptoLogoResponse
import my.demo.myapplication.domain.model.CryptocurrencyResponse
import my.demo.myapplication.domain.usecase.GetCryptoListUseCase
import my.demo.myapplication.domain.usecase.GetCryptoLogoUseCase
import my.demo.myapplication.utils.Constants
/**
 * ViewModel class responsible for managing data related to cryptocurrencies in the home screen.
 * This class integrates with UseCases to retrieve crypto list and logo data, processes the data,
 * and provides functions for sorting and filtering crypto information.
 *
 * @param cryptoListUseCase UseCase for retrieving the list of cryptocurrencies.
 * @param cryptoLogoUseCase UseCase for retrieving logos of cryptocurrencies.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private var cryptoListUseCase: GetCryptoListUseCase,
    private var cryptoLogoUseCase: GetCryptoLogoUseCase
) : ViewModel() {

    private var slugs = ""

    // List to store CryptoData objects representing cryptocurrencies
    var cryptoList = mutableListOf<CryptoData>()

    // Channels for handling results of crypto list and crypto logo requests
    private val _resultCryptoList = Channel<HandleResult<CryptocurrencyResponse>>(Channel.BUFFERED)
    val resultCryptoList: Flow<HandleResult<CryptocurrencyResponse>> = _resultCryptoList.receiveAsFlow()

    private val _resultCryptoLogo = Channel<HandleResult<CryptoLogoResponse>>(Channel.BUFFERED)
    val resultCryptoLogo: Flow<HandleResult<CryptoLogoResponse>> = _resultCryptoLogo.receiveAsFlow()

    // Coroutine function to fetch and collect crypto list data
    fun getCryptoList() = viewModelScope.launch {
        cryptoListUseCase.getCrypto().collect { _resultCryptoList.send(it) }
    }

    // Coroutine function to fetch and collect crypto logo data based on slug
    private fun getCryptoLogo(slug: String) = viewModelScope.launch {
        cryptoLogoUseCase.getCryptoLogo(slug).collect { _resultCryptoLogo.send(it) }
    }

    // List to store CryptoModel objects representing cryptocurrencies with logos
    var cryptoModelList = mutableListOf<CryptoModel>()

    // Function to set data and initiate the retrieval of crypto logos
    fun setData(cryptoData: List<CryptoData>?) {
        val slugList = mutableListOf<String>()
        cryptoData?.forEach {
            it.slug?.let { it1 -> slugList.add(it1) }
        }
        slugs = slugList.joinToString(",")
        cryptoData?.toMutableList()?.let {
            cryptoList = it
        }
        getCryptoLogo(slugs)
    }

    // Function to return a sorted list of CryptoModel objects by market cap
    fun sortedCryptoByMarketCap(): List<CryptoModel> {
        return cryptoModelList.sortedBy { it.marketCap }
    }

    // Function to retrieve CryptoModel object for Bitcoin from the list
    fun getBitcoinData(): CryptoModel? {
        return cryptoModelList.firstOrNull { it.slug == Constants.BITCOIN }
    }

    /**
     * Function to filter and return a sorted list of CryptoModel objects based on specified criteria
     * @param isSortByPrice: Flag to determine whether to sort by price or volume
     */
    fun filter(isSortByPrice:Boolean): List<CryptoModel> {
        return cryptoModelList.sortedBy { if (isSortByPrice) it.price else it.volume24h }
    }
}