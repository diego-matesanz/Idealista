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
import com.diego.matesanz.idealista.R
import com.diego.matesanz.idealista.data.Result
import com.diego.matesanz.idealista.databinding.FragmentProductListBinding
import com.diego.matesanz.idealista.domain.models.ProductItem
import com.diego.matesanz.idealista.ui.screens.productList.ProductListAction.ToggleFavorite
import com.diego.matesanz.idealista.ui.screens.productList.adapter.ProductItemListener
import com.diego.matesanz.idealista.ui.screens.productList.adapter.ProductListAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDateTime

class ProductListFragment : Fragment(), ProductItemListener {

    private lateinit var binding: FragmentProductListBinding

    private val viewModel: ProductListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentProductListBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectState()
    }

    private fun collectState() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is Result.Loading -> {
                        /* TODO Add loader */
                    }

                    is Result.Error -> {
                        /* TODO Add error */
                    }

                    is Result.Success -> {
                        initRecyclerView(state.data)
                    }
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
