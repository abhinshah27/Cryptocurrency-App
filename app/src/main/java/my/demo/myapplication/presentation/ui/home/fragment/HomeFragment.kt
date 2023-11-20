package my.demo.myapplication.presentation.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import my.demo.myapplication.R
import my.demo.myapplication.domain.model.CryptoModel
import my.demo.myapplication.data.common.onError
import my.demo.myapplication.data.common.onLoading
import my.demo.myapplication.data.common.onSuccess
import my.demo.myapplication.domain.model.CryptoData
import my.demo.myapplication.domain.model.DataItem
import my.demo.myapplication.databinding.FragmentHomeBinding
import my.demo.myapplication.presentation.ui.home.CryptoAdapter
import my.demo.myapplication.presentation.ui.home.viewmodel.HomeViewModel
import my.demo.myapplication.utils.hide
import my.demo.myapplication.utils.show
import my.demo.myapplication.utils.showToast


/**
 * HomeFragment class represents a fragment for displaying cryptocurrency data.
 */
class HomeFragment : Fragment() {

    // Adapter for handling cryptocurrency data in RecyclerView.
    private var adapter: CryptoAdapter? = null

    // ViewModel for managing data related to the home screen.
    private val homeViewModel: HomeViewModel by activityViewModels()

    // View binding for the fragment.
    private lateinit var mBinding: FragmentHomeBinding

    // Inflates the fragment layout and returns the root view.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    // Called after the view has been created. Handles click listeners and initializes data.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        clickListener()
        init()
    }

    // Sets up a click listener for a filter button.
    private fun clickListener() {
        mBinding.cardFilter.setOnClickListener {
            showPopup(it)
        }
    }

    // Initializes the fragment by observing LiveData and fetching cryptocurrency data.
    private fun init() {
        // Observes the result of fetching the list of cryptocurrencies.
        lifecycleScope.launch {
            homeViewModel.resultCryptoList.collect { handleResult ->
                handleResult.onLoading {
                    mBinding.rvCrypto.hide()
                    mBinding.progressBar.show()
                }.onSuccess {
                    homeViewModel.setData(it.cryptoData)
                }.onError {
                    mBinding.progressBar.hide()
                    it.messageResource?.let { it1 ->
                        showToast(
                            getString(it1),
                            requireContext(),
                        )
                    }
                }
            }
        }
        homeViewModel.getCryptoList()

        // Observes the result of fetching cryptocurrency logos.
        lifecycleScope.launch {
            homeViewModel.resultCryptoLogo.collect { handleResult ->
                handleResult.onLoading {
                }.onSuccess {
                    // Processes the data to create a list of CryptoModel objects.
                    val dataItemList: List<DataItem> = it.data.values.toList()
                    homeViewModel.cryptoModelList.clear()
                    dataItemList.forEach { dataItem ->
                        val cryptoData = getCryptoDataBySlug(dataItem.slug)
                        val percentChange24h = cryptoData?.quote?.uSD?.percentChange24h ?: 0.0
                        homeViewModel.cryptoModelList.add(
                            CryptoModel(
                                name = cryptoData?.name ?: "",
                                symbol = cryptoData?.symbol ?: "",
                                slug = cryptoData?.slug ?: "",
                                marketCap = cryptoData?.quote?.uSD?.marketCap ?: 0.0,
                                percentChange24h = percentChange24h,
                                volume24h = cryptoData?.quote?.uSD?.volume24h ?: 0.0,
                                price = cryptoData?.quote?.uSD?.price ?: 0.0,
                                isPositive = percentChange24h > 0,
                                logo = dataItem.logo
                            )
                        )
                    }
                    setRecyclerview()

                }.onError {
                    mBinding.progressBar.hide()
                    it.messageResource?.let { it1 ->
                        showToast(
                            getString(it1),
                            requireContext(),
                        )
                    }
                }
            }
        }
    }

    // Sets up the RecyclerView after processing cryptocurrency data.
    private fun setRecyclerview() {
        mBinding.apply {
            progressBar.hide()
            rvCrypto.show()
            model = homeViewModel.getBitcoinData()
            executePendingBindings()
            adapter = CryptoAdapter()
            rvCrypto.adapter = adapter
            rvCrypto.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            adapter?.submitList(homeViewModel.sortedCryptoByMarketCap())
        }
    }

    // Retrieves cryptocurrency data based on its slug.
    private fun getCryptoDataBySlug(slug: String): CryptoData? {
        return homeViewModel.cryptoList.find { it.slug == slug }
    }

    // Displays a popup menu for filtering cryptocurrency data.
    private fun showPopup(v: View) {
        val popup = PopupMenu(requireActivity(), v)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.filter_option, popup.menu)
        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_action_price -> {
                    adapter?.submitList(homeViewModel.filter(isSortByPrice = true))
                }
                R.id.menu_action_volume_24h -> {
                    adapter?.submitList(homeViewModel.filter(isSortByPrice = false))
                }
            }
            true
        }
        popup.show()
    }
}
