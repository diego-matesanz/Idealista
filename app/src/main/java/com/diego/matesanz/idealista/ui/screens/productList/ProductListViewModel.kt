package com.diego.matesanz.idealista.ui.screens.productList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diego.matesanz.idealista.domain.models.ProductItem
import com.diego.matesanz.idealista.usecases.productList.GetProductsUseCase
import com.diego.matesanz.idealista.usecases.productList.ToggleProductFavoriteUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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

    val state: StateFlow<UiState> =
        getProductsUseCase()
            .map { products -> UiState(products = products, isLoading = false) }
            .catch { UiState(errorMessage = it.message) }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                UiState(isLoading = true)
            )

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

    data class UiState(
        val isLoading: Boolean = false,
        val products: List<ProductItem> = emptyList(),
        val errorMessage: String? = null,
    )
}
