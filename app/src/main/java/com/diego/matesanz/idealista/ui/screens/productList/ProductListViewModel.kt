package com.diego.matesanz.idealista.ui.screens.productList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diego.matesanz.idealista.data.Result
import com.diego.matesanz.idealista.data.stateAsResultIn
import com.diego.matesanz.idealista.domain.models.ProductItem
import com.diego.matesanz.idealista.usecases.productList.GetProductsUseCase
import com.diego.matesanz.idealista.usecases.productList.ToggleProductFavoriteUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

sealed interface ProductListAction {
    data class ToggleFavorite(val product: ProductItem) : ProductListAction
}

@KoinViewModel
class ProductListViewModel(
    getProductsUseCase: GetProductsUseCase,
    private val toggleProductFavoriteUseCase: ToggleProductFavoriteUseCase
) : ViewModel() {

    val state: StateFlow<Result<List<ProductItem>>> =
        getProductsUseCase()
            .stateAsResultIn(viewModelScope)

    fun onAction(action: ProductListAction) {
        when (action) {
            is ProductListAction.ToggleFavorite -> toggleFavorite(action.product)
        }
    }

    private fun toggleFavorite(product: ProductItem) {
        viewModelScope.launch {
            toggleProductFavoriteUseCase(product)
        }
    }
}
