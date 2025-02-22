package com.diego.matesanz.idealista.ui.screens.productList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.diego.matesanz.idealista.App
import com.diego.matesanz.idealista.R
import com.diego.matesanz.idealista.data.repositories.ProductRepository
import com.diego.matesanz.idealista.databinding.FragmentProductListBinding
import com.diego.matesanz.idealista.domain.models.ProductItem
import com.diego.matesanz.idealista.framework.database.datasource.ProductRoomDataSource
import com.diego.matesanz.idealista.framework.remote.ProductsClient
import com.diego.matesanz.idealista.framework.remote.datasource.ProductsServerDataSource
import com.diego.matesanz.idealista.ui.screens.productList.ProductListAction.ToggleFavorite
import com.diego.matesanz.idealista.ui.screens.productList.adapter.ProductItemListener
import com.diego.matesanz.idealista.ui.screens.productList.adapter.ProductListAdapter
import com.diego.matesanz.idealista.usecases.productList.GetProductsUseCase
import com.diego.matesanz.idealista.usecases.productList.ToggleProductFavoriteUseCase
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class ProductListFragment : Fragment(), ProductItemListener {

    private lateinit var binding: FragmentProductListBinding
    private lateinit var viewModel: ProductListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentProductListBinding.inflate(layoutInflater)

        val app = requireContext().applicationContext as App
        val productsClient = ProductsClient.instance
        val productsServerDataSource = ProductsServerDataSource(productsClient)
        val productRoomDataSource = ProductRoomDataSource(app.database.productItemDao())
        val productRepository = ProductRepository(
            productsServerDataSource,
            productRoomDataSource,
        )
        val getProductsUseCase = GetProductsUseCase(productRepository)
        val toggleProductFavoriteUseCase =
            ToggleProductFavoriteUseCase(productRepository)

        viewModel = ProductListViewModel(
            getProductsUseCase,
            toggleProductFavoriteUseCase,
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectState()
    }

    private fun collectState() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                if (state.isLoading) {
                    //binding.progressBar.visibility = View.VISIBLE
                } else if (state.products.isNotEmpty()) {
                    //binding.progressBar.visibility = View.GONE
                    initRecyclerView(state.products)
                }
            }
        }
    }

    private fun initRecyclerView(products: List<ProductItem>) {
        binding.recyclerView.adapter = ProductListAdapter(
            products = products,
            listener = this,
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun showToast(isSaved: Boolean) {
        val date = LocalDateTime.now().toLocalDate()
        val toastMessage = if (isSaved) {
            requireContext().getString(R.string.property_saved_message, date)
        } else {
            requireContext().getString(R.string.property_removed_message, date)
        }
        Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_LONG).show()
    }

    override fun onProductClick(propertyCode: String) {
        val action =
            ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment()
                .setPropertyCode(propertyCode)
        findNavController().navigate(action)
    }

    override fun onFavoriteClick(product: ProductItem) {
        viewModel.onAction(ToggleFavorite(product))
        showToast(!product.isSaved)
    }
}
