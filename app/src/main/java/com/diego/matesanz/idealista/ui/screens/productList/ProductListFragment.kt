package com.diego.matesanz.idealista.ui.screens.productList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.diego.matesanz.idealista.data.repositories.ProductRepository
import com.diego.matesanz.idealista.databinding.FragmentProductListBinding
import com.diego.matesanz.idealista.domain.models.ProductItem
import com.diego.matesanz.idealista.usecases.GetProductsUseCase
import com.diego.matesanz.idealista.framework.remote.ProductsClient
import com.diego.matesanz.idealista.framework.remote.datasource.ProductsServerDataSource
import kotlinx.coroutines.launch

class ProductListFragment : Fragment() {

    private lateinit var binding: FragmentProductListBinding
    private lateinit var viewModel: ProductListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentProductListBinding.inflate(layoutInflater)
        viewModel = ProductListViewModel(
            GetProductsUseCase(
                ProductRepository(
                    ProductsServerDataSource(ProductsClient.instance)
                )
            )
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
        binding.recyclerView.adapter = ProductListAdapter(products)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}
