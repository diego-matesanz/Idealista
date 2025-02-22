package com.diego.matesanz.idealista.ui.screens.productDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.diego.matesanz.idealista.App
import com.diego.matesanz.idealista.data.repositories.ProductRepository
import com.diego.matesanz.idealista.framework.database.datasource.ProductRoomDataSource
import com.diego.matesanz.idealista.framework.remote.ProductsClient
import com.diego.matesanz.idealista.framework.remote.datasource.ProductsServerDataSource
import com.diego.matesanz.idealista.usecases.productDetail.GetProductDetailUseCase
import com.diego.matesanz.idealista.usecases.productList.GetProductsUseCase
import com.diego.matesanz.idealista.usecases.productList.ToggleProductFavoriteUseCase

class ProductDetailFragment : Fragment() {

    private lateinit var viewModel: ProductDetailViewModel

    private val args: ProductDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val app = requireContext().applicationContext as App
        val productsClient = ProductsClient.instance
        val productsServerDataSource = ProductsServerDataSource(productsClient)
        val productRoomDataSource = ProductRoomDataSource(app.database.productItemDao())
        val productRepository = ProductRepository(
            productsServerDataSource,
            productRoomDataSource,
        )
        val getProductDetailUseCase = GetProductDetailUseCase(productRepository)

        viewModel = ProductDetailViewModel(
            propertyCode = args.propertyCode,
            getProductDetailUseCase = getProductDetailUseCase
        )

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ProductDetailScreen(viewModel = viewModel)
            }
        }
    }
}
