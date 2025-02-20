package com.diego.matesanz.idealista.ui.screens.productList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diego.matesanz.idealista.domain.models.ProductItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val getProductsUseCase: com.diego.matesanz.idealista.usecases.GetProductsUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            _state.update { it.copy(products = getProductsUseCase(), isLoading = false) }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val products: List<ProductItem> = emptyList(),
        val errorMessage: String? = null
    )
}
