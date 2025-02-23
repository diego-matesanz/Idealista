package com.diego.matesanz.idealista.ui.screens.productDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diego.matesanz.idealista.domain.models.ProductDetail
import com.diego.matesanz.idealista.usecases.productDetail.GetProductDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ProductDetailViewModel(
    private val propertyCode: String,
    private val getProductDetailUseCase: GetProductDetailUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    init {
        getProductDetail()
    }

    private fun getProductDetail() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            _state.update {
                it.copy(
                    productDetail = getProductDetailUseCase(propertyCode),
                    isLoading = false
                )
            }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val productDetail: ProductDetail? = null,
        val error: Error? = null,
    )
}
